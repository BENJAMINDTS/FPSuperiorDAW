/**
 * Script para manejo de formularios
 * Validaciones y funcionalidades específicas para formularios
 * @package JavaScript
 * @category Forms
 * @author Benjamín Santiago González
 * @since 2025
 */

$(document).ready(function () {
  // Inicializar contadores de caracteres
  initCharCounters();

  // Validación específica de email
  initEmailValidation();

  // Validación de contraseñas
  initPasswordValidation();

  // Manejo de formularios dinámicos
  initDynamicForms();
});

/**
 * Inicializa contadores de caracteres para textareas
 */
function initCharCounters() {
  $('textarea[maxlength]').each(function () {
    var textarea = $(this);
    var maxLength = parseInt(textarea.attr('maxlength'));
    var counterId = textarea.attr('id') + '-counter';

    // Crear contador si no existe
    if (!$('#' + counterId).length) {
      var counter = $('<div class="char-counter" id="' + counterId + '">0/' + maxLength + '</div>');
      textarea.after(counter);
    }

    // Actualizar contador al escribir
    textarea.on('input', function () {
      var length = $(this).val().length;
      var counter = $('#' + counterId);
      counter.text(length + '/' + maxLength);

      // Cambiar color según el porcentaje
      var percentage = (length / maxLength) * 100;
      counter.removeClass('warning danger');

      if (percentage > 80 && percentage < 90) {
        counter.addClass('warning');
      } else if (percentage >= 90) {
        counter.addClass('danger');
      }
    });

    // Actualizar inicialmente
    textarea.trigger('input');
  });
}

/**
 * Inicializa validación específica para campos de email
 */
function initEmailValidation() {
  $('input[type="email"]').on('blur', function () {
    var email = $(this).val().trim();
    var emailField = $(this);

    if (email && !isValidEmail(email)) {
      emailField.addClass('is-invalid');
      showFieldError(emailField, 'Por favor, ingrese un email válido.');
    } else {
      emailField.removeClass('is-invalid');
      hideFieldError(emailField);
    }
  });
}

/**
 * Inicializa validación para campos de contraseña
 */
function initPasswordValidation() {
  $('input[type="password"]').each(function () {
    var passwordField = $(this);

    // Validar fortaleza de contraseña
    passwordField.on('keyup', function () {
      var password = $(this).val();
      var strength = checkPasswordStrength(password);
      updatePasswordStrengthIndicator(passwordField, strength);
    });

    // Confirmación de contraseña
    if (passwordField.attr('id') === 'confirm_password') {
      var originalPasswordField = $('input[type="password"]').not('#confirm_password').first();

      passwordField.on('keyup', function () {
        var password = originalPasswordField.val();
        var confirmPassword = $(this).val();

        if (confirmPassword && password !== confirmPassword) {
          $(this).addClass('is-invalid');
          showFieldError($(this), 'Las contraseñas no coinciden.');
        } else {
          $(this).removeClass('is-invalid');
          hideFieldError($(this));
        }
      });
    }
  });
}

/**
 * Inicializa formularios dinámicos
 */
function initDynamicForms() {
  // Manejo de campos dinámicos (agregar/eliminar)
  $('.btn-agregar-campo').on('click', function (e) {
    e.preventDefault();
    var template = $(this).data('template');
    var container = $(this).data('container');
    var index = $(container + ' .campo-dinamico').length;

    var html = $(template).html();
    html = html.replace(/__index__/g, index);

    $(container).append(html);

    // Inicializar el nuevo campo
    initDynamicField($(container + ' .campo-dinamico').last());
  });

  // Eliminación de campos dinámicos
  $(document).on('click', '.btn-eliminar-campo', function (e) {
    e.preventDefault();
    $(this).closest('.campo-dinamico').remove();
  });

  // Prevenir envío duplicado de formularios
  $('form').on('submit', function () {
    var submitButton = $(this).find('button[type="submit"]');
    submitButton.prop('disabled', true).html('<i class="fas fa-spinner fa-spin me-2"></i>Procesando...');
  });
}

/**
 * Muestra un mensaje de error para un campo específico
 * @param {jQuery} field - Campo del formulario
 * @param {string} message - Mensaje de error
 */
function showFieldError(field, message) {
  var errorDiv = field.siblings('.invalid-feedback');
  if (errorDiv.length === 0) {
    errorDiv = $('<div class="invalid-feedback"></div>');
    field.after(errorDiv);
  }
  errorDiv.text(message).show();
}

/**
 * Oculta el mensaje de error de un campo
 * @param {jQuery} field - Campo del formulario
 */
function hideFieldError(field) {
  field.siblings('.invalid-feedback').hide();
}

/**
 * Verifica la fortaleza de una contraseña
 * @param {string} password - Contraseña a verificar
 * @returns {number} Puntuación de fortaleza (0-4)
 */
function checkPasswordStrength(password) {
  var score = 0;

  if (!password) return 0;

  // Longitud mínima
  if (password.length >= 8) score++;

  // Contiene mayúsculas
  if (/[A-Z]/.test(password)) score++;

  // Contiene minúsculas
  if (/[a-z]/.test(password)) score++;

  // Contiene números
  if (/[0-9]/.test(password)) score++;

  // Contiene caracteres especiales
  if (/[^A-Za-z0-9]/.test(password)) score++;

  return Math.min(score, 4); // Máximo 4
}

/**
 * Actualiza el indicador de fortaleza de contraseña
 * @param {jQuery} field - Campo de contraseña
 * @param {number} strength - Fortaleza (0-4)
 */
function updatePasswordStrengthIndicator(field, strength) {
  var indicator = field.siblings('.password-strength');
  if (indicator.length === 0) {
    indicator = $('<div class="password-strength mt-2"></div>');
    field.after(indicator);
  }

  var strengthText = ['Muy débil', 'Débil', 'Regular', 'Fuerte', 'Muy fuerte'];
  var strengthColors = ['danger', 'warning', 'info', 'success', 'success'];

  indicator.html(`
        <div class="progress" style="height: 5px;">
            <div class="progress-bar bg-${strengthColors[strength]}" 
                 style="width: ${(strength / 4) * 100}%"></div>
        </div>
        <small class="text-${strengthColors[strength]}">${strengthText[strength]}</small>
    `);
}

/**
 * Inicializa un campo dinámico recién agregado
 * @param {jQuery} field - Campo dinámico
 */
function initDynamicField(field) {
  // Inicializar cualquier funcionalidad específica
  field.find('input[type="email"]').each(function () {
    $(this).on('blur', function () {
      var email = $(this).val().trim();
      if (email && !isValidEmail(email)) {
        $(this).addClass('is-invalid');
      } else {
        $(this).removeClass('is-invalid');
      }
    });
  });

  // Inicializar contadores de caracteres
  field.find('textarea[maxlength]').each(function () {
    $(this).on('input', function () {
      var maxLength = parseInt($(this).attr('maxlength'));
      var length = $(this).val().length;
      $(this).siblings('.char-counter').text(length + '/' + maxLength);
    });
  });
}