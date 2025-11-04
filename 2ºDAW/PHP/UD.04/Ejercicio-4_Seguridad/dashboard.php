<?php
require 'includes/config.php';
requerirLogin();
?>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Dashboard</h1>
        
        <div class="nav">
            <ul>
                <li><a href="index.php">Inicio</a></li>
                <?php if (tieneRol('admin')): ?>
                    <li><a href="admin.php">Admin</a></li>
                <?php endif; ?>
                <?php if (tieneRol('editor')): ?>
                    <li><a href="editor.php">Editor</a></li>
                <?php endif; ?>
                <li><a href="usuario.php">Mi Perfil</a></li>
                <li><a href="logout.php">Logout</a></li>
            </ul>
        </div>

        <div class="user-info">
            <h3>Bienvenido al Dashboard</h3>
            <p><strong>Usuario:</strong> <?php echo $_SESSION['user_nombre']; ?></p>
            <p><strong>Rol:</strong> <?php echo $_SESSION['user_rol']; ?></p>
            <p><strong>Email:</strong> <?php echo $_SESSION['user_email']; ?></p>
        </div>

        <div class="features">
            <div class="feature">
                <h4>Mi Perfil</h4>
                <p>Gestiona tu información personal</p>
                <a href="usuario.php" class="btn">Ver Perfil</a>
            </div>

            <?php if (tieneRol('admin')): ?>
            <div class="feature">
                <h4>Panel Admin</h4>
                <p>Gestión completa del sistema</p>
                <a href="admin.php" class="btn">Acceder</a>
            </div>
            <?php endif; ?>

            <?php if (tieneRol('editor')): ?>
            <div class="feature">
                <h4>Panel Editor</h4>
                <p>Gestión de contenidos</p>
                <a href="editor.php" class="btn">Acceder</a>
            </div>
            <?php endif; ?>
        </div>

        <div class="message info">
            <h4>Información del Sistema</h4>
            <p>Tu rol actual: <strong><?php echo $_SESSION['user_rol']; ?></strong></p>
            <p>Permisos disponibles según tu rol</p>
        </div>
    </div>
</body>
</html>