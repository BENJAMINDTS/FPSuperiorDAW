<?php
$current_file = $_SERVER['PHP_SELF'];
$is_index = (strpos($current_file, 'index.php') !== false);
$is_in_views = (strpos($current_file, 'views/') !== false);

if ($is_index) {
    $css_path = 'css/styles.css';
} elseif ($is_in_views) {
    $css_path = '../css/styles.css';
} else {
    $css_path = 'styles.css';
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Aplicación PHP</title>
    <link rel="stylesheet" href="<?php echo $css_path; ?>">
</head>
<body>
    <header class="header">
        <div class="header-content">
            <h1>Tarea 2</h1>
            <nav>
                <ul class="nav-menu">
                    <li><a href="<?php echo $is_in_views ? '../index.php' : 'index.php'; ?>">Inicio</a></li>
                    <li><a href="<?php echo $is_in_views ? 'productos.php' : 'views/productos.php'; ?>">Productos</a></li>
                    <li><a href="<?php echo $is_in_views ? 'calculadora.php' : 'views/calculadora.php'; ?>">Calculadora</a></li>
                </ul>
            </nav>
        </div>
    </header>