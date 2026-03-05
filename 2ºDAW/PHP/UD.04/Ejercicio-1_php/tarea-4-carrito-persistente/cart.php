<?php
// Definir catálogo de piezas de coche (mismo que en catalog.php)
$productos = [
  1 => ['id' => 1, 'name' => 'Filtro de Aceite', 'price' => 25.50, 'categoria' => 'Filtros'],
  2 => ['id' => 2, 'name' => 'Pastillas de Freno Delanteras', 'price' => 45.00, 'categoria' => 'Frenos'],
  3 => ['id' => 3, 'name' => 'Batería 12V 60Ah', 'price' => 89.99, 'categoria' => 'Eléctrico'],
  4 => ['id' => 4, 'name' => 'Aceite Motor 5W-30 Sintético', 'price' => 32.75, 'categoria' => 'Lubricantes'],
  5 => ['id' => 5, 'name' => 'Neumático 205/55 R16', 'price' => 68.00, 'categoria' => 'Ruedas'],
  6 => ['id' => 6, 'name' => 'Amortiguador Delantero', 'price' => 55.25, 'categoria' => 'Suspensión'],
  7 => ['id' => 7, 'name' => 'Bujías Iridium', 'price' => 12.99, 'categoria' => 'Motor'],
  8 => ['id' => 8, 'name' => 'Correa de Distribución', 'price' => 38.50, 'categoria' => 'Motor'],
  9 => ['id' => 9, 'name' => 'Líquido de Freno DOT4', 'price' => 8.45, 'categoria' => 'Frenos'],
  10 => ['id' => 10, 'name' => 'Filtro de Aire', 'price' => 18.30, 'categoria' => 'Filtros'],
  11 => ['id' => 11, 'name' => 'Radiador', 'price' => 120.00, 'categoria' => 'Refrigeración'],
  12 => ['id' => 12, 'name' => 'Alternador', 'price' => 145.00, 'categoria' => 'Eléctrico']
];

// Procesar eliminar producto
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['eliminar_id'])) {
  $eliminar_id = (int)$_POST['eliminar_id'];

  if (isset($_COOKIE['cart'])) {
    $carrito = json_decode($_COOKIE['cart'], true);

    if (isset($carrito[$eliminar_id])) {
      unset($carrito[$eliminar_id]);

      // Actualizar cookie
      if (empty($carrito)) {
        // Si el carrito queda vacío, eliminar la cookie
        setcookie('cart', '', time() - 3600, "/");
        setcookie('cart_empty', 'true', time() + 5, "/");
      } else {
        $carrito_json = json_encode($carrito);
        setcookie('cart', $carrito_json, time() + (24 * 60 * 60), "/");
        setcookie('cart_updated', 'true', time() + 5, "/");
      }
    }
  }

  header("Location: cart.php");
  exit();
}

// Procesar actualizar cantidad
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['actualizar_id']) && isset($_POST['cantidad'])) {
  $actualizar_id = (int)$_POST['actualizar_id'];
  $cantidad = max(1, (int)$_POST['cantidad']); // Mínimo 1

  if (isset($_COOKIE['cart']) && $cantidad <= 99) { // Límite de 99 por producto
    $carrito = json_decode($_COOKIE['cart'], true);

    if (isset($carrito[$actualizar_id])) {
      $carrito[$actualizar_id]['cantidad'] = $cantidad;

      $carrito_json = json_encode($carrito);
      setcookie('cart', $carrito_json, time() + (24 * 60 * 60), "/");
      setcookie('cart_updated', 'true', time() + 5, "/");
    }
  }

  header("Location: cart.php");
  exit();
}

// Procesar vaciar carrito
if (isset($_GET['vaciar'])) {
  setcookie('cart', '', time() - 3600, "/");
  setcookie('cart_empty', 'true', time() + 5, "/");
  header("Location: cart.php");
  exit();
}

// Obtener carrito actual
$carrito = [];
$total = 0;
$num_productos = 0;
$categorias_carrito = [];

if (isset($_COOKIE['cart'])) {
  $carrito = json_decode($_COOKIE['cart'], true);

  foreach ($carrito as $item) {
    $num_productos += $item['cantidad'];
    $subtotal = $item['producto']['price'] * $item['cantidad'];
    $total += $subtotal;

    // Agrupar por categoría para estadísticas
    $categoria = $item['producto']['categoria'];
    if (!isset($categorias_carrito[$categoria])) {
      $categorias_carrito[$categoria] = 0;
    }
    $categorias_carrito[$categoria] += $item['cantidad'];
  }
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Carrito de Repuestos - Tarea 4</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>

<body>
  <header class="header">
    <div class="header-content">
      <h1>Tarea 4 - Carrito de Repuestos</h1>
      <nav>
        <ul class="nav-menu">
          <li><a href="../index.php">Inicio</a></li>
          <li><a href="catalog.php">Catálogo</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <div class="main-content">
    <div class="container">
      <h1>Mi Carrito de Repuestos</h1>

      <?php if (isset($_COOKIE['cart_updated'])): ?>
        <div class="success">
          <p>Carrito actualizado correctamente.</p>
          <?php setcookie('cart_updated', '', time() - 3600, "/"); ?>
        </div>
      <?php endif; ?>

      <?php if (isset($_COOKIE['cart_empty'])): ?>
        <div class="info">
          <p>Carrito vaciado correctamente.</p>
          <?php setcookie('cart_empty', '', time() - 3600, "/"); ?>
        </div>
      <?php endif; ?>

      <?php if (empty($carrito)): ?>
        <div class="info">
          <h2>Carrito Vacío</h2>
          <p>No hay piezas de coche en el carrito.</p>
          <p>Visita nuestro catálogo para añadir repuestos.</p>
        </div>
      <?php else: ?>

        <div class="cart-stats">
          <div class="stat-card">
            <h3>Resumen General</h3>
            <p><strong>Total de piezas:</strong> <?php echo $num_productos; ?></p>
            <p><strong>Productos diferentes:</strong> <?php echo count($carrito); ?></p>
            <p><strong>Categorías en carrito:</strong> <?php echo count($categorias_carrito); ?></p>
          </div>
        </div>

        <div class="product-list">
          <table>
            <thead>
              <tr>
                <th>Pieza</th>
                <th>Categoría</th>
                <th>Precio Unitario</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <?php foreach ($carrito as $item):
                $producto = $item['producto'];
                $subtotal = $producto['price'] * $item['cantidad'];
              ?>
                <tr>
                  <td>
                    <strong><?php echo htmlspecialchars($producto['name']); ?></strong>
                    <br><small>REF: <?php echo str_pad($producto['id'], 3, '0', STR_PAD_LEFT); ?></small>
                  </td>
                  <td><?php echo htmlspecialchars($producto['categoria']); ?></td>
                  <td><?php echo number_format($producto['price'], 2); ?> €</td>
                  <td>
                    <form method="POST" action="" class="quantity-form">
                      <input type="hidden" name="actualizar_id" value="<?php echo $producto['id']; ?>">
                      <input type="number" name="cantidad" value="<?php echo $item['cantidad']; ?>"
                        min="1" max="99" class="quantity-input">
                      <button type="submit" class="btn btn-small">Actualizar</button>
                    </form>
                  </td>
                  <td><?php echo number_format($subtotal, 2); ?> €</td>
                  <td>
                    <form method="POST" action="" style="display: inline;">
                      <input type="hidden" name="eliminar_id" value="<?php echo $producto['id']; ?>">
                      <button type="submit" class="btn btn-danger btn-small">Eliminar</button>
                    </form>
                  </td>
                </tr>
              <?php endforeach; ?>
            </tbody>
          </table>

          <div class="result">
            <h2>Total del Pedido</h2>
            <p><strong>Total de piezas:</strong> <?php echo $num_productos; ?> unidades</p>
            <p><strong>Importe total:</strong> <?php echo number_format($total, 2); ?> €</p>
            <p><strong>IVA (21%):</strong> <?php echo number_format($total * 0.21, 2); ?> €</p>
            <p><strong>Total con IVA:</strong> <?php echo number_format($total * 1.21, 2); ?> €</p>
            <p><strong>Tamaño de la cookie:</strong> <?php echo strlen(json_encode($carrito)); ?> bytes / 4096 bytes</p>

            <?php if (strlen(json_encode($carrito)) > 3000): ?>
              <div class="warning">
                <p>¡Atención! El carrito está cerca del límite de tamaño.</p>
              </div>
            <?php endif; ?>
          </div>
        </div>

        <div class="categorias-carrito">
          <h3>Piezas por Categoría</h3>
          <div class="categorias-list">
            <?php foreach ($categorias_carrito as $categoria => $cantidad): ?>
              <span class="categoria-tag"><?php echo $categoria; ?>: <?php echo $cantidad; ?></span>
            <?php endforeach; ?>
          </div>
        </div>

      <?php endif; ?>

      <div class="task-links">
        <a href="catalog.php" class="btn">Seguir Comprando</a>
        <?php if (!empty($carrito)): ?>
          <a href="cart.php?vaciar=1" class="btn btn-danger">Vaciar Carrito Completo</a>
        <?php endif; ?>
        <a href="../index.php" class="btn btn-secondary">Volver al Inicio</a>
      </div>
    </div>
  </div>

  <footer class="footer">
    <div class="footer-content">
      <p>Tarea 4 - AutoRepuestos Pro - Sistema de Carrito Persistente</p>
    </div>
  </footer>
</body>

</html>