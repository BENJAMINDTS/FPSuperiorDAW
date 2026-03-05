<?php

/**
 * Vista: Formulario de Sugerencia
 * Formulario para crear nuevas sugerencias
 * @package Views
 * @category Sugerencia
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

$titulo = 'Nueva Sugerencia';
$icono = 'comment-medical';
$breadcrumb = 'Crear Sugerencia';
?>
<!-- Contenido específico -->
<div class="row justify-content-center">
  <div class="col-md-8">
    <div class="card">
      <div class="card-header bg-primary text-white">
        <h5 class="card-title mb-0">
          <i class="fas fa-comment-medical me-2"></i>Formulario de Sugerencia
        </h5>
      </div>
      <div class="card-body">
        <form action="/daw/sugerencia/guardar" method="POST">
          <div class="mb-3">
            <label for="nombre" class="form-label">Nombre completo *</label>
            <input type="text" class="form-control" id="nombre" name="nombre"
              required maxlength="100" placeholder="Ingrese su nombre">
          </div>

          <div class="mb-3">
            <label for="email" class="form-label">Correo electrónico *</label>
            <input type="email" class="form-control" id="email" name="email"
              required placeholder="ejemplo@correo.com">
          </div>

          <div class="mb-3">
            <label for="mensaje" class="form-label">Mensaje *</label>
            <textarea class="form-control" id="mensaje" name="mensaje"
              rows="5" required maxlength="500"
              placeholder="Escriba su sugerencia aquí..."></textarea>
            <div class="form-text">Máximo 500 caracteres</div>
          </div>

          <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a href="/daw/sugerencia/index" class="btn btn-secondary me-md-2">
              <i class="fas fa-times me-1"></i> Cancelar
            </a>
            <button type="submit" class="btn btn-primary">
              <i class="fas fa-paper-plane me-1"></i> Enviar Sugerencia
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>