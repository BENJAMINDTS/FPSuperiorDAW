// Esperar a que el documento esté listo
$(document).ready(function() {
    // Remover la clase 'no-js' si existe (para estilos cuando JS está deshabilitado)
    $('body').removeClass('no-js');
    
    // Asignar evento al botón
    $('#verFecha').click(function() {
        mostrarFechaActual();
    });
    
    // Verificar si jQuery se cargó correctamente
    if (typeof jQuery == 'undefined') {
        // jQuery no se cargó, probablemente porque JS está deshabilitado
        console.error('jQuery no se pudo cargar. Verifica que JavaScript esté habilitado.');
    } else {
        console.log('jQuery cargado correctamente. Versión: ' + jQuery.fn.jquery);
    }
});

// Función para mostrar la fecha y hora actual
function mostrarFechaActual() {
    // Obtener fecha y hora actual
    const ahora = new Date();
    
    // Formatear la fecha en español
    const opciones = {
        weekday: 'long',
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        timeZoneName: 'short'
    };
    
    const fechaFormateada = ahora.toLocaleDateString('es-ES', opciones);
    
    // Mostrar en ventana emergente
    alert('Fecha y hora actual:\n' + fechaFormateada);
    

}

// Función para detectar si JavaScript está habilitado
function verificarJavaScript() {
    const jsStatus = document.getElementById('js-status');
    if (jsStatus) {
        jsStatus.textContent = 'JavaScript está HABILITADO';
        jsStatus.style.color = 'green';
    }
}

// Ejecutar verificación al cargar
verificarJavaScript();