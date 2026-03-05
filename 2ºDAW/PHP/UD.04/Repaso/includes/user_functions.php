<?php
// Funciones de usuario y preferencias
// Verificar si las funciones ya están declaradas

if (!function_exists('getUserPreferences')) {

function getUserPreferences() {
    $defaults = [
        'theme' => 'light',
        'language' => 'es',
        'visits' => 0
    ];
    
    if (isset($_COOKIE['user_preferences'])) {
        $preferences = json_decode($_COOKIE['user_preferences'], true);
        if (json_last_error() === JSON_ERROR_NONE && is_array($preferences)) {
            return array_merge($defaults, $preferences);
        }
    }
    
    return $defaults;
}

function saveUserPreferences($preferences) {
    $current = getUserPreferences();
    $updated = array_merge($current, $preferences);
    
    // Guardar en cookie por 1 año
    $result = setcookie('user_preferences', json_encode($updated), [
        'expires' => time() + (365 * 24 * 60 * 60),
        'path' => '/',
        'secure' => isset($_SERVER['HTTPS']), // Solo seguro si es HTTPS
        'httponly' => true,
        'samesite' => 'Strict'
    ]);
    
    // Actualizar inmediatamente en la sesión actual
    $_COOKIE['user_preferences'] = json_encode($updated);
    
    return $result;
}

function incrementVisitCount() {
    $preferences = getUserPreferences();
    $preferences['visits'] = ($preferences['visits'] ?? 0) + 1;
    saveUserPreferences($preferences);
    return $preferences['visits'];
}

function getUserInfo($username) {
    global $users;
    return $users[$username] ?? null;
}

function updateUserProfile($username, $data) {
    global $users;
    
    if (isset($users[$username])) {
        $users[$username] = array_merge($users[$username], $data);
        return true;
    }
    
    return false;
}

function applyUserPreferences() {
    $preferences = getUserPreferences();
    
    // Aplicar tema
    if (isset($preferences['theme'])) {
        // Esto se aplicará en el HTML via data-theme
    }
    
    // Aplicar idioma
    if (isset($preferences['language'])) {
        // Esto se aplicará en el HTML via lang attribute
    }
    
    return $preferences;
}

} // Fin del if !function_exists
?>