<?php
session_start();

// Verificar autenticación
if (!isset($_SESSION['usuario_autenticado']) || $_SESSION['usuario_autenticado'] !== true) {
  header('Location: login.php');
  exit();
}

$usuario = $_SESSION['usuario'];
$login_time = $_SESSION['login_time'];
$tiempo_sesion = time() - $login_time;
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Página de Sesión</title>
  <link rel="stylesheet" href="styles.css">
</head>

<body>
  <div class="container">
    <header>
      <h1>Página de Sesión</h1>
      <p>Bienvenido al área autenticada del sistema</p>
    </header>

    <main>
      <div class="message success">
        Has iniciado sesión correctamente como <strong><?php echo $usuario; ?></strong>
      </div>

      <section class="section">
        <h2>Información de la Sesión</h2>
        <div class="info-grid">
          <div class="info-item">
            <strong>Usuario autenticado:</strong>
            <span><?php echo $usuario; ?></span>
          </div>
          <div class="info-item">
            <strong>Tiempo de sesión activa:</strong>
            <span><?php echo gmdate("H:i:s", $tiempo_sesion); ?></span>
          </div>
          <div class="info-item">
            <strong>Hora de inicio de sesión:</strong>
            <span><?php echo date('d/m/Y H:i:s', $login_time); ?></span>
          </div>
          <div class="info-item">
            <strong>Estado:</strong>
            <span>Sesión activa y válida</span>
          </div>
        </div>
      </section>

      <section class="section">
        <h2>Contenido Protegido</h2>
        <div class="content-grid">
          <div class="content-card">
            <h3>Bienvenido al Sistema</h3>
            <p>Esta página demuestra el acceso a contenido protegido mediante autenticación. Solo los usuarios con credenciales válidas pueden acceder a esta sección.</p>
          </div>
          <div class="content-card">
            <h3>Características de Seguridad</h3>
            <p>El sistema utiliza variables de sesión para verificar la autenticación del usuario en cada página protegida.</p>
          </div>
        </div>
      </section>

      <section class="section">
        <h2>Acciones</h2>
        <div class="actions-grid">
          <a href="logout.php" class="btn btn-error">
            Cerrar Sesión
          </a>
        </div>
      </section>
    </main>

    <footer>
      <p>&copy; 2025 Sistema de Autenticación - Página de Sesión - Benjamin Santiago González</p>
    </footer>
  </div>
</body>

</html>