<?php
session_start();

// Guardar mensaje antes de destruir
if (isset($_SESSION['nombre'])) {
    $nombre = $_SESSION['nombre'];
    $_SESSION['mensaje'] = [
        'tipo' => 'warning',
        'texto' => "Sesión cerrada. ¡Hasta pronto $nombre!"
    ];
}

// Eliminar solo el nombre de usuario (mantener otras variables de sesión)
unset($_SESSION['nombre']);

// Redirigir al index
header('Location: index.php');
exit();
?>