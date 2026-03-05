<?php
if (session_status() === PHP_SESSION_NONE) {
  session_start();
}

// Verificar si el usuario ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
  header("Location: index.php?accion=login"); // Redirigir al login si no hay sesión
  exit();
}
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Subir Archivo</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>

  <div class="container">
    <h2>Subir Archivo</h2>

    <?php if (isset($mensaje)): ?>
      <div class="alerta <?= strpos($mensaje, 'Error') !== false ? 'alerta-error' : 'alerta-exito' ?>">
        <?= htmlspecialchars($mensaje) ?>
      </div>
    <?php endif; ?>

    <form action="index.php?accion=subirArchivo" method="POST" enctype="multipart/form-data">
      <label for="archivo">Seleccionar Archivo:</label>
      <input type="file" name="archivo" id="archivo" required>

      <button type="submit">Subir Archivo</button>
    </form>

    <p><a href="index.php?accion=dashboard">Volver al Dashboard</a></p>
  </div>

</body>

</html>
