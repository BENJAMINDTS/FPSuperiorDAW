<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>Gestión de Usuarios</title>
  <link rel="stylesheet" href="css/styles.css">
  <style>
    
  </style>
</head>

<body>

  <div class="container" style="max-width: 700px;">
    <h2>Gestión de Usuarios</h2>

    <div class="acciones">
      <a href="index.php" class="btn-link">← Nuevo Usuario</a>

      <form action="index.php?accion=listar" method="POST" class="search-box">
        <input type="text" name="busqueda" placeholder="Buscar por nombre...">
        <button type="submit">Buscar</button>
      </form>
    </div>

    <table>
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Email</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <?php if (!empty($lista_usuarios)): ?>
          <?php foreach ($lista_usuarios as $usr): ?>
            <tr>
              <td><?= htmlspecialchars($usr['nombre']) ?></td>
              <td><?= htmlspecialchars($usr['email']) ?></td>
              <td>
                <a href="index.php?accion=editar&email=<?= urlencode($usr['email']) ?>" class="btn-mini btn-edit">Editar</a>

                <a href="index.php?accion=eliminar&email=<?= urlencode($usr['email']) ?>"
                  class="btn-mini btn-del"
                  onclick="return confirm('¿Seguro que quieres eliminar a <?= $usr['nombre'] ?>?');">
                  Borrar
                </a>
              </td>
            </tr>
          <?php endforeach; ?>
        <?php else: ?>
          <tr>
            <td colspan="3" style="text-align:center">No se encontraron usuarios</td>
          </tr>
        <?php endif; ?>
      </tbody>
    </table>

    <?php if (isset($_POST['busqueda'])): ?>
      <div style="text-align:center; margin-top:10px;">
        <a href="index.php?accion=listar" class="btn-link">Ver todos</a>
      </div>
    <?php endif; ?>
  </div>

</body>

</html>