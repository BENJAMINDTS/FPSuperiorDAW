<?php
// Procesar cambio de idioma
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['idioma'])) {
  $idioma = $_POST['idioma'];
  $expiry = time() + (365 * 24 * 60 * 60); // 1 año

  setcookie('lang', $idioma, $expiry, "/");
  header("Location: index.php");
  exit();
}

// Procesar borrado de idioma
if (isset($_GET['reset'])) {
  $past = time() - 3600;
  setcookie('lang', '', $past, "/");
  header("Location: index.php");
  exit();
}

// Determinar idioma actual
$idioma_actual = 'es'; // Por defecto español
if (isset($_COOKIE['lang'])) {
  $idioma_actual = $_COOKIE['lang'];
}

// Textos en diferentes idiomas
$textos = [
  'es' => [
    'titulo' => 'Sistema de Idioma',
    'bienvenida' => 'Bienvenido a nuestro sitio',
    'descripcion' => 'Este es un sistema de selección de idioma que utiliza cookies para recordar tu preferencia.',
    'instrucciones' => 'Selecciona tu idioma preferido:',
    'boton' => 'Cambiar Idioma',
    'reset' => 'Restablecer idioma',
    'cookie_info' => 'Idioma almacenado en cookie: ESPAÑOL',
    'current_lang' => 'Idioma actual: Español'
  ],
  'en' => [
    'titulo' => 'Language System',
    'bienvenida' => 'Welcome to our site',
    'descripcion' => 'This is a language selection system that uses cookies to remember your preference.',
    'instrucciones' => 'Select your preferred language:',
    'boton' => 'Change Language',
    'reset' => 'Reset language',
    'cookie_info' => 'Language stored in cookie: ENGLISH',
    'current_lang' => 'Current language: English'
  ]
];
?>
<!DOCTYPE html>
<html lang="<?php echo $idioma_actual; ?>">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><?php echo $textos[$idioma_actual]['titulo']; ?> - Tarea 3</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>

<body>
  <header class="header">
    <div class="header-content">
      <h1>Tarea 3 - <?php echo $textos[$idioma_actual]['titulo']; ?></h1>
      <nav>
        <ul class="nav-menu">
          <li><a href="../index.php">Inicio</a></li>
          <li><a href="index.php?reset=1" class="btn-danger"><?php echo $textos[$idioma_actual]['reset']; ?></a></li>
        </ul>
      </nav>
    </div>
  </header>

  <div class="main-content">
    <div class="container">
      <h1><?php echo $textos[$idioma_actual]['bienvenida']; ?></h1>

      <div class="greeting">
        <h2><?php echo $textos[$idioma_actual]['current_lang']; ?></h2>
        <p><?php echo $textos[$idioma_actual]['descripcion']; ?></p>
      </div>

      <?php if (isset($_COOKIE['lang'])): ?>
        <div class="success">
          <p><strong><?php echo $textos[$idioma_actual]['cookie_info']; ?></strong></p>
          <p>La cookie expirará en 1 año.</p>
        </div>
      <?php else: ?>
        <div class="info">
          <p>No hay preferencia de idioma guardada. Se está usando el idioma por defecto (Español).</p>
        </div>
      <?php endif; ?>

      <div class="calculator">
        <h3><?php echo $textos[$idioma_actual]['instrucciones']; ?></h3>
        <form method="POST" action="">
          <div class="form-group">
            <label for="idioma">Seleccionar idioma:</label>
            <select id="idioma" name="idioma" required>
              <option value="es" <?php echo $idioma_actual === 'es' ? 'selected' : ''; ?>>Español</option>
              <option value="en" <?php echo $idioma_actual === 'en' ? 'selected' : ''; ?>>English</option>
            </select>
          </div>

          <button type="submit" class="btn"><?php echo $textos[$idioma_actual]['boton']; ?></button>
        </form>
      </div>

      <div class="task-links">
        <a href="index.php?reset=1" class="btn btn-danger"><?php echo $textos[$idioma_actual]['reset']; ?></a>
        <a href="../index.php" class="btn btn-secondary">Volver al Inicio</a>
      </div>
    </div>
  </div>

  <footer class="footer">
    <div class="footer-content">
      <p>Tarea 3 - Preferencia de Idioma</p>
    </div>
  </footer>
</body>

</html>