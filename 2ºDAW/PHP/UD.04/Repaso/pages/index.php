<?php
require_once '../config/config.php';

if (!isAuthenticated()) {
    header('Location: login.php');
    exit;
}

// Aplicar preferencias del usuario
$preferences = applyUserPreferences();
$visitCount = incrementVisitCount();

// Contador de visitas en sesión
$_SESSION['visit_count'] = ($_SESSION['visit_count'] ?? 0) + 1;
?>
<!DOCTYPE html>
<html lang="<?php echo $preferences['language']; ?>" data-theme="<?php echo $preferences['theme']; ?>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio - Sistema de Autenticación</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/theme.js" defer></script>
</head>
<body>
    <div class="header">
        <div class="container">
            <div class="header-content">
                <h1>Sistema de Autenticación</h1>
                <div class="user-menu">
                    <span>Hola, <?php echo htmlspecialchars($_SESSION['user_id']); ?></span>
                    <a href="profile.php" class="btn btn-primary">Mi Perfil</a>
                    <a href="logout.php" class="btn btn-danger">Cerrar Sesión</a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="card">
            <h2>Bienvenido, <?php echo htmlspecialchars($_SESSION['user_id']); ?>!</h2>
            <p>Has iniciado sesión correctamente en el sistema.</p>
            
            <div class="stats-grid">
                <div class="stat-card">
                    <h3>Visitas en esta sesión</h3>
                    <p><?php echo $_SESSION['visit_count']; ?></p>
                </div>
                <div class="stat-card">
                    <h3>Visitas totales</h3>
                    <p><?php echo $visitCount; ?></p>
                </div>
                <div class="stat-card">
                    <h3>Tiempo de sesión</h3>
                    <p><?php echo floor((time() - $_SESSION['login_time']) / 60); ?> min</p>
                </div>
                <div class="stat-card">
                    <h3>Tu rol</h3>
                    <p><?php echo htmlspecialchars($_SESSION['user_role']); ?></p>
                </div>
            </div>
        </div>
        
        <?php if (isAdmin()): ?>
            <div class="admin-section">
                <h2>🔧 Panel de Administración</h2>
                <p>Bienvenido al área de administración. Desde aquí puedes gestionar usuarios, ver estadísticas del sistema y configurar opciones avanzadas.</p>
                <div class="mt-2">
                    <a href="admin.php" class="btn btn-warning">Ir al Panel de Admin</a>
                </div>
            </div>
        <?php endif; ?>
        
        <div class="grid grid-2">
            <div class="card">
                <h2>Preferencias de Usuario</h2>
                <form method="POST" action="../actions/update_preferences.php" id="preference-form">
                    <input type="hidden" name="csrf_token" value="<?php echo generateCSRFToken(); ?>">
                    
                    <div class="form-group">
                        <label for="theme">Tema:</label>
                        <select id="theme" name="theme" class="form-control">
                            <option value="light" <?php echo $preferences['theme'] === 'light' ? 'selected' : ''; ?>>🌞 Claro</option>
                            <option value="dark" <?php echo $preferences['theme'] === 'dark' ? 'selected' : ''; ?>>🌙 Oscuro</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="language">Idioma:</label>
                        <select id="language" name="language" class="form-control">
                            <option value="es" <?php echo $preferences['language'] === 'es' ? 'selected' : ''; ?>>🇪🇸 Español</option>
                            <option value="en" <?php echo $preferences['language'] === 'en' ? 'selected' : ''; ?>>🇺🇸 English</option>
                        </select>
                    </div>
                    
                    <button type="submit" class="btn btn-success">Guardar Preferencias</button>
                </form>
            </div>
            
            <div class="card">
                <h2>Accesos Rápidos</h2>
                <div style="display: flex; flex-direction: column; gap: 0.5rem;">
                    <a href="protected.php" class="btn btn-primary">🔒 Página Protegida</a>
                    <a href="profile.php" class="btn btn-primary">👤 Mi Perfil</a>
                    <?php if (isAdmin()): ?>
                        <a href="admin.php" class="btn btn-warning">⚙️ Panel de Administración</a>
                    <?php endif; ?>
                </div>
                
                <div class="mt-2">
                    <h3>Información de Sesión</h3>
                    <p><strong>Token:</strong> <code><?php echo substr($_SESSION['session_token'], 0, 16); ?>...</code></p>
                    <p><strong>IP:</strong> <?php echo htmlspecialchars($_SERVER['REMOTE_ADDR']); ?></p>
                    <p><strong>Inicio:</strong> <?php echo date('H:i:s', $_SESSION['login_time']); ?></p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>