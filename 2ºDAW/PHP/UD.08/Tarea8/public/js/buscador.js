/**
 * @file buscador.js
 * @description Lógica del lado del cliente para la búsqueda asíncrona de libros.
 * Gestiona la validación de entrada y las peticiones AJAX al servidor Laravel.
 * * @project Tarea8
 * @author BenjaminDTS
 * @version 1.0.0
 * @requires jQuery 3.6.0+
 */

$(document).ready(function () {
  /**
   * Referencias a los elementos del DOM (Document Object Model).
   * Se almacenan en variables para evitar buscar en el DOM repetidamente (caché).
   * @type {jQuery}
   */
  const $inputTexto = $('#texto');
  const $resultadosDiv = $('#resultados');
  const $errorMsg = $('#error-msg');

  /**
   * Evento principal que detecta la escritura del usuario.
   * Se dispara cada vez que el usuario levanta una tecla dentro del campo de texto.
   *
   * @event keyup
   */
  $inputTexto.on('keyup', function () {
    // Obtenemos el valor actual del input
    let query = $(this).val();

    /**
     * 1. Verificación de campo vacío.
     * Si no hay texto, limpiamos los resultados y ocultamos errores.
     */
    if (query.length === 0) {
      $resultadosDiv.empty();
      $errorMsg.hide();
      return;
    }

    /**
     * 2. Validación en el Cliente (RA8_e).
     * Expresión regular para permitir únicamente letras (mayúsculas/minúsculas) y espacios.
     * @type {RegExp}
     */
    let soloLetras = /^[a-zA-Z\s]+$/;

    if (!soloLetras.test(query)) {
      // Si la validación falla, mostramos el mensaje de error y detenemos la ejecución.
      $errorMsg.show();
      return;
    } else {
      // Si es válido, ocultamos cualquier error previo.
      $errorMsg.hide();
    }

    /**
     * 3. Petición AJAX (RA8_f).
     * Envía los datos al controlador de Laravel sin recargar la página.
     */
    $.ajax({
      url: '/buscador/search',
      type: 'GET',
      data: { texto: query }, // Laravel recibirá esto como ?texto=valor
      dataType: 'json',

      /**
       * Se ejecuta si la petición al servidor es exitosa (HTTP 200).
       * * @param {Array<Object>} data - Array de objetos JSON con los libros encontrados.
       */
      success: function (data) {
        $resultadosDiv.empty(); // Limpiar resultados anteriores para no acumularlos

        if (data.length > 0) {
          // Recorremos el array de libros y generamos el HTML dinámicamente
          $.each(data, function (index, libro) {
            // Construcción de la tarjeta del libro
            let html = `
                            <div class="book-card">
                                <div class="book-title">${libro.titulo}</div>
                                <div class="book-author">Autor: ${libro.autor || 'Desconocido'}</div>
                            </div>
                        `;
            $resultadosDiv.append(html);
          });
        } else {
          // Feedback visual si la búsqueda no arroja resultados
          $resultadosDiv.html('<p style="color: #6b7280; text-align: center;">No se encontraron libros.</p>');
        }
      },

      /**
       * Se ejecuta si ocurre un error en la petición (ej. Error 500 o 422).
       * * @param {jqXHR} xhr - Objeto XMLHttpRequest.
       * @param {string} status - Estado del error.
       * @param {string} error - Descripción del error.
       */
      error: function (xhr, status, error) {
        console.error("Error en la petición AJAX:", error);
        // En caso de error (incluso si pasa el filtro JS pero falla en PHP), mostramos el mensaje.
        $errorMsg.show();
      }
    });
  });
});