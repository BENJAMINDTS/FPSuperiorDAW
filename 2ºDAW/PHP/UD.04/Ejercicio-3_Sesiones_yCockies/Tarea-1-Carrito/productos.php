<?php
session_start();

// Inicializar carrito si no existe
if (!isset($_SESSION['carrito'])) {
    $_SESSION['carrito'] = [];
}

// Array de productos
$productos = [
    ['id' => 1, 'nombre' => 'Laptop', 'precio' => 1200],
    ['id' => 2, 'nombre' => 'Mouse', 'precio' => 25],
    ['id' => 3, 'nombre' => 'Teclado', 'precio' => 75],
    ['id' => 4, 'nombre' => 'Monitor', 'precio' => 300],
    ['id' => 5, 'nombre' => 'Auriculares', 'precio' => 50]
];

// Procesar agregar al carrito
if (isset($_POST['agregar'])) {
    $producto_id = $_POST['producto_id'];
    
    // Buscar el producto en el array
    foreach ($productos as $producto) {
        if ($producto['id'] == $producto_id) {
            $_SESSION['carrito'][] = $producto;
            $mensaje = $producto['nombre'] . " agregado al carrito correctamente";
            break;
        }
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos - Carrito de Compras</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <header class="header">
        <div class="header-content">
            <h1>Tienda Online</h1>
            <nav>
                <ul class="nav-menu">
                    <li><a href="productos.php">Productos</a></li>
                    <li><a href="carrito.php">Carrito (<?php echo count($_SESSION['carrito']); ?>)</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main-content">
        <div class="container">
            <h1>Nuestros Productos</h1>
            
            <?php if (isset($mensaje)): ?>
                <div class="success"><?php echo $mensaje; ?></div>
            <?php endif; ?>

            <div class="products-grid">
                <?php foreach ($productos as $producto): ?>
                <div class="product-card">
                    <div class="product-info">
                        <h3><?php echo $producto['nombre']; ?></h3>
                        <div class="product-price">$<?php echo number_format($producto['precio'], 2); ?></div>
                        <div class="product-id">ID: <?php echo $producto['id']; ?></div>
                    </div>
                    <form method="POST" class="product-form">
                        <input type="hidden" name="producto_id" value="<?php echo $producto['id']; ?>">
                        <button type="submit" name="agregar" class="btn">Agregar al Carrito</button>
                    </form>
                </div>
                <?php endforeach; ?>
            </div>

            <div style="margin-top: 30px;">
                <a href="carrito.php" class="btn btn-success">Ver Carrito (<?php echo count($_SESSION['carrito']); ?> productos)</a>
            </div>
        </div>
    </main>

    <footer class="footer">
        <div class="footer-content">
            <p>Carrito de Compras con Sesiones PHP</p>
        </div>
    </footer>
</body>
</html>