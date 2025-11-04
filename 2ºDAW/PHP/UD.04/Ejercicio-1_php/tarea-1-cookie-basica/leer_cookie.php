<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Leer Cookie - Tarea 1</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <header class="header">
        <div class="header-content">
            <h1>Tarea 1 - Leer Cookie</h1>
            <nav>
                <ul class="nav-menu">
                    <li><a href="../index.php">Inicio</a></li>
                    <li><a href="set_cookie.php">Establecer Cookie</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="main-content">
        <div class="container">
            <h1>Valor de la Cookie</h1>
            
            <?php
            if(isset($_COOKIE['usuario'])) {
                $valor = htmlspecialchars($_COOKIE['usuario']);
                $expiry_date = date('Y-m-d', time() + (7 * 24 * 60 * 60));
                
                echo '<div class="result">';
                echo '<h2>Cookie Encontrada</h2>';
                echo "<p><strong>Nombre:</strong> usuario</p>";
                echo "<p><strong>Valor:</strong> $valor</p>";
                echo "<p><strong>Fecha estimada de expiración:</strong> $expiry_date</p>";
                echo '</div>';
            } else {
                echo '<div class="error">';
                echo '<h2>No hay cookie disponible</h2>';
                echo '<p>La cookie \"usuario\" no está establecida o ha expirado.</p>';
                echo '</div>';
            }
            ?>
            
            <div class="task-links">
                <a href="set_cookie.php" class="btn">Establecer Cookie Nuevamente</a>
                <a href="../index.php" class="btn btn-secondary">Volver al Inicio</a>
            </div>
        </div>
    </div>

    <footer class="footer">
        <div class="footer-content">
            <p>Tarea 1 - Cookie Básica</p>
        </div>
    </footer>
</body>
</html>