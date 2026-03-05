/**
 * @fileoverview Script principal para la resolución de ejercicios de jQuery y Ajax.
 * @author BenjaminDTS
 * @version 1.0
 */

// ==========================================
// Funciones de los Ejercicios
// ==========================================

/**
 * @description Ejercicio 1: Actualiza el texto del encabezado principal al ser invocado.
 * @function
 */
function actualizarEncabezado() {
  $('#titulo-principal').text('Hola, usuario');
}

/**
 * @description Ejercicio 2: Lee el valor del campo de texto y añade un nuevo 
 * elemento de lista (<li>) a la lista dinámica si el campo no está vacío. 
 * Posteriormente, limpia el campo de texto.
 * @function
 */
function agregarElementoLista() {
  const input = $('#input-tarea');
  const valor = input.val().trim();

  if (valor !== "") {
    $('#lista-dinamica').append(`<li>${valor}</li>`);
    input.val(''); // Limpia el campo
  }
}

/**
 * @description Ejercicio 3: Genera e inyecta dinámicamente el HTML de una 
 * nueva tarjeta en el contenedor correspondiente.
 * @function
 */
function generarTarjetaDinamica() {
  const htmlTarjeta = `
    <div class="tarjeta">
        <h3>Card Dinámica</h3>
        <p>Generada automáticamente mediante jQuery.</p>
    </div>`;
  $('#contenedor-tarjetas').append(htmlTarjeta);
}

/**
 * @description Ejercicio 4: Crea un nuevo párrafo con estilos específicos y lo inserta 
 * justo después del primer párrafo encontrado dentro del contenedor.
 * @function
 */
function insertarParrafoEspecifico() {
  $('<p class="p-bloque" style="color:green;">Nuevo párrafo (Insertado)</p>')
    .insertAfter('#contenedor-parrafos p:first');
}

/**
 * @description Ejercicio 5: Elimina el último elemento (<li>) presente en la lista dinámica.
 * @function
 */
function eliminarUltimoElemento() {
  $('#lista-dinamica li:last').remove();
}

/**
 * @description Ejercicio 6: Modifica las propiedades CSS del párrafo seleccionado, 
 * cambiando su color, fondo, relleno y borde.
 * @function
 */
function cambiarEstiloDinamico() {
  $('#parrafo-estilo').css({
    'color': '#ffffff',
    'background-color': '#2c3e50',
    'padding': '15px',
    'border-radius': '5px'
  });
}

/**
 * @description Ejercicio 7: Lee el valor actual del visor del contador, 
 * lo convierte a número entero, lo incrementa en uno y actualiza la vista.
 * @function
 */
function incrementarContador() {
  let actual = parseInt($('#visor-contador').text());
  $('#visor-contador').text(actual + 1);
}

/**
 * @description Ejercicio 8: Realiza una petición AJAX (GET) a JSONPlaceholder 
 * para obtener una lista de usuarios. Muestra un estado de carga, procesa 
 * los primeros 5 resultados y los renderiza en una lista. Maneja posibles errores.
 * @function
 */
function cargarUsuariosAjax() {
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
}

// ==========================================
// Inicialización de Eventos
// ==========================================

/**
 * @description Se ejecuta cuando el DOM está completamente cargado. 
 * Se encarga de vincular todos los botones con sus respectivas funciones.
 */
$(document).ready(function () {
  $('#btn-ejercicio1').on('click', actualizarEncabezado);
  $('#btn-agregar-lista').on('click', agregarElementoLista);
  $('#btn-tarjeta').on('click', generarTarjetaDinamica);
  $('#btn-insertar-especifico').on('click', insertarParrafoEspecifico);
  $('#btn-eliminar-ultimo').on('click', eliminarUltimoElemento);
  $('#btn-cambiar-estilo').on('click', cambiarEstiloDinamico);
  $('#btn-incrementar').on('click', incrementarContador);
  $('#btn-cargar-ajax').on('click', cargarUsuariosAjax);
});