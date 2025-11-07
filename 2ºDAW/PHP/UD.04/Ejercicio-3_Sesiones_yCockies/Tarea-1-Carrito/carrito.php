<?php
session_start();

// Procesar eliminar producto
if (isset($_POST['eliminar'])) {
    $indice = $_POST['indice'];
    if (isset($_SESSION['carrito'][$indice])) {
        $producto_eliminado = $_SESSION['carrito'][$indice]['nombre'];
        unset($_SESSION['carrito'][$indice]);
        $_SESSION['carrito'] = array_values($_SESSION['carrito']);
        $mensaje = $producto_eliminado . " eliminado del carrito";
    }
}

// Procesar vaciar carrito
if (isset($_POST['vaciar'])) {
    $_SESSION['carrito'] = [];
    $mensaje = "Carrito vaciado correctamente";
}

// Calcular total
$total = 0;
foreach ($_SESSION['carrito'] as $producto) {
    $total += $producto['precio'];
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito de Compras</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <header class="header">
        <div class="header-content">
            <h1>Mi Carrito</h1>
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
            <h1>Productos en el Carrito</h1>
            
            <?php if (isset($mensaje)): ?>
                <div class="info"><?php echo $mensaje; ?></div>
            <?php endif; ?>

            <?php if (empty($_SESSION['carrito'])): ?>
                <div class="warning">
                    <h2>Tu carrito esta vacio</h2>
                    <p>Agrega algunos productos desde nuestra tienda.</p>
                    <a href="productos.php" class="btn">Ir a Productos</a>
                </div>
            <?php else: ?>
                <div class="cart-stats">
                    <div class="stat-card">
                        <h3>Resumen del Carrito</h3>
                        <p>Total de productos: <strong><?php echo count($_SESSION['carrito']); ?></strong></p>
                        <p>Total a pagar: <strong>$<?php echo number_format($total, 2); ?></strong></p>
                    </div>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Precio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($_SESSION['carrito'] as $indice => $producto): ?>
                        <tr>
                            <td><?php echo $producto['nombre']; ?></td>
                            <td>$<?php echo number_format($producto['precio'], 2); ?></td>
                            <td>
                                <form method="POST" style="display: inline;">
                                    <input type="hidden" name="indice" value="<?php echo $indice; ?>">
                                    <button type="submit" name="eliminar" class="btn btn-danger">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                        <?php endforeach; ?>
                    </tbody>
                </table>

                <div style="margin-top: 20px; display: flex; gap: 10px; justify-content: center;">
                    <form method="POST">
                        <button type="submit" name="vaciar" class="btn btn-danger" 
                                onclick="return confirm('¿Estas seguro de vaciar el carrito?')">
                            Vaciar Carrito
                        </button>
                    </form>
                    <a href="productos.php" class="btn">Seguir Comprando</a>
                    <button class="btn btn-success">Proceder al Pago</button>
                </div>
            <?php endif; ?>
        </div>
    </main>

    <footer class="footer">
        <div class="footer-content">
            <p>Carrito de Compras con Sesiones PHP</p>
        </div>
    </footer>
</body>
</html>