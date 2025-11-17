<?php
// Funciones de autenticación
// Verificar si las funciones ya están declaradas

if (!function_exists('isAuthenticated')) {

function isAuthenticated() {
    if (!isset($_SESSION['user_id']) || !isset($_SESSION['login_time'])) {
        return false;
    }
    
    // Verificar tiempo de expiración de sesión
    if (time() - $_SESSION['login_time'] > SESSION_TIMEOUT) {
        session_destroy();
        return false;
    }
    
    // Verificar IP (protección adicional)
    if (isset($_SESSION['user_ip']) && $_SESSION['user_ip'] !== $_SERVER['REMOTE_ADDR']) {
        session_destroy();
        return false;
    }
    
    // Actualizar tiempo de sesión
    $_SESSION['login_time'] = time();
    return true;
}

function loginUser($username, $password, $remember = false) {
    global $users;
    
    // Verificar intentos fallidos
    if (isset($_SESSION['login_attempts']) && $_SESSION['login_attempts'] >= MAX_LOGIN_ATTEMPTS) {
        if (isset($_SESSION['last_attempt_time']) &&
            (time() - $_SESSION['last_attempt_time']) < LOGIN_TIMEOUT) {
            return 'Demasiados intentos fallidos. Espere ' . ceil((LOGIN_TIMEOUT - (time() - $_SESSION['last_attempt_time'])) / 60) . ' minutos.';
        } else {
            // Resetear contador si ha pasado el tiempo de bloqueo
            unset($_SESSION['login_attempts']);
        }
    }
    
    if (!isset($users[$username]) || !password_verify($password, $users[$username]['password'])) {
        // Incrementar contador de intentos fallidos
        $_SESSION['login_attempts'] = ($_SESSION['login_attempts'] ?? 0) + 1;
        $_SESSION['last_attempt_time'] = time();
        return 'Usuario o contraseña incorrectos.';
    }
    
    // Login exitoso
    $_SESSION['user_id'] = $username;
    $_SESSION['user_role'] = $users[$username]['role'];
    $_SESSION['login_time'] = time();
    $_SESSION['user_ip'] = $_SERVER['REMOTE_ADDR'];
    $_SESSION['session_token'] = bin2hex(random_bytes(32));
    
    // Resetear contador de intentos fallidos
    unset($_SESSION['login_attempts']);
    
    // Recordar usuario si se solicita
    if ($remember) {
        setRememberMeCookie($username);
    }
    
    return true;
}

function setRememberMeCookie($username) {
    $token = bin2hex(random_bytes(32));
    $expiry = time() + (30 * 24 * 60 * 60); // 30 días
    
    setcookie('remember_me', $token . ':' . $username, [
        'expires' => $expiry,
        'path' => '/',
        'secure' => true,
        'httponly' => true,
        'samesite' => 'Strict'
    ]);
    
    // En un sistema real, guardaríamos el token en la base de datos
}

function isAdmin() {
    return isset($_SESSION['user_role']) && $_SESSION['user_role'] === 'admin';
}

function requireAuth() {
    if (!isAuthenticated()) {
        header('Location: /sistema-autenticacion/pages/login.php');
        exit;
    }
}

function requireAdmin() {
    requireAuth();
    if (!isAdmin()) {
        header('Location: /sistema-autenticacion/pages/index.php');
        exit;
    }
}

} // Fin del if !function_exists
?>