<?php

/**
 * Vista: Layout Footer
 * Pie de página con enlaces a JS separados
 * @package Views
 * @category Layouts
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}
?>
<!-- Fin del contenido principal -->
</div>
</div>
</div>

<!-- Footer -->
<footer class="mt-auto">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <h5>Sistema DAW</h5>
        <p>Aplicación web desarrollada con PHP MVC</p>
        <p class="text-muted small">
          <i class="fas fa-code me-1"></i> Desarrollado por Benjamín Santiago González
        </p>
      </div>
      <div class="col-md-6 text-md-end">
        <h5>Enlaces</h5>
        <ul class="list-unstyled">
          <li><a href="/daw/sugerencia/index" class="text-white">Sugerencias</a></li>
          <li><a href="/daw/usuario/index" class="text-white">Usuarios</a></li>
          <li><a href="/daw/producto/index" class="text-white">Productos</a></li>
        </ul>
      </div>
    </div>
    <hr class="bg-white">
    <div class="row">
      <div class="col-12 text-center">
        <p class="mb-0 small">
          &copy; <?php echo date('Y'); ?> Sistema DAW - Todos los derechos reservados
        </p>
      </div>
    </div>
  </div>
</footer>

<!-- Scripts comunes -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/daw/public/js/main.js"></script>

<!-- Scripts específicos de la página -->
<?php if (isset($js_extra)): ?>
  <script src="/daw/public/js/<?php echo $js_extra; ?>.js"></script>
<?php endif; ?>
</body>

</html>