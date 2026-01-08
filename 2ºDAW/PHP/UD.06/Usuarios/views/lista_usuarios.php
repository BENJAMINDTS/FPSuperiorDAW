<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>Lista de Usuarios</title>
  <link rel="stylesheet" href="assets/css/estilos.css">
</head>

<body>

  <div class="container" style="max-width: 600px;">
    <h2>Usuarios Registrados</h2>

    <div style="text-align: center; margin-bottom: 20px;">
      <a href="index.php" class="btn-link">← Volver al Registro</a>
    </div>

    <table>
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Email</th>
          <th>Password</th>
        </tr>
      </thead>
      <tbody>
        <?php if (!empty($lista_usuarios)): ?>
          <?php foreach ($lista_usuarios as $usuario): ?>
            <tr>
              <td><?= htmlspecialchars($usuario['nombre']) ?></td>
              <td><?= htmlspecialchars($usuario['email']) ?></td>
              <td><?= htmlspecialchars($usuario['password']) ?></td>
            </tr>
          <?php endforeach; ?>
        <?php else: ?>
          <tr>
            <td colspan="3" style="text-align:center;">No hay usuarios en la tabla 'usuarios2'</td>
          </tr>
        <?php endif; ?>
      </tbody>
    </table>
  </div>

</body>

</html>