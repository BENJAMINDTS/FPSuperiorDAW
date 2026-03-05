<?php
session_start();

// Verificar autenticación y rol
if (!isset($_SESSION['usuario']) || $_SESSION['rol'] !== 'administrador') {
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
  <title>Panel de Administración</title>
  <link rel="stylesheet" href="styles.css">
</head>

<body>
  <div class="container">
    <header>
      <h1>Panel de Administración</h1>
      <p>Bienvenido al área restringida para administradores</p>
    </header>

    <main>
      <div class="message success">
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
        <h2>Funcionalidades de Administración</h2>
        <div class="features-grid">
          <div class="feature-card">
            <h3>Gestión de Usuarios</h3>
            <p>Crear, editar y eliminar usuarios del sistema</p>
          </div>
          <div class="feature-card">
            <h3>Configuración del Sistema</h3>
            <p>Modificar parámetros y configuraciones globales</p>
          </div>
          <div class="feature-card">
            <h3>Reportes y Estadísticas</h3>
            <p>Generar reportes detallados del sistema</p>
          </div>
          <div class="feature-card">
            <h3>Auditoría</h3>
            <p>Revisar logs y actividades del sistema</p>
          </div>
        </div>
      </section>

      <section class="section">
        <h2>Acciones</h2>
        <div class="actions-grid">
          <a href="user.php" class="btn btn-warning">
            Ir a Panel de Usuario
          </a>
          <a href="logout.php" class="btn btn-error">
            Cerrar Sesión
          </a>
        </div>
      </section>
    </main>

    <footer>
      <p>&copy; 2025 Sistema de Autenticación - Panel de Administración - Benjamin Santiago González</p>
    </footer>
  </div>
</body>

</html>