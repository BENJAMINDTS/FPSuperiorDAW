<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>Editar Usuario</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>
  <div class="container">
    <h2>Editar Usuario</h2>

    <form action="index.php?accion=actualizar" method="POST">
      <input type="hidden" name="email_original" value="<?= htmlspecialchars($usuario['email']) ?>">

      <label>Nombre:</label>
      <input type="text" name="nombre" value="<?= htmlspecialchars($usuario['nombre']) ?>" required>

      <label>Email:</label>
      <input type="email" name="email" value="<?= htmlspecialchars($usuario['email']) ?>" required>

      <button type="submit" style="background-color: #ffc107; color: #333;">Actualizar Datos</button>
    </form>

    <div style="text-align:center; margin-top:15px;">
      <a href="index.php?accion=listar" class="btn-link">Cancelar</a>
    </div>
  </div>
</body>

</html>