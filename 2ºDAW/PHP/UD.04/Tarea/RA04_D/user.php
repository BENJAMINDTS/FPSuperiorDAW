<?php
session_start();

// Verificar autenticación
if (!isset($_SESSION['usuario'])) {
    header('Location: login.php');
    exit();
}

$usuario = $_SESSION['usuario'];
$rol = $_SESSION['rol'];
$login_time = $_SESSION['login_time'];
$tiempo_sesion = time() - $login_time;
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Usuario</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Panel de Usuario</h1>
            <p>Bienvenido al área de usuarios</p>
        </header>

        <main>
            <div class="message info">
                Has iniciado sesión como <strong><?php echo $usuario; ?></strong> con rol <strong><?php echo $rol; ?></strong>
            </div>

            <section class="section">
                <h2>Información de la Sesión</h2>
                <div class="info-grid">
                    <div class="info-item">
                        <strong>Usuario:</strong>
                        <span><?php echo $usuario; ?></span>
                    </div>
                    <div class="info-item">
                        <strong>Rol:</strong>
                        <span><?php echo $rol; ?></span>
                    </div>
                    <div class="info-item">
                        <strong>Tiempo de sesión:</strong>
                        <span><?php echo gmdate("H:i:s", $tiempo_sesion); ?></span>
                    </div>
                    <div class="info-item">
                        <strong>Hora de login:</strong>
                        <span><?php echo date('d/m/Y H:i:s', $login_time); ?></span>
                    </div>
                </div>
            </section>

            <section class="section">
                <h2>Funcionalidades de Usuario</h2>
                <div class="features-grid">
                    <div class="feature-card">
                        <h3>Mi Perfil</h3>
                        <p>Gestionar tu información personal</p>
                    </div>
                    <div class="feature-card">
                        <h3>Mis Datos</h3>
                        <p>Consultar y modificar tus datos</p>
                    </div>
                    <div class="feature-card">
                        <h3>Preferencias</h3>
                        <p>Configurar tus preferencias personales</p>
                    </div>
                    <div class="feature-card">
                        <h3>Actividad</h3>
                        <p>Revisar tu historial de actividad</p>
                    </div>
                </div>
            </section>

            <?php if ($_SESSION['rol'] === 'administrador'): ?>
            <section class="section">
                <div class="message warning">
                    <strong>Nota:</strong> Tienes permisos de administrador. Puedes acceder al panel de administración.
                </div>
            </section>
            <?php endif; ?>

            <section class="section">
                <h2>Acciones</h2>
                <div class="actions-grid">
                    <?php if ($_SESSION['rol'] === 'administrador'): ?>
                    <a href="admin.php" class="btn btn-primary">
                        Ir a Panel de Administración
                    </a>
                    <?php endif; ?>
                    <a href="logout.php" class="btn btn-error">
                        Cerrar Sesión
                    </a>
                </div>
            </section>
        </main>

        <footer>
            <p>&copy; 2025 Sistema de Autenticación - Panel de Usuario - Benjamin Santiago González</p>
        </footer>
    </div>
</body>
</html>