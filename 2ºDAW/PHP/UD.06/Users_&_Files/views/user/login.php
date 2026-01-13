<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Iniciar Sesión</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>

  <div class="container">
    <h2>Iniciar Sesión</h2>

    <?php if (isset($error)): ?>
      <div class="alerta alerta-error">
        <?= $error ?>
      </div>
    <?php endif; ?>

    <form action="index.php?accion=login" method="POST">
      <label for="email">Correo Electrónico</label>
      <input type="email" name="email" id="email" placeholder="ejemplo@correo.com" required>

      <label for="password">Contraseña</label>
      <input type="password" name="password" id="password" placeholder="••••••••" required>

      <button type="submit">Iniciar Sesión</button>
    </form>
    <p>¿No tienes una cuenta? <a href="index.php?accion=registro">Regístrate aquí</a></p>
  </div>

</body>

</html>
