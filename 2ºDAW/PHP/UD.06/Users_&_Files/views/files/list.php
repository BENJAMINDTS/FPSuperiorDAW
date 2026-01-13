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
  <title>Mis Archivos</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>

  <div class="container">
    <h2>Mis Archivos</h2>

    <?php if (empty($lista_archivos)): ?>
      <div class="alerta alerta-info">
        No has subido ningún archivo todavía.
      </div>
    <?php else: ?>
      <table>
        <thead>
          <tr>
            <th>Nombre del Archivo</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <?php foreach ($lista_archivos as $archivo): ?>
            <tr>
              <td><?= htmlspecialchars($archivo['nombre']) ?></td>
              <td>
                <a href="<?= htmlspecialchars($archivo['ruta']) ?>" target="_blank">Ver</a> |
                <a href="index.php?accion=eliminarArchivo&id=<?= $archivo['id'] ?>"
                  onclick="return confirm('¿Estás seguro de que quieres eliminar este archivo?');"
                  style="color: #e74c3c;">Eliminar</a>
              </td>
            </tr>
          <?php endforeach; ?>
        </tbody>
      </table>
    <?php endif; ?>

    <div style="margin-top: 30px; border-top: 1px solid #eee; padding-top: 20px;">
      <p><a href="index.php?accion=subirArchivo">Subir nuevo archivo</a></p>
      <p><a href="index.php?accion=dashboard">Volver al Dashboard</a></p>
    </div>
  </div>

</body>

</html>