<?php
// Establecer cookie si se envió el formulario
if (isset($_POST['modo'])) {
    $modo = $_POST['modo'];
    setcookie('modo_visualizacion', $modo, time() + (7 * 24 * 60 * 60)); // 1 semana
    $mensaje = "Preferencia guardada correctamente";
    header('Location: preferencias.php');
    exit;
}

// Obtener modo actual
$modo_actual = isset($_COOKIE['modo_visualizacion']) ? $_COOKIE['modo_visualizacion'] : 'claro';
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Preferencias de Visualización</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .modo-oscuro {
            background: linear-gradient(135deg, #2c3e50 0%, #34495e 100%) !important;
            color: #ecf0f1 !important;
        }
        .modo-oscuro .container {
            background-color: rgba(52, 73, 94, 0.95) !important;
            color: #ecf0f1 !important;
        }
        .modo-oscuro .task-card {
            background: linear-gradient(145deg, #34495e, #2c3e50) !important;
            color: #ecf0f1 !important;
            border: 1px solid #4a6572 !important;
        }
        .modo-oscuro h1, .modo-oscuro h2, .modo-oscuro h3 {
            color: #3498db !important;
        }
    </style>
</head>
<body class="<?php echo $modo_actual === 'oscuro' ? 'modo-oscuro' : ''; ?>">
    <header class="header">
        <div class="header-content">
            <h1>Preferencias de Usuario</h1>
            <nav>
                <ul class="nav-menu">
                    <li><a href="preferencias.php">Preferencias</a></li>
                    <li><a href="../Tarea-1-Carrito/productos.php">Tienda</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="main-content">
        <div class="container">
            <h1>Configura tus Preferencias</h1>
            
            <?php if (isset($_GET['mensaje'])): ?>
                <div class="success"><?php echo htmlspecialchars($_GET['mensaje']); ?></div>
            <?php endif; ?>

            <div class="calculator">
                <h2>Modo de Visualización Actual: 
                    <span style="color: #2575fc;"><?php echo ucfirst($modo_actual); ?></span>
                </h2>
                
                <form method="POST">
                    <div class="form-group">
                        <label>Selecciona el modo de visualización:</label>
                        <select name="modo" class="form-control">
                            <option value="claro" <?php echo $modo_actual === 'claro' ? 'selected' : ''; ?>>Modo Claro</option>
                            <option value="oscuro" <?php echo $modo_actual === 'oscuro' ? 'selected' : ''; ?>>Modo Oscuro</option>
                        </select>
                    </div>
                    
                    <button type="submit" class="btn">Guardar Preferencias</button>
                </form>
            </div>

            <div class="info">
                <h3>Información sobre las Cookies</h3>
                <p>Tu preferencia se guarda en una cookie que expirará en 7 días.</p>
                <p>Modo actual: <strong><?php echo $modo_actual; ?></strong></p>
                <?php if (isset($_COOKIE['modo_visualizacion'])): ?>
                    <p>Cookie establecida: <strong>Si</strong></p>
                <?php else: ?>
                    <p>Cookie establecida: <strong>No</strong></p>
                <?php endif; ?>
            </div>
        </div>
    </main>

    <footer class="footer">
        <div class="footer-content">
            <p>Preferencias de Usuario con Cookies PHP</p>
        </div>
    </footer>
</body>
</html>