<?php
require 'includes/config.php';

if ($_POST) {
    $nombre = trim($_POST['nombre']);
    $email = trim($_POST['email']);
    $password = $_POST['password'];
    $telefono = trim($_POST['telefono']);
    
    // Verificar si email ya existe
    $emailExiste = false;
    foreach ($_SESSION['usuarios'] as $usuario) {
        if ($usuario['email'] === $email) {
            $emailExiste = true;
            break;
        }
    }
    
    if ($emailExiste) {
        $error = "El email ya está registrado";
    } else {
        // Encriptar teléfono
        $telefono_encriptado = encrypt($telefono);
        
        // Crear nuevo usuario
        $nuevoUsuario = [
            'id' => count($_SESSION['usuarios']) + 1,
            'nombre' => $nombre,
            'email' => $email,
            'password' => password_hash($password, PASSWORD_DEFAULT),
            'telefono' => $telefono_encriptado,
            'rol' => 'usuario'
        ];
        
        $_SESSION['usuarios'][] = $nuevoUsuario;
        $_SESSION['success'] = "Usuario registrado correctamente";
        header('Location: login.php');
        exit;
    }
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Registro de Usuario</h1>
        
        <div class="nav">
            <ul>
                <li><a href="index.php">Inicio</a></li>
                <li><a href="login.php">Login</a></li>
            </ul>
        </div>

        <?php if (isset($error)): ?>
            <div class="message error"><?php echo $error; ?></div>
        <?php endif; ?>

        <form method="post">
            <div class="form-group">
                <label>Nombre completo:</label>
                <input type="text" name="nombre" value="<?php echo $_POST['nombre'] ?? ''; ?>" required>
            </div>
            
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="<?php echo $_POST['email'] ?? ''; ?>" required>
            </div>
            
            <div class="form-group">
                <label>Contraseña:</label>
                <input type="password" name="password" required>
            </div>
            
            <div class="form-group">
                <label>Teléfono:</label>
                <input type="text" name="telefono" value="<?php echo $_POST['telefono'] ?? ''; ?>">
            </div>
            
            <button type="submit">Registrarse</button>
        </form>
        
        <p style="text-align: center; margin-top: 15px;">
            ¿Ya tienes cuenta? <a href="login.php">Inicia sesión aquí</a>
        </p>
    </div>
</body>
</html>