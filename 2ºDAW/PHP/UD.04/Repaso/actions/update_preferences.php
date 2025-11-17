<?php
require_once '../config/config.php';

if (!isAuthenticated()) {
    header('Location: ../pages/login.php');
    exit;
}

$success = false;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $csrf_token = $_POST['csrf_token'] ?? '';
    $theme = sanitizeInput($_POST['theme'] ?? '');
    $language = sanitizeInput($_POST['language'] ?? '');
    
    if (!verifyCSRFToken($csrf_token)) {
        $_SESSION['error'] = 'Token de seguridad inválido.';
    } else {
        // Validar y sanitizar valores
        $theme = in_array($theme, ['light', 'dark']) ? $theme : 'light';
        $language = in_array($language, ['es', 'en']) ? $language : 'es';
        
        $preferences = [
            'theme' => $theme,
            'language' => $language
        ];
        
        if (saveUserPreferences($preferences)) {
            $_SESSION['success'] = 'Preferencias actualizadas correctamente.';
            $success = true;
            
            // Aplicar cambios inmediatamente recargando la página
            header('Location: ' . $_SERVER['HTTP_REFERER'] ?: '../pages/index.php');
            exit;
        } else {
            $_SESSION['error'] = 'Error al guardar las preferencias.';
        }
    }
}

// Redirigir de vuelta
header('Location: ../pages/index.php');
exit;
?>