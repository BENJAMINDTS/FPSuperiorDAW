<?php
session_start();

// Redirigir si ya hay una sesión activa
if (isset($_SESSION['usuario'])) {
  if ($_SESSION['rol'] === 'administrador') {
    header('Location: admin.php');
  } else {
    header('Location: user.php');
  }
  exit();
}

// Procesar el formulario de login
$mensaje_tipo = '';
$mensaje_texto = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $usuario = $_POST['usuario'] ?? '';
  $password = $_POST['password'] ?? '';
  $rol = $_POST['rol'] ?? '';

  // Validar credenciales
  $credenciales_validas = false;

  if ($usuario === 'admin' && $password === 'admin123' && $rol === 'administrador') {
    $credenciales_validas = true;
  } elseif ($usuario === 'user' && $password === 'user123' && $rol === 'usuario') {
    $credenciales_validas = true;
  }

  if ($credenciales_validas) {
    // Iniciar sesión
    $_SESSION['usuario'] = $usuario;
    $_SESSION['rol'] = $rol;
    $_SESSION['login_time'] = time();

    // Redirigir según el rol
    if ($rol === 'administrador') {
      header('Location: admin.php');
    } else {
      header('Location: user.php');
    }
    exit();
  } else {
    $mensaje_tipo = 'error';
    $mensaje_texto = 'Credenciales incorrectas. Por favor, verifica tu usuario, contraseña y rol.';
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
              value="<?php echo $_POST['usuario'] ?? ''; ?>">
          </div>

          <div class="form-group">
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
          </div>

          <div class="form-group">
            <label for="rol">Rol:</label>
            <select id="rol" name="rol" required>
              <option value="">Selecciona un rol</option>
              <option value="administrador" <?php echo ($_POST['rol'] ?? '') === 'administrador' ? 'selected' : ''; ?>>Administrador</option>
              <option value="usuario" <?php echo ($_POST['rol'] ?? '') === 'usuario' ? 'selected' : ''; ?>>Usuario</option>
            </select>
          </div>

          <button type="submit" class="btn btn-primary btn-login">
            Iniciar Sesión
          </button>
        </form>
      </section>

      <section class="section">
        <h2>Credenciales de Prueba</h2>
        <div class="credenciales-grid">
          <div class="credencial-item">
            <h3>Administrador</h3>
            <p><strong>Usuario:</strong> admin</p>
            <p><strong>Contraseña:</strong> admin123</p>
            <p><strong>Rol:</strong> administrador</p>
          </div>
          <div class="credencial-item">
            <h3>Usuario</h3>
            <p><strong>Usuario:</strong> user</p>
            <p><strong>Contraseña:</strong> user123</p>
            <p><strong>Rol:</strong> usuario</p>
          </div>
        </div>
      </section>
    </main>

    <footer>
      <p>&copy; 2025 Sistema de Autenticación - Benjamin Santiago González</p>
    </footer>
  </div>
</body>

</html>