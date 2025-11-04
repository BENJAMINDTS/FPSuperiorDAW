<?php
session_start();

// Datos en sesión (simula base de datos)
if (!isset($_SESSION['usuarios'])) {
    $_SESSION['usuarios'] = [
        [
            'id' => 1,
            'nombre' => 'Administrador',
            'email' => 'admin@test.com',
            'password' => password_hash('admin123', PASSWORD_DEFAULT),
            'telefono' => '123456789',
            'rol' => 'admin'
        ],
        [
            'id' => 2,
            'nombre' => 'Editor',
            'email' => 'editor@test.com',
            'password' => password_hash('editor123', PASSWORD_DEFAULT),
            'telefono' => '987654321',
            'rol' => 'editor'
        ]
    ];
}

// Clave para encriptar
define('ENCRYPT_KEY', 'mi_clave_secreta_32caracteres!!');

function encrypt($data) {
    if (empty($data)) return '';
    return openssl_encrypt($data, 'AES-256-CBC', ENCRYPT_KEY, 0, substr(ENCRYPT_KEY, 0, 16));
}

function decrypt($encrypted) {
    if (empty($encrypted)) return '';
    return openssl_decrypt($encrypted, 'AES-256-CBC', ENCRYPT_KEY, 0, substr(ENCRYPT_KEY, 0, 16));
}

function estaLogueado() {
    return isset($_SESSION['user_id']);
}

function requerirLogin() {
    if (!estaLogueado()) {
        header('Location: login.php');
        exit;
    }
}

function tieneRol($rol) {
    return estaLogueado() && $_SESSION['user_rol'] === $rol;
}

function requerirRol($rol) {
    requerirLogin();
    if (!tieneRol($rol)) {
        $_SESSION['error'] = "Acceso denegado";
        header('Location: dashboard.php');
        exit;
    }
}

function getUserInfo() {
    if (!estaLogueado()) return null;
    
    foreach ($_SESSION['usuarios'] as $usuario) {
        if ($usuario['id'] == $_SESSION['user_id']) {
            return $usuario;
        }
    }
    return null;
}
?>