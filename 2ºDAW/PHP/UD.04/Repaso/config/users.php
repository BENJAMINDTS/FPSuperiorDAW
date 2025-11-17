<?php
// Base de datos de usuarios simulada

$users = [
    'admin' => [
        'password' => password_hash('admin123', PASSWORD_DEFAULT),
        'role' => 'admin',
        'email' => 'admin@example.com',
        'name' => 'Administrador del Sistema'
    ],
    'usuario1' => [
        'password' => password_hash('user123', PASSWORD_DEFAULT),
        'role' => 'user',
        'email' => 'usuario1@example.com',
        'name' => 'Juan Pérez'
    ],
    'usuario2' => [
        'password' => password_hash('user456', PASSWORD_DEFAULT),
        'role' => 'user',
        'email' => 'usuario2@example.com',
        'name' => 'María García'
    ],
    'manager' => [
        'password' => password_hash('manager123', PASSWORD_DEFAULT),
        'role' => 'admin',
        'email' => 'manager@example.com',
        'name' => 'Gerente General'
    ]
];
?>