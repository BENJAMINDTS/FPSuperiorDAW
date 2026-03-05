<?php
require_once '../config/config.php';
requireAuth();

$error = '';
$success = '';

// Obtener información del usuario actual
$userInfo = getUserInfo($_SESSION['user_id']);
$preferences = getUserPreferences();

// Procesar actualización de perfil
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $csrf_token = $_POST['csrf_token'] ?? '';
    $email = sanitizeInput($_POST['email'] ?? '');
    $name = sanitizeInput($_POST['name'] ?? '');
    
    if (!verifyCSRFToken($csrf_token)) {
        $error = 'Token de seguridad inválido.';
    } elseif (!validateEmail($email)) {
        $error = 'Por favor, introduce un email válido.';
    } elseif (empty($name)) {
        $error = 'El nombre no puede estar vacío.';
    } else {
        // En un sistema real, actualizaríamos en la base de datos
        $updateData = [
            'email' => $email,
            'name' => $name
        ];
        
        if (updateUserProfile($_SESSION['user_id'], $updateData)) {
            $success = 'Perfil actualizado correctamente.';
            $userInfo = getUserInfo($_SESSION['user_id']); // Actualizar info local
        } else {
            $error = 'Error al actualizar el perfil.';
        }
    }
}

$csrf_token = generateCSRFToken();
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil - Sistema de Autenticación</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <div class="header-content">
                <h1>👤 Mi Perfil</h1>
                <div class="user-menu">
                    <span>Hola, <?php echo htmlspecialchars($_SESSION['user_id']); ?></span>
                    <a href="index.php" class="btn btn-primary">🏠 Inicio</a>
                    <a href="logout.php" class="btn btn-danger">🚪 Cerrar Sesión</a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="container container-sm">
        <?php if ($error): ?>
            <div class="alert alert-error">
                <?php echo htmlspecialchars($error); ?>
            </div>
        <?php endif; ?>
        
        <?php if ($success): ?>
            <div class="alert alert-success">
                <?php echo htmlspecialchars($success); ?>
            </div>
        <?php endif; ?>

        <div class="card fade-in">
            <h2>📊 Información del Perfil</h2>
            <div class="grid grid-2">
                <div class="stat-card">
                    <h3>Usuario</h3>
                    <p><?php echo htmlspecialchars($_SESSION['user_id']); ?></p>
                </div>
                <div class="stat-card">
                    <h3>Rol</h3>
                    <p>
                        <span class="badge <?php echo $_SESSION['user_role'] === 'admin' ? 'badge-warning' : 'badge-primary'; ?>">
                            <?php echo htmlspecialchars($_SESSION['user_role']); ?>
                        </span>
                    </p>
                </div>
                <div class="stat-card">
                    <h3>Miembro desde</h3>
                    <p><?php echo date('d/m/Y', strtotime('2024-01-01')); ?></p>
                </div>
                <div class="stat-card">
                    <h3>Estado</h3>
                    <p><span class="badge badge-success">Activo</span></p>
                </div>
            </div>
        </div>

        <div class="card mt-3">
            <h2>✏️ Editar Información Personal</h2>
            <form method="POST">
                <input type="hidden" name="csrf_token" value="<?php echo htmlspecialchars($csrf_token); ?>">
                
                <div class="form-group">
                    <label for="username">Nombre de Usuario:</label>
                    <input type="text" id="username" class="form-control" 
                           value="<?php echo htmlspecialchars($_SESSION['user_id']); ?>" disabled>
                    <small style="color: var(--gray-600);">El nombre de usuario no se puede cambiar</small>
                </div>
                
                <div class="form-group">
                    <label for="name">Nombre Completo:</label>
                    <input type="text" id="name" name="name" class="form-control" 
                           value="<?php echo htmlspecialchars($userInfo['name'] ?? ''); ?>" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Correo Electrónico:</label>
                    <input type="email" id="email" name="email" class="form-control" 
                           value="<?php echo htmlspecialchars($userInfo['email'] ?? ''); ?>" required>
                </div>
                
                <div class="form-group">
                    <label for="role">Rol:</label>
                    <input type="text" id="role" class="form-control" 
                           value="<?php echo htmlspecialchars($_SESSION['user_role']); ?>" disabled>
                </div>
                
                <button type="submit" class="btn btn-success">💾 Guardar Cambios</button>
            </form>
        </div>

        <div class="card mt-3">
            <h2>📈 Estadísticas Personales</h2>
            <div class="grid grid-3">
                <div class="stat-card">
                    <h3>Visitas Totales</h3>
                    <p><?php echo $preferences['visits']; ?></p>
                </div>
                <div class="stat-card">
                    <h3>Sesiones Activas</h3>
                    <p><?php echo $_SESSION['visit_count'] ?? 1; ?></p>
                </div>
                <div class="stat-card">
                    <h3>Preferencia</h3>
                    <p><?php echo $preferences['theme'] === 'dark' ? '🌙 Oscuro' : '🌞 Claro'; ?></p>
                </div>
            </div>
        </div>

        <div class="card mt-3">
            <h2>🔐 Seguridad de la Cuenta</h2>
            <div class="grid grid-2">
                <div>
                    <h4>Cambio de Contraseña</h4>
                    <p>Actualiza tu contraseña regularmente para mantener la seguridad de tu cuenta.</p>
                    <button class="btn btn-warning mt-1">🔑 Cambiar Contraseña</button>
                </div>
                <div>
                    <h4>Sesión Actual</h4>
                    <p><strong>Iniciada:</strong> <?php echo date('d/m/Y H:i:s', $_SESSION['login_time']); ?></p>
                    <p><strong>IP:</strong> <?php echo htmlspecialchars($_SESSION['user_ip']); ?></p>
                    <p><strong>Expira en:</strong> <?php echo floor((SESSION_TIMEOUT - (time() - $_SESSION['login_time'])) / 60); ?> minutos</p>
                </div>
            </div>
        </div>

        <div class="text-center mt-4">
            <a href="index.php" class="btn btn-primary">🏠 Volver al Inicio</a>
            <?php if (isAdmin()): ?>
                <a href="admin.php" class="btn btn-warning">⚙️ Panel de Admin</a>
            <?php endif; ?>
        </div>
    </div>
</body>
</html>