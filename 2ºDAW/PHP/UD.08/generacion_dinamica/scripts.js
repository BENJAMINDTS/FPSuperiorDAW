/**
 * Script principal para la resolución de ejercicios de jQuery y Ajax.
 * @author Gemini
 * @version 1.0
 */

$(document).ready(function () {

  /**
   * Ejercicio 1: Actualización de encabezado.
   */
  $('#btn-ejercicio1').on('click', function () {
    $('#titulo-principal').text('Hola, usuario');
  });

  /**
   * Ejercicio 2: Creación de lista dinámica.
   */
  $('#btn-agregar-lista').on('click', function () {
    const input = $('#input-tarea');
    const valor = input.val().trim();

    if (valor !== "") {
      $('#lista-dinamica').append(`<li>${valor}</li>`);
      input.val(''); // Limpia el campo
    }
  });

  /**
   * Ejercicio 3: Generación de tarjetas dinámicas.
   */
  $('#btn-tarjeta').on('click', function () {
    const htmlTarjeta = `
            <div class="tarjeta">
                <h3>Card Dinámica</h3>
                <p>Generada automáticamente mediante jQuery.</p>
            </div>`;
    $('#contenedor-tarjetas').append(htmlTarjeta);
  });

  /**
   * Ejercicio 4: Inserción en posición específica.
   */
  $('#btn-insertar-especifico').on('click', function () {
    $('<p class="p-bloque" style="color:green;">Nuevo párrafo (Insertado)</p>')
      .insertAfter('#contenedor-parrafos p:first');
  });

  /**
   * Ejercicio 5: Eliminación del último elemento.
   */
  $('#btn-eliminar-ultimo').on('click', function () {
    $('#lista-dinamica li:last').remove();
  });

  /**
   * Ejercicio 6: Cambio dinámico de estilo.
   */
  $('#btn-cambiar-estilo').on('click', function () {
    $('#parrafo-estilo').css({
      'color': '#ffffff',
      'background-color': '#2c3e50',
      'padding': '15px',
      'border-radius': '5px'
    });
  });

  /**
   * Ejercicio 7: Contador dinámico.
   */
  $('#btn-incrementar').on('click', function () {
    let actual = parseInt($('#visor-contador').text());
    $('#visor-contador').text(actual + 1);
  });

  /**
   * Ejercicio 8: Uso de API pública con $.get()
   * Carga una lista de usuarios de JSONPlaceholder.
   */
  $('#btn-cargar-ajax').on('click', function () {
    const output = $('#output-ajax');
    output.html('<p>Cargando datos...</p>');

    $.get('https://jsonplaceholder.typicode.com/users', function (data) {
      output.empty();
      let listaHtml = '<ul>';
      data.slice(0, 5).forEach(user => {
        listaHtml += `<li><strong>${user.name}</strong> - ${user.email}</li>`;
      });
      listaHtml += '</ul>';
      output.append(listaHtml);
    }).fail(function () {
      output.html('<p style="color:red;">Error al conectar con la API.</p>');
    });
  });

});