<?php
session_start();

// Reiniciar contador de visitas
$_SESSION['visitas'] = 0; // Se incrementará a 1 en el index

// Mensaje de confirmación
$_SESSION['mensaje'] = [
    'tipo' => 'info',
    'texto' => 'Contador de visitas reiniciado'
];

// Redirigir al index
header('Location: index.php');
exit();
?>