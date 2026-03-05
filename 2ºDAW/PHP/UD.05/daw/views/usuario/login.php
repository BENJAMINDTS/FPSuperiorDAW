<?php

/**
 * Vista: Formulario de Login
 * Formulario para iniciar sesión en el sistema
 * @package Views
 * @category Usuario
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

$titulo = 'Iniciar Sesión';
$icono = 'sign-in-alt';
?>
<!-- Contenido específico -->
<div class="row justify-content-center">
  <div class="col-md-6 col-lg-4">
    <div class="card shadow">
      <div class="card-header bg-primary text-white text-center">
        <h4 class="card-title mb-0">
          <i class="fas fa-sign-in-alt me-2"></i>Iniciar Sesión
        </h4>
      </div>
      <div class="card-body">
        <form action="/daw/usuario/login" method="POST">
          <div class="mb-3">
            <label for="email" class="form-label">Correo electrónico</label>
            <div class="input-group">
              <span class="input-group-text">
                <i class="fas fa-envelope"></i>
              </span>
              <input type="email" class="form-control" id="email" name="email"
                required placeholder="usuario@ejemplo.com">
            </div>
          </div>

          <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <div class="input-group">
              <span class="input-group-text">
                <i class="fas fa-lock"></i>
              </span>
              <input type="password" class="form-control" id="password" name="password"
                required placeholder="Ingrese su contraseña">
            </div>
          </div>

          <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="remember" name="remember">
            <label class="form-check-label" for="remember">Recordar sesión</label>
          </div>

          <div class="d-grid">
            <button type="submit" class="btn btn-primary btn-lg">
              <i class="fas fa-sign-in-alt me-2"></i>Iniciar Sesión
            </button>
          </div>
        </form>

        <hr class="my-4">

        <div class="text-center">
          <p class="mb-2">¿No tienes una cuenta?</p>
          <a href="/daw/usuario/registrar" class="btn btn-outline-primary">
            <i class="fas fa-user-plus me-1"></i>Registrarse
          </a>
        </div>
      </div>
      <div class="card-footer text-center">
        <small class="text-muted">
          Sistema desarrollado para DAW &copy; <?php echo date('Y'); ?>
        </small>
      </div>
    </div>
  </div>
</div>