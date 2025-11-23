<?php
session_start();

// Si ya hay sesión establecida, redirigir a sesion.php
if (isset($_SESSION['datos_guardados']) || !empty($_SESSION)) {
  header('Location: sesion.php');
  exit();
}

// Credenciales simples para este ejercicio
$usuario_correcto = "foc";
$password_correcto = "Fdwes!22";

// Procesar login
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $usuario = $_POST['usuario'] ?? '';
  $password = $_POST['password'] ?? '';

  if ($usuario === $usuario_correcto && $password === $password_correcto) {
    // Crear sesión básica
    $_SESSION['usuario'] = $usuario;
    $_SESSION['login_time'] = time();

    header('Location: sesion.php');
    exit();
  } else {
    $error = "Credenciales incorrectas";
  }
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login - Sistema de Gestión</title>
  <link rel="stylesheet" href="styles.css">
</head>

<body>
  <div class="container">
    <header>
      <h1>Sistema de Gestión</h1>
      <p>Iniciar Sesión</p>
    </header>

    <main>
      <section class="section login-section">
        <h2>Acceso al Sistema</h2>

        <?php if (isset($error)): ?>
          <div class="message error">
            <?php echo $error; ?>
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
        <h2>Credenciales de Prueba</h2>
        <div class="credenciales-info">
          <p><strong>Usuario:</strong> foc</p>
          <p><strong>Contraseña:</strong> Fdwes!22</p>
        </div>
      </section>
    </main>

    <footer>
      <p>&copy; 2025 Sistema de Gestión - Login - Benjamin Santiago González</p>
    </footer>
  </div>
</body>

</html>