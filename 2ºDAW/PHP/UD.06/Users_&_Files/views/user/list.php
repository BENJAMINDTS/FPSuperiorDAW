<?php
if (session_status() === PHP_SESSION_NONE) {
  session_start();
}
$registros_por_pagina = 5;
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Lista de Usuarios</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>

  <div class="container">
    <h2>Lista de Usuarios</h2>

    <form action="index.php?accion=listar" method="POST" class="form-busqueda">
      <input type="text" name="busqueda" placeholder="Buscar por nombre..."
        value="<?= isset($_POST['busqueda']) ? htmlspecialchars($_POST['busqueda']) : '' ?>">
      <button type="submit">Buscar</button>
    </form>

    <?php if (empty($lista_usuarios)): ?>
      <div class="alerta alerta-error">
        No hay usuarios registrados o no se encontraron resultados para su búsqueda.
      </div>
    <?php else: ?>
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Email</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <?php foreach ($lista_usuarios as $usuario): ?>
            <tr>
              <td><?= htmlspecialchars($usuario['nombre']) ?></td>
              <td><?= htmlspecialchars($usuario['email']) ?></td>
              <td>
                <a href="index.php?accion=editar&id=<?= $usuario['id'] ?>">Editar</a> |
                <a href="index.php?accion=eliminar&id=<?= $usuario['id'] ?>"
                  onclick="return confirm('¿Estás seguro de que quieres eliminar a este usuario?');"
                  style="color: #e74c3c;">Eliminar</a>
              </td>
            </tr>
          <?php endforeach; ?>
        </tbody>
      </table>

      <div class="paginacion">
        <?php if ($pagina_actual > 1): ?>
          <a href="index.php?accion=listar&pagina=<?= $pagina_actual - 1 ?>" class="btn-pagi">« Anterior</a>
        <?php endif; ?>

        <span class="pagina-info">Página <?= $pagina_actual ?></span>

        <?php
        // Si la cantidad de usuarios devuelta es igual al límite,
        // asumimos que podría haber una página siguiente.
        if (count($lista_usuarios) == $registros_por_pagina):
        ?>
          <a href="index.php?accion=listar&pagina=<?= $pagina_actual + 1 ?>" class="btn-pagi">Siguiente »</a>
        <?php endif; ?>
      </div>
    <?php endif; ?>

    <div style="margin-top: 30px; border-top: 1px solid #eee; padding-top: 20px;">
      <p><a href="index.php?accion=registro">Registrar nuevo usuario</a></p>
      <p><a href="index.php?accion=dashboard">Volver al Dashboard</a></p>
    </div>
  </div>

</body>

</html>