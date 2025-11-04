<?php
require 'includes/config.php';
requerirLogin();

$usuario = getUserInfo();
$telefono_desencriptado = decrypt($usuario['telefono']);
?>
<!DOCTYPE html>
<html>
<head>
    <title>Mi Perfil</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Mi Perfil</h1>
        
        <div class="nav">
            <ul>
                <li><a href="index.php">Inicio</a></li>
                <li><a href="dashboard.php">Dashboard</a></li>
                <li><a href="logout.php">Logout</a></li>
            </ul>
        </div>

        <div class="user-info">
            <h3>Información Personal</h3>
            <p><strong>ID:</strong> <?php echo $usuario['id']; ?></p>
            <p><strong>Nombre:</strong> <?php echo $usuario['nombre']; ?></p>
            <p><strong>Email:</strong> <?php echo $usuario['email']; ?></p>
            <p><strong>Teléfono:</strong> <?php echo $telefono_desencriptado ?: 'No proporcionado'; ?></p>
            <p><strong>Rol:</strong> <?php echo $usuario['rol']; ?></p>
            <p><strong>Fecha de registro:</strong> <?php echo date('d/m/Y H:i:s'); ?></p>
        </div>

        <div class="message info">
            <h4>Seguridad</h4>
            <p><strong>Contraseña:</strong> Encriptada con password_hash()</p>
            <p><strong>Teléfono:</strong> <?php echo $telefono_desencriptado ? 'Encriptado con OpenSSL' : 'No proporcionado'; ?></p>
        </div>
    </div>
</body>
</html>