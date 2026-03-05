<?php
require 'includes/config.php';
requerirRol('editor');
?>
<!DOCTYPE html>
<html>
<head>
    <title>Panel Editor</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Panel de Editor</h1>
        
        <div class="nav">
            <ul>
                <li><a href="index.php">Inicio</a></li>
                <li><a href="dashboard.php">Dashboard</a></li>
                <li><a href="logout.php">Logout</a></li>
            </ul>
        </div>

        <div class="message info">
            <h3>¡Acceso de Editor!</h3>
            <p>Puedes gestionar contenidos pero no configuraciones del sistema</p>
        </div>

        <div class="features">
            <div class="feature">
                <h4>Crear Contenido</h4>
                <p>Crear nuevos artículos y páginas</p>
            </div>
            <div class="feature">
                <h4>Gestionar Contenido</h4>
                <p>Editar y organizar contenidos</p>
            </div>
            <div class="feature">
                <h4>Multimedia</h4>
                <p>Gestionar imágenes y archivos</p>
            </div>
        </div>
    </div>
</body>
</html>