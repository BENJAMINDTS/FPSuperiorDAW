<?php
// Configuración de la aplicación
session_start();

// Configuración de seguridad
define('SESSION_TIMEOUT', 1800); // 30 minutos en segundos
define('CSRF_TOKEN_LIFETIME', 3600); // 1 hora en segundos
define('MAX_LOGIN_ATTEMPTS', 5); // Intentos máximos de login
define('LOGIN_TIMEOUT', 900); // 15 minutos de bloqueo tras intentos fallidos

// Incluir archivos necesarios UNA SOLA VEZ
require_once __DIR__ . '/../includes/auth_functions.php';
require_once __DIR__ . '/../includes/security_functions.php';
require_once __DIR__ . '/../includes/user_functions.php';
require_once __DIR__ . '/users.php';
// Aplicar preferencias del usuario automáticamente
if (!function_exists('applyUserPreferences')) {
    function applyUserPreferences() {
        return getUserPreferences();
    }
}

// Aplicar preferencias en cada carga
$preferences = applyUserPreferences();
?>