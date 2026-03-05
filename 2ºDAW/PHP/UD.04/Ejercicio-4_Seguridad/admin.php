<?php
require 'includes/config.php';
requerirRol('admin');
?>
<!DOCTYPE html>
<html>
<head>
    <title>Panel Admin</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Panel de Administración</h1>
        
        <div class="nav">
            <ul>
                <li><a href="index.php">Inicio</a></li>
                <li><a href="dashboard.php">Dashboard</a></li>
                <li><a href="logout.php">Logout</a></li>
            </ul>
        </div>

        <div class="message success">
            <h3>¡Acceso de Administrador!</h3>
            <p>Tienes permisos completos sobre el sistema</p>
        </div>

        <div class="features">
            <div class="feature">
                <h4>Gestión de Usuarios</h4>
                <p>Crear, editar y eliminar usuarios</p>
            </div>
            <div class="feature">
                <h4>Configuración</h4>
                <p>Configuración del sistema</p>
            </div>
            <div class="feature">
                <h4>Estadísticas</h4>
                <p>Reportes y analytics</p>
            </div>
        </div>

        <div class="user-info">
            <h4>Usuarios en el sistema:</h4>
            <ul>
                <?php foreach ($_SESSION['usuarios'] as $usuario): ?>
                    <li>
                        <?php echo $usuario['nombre']; ?> 
                        (<?php echo $usuario['email']; ?>)
                        <span class="role-badge role-<?php echo $usuario['rol']; ?>">
                            <?php echo $usuario['rol']; ?>
                        </span>
                    </li>
                <?php endforeach; ?>
            </ul>
        </div>
    </div>
</body>
</html>