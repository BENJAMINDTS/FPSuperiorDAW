<?php

/**
 * Vista: Lista de Usuarios
 * Muestra todos los usuarios registrados
 * @package Views
 * @category Usuario
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

$titulo = 'Usuarios';
$icono = 'users';
$accion = [
  'url' => '/daw/usuario/crear',  // ← CAMBIADO de 'registrar' a 'crear'
  'icono' => 'user-plus',
  'texto' => 'Nuevo Usuario'
];
?>
<!-- Contenido específico -->
<div class="card">
  <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
    <h5 class="card-title mb-0">
      <i class="fas fa-users me-2"></i>Usuarios Registrados
    </h5>
    <!-- BOTÓN PARA CREAR NUEVO USUARIO -->
    <a href="/daw/usuario/crear" class="btn btn-light btn-sm">  <!-- ← CAMBIADO -->
      <i class="fas fa-user-plus me-1"></i> Nuevo Usuario
    </a>
  </div>
  <div class="card-body">
    <?php if (empty($usuarios)): ?>
      <div class="alert alert-info">
        <i class="fas fa-info-circle me-2"></i>
        No hay usuarios registrados.
        <a href="/daw/usuario/crear" class="alert-link">Registrar el primero</a>  <!-- ← CAMBIADO -->
      </div>
    <?php else: ?>
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Email</th>
              <th>Teléfono</th>
              <th>Fecha Registro</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <?php foreach ($usuarios as $usuario): ?>
              <tr>
                <td><?php echo $usuario['id']; ?></td>
                <td><?php echo htmlspecialchars($usuario['nombre']); ?></td>
                <td><?php echo htmlspecialchars($usuario['email']); ?></td>
                <td>
                  <?php echo !empty($usuario['telefono']) ? htmlspecialchars($usuario['telefono']) : '<span class="text-muted">No especificado</span>'; ?>
                </td>
                <td><?php echo date('d/m/Y', strtotime($usuario['fecha_registro'])); ?></td>
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
    Total: <?php echo count($usuarios); ?> usuarios
  </div>
</div>