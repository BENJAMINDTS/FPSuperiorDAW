<?php
session_start();

// Obtener la ruta base automáticamente
$protocol = isset($_SERVER['HTTPS']) && $_SERVER['HTTPS'] === 'on' ? "https" : "http";
$host = $_SERVER['HTTP_HOST'];
$script_path = dirname($_SERVER['SCRIPT_NAME']);
$base_url = $protocol . "://" . $host . $script_path;
$base_url = rtrim($base_url, '/') . '/';

// Configuración
$tiempo_inactividad = 15; // 15 seg para pruebas

// Verificar si el usuario está logueado
if (!isset($_SESSION['usuario'])) {
    header('Location: ' . $base_url . 'login.php');
    exit;
}

// Verificar inactividad
if (isset($_SESSION['ultimo_acceso'])) {
    $tiempo_transcurrido = time() - $_SESSION['ultimo_acceso'];
    if ($tiempo_transcurrido > $tiempo_inactividad) {
        session_destroy();
        header('Location: ' . $base_url . 'login.php?expirada=1');
        exit;
    }
}

// Actualizar último acceso (también con ping AJAX)
if (!isset($_GET['ping'])) {
    $_SESSION['ultimo_acceso'] = time();
}

// Procesar logout
if (isset($_GET['logout'])) {
    session_destroy();
    header('Location: ' . $base_url . 'login.php?logout=1');
    exit;
}

// Si es una petición ping, solo responder y salir
if (isset($_GET['ping'])) {
    $_SESSION['ultimo_acceso'] = time();
    echo "OK";
    exit;
}

$tiempo_restante = $tiempo_inactividad - (time() - $_SESSION['ultimo_acceso']);
$minutos_restantes = floor($tiempo_restante / 60);
$segundos_restantes = $tiempo_restante % 60;
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <div class="header-content">
            <h1>Dashboard del Sistema</h1>
            <div style="color: white;">
                Usuario: <?php echo htmlspecialchars($_SESSION['usuario']); ?>
            </div>
        </div>
    </div>

    <main class="main-content">
        <div class="container">
            <h1>Bienvenido, <?php echo htmlspecialchars($_SESSION['usuario']); ?></h1>
            
            <div class="success">
                <h2>Sesión Activa</h2>
                <p>Has iniciado sesión correctamente en el sistema.</p>
            </div>

            <div class="calculator">
                <h2>Información de la Sesión</h2>
                
                <div class="stat-card">
                    <h3>Tiempo de Inactividad Restante</h3>
                    <p style="font-size: 2rem; color: #2575fc; font-weight: bold;">
                        <span id="tiempo-restante"><?php echo $minutos_restantes; ?>m <?php echo $segundos_restantes; ?>s</span>
                    </p>
                    <p>La sesión se cerrará automáticamente después de 15 seg de inactividad.</p>
                </div>

                <div class="info">
                    <h3>Actividad de la Sesión</h3>
                    <p><strong>Usuario:</strong> <?php echo htmlspecialchars($_SESSION['usuario']); ?></p>
                    <p><strong>Último acceso:</strong> <?php echo date('H:i:s', $_SESSION['ultimo_acceso']); ?></p>
                    <p><strong>Hora actual:</strong> <?php echo date('H:i:s'); ?></p>
                </div>
            </div>

            <div style="margin-top: 30px; display: flex; gap: 15px; justify-content: center; flex-wrap: wrap;">
                <a href="?logout=1" class="btn btn-danger" 
                   onclick="return confirm('¿Estás seguro de que deseas cerrar sesión?')">
                    Cerrar Sesión
                </a>
                <a href="../Ejercicio-1_Carrito_compras/productos.php" class="btn">Ir a la Tienda</a>
                <a href="../Ejercicio-2_Preferencias_usuario/preferencias.php" class="btn btn-secondary">Configurar Preferencias</a>
                <button onclick="location.reload()" class="btn">Actualizar Página</button>
            </div>

            <div class="warning" style="margin-top: 20px;">
                <h3>Para probar la expiración de sesión:</h3>
                <p>Espera 15 seg sin interactuar con la página y luego recarga.</p>
                <p><small>Mueve el mouse o presiona teclas para resetear el temporizador</small></p>
            </div>
        </div>
    </main>

    <footer class="footer">
        <div class="footer-content">
            <p>Sistema de Sesiones con Temporizador PHP - Usuario: <?php echo htmlspecialchars($_SESSION['usuario']); ?></p>
        </div>
    </footer>

    <script>
        let tiempoRestante = <?php echo $tiempo_restante; ?>;
        
        function actualizarTemporizador() {
            if (tiempoRestante > 0) {
                tiempoRestante--;
                const minutos = Math.floor(tiempoRestante / 60);
                const segundos = tiempoRestante % 60;
                document.getElementById('tiempo-restante').textContent = 
                    minutos + 'm ' + segundos + 's';
            } else {
                document.getElementById('tiempo-restante').textContent = '0m 0s';
                // Redirigir automáticamente cuando expire
                setTimeout(() => {
                    window.location.href = 'login.php?expirada=1';
                }, 1000);
            }
        }
        
        // Actualizar cada segundo
        setInterval(actualizarTemporizador, 1000);
        
        // Resetear el temporizador en cualquier interacción
        function resetearTemporizador() {
            fetch('?ping=1')
                .then(response => {
                    if (response.ok) {
                        tiempoRestante = <?php echo $tiempo_inactividad; ?>;
                        console.log('Temporizador reseteado');
                    }
                })
                .catch(error => console.error('Error:', error));
        }
        
        // Eventos de interacción
        document.addEventListener('click', resetearTemporizador);
        document.addEventListener('keypress', resetearTemporizador);
        document.addEventListener('mousemove', resetearTemporizador);
        document.addEventListener('scroll', resetearTemporizador);
    </script>
</body>
</html>