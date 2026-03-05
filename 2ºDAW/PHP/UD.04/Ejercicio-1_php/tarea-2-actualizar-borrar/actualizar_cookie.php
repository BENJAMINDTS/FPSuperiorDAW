<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Actualizar Cookie - Tarea 2</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>

<body>
  <header class="header">
    <div class="header-content">
      <h1>Tarea 2 - Actualizar Cookie</h1>
      <nav>
        <ul class="nav-menu">
          <li><a href="../index.php">Inicio</a></li>
          <li><a href="borrar_cookie.php">Borrar Cookie</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <div class="main-content">
    <div class="container">
      <h1>Actualizar Valor de Cookie</h1>

      <?php
      // Procesar el formulario si se envió
      if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['nuevo_valor'])) {
        $nuevo_valor = $_POST['nuevo_valor'];

        // Validar que no contenga caracteres de control
        if (preg_match('/[\x00-\x1F\x7F]/', $nuevo_valor)) {
          echo '<div class="error">';
          echo '<h2>Error de validación</h2>';
          echo '<p>El valor no puede contener caracteres de control.</p>';
          echo '</div>';
        } else {
          // Sanitizar el valor
          $nuevo_valor = htmlspecialchars($nuevo_valor, ENT_QUOTES, 'UTF-8');
          $expiry = time() + (7 * 24 * 60 * 60);

          // Actualizar la cookie
          setcookie('usuario', $nuevo_valor, $expiry, "/");

          echo '<div class="success">';
          echo '<h2>Cookie Actualizada</h2>';
          echo "<p>El valor de la cookie se ha actualizado a: <strong>$nuevo_valor</strong></p>";
          echo '</div>';
        }
      }
      ?>

      <div class="calculator">
        <form method="POST" action="">
          <div class="form-group">
            <label for="nuevo_valor">Nuevo valor para la cookie "usuario":</label>
            <input type="text" id="nuevo_valor" name="nuevo_valor"
              value="<?php echo isset($_COOKIE['usuario']) ? htmlspecialchars($_COOKIE['usuario']) : 'alumno123'; ?>"
              required maxlength="50">
          </div>

          <button type="submit" class="btn">Actualizar Cookie</button>
        </form>
      </div>

      <?php if (isset($_COOKIE['usuario'])): ?>
        <div class="info">
          <p><strong>Valor actual:</strong> <?php echo htmlspecialchars($_COOKIE['usuario']); ?></p>
        </div>
      <?php endif; ?>

      <div class="task-links">
        <a href="borrar_cookie.php" class="btn btn-danger">Borrar Cookie</a>
        <a href="../index.php" class="btn btn-secondary">Volver al Inicio</a>
      </div>
    </div>
  </div>

  <footer class="footer">
    <div class="footer-content">
      <p>Tarea 2 - Actualizar y Eliminar Cookies</p>
    </div>
  </footer>
</body>

</html>