<?php
require_once '../config/config.php';

// Registrar cierre de sesión
if (isset($_SESSION['user_id'])) {
    error_log("Usuario {$_SESSION['user_id']} cerró sesión - " . date('Y-m-d H:i:s'));
}

// Destruir todas las variables de sesión
$_SESSION = array();

// Destruir la cookie de sesión
if (ini_get("session.use_cookies")) {
    $params = session_get_cookie_params();
    setcookie(session_name(), '', time() - 42000,
        $params["path"], $params["domain"],
        $params["secure"], $params["httponly"]
    );
}

// Destruir la sesión
session_destroy();

// Eliminar cookie de recordar
setcookie('remember_me', '', [
    'expires' => time() - 3600,
    'path' => '/',
    'secure' => true,
    'httponly' => true,
    'samesite' => 'Strict'
]);

// Redirigir al login
header('Location: login.php');
exit;
?>