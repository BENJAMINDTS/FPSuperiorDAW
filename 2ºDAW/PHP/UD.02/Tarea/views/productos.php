<?php
include '../contents/header.php';
?>
<body>
    <div class="main-content">
        <div class="container">
            <h1>Lista de Productos</h1>
            <div class="product-list">
                <?php
                $filePath = '../contents/productos.txt';
                if (file_exists($filePath)) {
                    $file = fopen($filePath, 'r');
                    if ($file) {
                        echo '<table>';
                        echo '<tr><th>Producto</th><th>Precio</th></tr>';
                        while (($line = fgets($file)) !== false) {
                            $parts = explode(',', trim($line));
                            if (count($parts) == 2) {
                                $producto = htmlspecialchars($parts[0]);
                                $precio = htmlspecialchars($parts[1]);
                                echo "<tr><td>$producto</td><td>\$$precio</td></tr>";
                            }
                        }
                        echo '</table>';
                        fclose($file);
                    } else {
                        echo '<p class="error">No se pudo abrir el archivo de productos.</p>';
                    }
                } else {
                    echo '<p class="error">El archivo de productos no existe.</p>';
                }
                ?>
            </div>
        </div>
    </div>
</body>
<?php
include '../contents/footer.php';
?>
