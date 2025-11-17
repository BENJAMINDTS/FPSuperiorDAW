<?php
// Funciones de seguridad
// Verificar si las funciones ya están declaradas

if (!function_exists('generateCSRFToken')) {

function generateCSRFToken() {
    if (!isset($_SESSION['csrf_tokens'])) {
        $_SESSION['csrf_tokens'] = [];
    }
    
    $token = bin2hex(random_bytes(32));
    $_SESSION['csrf_tokens'][$token] = time() + CSRF_TOKEN_LIFETIME;
    
    // Limpiar tokens expirados
    foreach ($_SESSION['csrf_tokens'] as $storedToken => $expiry) {
        if ($expiry < time()) {
            unset($_SESSION['csrf_tokens'][$storedToken]);
        }
    }
    
    return $token;
}

function verifyCSRFToken($token) {
    if (!isset($_SESSION['csrf_tokens'][$token])) {
        return false;
    }
    
    if ($_SESSION['csrf_tokens'][$token] < time()) {
        unset($_SESSION['csrf_tokens'][$token]);
        return false;
    }
    
    unset($_SESSION['csrf_tokens'][$token]);
    return true;
}

function sanitizeInput($data) {
    return htmlspecialchars(trim($data), ENT_QUOTES, 'UTF-8');
}

function validateEmail($email) {
    return filter_var($email, FILTER_VALIDATE_EMAIL);
}

function checkSessionToken() {
    if (!isset($_SESSION['session_token'])) {
        return false;
    }
    return true;
}

} // Fin del if !function_exists
?>