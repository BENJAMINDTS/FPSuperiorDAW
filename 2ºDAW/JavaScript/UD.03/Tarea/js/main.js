// RA3_h: Comentarios explicativos del código
// RA3_c: Utilización de objetos predefinidos para cambiar el aspecto del navegador

/**
 * Función para abrir la ventana emergente del reloj
 * RA3_f: Características propias del lenguaje en documentos compuestos por varias ventanas
 */
function abrirReloj() {
    // RA3_h: Mensaje en consola cada vez que se pulsa el botón
    console.log("Botón 'Dame la hora' pulsado - Abriendo ventana del reloj");
    
    // RA3_c: Propiedades de la ventana emergente
    const propiedadesVentana = [
        'width=450',        // Ancho: 450px
        'height=350',       // Alto: 350px
        'top=300',          // Distancia desde el lado superior: 300px
        'left=200',         // Distancia desde el lado izquierdo: 200px
        'scrollbars=no',    // Sin barras de scroll
        'resizable=no',     // No redimensionable
        'toolbar=no',       // Sin barra de herramientas
        'menubar=no',       // Sin barra de menú
        'location=no'       // Sin barra de dirección
    ].join(',');
    
    // RA3_e: Abrir ventana emergente con el reloj
    window.open('reloj.html', 'relojDigital', propiedadesVentana);
}

// RA3_e: Interacción con el usuario - Asignar evento al botón
document.addEventListener('DOMContentLoaded', function() {
    const btnHora = document.getElementById('btnHora');
    if (btnHora) {
        btnHora.addEventListener('click', abrirReloj);
    }
});