<?php
if (session_status() === PHP_SESSION_NONE) {
  session_start();
}

// Verificar si el usuario ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
  header("Location: index.php?accion=login"); // Redirigir al login si no hay sesión
  exit();
}

// Si no se proporciona un usuario para editar, redirigir al dashboard
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
  <title>Editar Usuario</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>

  <div class="container">
    <h2>Editar Usuario</h2>

    <form action="index.php?accion=actualizar" method="POST">
      <input type="hidden" name="id" value="<?= htmlspecialchars($usuario['id']) ?>">

      <label for="nombre">Nombre Completo</label>
      <input type="text" name="nombre" id="nombre" value="<?= htmlspecialchars($usuario['nombre']) ?>" required>

      <label for="email">Correo Electrónico</label>
      <input type="email" name="email" id="email" value="<?= htmlspecialchars($usuario['email']) ?>" required>

      <button type="submit">Guardar Cambios</button>
    </form>

    <p><a href="index.php?accion=dashboard">Volver al Dashboard</a></p>
    <p><a href="index.php?accion=listar">Volver a la Lista de Usuarios</a></p>
  </div>

</body>

</html>