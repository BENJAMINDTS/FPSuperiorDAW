<?php
require_once '../config/config.php';

// Verificar si ya está autenticado
if (isAuthenticated()) {
    header('Location: index.php');
    exit;
}

$error = '';
$remember = false;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = sanitizeInput($_POST['username'] ?? '');
    $password = $_POST['password'] ?? '';
    $remember = isset($_POST['remember']);
    $csrf_token = $_POST['csrf_token'] ?? '';
    
    if (!verifyCSRFToken($csrf_token)) {
        $error = 'Token de seguridad inválido. Por favor, recarga la página.';
    } else {
        $result = loginUser($username, $password, $remember);
        
        if ($result === true) {
            incrementVisitCount();
            header('Location: index.php');
            exit;
        } else {
            $error = $result;
        }
    }
}

$csrf_token = generateCSRFToken();
?>
<?php
require_once '../config/config.php';

// Aplicar preferencias incluso en login
$preferences = applyUserPreferences();

if (isAuthenticated()) {
    header('Location: index.php');
    exit;
}


?>
<!DOCTYPE html>
<html lang="<?php echo $preferences['language']; ?>" data-theme="<?php echo $preferences['theme']; ?>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Sistema de Autenticación</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/login.css">
</head>
<body>
    <div class="login-container">
        <div class="login-form">
            <h2>Iniciar Sesión</h2>
            
            <?php if ($error): ?>
                <div class="alert alert-error">
                    <?php echo htmlspecialchars($error); ?>
                </div>
            <?php endif; ?>
            
            <form method="POST">
                <input type="hidden" name="csrf_token" value="<?php echo htmlspecialchars($csrf_token); ?>">
                
                <div class="form-group">
                    <label for="username">Usuario:</label>
                    <input type="text" id="username" name="username" class="form-control" 
                           value="<?php echo htmlspecialchars($_POST['username'] ?? ''); ?>" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                
                <div class="form-options">
                    <div class="checkbox-group">
                        <input type="checkbox" id="remember" name="remember" <?php echo $remember ? 'checked' : ''; ?>>
                        <label for="remember">Recordarme</label>
                    </div>
                    
                    <a href="#" class="forgot-password">¿Olvidó su contraseña?</a>
                </div>
                
                <button type="submit" class="btn btn-primary login-btn">Iniciar Sesión</button>
            </form>
            
            <div class="login-links">
                <a href="index.php">← Volver al inicio</a>
            </div>
        </div>
    </div>
</body>
</html>