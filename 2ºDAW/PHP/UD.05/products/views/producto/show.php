<?php
  require_once '../../config/database.php';
  require_once '../../models/productoModel.php';
  require_once '../../controllers/productoControler.php';
  $model = new ProductoModel($connection);
  $controller = new ProductoController($model);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Producto List</title>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Lista de Productos</h1>
            <p>Gestión completa de inventario</p>
        </div>
        
        <div class="table-container">
            <table class="product-table">
                <tr>
                    <th>Nombre</th>
                    <th>Precio</th>
                </tr>
                <?php
                    $products = $model->getAllProducts();
                    if(empty($products)) {
                        echo '<tr><td colspan="2" class="no-products">No hay productos disponibles</td></tr>';
                    } else {
                        foreach ($products as $product) {
                            echo "<tr>";
                            echo "<td class='product-name'>" . htmlspecialchars($product['nombre']) . "</td>";
                            echo "<td class='product-price'>$" . htmlspecialchars($product['precio']) . "</td>";
                            echo "</tr>";
                        }
                    }
                ?>
            </table>
        </div>
    </div>
</body>