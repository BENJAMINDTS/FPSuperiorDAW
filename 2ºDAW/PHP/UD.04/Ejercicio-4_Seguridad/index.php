<?php
require 'includes/config.php';
?>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Autenticación</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Sistema de Autenticación</h1>
        
        <div class="nav">
            <ul>
                <li><a href="index.php">Inicio</a></li>
                <?php if (!estaLogueado()): ?>
                    <li><a href="registro.php">Registro</a></li>
                    <li><a href="login.php">Login</a></li>
                <?php else: ?>
                    <li><a href="dashboard.php">Dashboard</a></li>
                    <?php if (tieneRol('admin')): ?>
                        <li><a href="admin.php">Admin</a></li>
                    <?php endif; ?>
                    <?php if (tieneRol('editor')): ?>
                        <li><a href="editor.php">Editor</a></li>
                    <?php endif; ?>
                    <li><a href="usuario.php">Mi Perfil</a></li>
                    <li><a href="logout.php">Logout</a></li>
                <?php endif; ?>
            </ul>
        </div>

        <?php if (isset($_SESSION['success'])): ?>
            <div class="message success"><?php echo $_SESSION['success']; unset($_SESSION['success']); ?></div>
        <?php endif; ?>

        <?php if (isset($_SESSION['error'])): ?>
            <div class="message error"><?php echo $_SESSION['error']; unset($_SESSION['error']); ?></div>
        <?php endif; ?>

        <?php if (estaLogueado()): ?>
            <div class="user-info">
                <h3>¡Hola, <?php echo $_SESSION['user_nombre']; ?>!</h3>
                <p>Email: <?php echo $_SESSION['user_email']; ?></p>
                <p>Rol: <?php echo $_SESSION['user_rol']; ?>
                    <span class="role-badge role-<?php echo $_SESSION['user_rol']; ?>">
                        <?php echo $_SESSION['user_rol']; ?>
                    </span>
                </p>
            </div>

            <div class="features">
                <div class="feature">
                    <h4>Dashboard</h4>
                    <p>Panel principal del sistema</p>
                    <a href="dashboard.php" class="btn">Ir al Dashboard</a>
                </div>
                
                <?php if (tieneRol('admin')): ?>
                <div class="feature">
                    <h4>Admin</h4>
                    <p>Zona de administración</p>
                    <a href="admin.php" class="btn">Acceder</a>
                </div>
                <?php endif; ?>

                <?php if (tieneRol('editor')): ?>
                <div class="feature">
                    <h4>Editor</h4>
                    <p>Zona de edición</p>
                    <a href="editor.php" class="btn">Acceder</a>
                </div>
                <?php endif; ?>

                <div class="feature">
                    <h4>Perfil</h4>
                    <p>Mi información personal</p>
                    <a href="usuario.php" class="btn">Ver Perfil</a>
                </div>
            </div>

        <?php else: ?>
            <div class="message info">
                <h3>Bienvenido al Sistema</h3>
                <p>Por favor inicia sesión o regístrate para acceder al sistema.</p>
            </div>

            <div class="actions">
                <a href="login.php" class="btn">Iniciar Sesión</a>
                <a href="registro.php" class="btn">Registrarse</a>
            </div>

            <div class="features">
                <div class="feature">
                    <h4>Registro Seguro</h4>
                    <p>Contraseñas encriptadas con password_hash()</p>
                </div>
                <div class="feature">
                    <h4>Control de Roles</h4>
                    <p>Diferentes permisos para admin, editor y usuario</p>
                </div>
                <div class="feature">
                    <h4>Datos Protegidos</h4>
                    <p>Teléfonos encriptados con OpenSSL</p>
                </div>
            </div>
        <?php endif; ?>
    </div>
</body>
</html>