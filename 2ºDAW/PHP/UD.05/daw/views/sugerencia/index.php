<?php

/**
 * Vista: Lista de Sugerencias
 * Muestra todas las sugerencias registradas
 * @package Views
 * @category Sugerencia
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

$titulo = 'Sugerencias';
$icono = 'comment-dots';
$accion = [
  'url' => '/daw/sugerencia/crear',
  'icono' => 'plus',
  'texto' => 'Nueva Sugerencia'
];
?>
<!-- Contenido específico -->
<div class="card">
  <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
    <h5 class="card-title mb-0">
      <i class="fas fa-list me-2"></i>Lista de Sugerencias
    </h5>
    <!-- BOTÓN PARA CREAR NUEVA SUGERENCIA -->
    <a href="/daw/sugerencia/crear" class="btn btn-light btn-sm">
      <i class="fas fa-plus me-1"></i> Nueva Sugerencia
    </a>
  </div>
  <div class="card-body">
    <?php if (empty($sugerencias)): ?>
      <div class="alert alert-info">
        <i class="fas fa-info-circle me-2"></i>
        No hay sugerencias registradas.
        <a href="index.php?controller=sugerencia&action=crear" class="alert-link">Crear la primera</a>
      </div>
    <?php else: ?>
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Email</th>
              <th>Mensaje</th>
              <th>Fecha</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <?php foreach ($sugerencias as $sugerencia): ?>
              <tr>
                <td><?php echo $sugerencia['id']; ?></td>
                <td><?php echo htmlspecialchars($sugerencia['nombre']); ?></td>
                <td><?php echo htmlspecialchars($sugerencia['email']); ?></td>
                <td>
                  <?php
                  $mensaje = htmlspecialchars($sugerencia['mensaje']);
                  echo strlen($mensaje) > 50 ? substr($mensaje, 0, 50) . '...' : $mensaje;
                  ?>
                </td>
                <td><?php echo date('d/m/Y H:i', strtotime($sugerencia['fecha_creacion'])); ?></td>
                <td>
                  <a href="#" class="btn btn-sm btn-info" title="Ver">
                    <i class="fas fa-eye"></i>
                  </a>
                </td>
              </tr>
            <?php endforeach; ?>
          </tbody>
        </table>
      </div>
    <?php endif; ?>
  </div>
  <div class="card-footer text-muted">
    Total: <?php echo count($sugerencias); ?> sugerencias
  </div>
</div>