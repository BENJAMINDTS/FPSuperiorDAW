<?php
require_once(__DIR__ . '/../../config/database.php');
require_once(__DIR__ . '/../../models/estudiantes.php');
require_once(__DIR__ . '/../../controllers/EstudiantesController.php');

// Crear instancias
$estudiantesController = new EstudiantesController($connection);

$mensaje = '';
$error = '';

// Procesar el formulario cuando se envía
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    try {
        $dni = $_POST['dni'] ?? '';
        $nombre = $_POST['nombre'] ?? '';
        $email = $_POST['email'] ?? '';
        
        // Validaciones básicas
        if (empty($dni) || empty($nombre) || empty($email)) {
            $error = "DNI, nombre y email son campos obligatorios";
        } else {
            // Agregar estudiante usando el controlador
            $resultado = $estudiantesController->addEstudiante($dni, $nombre, $email);
            
            if ($resultado) {
                $mensaje = "Estudiante agregado correctamente";
                // Limpiar los campos después de guardar
                $_POST = array();
            } else {
                $error = "Error al agregar el estudiante";
            }
        }
    } catch (PDOException $e) {
        if ($e->getCode() == 23000) { // Error de duplicado (clave única)
            $error = "El DNI o email ya existen en la base de datos";
        } else {
            $error = "Error al agregar estudiante: " . $e->getMessage();
        }
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Estudiante</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 600px;
            margin-top: 50px;
        }
        .btn-back {
            margin-bottom: 20px;
        }
        .required::after {
            content: " *";
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="list.php" class="btn btn-secondary btn-back">← Volver a la lista</a>
        
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h2 class="text-center mb-0">Agregar Nuevo Estudiante</h2>
            </div>
            <div class="card-body">
                <?php if ($mensaje): ?>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <?php echo $mensaje; ?>
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                <?php endif; ?>
                
                <?php if ($error): ?>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <?php echo $error; ?>
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                <?php endif; ?>
                
                <form method="POST" action="">
                    <div class="mb-3">
                        <label for="dni" class="form-label required">DNI</label>
                        <input type="text" class="form-control" id="dni" name="dni" 
                               value="<?php echo htmlspecialchars($_POST['dni'] ?? ''); ?>" 
                               pattern="[0-9]{8}" 
                               title="El DNI debe tener 8 dígitos" 
                               required>
                        <div class="form-text">Ingrese 8 dígitos del DNI</div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="nombre" class="form-label required">Nombre Completo</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" 
                               value="<?php echo htmlspecialchars($_POST['nombre'] ?? ''); ?>" 
                               required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="email" class="form-label required">Email</label>
                        <input type="email" class="form-control" id="email" name="email" 
                               value="<?php echo htmlspecialchars($_POST['email'] ?? ''); ?>" 
                               required>
                    </div>
                    
                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-primary btn-lg">Agregar Estudiante</button>
                        <a href="list.php" class="btn btn-outline-secondary">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Validación adicional en el cliente
        document.getElementById('dni').addEventListener('input', function(e) {
            this.value = this.value.replace(/[^0-9]/g, '');
        });
    </script>
</body>
</html>