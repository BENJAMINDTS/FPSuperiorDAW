<?php
session_start();

// Verificar si la sesión está establecida - redirigir a login si no hay datos
if (!isset($_SESSION['datos_guardados']) && empty($_SESSION)) {
  header('Location: login.php');
  exit();
}

// Manejar la cookie del horario
$horario_actual = '';
if (isset($_COOKIE['horario'])) {
  $horario_actual = $_COOKIE['horario'];
}

// Procesar el formulario
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  // Botón Grabar
  if (isset($_POST['grabar'])) {
    $telefono = $_POST['telefono'] ?? '';
    $email = $_POST['email'] ?? '';

    // Validar campos requeridos
    if (!empty($telefono) && !empty($email)) {
      $_SESSION['telefono'] = $telefono;
      $_SESSION['email'] = $email;
      $_SESSION['datos_guardados'] = true;
    }
  }

  // Botón Borrar
  if (isset($_POST['borrar'])) {
    // Eliminar datos de sesión
    session_unset();
    session_destroy();

    // Eliminar cookie de horario
    setcookie('horario', '', time() - 3600, '/');

    // Redirigir para evitar reenvío del formulario
    header('Location: sesion.php');
    exit();
  }

  // Botón Grabar Horario
  if (isset($_POST['grabar_horario'])) {
    $horario = $_POST['horario'] ?? '';
    if (!empty($horario)) {
      // Guardar en cookie por 30 días
      setcookie('horario', $horario, time() + (30 * 24 * 60 * 60), '/');
      $horario_actual = $horario;
    }
  }
}

// Cargar datos de sesión si existen
$telefono_actual = $_SESSION['telefono'] ?? '';
$email_actual = $_SESSION['email'] ?? '';
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestión de Datos - Sesión y Cookies</title>
  <link rel="stylesheet" href="styles.css">
</head>

<body>
  <div class="container">
    <header>
      <h1>Gestión de Datos Personales</h1>
      <p>Sistema de sesiones y cookies</p>
    </header>

    <main>
      <!-- Mensaje de estado -->
      <?php if (isset($_SESSION['datos_guardados'])): ?>
        <div class="message success">
          Datos guardados correctamente en la sesión
        </div>
      <?php endif; ?>

      <section class="section">
        <h2>Datos Personales</h2>
        <form method="POST" class="data-form">
          <div class="form-group">
            <label for="telefono">Teléfono:</label>
            <input type="text" id="telefono" name="telefono" required
              value="<?php echo htmlspecialchars($telefono_actual); ?>">
          </div>

          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required
              value="<?php echo htmlspecialchars($email_actual); ?>">
          </div>

          <div class="form-actions">
            <button type="submit" name="grabar" class="btn btn-primary">
              Grabar
            </button>
            <button type="submit" name="borrar" class="btn btn-error"
              onclick="return confirm('¿Estás seguro de que deseas borrar todos los datos?')">
              Borrar
            </button>
          </div>
        </form>
      </section>

      <section class="section">
        <h2>Preferencia de Horario</h2>
        <form method="POST" class="horario-form">
          <div class="form-group">
            <label for="horario">Horario:</label>
            <select id="horario" name="horario" required>
              <option value="">Selecciona un horario</option>
              <option value="Mañana" <?php echo $horario_actual === 'Mañana' ? 'selected' : ''; ?>>Mañana</option>
              <option value="Tarde" <?php echo $horario_actual === 'Tarde' ? 'selected' : ''; ?>>Tarde</option>
              <option value="Noche" <?php echo $horario_actual === 'Noche' ? 'selected' : ''; ?>>Noche</option>
            </select>
          </div>

          <div class="form-actions">
            <button type="submit" name="grabar_horario" class="btn btn-primary">
              Grabar Horario
            </button>
          </div>
        </form>
      </section>

      <section class="section">
        <h2>Estado Actual</h2>
        <div class="info-grid">
          <div class="info-item">
            <strong>Sesión activa:</strong>
            <span><?php echo isset($_SESSION['datos_guardados']) ? 'Sí' : 'No'; ?></span>
          </div>
          <div class="info-item">
            <strong>Teléfono guardado:</strong>
            <span><?php echo $telefono_actual ?: 'No guardado'; ?></span>
          </div>
          <div class="info-item">
            <strong>Email guardado:</strong>
            <span><?php echo $email_actual ?: 'No guardado'; ?></span>
          </div>
          <div class="info-item">
            <strong>Horario preferido:</strong>
            <span><?php echo $horario_actual ?: 'No establecido'; ?></span>
          </div>
        </div>
      </section>

      <section class="section">
        <h2>Información del Sistema</h2>
        <div class="info-content">
          <p>Este sistema utiliza <strong>sesiones PHP</strong> y <strong>cookies</strong> para almacenar información.</p>
          <ul>
            <li><strong>Sesiones:</strong> Almacenan teléfono y email en el servidor</li>
            <li><strong>Cookies:</strong> Almacenan la preferencia de horario en el navegador</li>
            <li><strong>Seguridad:</strong> Los datos sensibles se guardan en sesiones</li>
            <li><strong>Persistencia:</strong> Las cookies mantienen el horario entre sesiones</li>
          </ul>
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
      <p>&copy; 2025 Gestión de Datos - Sesion - Benjamin Santiago González</p>
    </footer>
  </div>
</body>

</html>