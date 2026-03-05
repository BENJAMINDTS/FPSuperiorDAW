/**
 * Script principal del sistema DAW
 * Funcionalidades comunes para todas las páginas
 * @package JavaScript
 * @category Main
 * @author Benjamín Santiago González
 * @since 2025
 */

/**
 * Inicializa la aplicación cuando el DOM está listo
 */
$(document).ready(function () {
  console.log('Sistema DAW cargado correctamente');

  // Configuración global
  initConfig();

  // Manejo de alertas
  initAlerts();

  // Manejo de confirmaciones
  initConfirmations();

  // Manejo de formularios
  initForms();
});

/**
 * Configuración inicial de la aplicación
 */
function initConfig() {
  // Deshabilitar el envío de formularios por Enter en campos de texto individuales
  $('input[type="text"]').on('keypress', function (e) {
    if (e.which === 13) {
      e.preventDefault();
      return false;
    }
  });

  // Activar tooltips de Bootstrap
  $('[data-bs-toggle="tooltip"]').tooltip();

  // Activar popovers de Bootstrap
  $('[data-bs-toggle="popover"]').popover();
}

/**
 * Manejo de alertas del sistema
 */
function initAlerts() {
  // Auto-ocultar alertas después de 5 segundos
  setTimeout(function () {
    $('.alert:not(.alert-permanent)').fadeOut('slow', function () {
      $(this).remove();
    });
  }, 5000);

  // Botón para cerrar alertas
  $('.alert .btn-close').on('click', function () {
    $(this).closest('.alert').fadeOut('slow', function () {
      $(this).remove();
    });
  });
}

/**
 * Manejo de confirmaciones para acciones destructivas
 */
function initConfirmations() {
  $('.btn-eliminar, .btn-danger[type="submit"]').on('click', function (e) {
    if (!confirm('¿Está seguro de realizar esta acción? Esta acción no se puede deshacer.')) {
      e.preventDefault();
      return false;
    }
  });

  $('.btn-cerrar-sesion').on('click', function (e) {
    if (!confirm('¿Está seguro de cerrar la sesión?')) {
      e.preventDefault();
      return false;
    }
  });
}

/**
 * Manejo básico de formularios
 */
function initForms() {
  // Validación básica en cliente
  $('form').on('submit', function (e) {
    var form = $(this);
    var required = form.find('[required]');
    var valid = true;

    required.each(function () {
      var field = $(this);
      var value = field.val().trim();

      if (!value) {
        valid = false;
        field.addClass('is-invalid');

        // Mostrar mensaje de error
        var errorDiv = field.siblings('.invalid-feedback');
        if (errorDiv.length === 0) {
          errorDiv = $('<div class="invalid-feedback">Este campo es obligatorio</div>');
          field.after(errorDiv);
        }
        errorDiv.show();
      } else {
        field.removeClass('is-invalid');
        field.siblings('.invalid-feedback').hide();
      }
    });

    if (!valid) {
      e.preventDefault();

      // Scroll al primer error
      var firstError = form.find('.is-invalid').first();
      if (firstError.length) {
        $('html, body').animate({
          scrollTop: firstError.offset().top - 100
        }, 500);
        firstError.focus();
      }

      // Mostrar alerta
      showAlert('Por favor, complete todos los campos obligatorios.', 'danger');
      return false;
    }

    // Mostrar indicador de carga
    $(this).addClass('loading');
  });
}

/**
 * Muestra una alerta en la página
 * @param {string} message - Mensaje a mostrar
 * @param {string} type - Tipo de alerta (success, danger, warning, info)
 * @param {number} timeout - Tiempo en milisegundos para auto-ocultar (0 para permanente)
 */
function showAlert(message, type = 'info', timeout = 5000) {
  var alertClass = 'alert-' + type;
  var icon = getAlertIcon(type);

  var alertHtml = `
        <div class="alert ${alertClass} alert-dismissible fade show" role="alert">
            <i class="${icon} me-2"></i>
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `;

  // Insertar al principio del contenedor principal
  $('.container > .row > .col-12').prepend(alertHtml);

  // Auto-ocultar si no es permanente
  if (timeout > 0) {
    setTimeout(function () {
      $('.alert').first().fadeOut('slow', function () {
        $(this).remove();
      });
    }, timeout);
  }
}

/**
 * Obtiene el icono correspondiente al tipo de alerta
 * @param {string} type - Tipo de alerta
 * @returns {string} Clase del icono
 */
function getAlertIcon(type) {
  switch (type) {
    case 'success':
      return 'fas fa-check-circle';
    case 'danger':
      return 'fas fa-exclamation-circle';
    case 'warning':
      return 'fas fa-exclamation-triangle';
    case 'info':
    default:
      return 'fas fa-info-circle';
  }
}

/**
 * Formatea una fecha para mostrar
 * @param {string} dateString - Fecha en formato ISO
 * @returns {string} Fecha formateada
 */
function formatDate(dateString) {
  var date = new Date(dateString);
  return date.toLocaleDateString('es-ES', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  });
}

/**
 * Valida un email
 * @param {string} email - Email a validar
 * @returns {boolean} True si es válido
 */
function isValidEmail(email) {
  var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return re.test(email);
}

/**
 * Formatea un número como precio
 * @param {number} amount - Cantidad
 * @param {string} currency - Moneda (€, $, etc.)
 * @returns {string} Precio formateado
 */
function formatPrice(amount, currency = '€') {
  return amount.toFixed(2).replace('.', ',') + ' ' + currency;
}