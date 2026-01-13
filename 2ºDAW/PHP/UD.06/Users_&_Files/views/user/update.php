<?php
session_start();

// Verificar si el usuario ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
  header("Location: index.php?accion=login"); // Redirigir al login si no hay sesión
  exit();
}

// Si no se proporciona un usuario para actualizar, redirigir al dashboard
if (!isset($usuario)) {
  header("Location: index.php?accion=dashboard");
  exit();
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Actualizar Perfil</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>

  <div class="container">
    <h2>Actualizar Perfil</h2>

    <?php if (isset($mensaje)): ?>
      <div class="alerta <?= strpos($mensaje, 'Error') !== false ? 'alerta-error' : 'alerta-exito' ?>">
        <?= $mensaje ?>
      </div>
    <?php endif; ?>

    <form action="index.php?accion=actualizarPerfil" method="POST">
      <input type="hidden" name="id" value="<?= htmlspecialchars($usuario['id']) ?>">

      <label for="nombre">Nombre Completo</label>
      <input type="text" name="nombre" id="nombre" value="<?= htmlspecialchars($usuario['nombre']) ?>" required>

      <label for="email">Correo Electrónico</label>
      <input type="email" name="email" id="email" value="<?= htmlspecialchars($usuario['email']) ?>" required>

      <button type="submit">Guardar Cambios</button>
    </form>

    <p><a href="index.php?accion=dashboard">Volver al Dashboard</a></p>
  </div>

</body>

</html>
