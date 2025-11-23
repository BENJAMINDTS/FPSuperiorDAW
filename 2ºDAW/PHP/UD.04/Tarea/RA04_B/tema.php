<?php
// Duración de la cookie (1 semana)
$cookie_duracion = time() + (7 * 24 * 60 * 60);

// Verificar si se ha enviado el formulario para cambiar el tema
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['tema'])) {
  $tema_seleccionado = $_POST['tema'];

  // Guardar la preferencia en una cookie
  setcookie('tema_preferido', $tema_seleccionado, $cookie_duracion, '/');

  // Redirigir para evitar reenvío del formulario
  header('Location: ' . $_SERVER['PHP_SELF']);
  exit();
}

// Obtener el tema actual (de la cookie o por defecto)
$tema_actual = 'claro'; // Tema por defecto
if (isset($_COOKIE['tema_preferido'])) {
  $tema_actual = $_COOKIE['tema_preferido'];
}

// Determinar si es la primera visita (sin cookie de tema)
$primera_visita = !isset($_COOKIE['tema_preferido']);

// Mensaje basado en la acción
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $mensaje_tipo = "success";
  $mensaje_texto = "Tema cambiado exitosamente. Tu preferencia se recordará por una semana.";
} elseif ($primera_visita) {
  $mensaje_tipo = "info";
  $mensaje_texto = "Bienvenido! Selecciona tu tema preferido. Usaremos cookies para recordar tu elección.";
} else {
  $mensaje_tipo = "info";
  $mensaje_texto = "Tema actual: " . ucfirst($tema_actual) . ". Puedes cambiarlo cuando quieras.";
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Preferencias de Tema - Sistema de Cookies</title>
  <link rel="stylesheet" href="styles.css" id="tema-css">
</head>

<body class="tema-<?php echo $tema_actual; ?>">
  <div class="container">
    <header>
      <h1>Preferencias de Tema</h1>
      <p>Sistema de personalización con Cookies PHP</p>
    </header>

    <main>
      <!-- Mensaje de estado -->
      <div class="message <?php echo $mensaje_tipo; ?>">
        <?php echo $mensaje_texto; ?>
      </div>

      <!-- Sección de selección de tema -->
      <section class="section">
        <h2>Selecciona tu Tema Preferido</h2>
        <form method="POST" class="temas-grid">
          <div class="tema-option <?php echo $tema_actual === 'claro' ? 'tema-seleccionado' : ''; ?>">
            <input type="radio" name="tema" value="claro" id="tema-claro"
              <?php echo $tema_actual === 'claro' ? 'checked' : ''; ?>>
            <label for="tema-claro" class="tema-label">
              <div class="tema-preview tema-claro">
                <div class="tema-header">Tema Claro</div>
                <div class="tema-content">
                  <div class="tema-elemento"></div>
                  <div class="tema-elemento"></div>
                  <div class="tema-elemento"></div>
                </div>
              </div>
            </label>
          </div>

          <div class="tema-option <?php echo $tema_actual === 'oscuro' ? 'tema-seleccionado' : ''; ?>">
            <input type="radio" name="tema" value="oscuro" id="tema-oscuro"
              <?php echo $tema_actual === 'oscuro' ? 'checked' : ''; ?>>
            <label for="tema-oscuro" class="tema-label">
              <div class="tema-preview tema-oscuro">
                <div class="tema-header">Tema Oscuro</div>
                <div class="tema-content">
                  <div class="tema-elemento"></div>
                  <div class="tema-elemento"></div>
                  <div class="tema-elemento"></div>
                </div>
              </div>
            </label>
          </div>

          <div class="form-actions">
            <button type="submit" class="btn btn-primary">
              Guardar Preferencia
            </button>

            <?php if (!$primera_visita): ?>
              <button type="button" class="btn btn-warning" onclick="eliminarCookie()">
                Eliminar Preferencia
              </button>
            <?php endif; ?>
          </div>
        </form>
      </section>

      <!-- Sección de información de cookies -->
      <section class="section">
        <h2>Información de la Cookie</h2>
        <div class="info-grid">
          <div class="info-item">
            <strong>Nombre de la Cookie:</strong>
            <span>tema_preferido</span>
          </div>
          <div class="info-item">
            <strong>Valor actual:</strong>
            <span><?php echo $tema_actual; ?></span>
          </div>
          <div class="info-item">
            <strong>Duración:</strong>
            <span>7 días</span>
          </div>
          <div class="info-item">
            <strong>Estado:</strong>
            <span>
              <?php
              if ($primera_visita) {
                echo "Sin preferencia guardada";
              } else {
                echo "Preferencia activa";
              }
              ?>
            </span>
          </div>
        </div>
      </section>
    </main>

    <footer>
      <p>&copy; 2025 Sistema de Preferencias con Cookies - Benjamin Santiago González</p>
    </footer>
  </div>

  <script>
    function eliminarCookie() {
      if (confirm('¿Estás seguro de que deseas eliminar tu preferencia guardada?')) {
        document.cookie = "tema_preferido=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        alert('Preferencia eliminada. La página se recargará.');
        location.reload();
      }
    }

    // Cambio inmediato al seleccionar un tema
    document.querySelectorAll('input[name="tema"]').forEach(radio => {
      radio.addEventListener('change', function() {
        document.body.className = 'tema-' + this.value;
      });
    });
  </script>
</body>

</html>