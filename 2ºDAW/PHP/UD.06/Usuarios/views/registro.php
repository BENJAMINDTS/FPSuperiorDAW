<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registro de Usuarios</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>

  <div class="container">
    <h2>Crear Cuenta</h2>

    <?php if (!empty($mensaje)): ?>
      <div class="alerta <?= strpos($mensaje, 'Error') !== false ? 'alerta-error' : 'alerta-exito' ?>">
        <?= $mensaje ?>
      </div>
    <?php endif; ?>

    <form action="index.php" method="POST">
      <label for="nombre">Nombre Completo</label>
      <input type="text" name="nombre" id="nombre" placeholder="Ej. Juan Pérez" required>

      <label for="email">Correo Electrónico</label>
      <input type="email" name="email" id="email" placeholder="ejemplo@correo.com" required>

      <label for="password">Contraseña</label>
      <input type="password" name="password" id="password" placeholder="••••••••" required>

      <button type="submit">Registrarse</button>

      <div style="margin-top: 15px; text-align: center;">
        <a href="index.php?accion=listar" class="btn-link">Ver lista de usuarios</a>
      </div>
    </form>
  </div>

</body>

</html>