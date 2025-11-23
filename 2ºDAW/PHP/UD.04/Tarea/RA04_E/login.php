<?php
session_start();

// Redirigir si ya hay una sesión activa
if (isset($_SESSION['usuario_autenticado']) && $_SESSION['usuario_autenticado'] === true) {
  header('Location: sesion.php');
  exit();
}

// Procesar el formulario de login
$mensaje_tipo = '';
$mensaje_texto = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $usuario = $_POST['usuario'] ?? '';
  $password = $_POST['password'] ?? '';

  // Validar credenciales
  if ($usuario === 'foc' && $password === 'Fdwes!22') {
    // Credenciales correctas - iniciar sesión
    $_SESSION['usuario_autenticado'] = true;
    $_SESSION['usuario'] = $usuario;
    $_SESSION['login_time'] = time();

    // Redirigir a la página de sesión
    header('Location: sesion.php');
    exit();
  } else {
    // Credenciales incorrectas
    $mensaje_tipo = 'error';
    $mensaje_texto = 'Credenciales incorrectas';
  }
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sistema de Autenticación - Login</title>
  <link rel="stylesheet" href="styles.css">
</head>

<body>
  <div class="container">
    <header>
      <h1>Sistema de Autenticación</h1>
      <p>Inicia sesión con tus credenciales</p>
    </header>

    <main>
      <section class="section login-section">
        <h2>Iniciar Sesión</h2>

        <?php if ($mensaje_tipo): ?>
          <div class="message <?php echo $mensaje_tipo; ?>">
            <?php echo $mensaje_texto; ?>
          </div>
        <?php endif; ?>

        <form method="POST" class="login-form">
          <div class="form-group">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" required
              value="<?php echo htmlspecialchars($_POST['usuario'] ?? ''); ?>">
          </div>

          <div class="form-group">
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
          </div>

          <button type="submit" class="btn btn-primary btn-login">
            Iniciar Sesión
          </button>
        </form>
      </section>

      <section class="section">
        <h2>Credenciales de Acceso</h2>
        <div class="credenciales-info">
          <p><strong>Usuario:</strong> foc</p>
          <p><strong>Contraseña:</strong> Fdwes!22</p>
        </div>
      </section>
    </main>

    <footer>
      <p>&copy; 2025 Sistema de Autenticación - Login - Benjamin Santiago González</p>
    </footer>
  </div>
</body>

</html>