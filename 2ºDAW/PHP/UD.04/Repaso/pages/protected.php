<?php
require_once '../config/config.php';
requireAuth(); // Requiere autenticación
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página Protegida - Sistema de Autenticación</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <div class="header-content">
                <h1>🔒 Área Protegida</h1>
                <div class="user-menu">
                    <span>Hola, <?php echo htmlspecialchars($_SESSION['user_id']); ?></span>
                    <a href="index.php" class="btn btn-primary">🏠 Inicio</a>
                    <a href="logout.php" class="btn btn-danger">🚪 Cerrar Sesión</a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="container container-sm">
        <div class="card fade-in">
            <h2>🎯 Contenido Exclusivo</h2>
            <p>Esta página está protegida y solo es accesible para usuarios autenticados. Has ingresado correctamente al sistema.</p>
            
            <div class="stats-grid mt-3">
                <div class="stat-card">
                    <h3>👤 Usuario</h3>
                    <p><?php echo htmlspecialchars($_SESSION['user_id']); ?></p>
                </div>
                <div class="stat-card">
                    <h3>🎭 Rol</h3>
                    <p><?php echo htmlspecialchars($_SESSION['user_role']); ?></p>
                </div>
                <div class="stat-card">
                    <h3>🕒 Sesión Activa</h3>
                    <p><?php echo floor((time() - $_SESSION['login_time']) / 60); ?> min</p>
                </div>
            </div>
        </div>
        
        <div class="card mt-3">
            <h2>🔐 Información de Seguridad</h2>
            <div class="grid grid-2">
                <div>
                    <h4>Token de Sesión</h4>
                    <p><code><?php echo htmlspecialchars($_SESSION['session_token']); ?></code></p>
                </div>
                <div>
                    <h4>Dirección IP</h4>
                    <p><?php echo htmlspecialchars($_SESSION['user_ip']); ?></p>
                </div>
                <div>
                    <h4>Hora de Inicio</h4>
                    <p><?php echo date('d/m/Y H:i:s', $_SESSION['login_time']); ?></p>
                </div>
                <div>
                    <h4>Tiempo Restante</h4>
                    <p><?php echo floor((SESSION_TIMEOUT - (time() - $_SESSION['login_time'])) / 60); ?> minutos</p>
                </div>
            </div>
        </div>
        
        <div class="card mt-3">
            <h2>📋 Características de Seguridad Implementadas</h2>
            <ul style="list-style: none; padding: 0;">
                <li style="padding: 0.5rem 0; border-bottom: 1px solid var(--border-color);">
                    ✅ <strong>Autenticación requerida</strong> - Solo usuarios logueados pueden acceder
                </li>
                <li style="padding: 0.5rem 0; border-bottom: 1px solid var(--border-color);">
                    ✅ <strong>Tokens CSRF</strong> - Protección contra ataques Cross-Site Request Forgery
                </li>
                <li style="padding: 0.5rem 0; border-bottom: 1px solid var(--border-color);">
                    ✅ <strong>Timeout de sesión</strong> - Expira después de 30 minutos de inactividad
                </li>
                <li style="padding: 0.5rem 0; border-bottom: 1px solid var(--border-color);">
                    ✅ <strong>Verificación de IP</strong> - La sesión está vinculada a tu dirección IP
                </li>
                <li style="padding: 0.5rem 0;">
                    ✅ <strong>Tokens únicos</strong> - Cada sesión tiene un token único de identificación
                </li>
            </ul>
        </div>
        
        <div class="text-center mt-4">
            <a href="index.php" class="btn btn-primary">🏠 Volver al Inicio</a>
            <a href="logout.php" class="btn btn-danger">🚪 Cerrar Sesión</a>
        </div>
    </div>
</body>
</html>