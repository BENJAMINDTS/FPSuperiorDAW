<?php
require_once(__DIR__ . '/../../config/database.php');
require_once(__DIR__ . '/../../models/estudiantes.php');
require_once(__DIR__ . '/../../controllers/EstudiantesController.php');

// Crear instancias
$estudiantesController = new EstudiantesController($connection);

// Obtener todos los estudiantes
$estudiantes = $estudiantesController->listEstudiantes();

// Debug: Verificar lo que obtenemos
// echo "<pre>"; print_r($estudiantes); echo "</pre>";
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Estudiantes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 30px;
        }
        .table-responsive {
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        .btn-add {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="text-primary">Lista de Estudiantes</h1>
            <a href="add.php" class="btn btn-success btn-add">+ Agregar Estudiante</a>
        </div>

        <?php if (isset($_GET['mensaje'])): ?>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <?php echo htmlspecialchars($_GET['mensaje']); ?>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <?php endif; ?>

        <?php if (empty($estudiantes)): ?>
            <div class="alert alert-info text-center">
                <h4>No hay estudiantes registrados</h4>
                <p>Comienza agregando el primer estudiante.</p>
                <a href="add.php" class="btn btn-primary">Agregar Primer Estudiante</a>
            </div>
        <?php else: ?>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($estudiantes as $estudiante): ?>
                            <?php if (isset($estudiante['dni']) && isset($estudiante['nombre']) && isset($estudiante['email'])): ?>
                                <tr>
                                    <td><?php echo htmlspecialchars($estudiante['dni']); ?></td>
                                    <td><?php echo htmlspecialchars($estudiante['nombre']); ?></td>
                                    <td><?php echo htmlspecialchars($estudiante['email']); ?></td>
                                    <td>
                                        <a href="remove.php?dni=<?php echo $estudiante['dni']; ?>" 
                                           class="btn btn-danger btn-sm" 
                                           onclick="return confirm('¿Está seguro de eliminar este estudiante?')">
                                            Eliminar
                                        </a>
                                    </td>
                                </tr>
                            <?php endif; ?>
                        <?php endforeach; ?>
                    </tbody>
                </table>
            </div>
            
            <div class="mt-3 text-muted">
                Total de estudiantes: <?php echo count($estudiantes); ?>
            </div>
        <?php endif; ?>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>