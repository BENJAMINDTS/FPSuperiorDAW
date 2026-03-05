<?php
// Definir catálogo de piezas de coche
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

// Agrupar productos por categoría para mejor presentación
$categorias = [];
foreach ($productos as $producto) {
  $categoria = $producto['categoria'];
  if (!isset($categorias[$categoria])) {
    $categorias[$categoria] = [];
  }
  $categorias[$categoria][] = $producto;
}

// Procesar añadir al carrito
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['producto_id'])) {
  $producto_id = (int)$_POST['producto_id'];

  // Validar que el producto existe
  if (!isset($productos[$producto_id])) {
    header("Location: catalog.php");
    exit();
  }

  // Obtener carrito actual
  $carrito = [];
  if (isset($_COOKIE['cart'])) {
    $carrito = json_decode($_COOKIE['cart'], true);
  }

  // Añadir producto al carrito
  if (isset($carrito[$producto_id])) {
    $carrito[$producto_id]['cantidad']++;
  } else {
    $carrito[$producto_id] = [
      'producto' => $productos[$producto_id],
      'cantidad' => 1
    ];
  }

  // Verificar tamaño del carrito
  $carrito_json = json_encode($carrito);
  if (strlen($carrito_json) > 4096) {
    // Carrito demasiado grande
    setcookie('cart_error', 'size_exceeded', time() + 60, "/");
  } else {
    // Guardar carrito en cookie
    setcookie('cart', $carrito_json, time() + (24 * 60 * 60), "/"); // 24 horas
    setcookie('cart_success', 'added', time() + 5, "/");
  }

  header("Location: catalog.php");
  exit();
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Catálogo de Piezas de Coche - Tarea 4</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>

<body>
  <header class="header">
    <div class="header-content">
      <h1>Tarea 4 - AutoRepuestos Pro</h1>
      <nav>
        <ul class="nav-menu">
          <li><a href="../index.php">Inicio</a></li>
          <li><a href="cart.php">Ver Carrito</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <div class="main-content">
    <div class="container">
      <h1>Catálogo de Piezas de Coche</h1>

      <?php if (isset($_COOKIE['cart_success'])): ?>
        <div class="success">
          <p>Producto añadido al carrito correctamente.</p>
          <?php setcookie('cart_success', '', time() - 3600, "/"); ?>
        </div>
      <?php endif; ?>

      <?php if (isset($_COOKIE['cart_error'])): ?>
        <div class="error">
          <h2>Límite de carrito excedido</h2>
          <p>El carrito ha superado el tamaño máximo permitido para cookies (4KB).</p>
          <p>Por favor, inicia sesión para una persistencia server-side o elimina algunos productos.</p>
          <?php setcookie('cart_error', '', time() - 3600, "/"); ?>
        </div>
      <?php endif; ?>

      <div class="stats-bar">
        <div class="stat-item">
          <strong>Total productos:</strong> <?php echo count($productos); ?>
        </div>
        <div class="stat-item">
          <strong>Categorías:</strong> <?php echo count($categorias); ?>
        </div>
        <div class="stat-item">
          <strong>Precio medio:</strong>
          <?php
          $precio_medio = array_sum(array_column($productos, 'price')) / count($productos);
          echo number_format($precio_medio, 2);
          ?> €
        </div>
      </div>

      <?php foreach ($categorias as $categoria_nombre => $productos_categoria): ?>
        <div class="category-section">
          <h2 class="category-title"><?php echo htmlspecialchars($categoria_nombre); ?></h2>
          <div class="products-grid">
            <?php foreach ($productos_categoria as $producto): ?>
              <div class="product-card">
                <div class="product-info">
                  <h3><?php echo htmlspecialchars($producto['name']); ?></h3>
                  <p class="product-price"><?php echo number_format($producto['price'], 2); ?> €</p>
                  <p class="product-id">REF: <?php echo str_pad($producto['id'], 3, '0', STR_PAD_LEFT); ?></p>
                </div>
                <form method="POST" action="" class="product-form">
                  <input type="hidden" name="producto_id" value="<?php echo $producto['id']; ?>">
                  <button type="submit" class="btn">Añadir al Carrito</button>
                </form>
              </div>
            <?php endforeach; ?>
          </div>
        </div>
      <?php endforeach; ?>

      <div class="task-links">
        <a href="cart.php" class="btn">Ver Carrito</a>
        <a href="../index.php" class="btn btn-secondary">Volver al Inicio</a>
      </div>
    </div>
  </div>

  <footer class="footer">
    <div class="footer-content">
      <p>Tarea 4 - AutoRepuestos Pro - Carrito Persistente</p>
    </div>
  </footer>
</body>

</html>