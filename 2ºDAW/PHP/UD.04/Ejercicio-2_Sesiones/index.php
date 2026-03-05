<?php
session_start();

// Inicializar contador de visitas si no existe
if (!isset($_SESSION['visitas'])) {
    $_SESSION['visitas'] = 1;
} else {
    $_SESSION['visitas']++;
}

// Inicializar tiempo de sesión si no existe
if (!isset($_SESSION['inicio_sesion'])) {
    $_SESSION['inicio_sesion'] = time();
}

// Calcular tiempo transcurrido
$tiempo_transcurrido = time() - $_SESSION['inicio_sesion'];
$fecha_inicio = date('d/m/Y H:i:s', $_SESSION['inicio_sesion']);

// Formatear tiempo transcurrido
$horas = floor($tiempo_transcurrido / 3600);
$minutos = floor(($tiempo_transcurrido % 3600) / 60);
$segundos = $tiempo_transcurrido % 60;
$tiempo_formateado = sprintf("%02d:%02d:%02d", $horas, $minutos, $segundos);
?>

<?php include 'includes/header.php'; ?>

<main>
    <!-- Ejercicio 1: Contador de visitas -->
    <section class="section">
        <h2>Contador de Visitas</h2>
        <div class="message <?php echo $_SESSION['visitas'] == 1 ? 'warning' : 'info'; ?>">
            <?php if ($_SESSION['visitas'] == 1): ?>
                <strong>¡Bienvenido por primera vez!</strong> Esta es tu primera visita a nuestra página.
            <?php else: ?>
                <strong>Visita número:</strong> <?php echo $_SESSION['visitas']; ?>
            <?php endif; ?>
        </div>
        <div class="btn-group">
            <a href="reiniciar_visitas.php" class="btn">Reiniciar Contador</a>
        </div>
    </section>

    <!-- Ejercicio 2: Nombre de usuario -->
    <section class="section">
        <h2>Gestión de Usuario</h2>
        <?php if (isset($_SESSION['nombre'])): ?>
            <div class="message success">
                <strong>¡Hola <?php echo htmlspecialchars($_SESSION['nombre']); ?>!</strong> Bienvenido de nuevo.
            </div>
            <div class="btn-group">
                <a href="cerrar_sesion.php" class="btn btn-danger">Cerrar Sesión</a>
            </div>
        <?php else: ?>
            <form method="POST" action="procesar.php" class="form-group">
                <label for="nombre">Ingresa tu nombre:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Escribe tu nombre aquí..." required>
                <button type="submit" class="btn">Guardar Nombre</button>
            </form>
        <?php endif; ?>
    </section>

    <!-- Ejercicio 3: Sesión activa -->
    <section class="section">
        <h2>Sesión Activa</h2>
        <div class="message info">
            <p><strong>Inicio de sesión:</strong> <?php echo $fecha_inicio; ?></p>
            <p><strong>Tiempo activo:</strong> <?php echo $tiempo_formateado; ?> (HH:MM:SS)</p>
            <p><strong>ID de sesión:</strong> <?php echo session_id(); ?></p>
        </div>
        <p><small>Recarga la página para actualizar el tiempo de sesión activa</small></p>
    </section>

    <!-- Información de sesión -->
    <section class="section">
        <h2>Información de la Sesión</h2>
        <div class="message">
            <pre><?php print_r($_SESSION); ?></pre>
        </div>
    </section>
</main>

</body>
</html>