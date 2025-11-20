<?php
require_once(__DIR__ . '/../../config/database.php');
require_once(__DIR__ . '/../../models/estudiantes.php');
require_once(__DIR__ . '/../../controllers/EstudiantesController.php');

// Crear instancias
$estudiantesController = new EstudiantesController($connection);

$mensaje = '';
$error = '';
$estudiante = null;

// Obtener el DNI desde la URL
$dni = $_GET['dni'] ?? '';

// Verificar si se proporcionó un DNI
if (empty($dni)) {
    $error = "No se especificó el estudiante a eliminar";
    header("Location: list.php?error=" . urlencode($error));
    exit();
}

// Obtener información del estudiante para mostrar
try {
    $estudiantes = $estudiantesController->listEstudiantes();
    
    // Buscar el estudiante por DNI
    foreach ($estudiantes as $est) {
        if (isset($est['dni']) && $est['dni'] == $dni) {
            $estudiante = $est;
            break;
        }
    }

    if (!$estudiante) {
        $error = "No se encontró el estudiante con DNI: $dni";
    }
} catch (Exception $e) {
    $error = "Error al obtener información del estudiante: " . $e->getMessage();
}

// Procesar la eliminación cuando se confirma
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    try {
        $confirmacion = $_POST['confirmacion'] ?? '';
        
        if ($confirmacion === 'si') {
            $resultado = $estudiantesController->deleteEstudiante($dni);
            
            if ($resultado) {
                $mensaje = "Estudiante eliminado correctamente";
                // Redirigir después de 2 segundos
                header("Refresh: 2; URL=list.php?mensaje=" . urlencode($mensaje));
            } else {
                $error = "Error al eliminar el estudiante";
            }
        } else {
            // Si cancela, redirigir inmediatamente a la lista
            header("Location: list.php");
            exit();
        }
    } catch (PDOException $e) {
        $error = "Error al eliminar estudiante: " . $e->getMessage();
    }
}

// Si hay error y no es POST, redirigir
if ($error && $_SERVER['REQUEST_METHOD'] !== 'POST') {
    header("Location: list.php?error=" . urlencode($error));
    exit();
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eliminar Estudiante</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 600px;
            margin-top: 50px;
        }
        .btn-back {
            margin-bottom: 20px;
        }
        .card {
            border: none;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        .card-header {
            background: linear-gradient(135deg, #dc3545, #c82333);
            color: white;
        }
        .estudiante-info {
            background-color: #f8f9fa;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            border-left: 4px solid #dc3545;
        }
        .btn-confirm {
            background: linear-gradient(135deg, #dc3545, #c82333);
            border: none;
        }
        .btn-confirm:hover {
            background: linear-gradient(135deg, #c82333, #a71e2a);
            transform: translateY(-1px);
        }
        .alert-auto-close {
            background: linear-gradient(135deg, #28a745, #20c997);
            color: white;
            border: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="list.php" class="btn btn-secondary btn-back">← Volver a la lista</a>
        
        <div class="card">
            <div class="card-header">
                <h2 class="text-center mb-0">Eliminar Estudiante</h2>
            </div>
            <div class="card-body">
                <?php if ($mensaje): ?>
                    <div class="alert alert-auto-close alert-dismissible fade show text-center" role="alert">
                        <h4 class="alert-heading">¡Éxito!</h4>
                        <?php echo $mensaje; ?>
                        <p class="mb-0">Redirigiendo a la lista en 2 segundos...</p>
                        <div class="mt-3">
                            <a href="list.php" class="btn btn-light">Ir ahora</a>
                        </div>
                    </div>
                <?php endif; ?>
                
                <?php if ($error && $_SERVER['REQUEST_METHOD'] === 'POST'): ?>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <h5 class="alert-heading">Error</h5>
                        <?php echo $error; ?>
                        <div class="mt-2">
                            <a href="list.php" class="btn btn-primary">Volver a la lista</a>
                        </div>
                    </div>
                <?php endif; ?>
                
                <?php if ($estudiante && empty($mensaje)): ?>
                    <div class="alert alert-warning">
                        <h5 class="alert-heading">¡Advertencia!</h5>
                        <p class="mb-0">Está a punto de eliminar permanentemente un estudiante. Esta acción no se puede deshacer.</p>
                    </div>
                    
                    <div class="estudiante-info">
                        <h5 class="text-danger mb-3">
                            <i class="bi bi-person-x-fill"></i> Información del Estudiante a Eliminar
                        </h5>
                        <div class="row">
                            <div class="col-md-4">
                                <strong class="text-primary">DNI:</strong><br>
                                <span class="fs-5"><?php echo htmlspecialchars($estudiante['dni']); ?></span>
                            </div>
                            <div class="col-md-8">
                                <strong class="text-primary">Nombre:</strong><br>
                                <span class="fs-5"><?php echo htmlspecialchars($estudiante['nombre']); ?></span>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12">
                                <strong class="text-primary">Email:</strong><br>
                                <span class="fs-6"><?php echo htmlspecialchars($estudiante['email']); ?></span>
                            </div>
                        </div>
                    </div>
                    
                    <form method="POST" action="" id="deleteForm">
                        <div class="mb-4">
                            <label class="form-label fw-bold text-dark">¿Está seguro que desea eliminar este estudiante?</label>
                            <div class="form-check mb-2">
                                <input class="form-check-input" type="radio" name="confirmacion" id="confirmar_si" value="si" required>
                                <label class="form-check-label text-danger fw-bold" for="confirmar_si">
                                    <i class="bi bi-check-circle-fill"></i> Sí, eliminar este estudiante permanentemente
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="confirmacion" id="confirmar_no" value="no" required>
                                <label class="form-check-label text-success fw-bold" for="confirmar_no">
                                    <i class="bi bi-x-circle-fill"></i> No, conservar el estudiante
                                </label>
                            </div>
                        </div>
                        
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-confirm btn-lg py-3">
                                <i class="bi bi-trash-fill"></i> Confirmar Eliminación
                            </button>
                            <a href="list.php" class="btn btn-outline-secondary btn-lg py-3">
                                <i class="bi bi-arrow-left"></i> Cancelar y Volver
                            </a>
                        </div>
                    </form>
                <?php endif; ?>
            </div>
        </div>
    </div>

    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        // Confirmación adicional antes de enviar el formulario
        document.getElementById('deleteForm')?.addEventListener('submit', function(e) {
            const confirmacion = document.querySelector('input[name="confirmacion"]:checked');
            
            if (!confirmacion) {
                e.preventDefault();
                alert('Por favor, seleccione una opción de confirmación');
                return;
            }
            
            if (confirmacion.value === 'si') {
                const estudianteNombre = '<?php echo addslashes($estudiante["nombre"] ?? ""); ?>';
                const estudianteDni = '<?php echo addslashes($estudiante["dni"] ?? ""); ?>';
                
                if (!confirm(`¿ESTÁ ABSOLUTAMENTE SEGURO?\n\nESTA ACCIÓN NO SE PUEDE DESHACER\n\nSe eliminará permanentemente:\n• Estudiante: ${estudianteNombre}\n• DNI: ${estudianteDni}\n\n¿Continuar con la eliminación?`)) {
                    e.preventDefault();
                }
            }
        });

        // Auto-redirección después de eliminar exitosamente
        <?php if ($mensaje): ?>
            setTimeout(function() {
                window.location.href = 'list.php?mensaje=<?php echo urlencode($mensaje); ?>';
            }, 2000);
        <?php endif; ?>
    </script>
</body>
</html>