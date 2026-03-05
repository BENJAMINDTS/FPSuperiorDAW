<?php
// Iniciar o reanudar la sesión
session_start();

// Manejar el cierre de sesión
if (isset($_POST['logout'])) {
  session_destroy();
  session_start(); // Iniciar nueva sesión
  $_SESSION['visitas'] = 1;
  $_SESSION['ultimo_acceso'] = time();
  $mensaje_tipo = "success";
  $mensaje_texto = "Sesión anterior cerrada. Se ha iniciado una nueva sesión.";
}
// Manejar el reinicio del contador
elseif (isset($_POST['reset'])) {
  $_SESSION['visitas'] = 1;
  $mensaje_tipo = "warning";
  $mensaje_texto = "El contador ha sido reiniciado.";
}
// Verificar si la sesión ya está activa y si existe el contador
elseif (isset($_SESSION['visitas'])) {
  // Incrementar el contador de visitas
  $_SESSION['visitas']++;
  $mensaje_tipo = "info";
  $mensaje_texto = "Bienvenido de nuevo! Tu sesión continúa activa.";
} else {
  // Inicializar el contador en 1 (primera visita)
  $_SESSION['visitas'] = 1;
  $mensaje_tipo = "success";
  $mensaje_texto = "Bienvenido por primera vez! Se ha iniciado una nueva sesión.";
}

// Obtener el ID de sesión para mostrar
$session_id = session_id();

// Obtener información adicional de la sesión
$ultimo_acceso = isset($_SESSION['ultimo_acceso']) ?
  date('d/m/Y H:i:s', $_SESSION['ultimo_acceso']) :
  'Primera visita';

// Actualizar el tiempo del último acceso
$_SESSION['ultimo_acceso'] = time();
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Contador de Visitas por Sesión</title>
  <link rel="stylesheet" href="styles.css">
</head>

<body>
  <div class="container">
    <header>
      <h1>Contador de Visitas por Sesión</h1>
      <p>Sistema de seguimiento de visitas durante la sesión activa</p>
    </header>

    <main>
      <!-- Mensaje de estado -->
      <div class="message <?php echo $mensaje_tipo; ?>">
        <?php echo $mensaje_texto; ?>
      </div>

      <!-- Sección del contador -->
      <section class="section">
        <h2>Estadísticas de Visitas</h2>
        <div class="contador-display">
          <div class="contador-numero">
            <?php echo $_SESSION['visitas']; ?>
          </div>
          <p class="contador-texto">visitas en esta sesión</p>
        </div>
      </section>

      <!-- Sección de información de sesión -->
      <section class="section">
        <h2>Información de la Sesión</h2>
        <div class="info-grid">
          <div class="info-item">
            <strong>ID de Sesión:</strong>
            <span><?php echo $session_id; ?></span>
          </div>
          <div class="info-item">
            <strong>Último acceso:</strong>
            <span><?php echo $ultimo_acceso; ?></span>
          </div>
          <div class="info-item">
            <strong>Estado:</strong>
            <span>
              <?php
              if ($_SESSION['visitas'] == 1) {
                echo "Sesión recién iniciada";
              } else {
                echo "Sesión activa (" . $_SESSION['visitas'] . " visitas)";
              }
              ?>
            </span>
          </div>
        </div>
      </section>

      <!-- Sección de acciones -->
      <section class="section">
        <h2>Acciones</h2>
        <div class="actions-grid">
          <form method="POST" class="action-form">
            <button type="submit" name="reload" class="btn btn-primary">
              Recargar Página
            </button>
          </form>

          <form method="POST" class="action-form">
            <button type="submit" name="reset" class="btn btn-warning">
              Reiniciar Contador
            </button>
          </form>

          <form method="POST" class="action-form">
            <button type="submit" name="logout" class="btn btn-error"
              onclick="return confirm('¿Estás seguro de que deseas cerrar la sesión? El contador se reiniciará.')">
              Cerrar Sesión
            </button>
          </form>
        </div>
      </section>
    </main>

    <footer>
      <p>&copy; 2025 Contador de Visitas por Sesión - Benjamin Santiago González</p>
    </footer>
  </div>
</body>

</html>