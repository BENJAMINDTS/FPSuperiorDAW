<?php
require_once '../config/config.php';

if (!isAuthenticated()) {
    header('Location: ../pages/login.php');
    exit;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $csrf_token = $_POST['csrf_token'] ?? '';
    $email = sanitizeInput($_POST['email'] ?? '');
    $name = sanitizeInput($_POST['name'] ?? '');
    
    if (!verifyCSRFToken($csrf_token)) {
        $_SESSION['error'] = 'Token de seguridad inválido.';
    } elseif (!validateEmail($email)) {
        $_SESSION['error'] = 'Por favor, introduce un email válido.';
    } elseif (empty($name)) {
        $_SESSION['error'] = 'El nombre no puede estar vacío.';
    } else {
        // En un sistema real, actualizaríamos en la base de datos
        $updateData = [
            'email' => $email,
            'name' => $name
        ];
        
        if (updateUserProfile($_SESSION['user_id'], $updateData)) {
            $_SESSION['success'] = 'Perfil actualizado correctamente.';
        } else {
            $_SESSION['error'] = 'Error al actualizar el perfil.';
        }
    }
}

header('Location: ../pages/profile.php');
exit;
?>