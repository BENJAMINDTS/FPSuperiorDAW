<?php
session_start();

// Obtener la ruta base automáticamente
$protocol = isset($_SERVER['HTTPS']) && $_SERVER['HTTPS'] === 'on' ? "https" : "http";
$host = $_SERVER['HTTP_HOST'];
$script_path = dirname($_SERVER['SCRIPT_NAME']);
$base_url = $protocol . "://" . $host . $script_path;
$base_url = rtrim($base_url, '/') . '/';

// Si ya está logueado, redirigir al dashboard
if (isset($_SESSION['usuario'])) {
    header('Location: ' . $base_url . 'dashboard.php');
    exit;
}

// Procesar login
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['login'])) {
    $usuario = trim($_POST['usuario']);
    $password = trim($_POST['password']);
    
    // Credenciales de prueba
    if ($usuario === 'admin' && $password === '1234') {
        $_SESSION['usuario'] = $usuario;
        $_SESSION['ultimo_acceso'] = time();
        header('Location: ' . $base_url . 'dashboard.php');
        exit;
    } else {
        $error = "Usuario o contraseña incorrectos";
    }
}

// Manejar parámetros GET
$mensaje_expirada = isset($_GET['expirada']);
$mensaje_logout = isset($_GET['logout']);
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .login-container {
            max-width: 400px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <h1>Sistema con Temporizador de Sesión</h1>
        </div>
    </div>

    <main class="main-content">
        <div class="container">
            <div class="login-container">
                <h1>Iniciar Sesión</h1>
                
                <?php if (isset($error)): ?>
                    <div class="error"><?php echo $error; ?></div>
                <?php endif; ?>

                <?php if ($mensaje_expirada): ?>
                    <div class="warning">
                        <h2>Sesión Expirada</h2>
                        <p>Tu sesión ha expirado por inactividad. Por favor, inicia sesión nuevamente.</p>
                    </div>
                <?php endif; ?>

                <?php if ($mensaje_logout): ?>
                    <div class="success">
                        <h2>Sesión Cerrada</h2>
                        <p>Has cerrado sesión correctamente.</p>
                    </div>
                <?php endif; ?>

                <div class="calculator">
                    <form method="POST">
                        <div class="form-group">
                            <label for="usuario">Usuario:</label>
                            <input type="text" id="usuario" name="usuario" value="admin" required class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label for="password">Contraseña:</label>
                            <input type="password" id="password" name="password" value="1234" required class="form-control">
                        </div>
                        
                        <button type="submit" name="login" class="btn">Iniciar Sesión</button>
                    </form>
                </div>

                <div class="info">
                    <h3>Credenciales de prueba:</h3>
                    <p><strong>Usuario:</strong> admin</p>
                    <p><strong>Contraseña:</strong> 1234</p>
                    <p><strong>Tiempo de inactividad:</strong> 15 seg (para pruebas)</p>
                </div>

                <div style="text-align: center; margin-top: 20px;">
                    <a href="../Ejercicio-1_Carrito/productos.php" class="btn btn-secondary">Ir a la Tienda</a>
                    <a href="../Tarea-2-Tema_Oscuro/preferencias.php" class="btn btn-secondary">Preferencias</a>
                </div>
            </div>
        </div>
    </main>

    <footer class="footer">
        <div class="footer-content">
            <p>Sistema de Sesiones con Temporizador PHP</p>
        </div>
    </footer>
</body>
</html>