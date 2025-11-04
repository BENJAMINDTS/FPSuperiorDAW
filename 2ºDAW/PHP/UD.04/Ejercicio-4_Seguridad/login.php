<?php
require 'includes/config.php';

if ($_POST) {
    $email = trim($_POST['email']);
    $password = $_POST['password'];

    // Buscar usuario
    $usuarioEncontrado = null;
    foreach ($_SESSION['usuarios'] as $usuario) {
        if ($usuario['email'] === $email && password_verify($password, $usuario['password'])) {
            $usuarioEncontrado = $usuario;
            break;
        }
    }

    if ($usuarioEncontrado) {
        $_SESSION['user_id'] = $usuarioEncontrado['id'];
        $_SESSION['user_nombre'] = $usuarioEncontrado['nombre'];
        $_SESSION['user_email'] = $usuarioEncontrado['email'];
        $_SESSION['user_rol'] = $usuarioEncontrado['rol'];
        $_SESSION['user_telefono'] = $usuarioEncontrado['telefono'];
        
        $_SESSION['success'] = "¡Bienvenido " . $usuarioEncontrado['nombre'] . "!";
        header('Location: index.php');
        exit;
    } else {
        $error = "Email o contraseña incorrectos";
    }
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Iniciar Sesión</h1>
        
        <div class="nav">
            <ul>
                <li><a href="index.php">Inicio</a></li>
                <li><a href="registro.php">Registro</a></li>
            </ul>
        </div>

        <?php if (isset($error)): ?>
            <div class="message error"><?php echo $error; ?></div>
        <?php endif; ?>

        <form method="post">
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="<?php echo $_POST['email'] ?? ''; ?>" required>
            </div>
            
            <div class="form-group">
                <label>Contraseña:</label>
                <input type="password" name="password" required>
            </div>
            
            <button type="submit">Iniciar Sesión</button>
        </form>
        
        <p style="text-align: center; margin-top: 15px;">
            ¿No tienes cuenta? <a href="registro.php">Regístrate aquí</a>
        </p>

        <div class="message info">
            <h4>Usuarios de prueba:</h4>
            <p><strong>Admin:</strong> admin@test.com / admin123</p>
            <p><strong>Editor:</strong> editor@test.com / editor123</p>
        </div>
    </div>
</body>
</html>