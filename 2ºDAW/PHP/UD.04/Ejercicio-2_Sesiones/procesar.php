<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['nombre'])) {
    // Sanitizar y guardar el nombre
    $_SESSION['nombre'] = htmlspecialchars(trim($_POST['nombre']));
    
    // Mensaje de éxito
    $_SESSION['mensaje'] = [
        'tipo' => 'success',
        'texto' => 'Nombre guardado correctamente'
    ];
}

// Redirigir de vuelta al index
header('Location: index.php');
exit();
?>