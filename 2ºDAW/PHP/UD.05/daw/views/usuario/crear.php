<?php

/**
 * Vista: Formulario de Registro de Usuario
 * Formulario para registrar nuevos usuarios
 * @package Views
 * @category Usuario
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

$titulo = 'Registro de Usuario';
$icono = 'user-plus';
$breadcrumb = 'Registrar Usuario';
?>
<!-- Contenido específico -->
<div class="row justify-content-center">
  <div class="col-md-8">
    <div class="card">
      <div class="card-header bg-primary text-white">
        <h5 class="card-title mb-0">
          <i class="fas fa-user-plus me-2"></i>Registro de Nuevo Usuario
        </h5>
      </div>
      <div class="card-body">
        <form action="/daw/usuario/guardar" method="POST">
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="nombre" class="form-label">Nombre completo *</label>
              <input type="text" class="form-control" id="nombre" name="nombre"
                required maxlength="100" placeholder="Ingrese su nombre">
            </div>

            <div class="col-md-6 mb-3">
              <label for="email" class="form-label">Correo electrónico *</label>
              <input type="email" class="form-control" id="email" name="email"
                required placeholder="ejemplo@correo.com">
            </div>
          </div>

          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="telefono" class="form-label">Teléfono</label>
              <input type="tel" class="form-control" id="telefono" name="telefono"
                placeholder="Ej: +34 600 123 456" maxlength="20">
            </div>

            <div class="col-md-6 mb-3">
              <label for="direccion" class="form-label">Dirección</label>
              <textarea class="form-control" id="direccion" name="direccion"
                rows="1" placeholder="Calle, número, ciudad"></textarea>
            </div>
          </div>

          <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a href="/daw/usuario/index" class="btn btn-secondary me-md-2">
              <i class="fas fa-times me-1"></i> Cancelar
            </a>
            <button type="submit" class="btn btn-primary">
              <i class="fas fa-user-check me-1"></i> Registrar Usuario
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>