// RA3_h: Comentarios explicativos del código
// RA3_e: Utilización de objetos predefinidos para interactuar con el usuario

/**
 * Función para formatear números a 2 dígitos
 * RA3_e: Asegurar que la hora aparezca correctamente formateada
 * @param {number} numero - Número a formatear
 * @returns {string} Número formateado a 2 dígitos
 */
function formatearDosDigitos(numero) {
    // RA3_e: Usar padStart para asegurar 2 dígitos (método de String)
    return numero.toString().padStart(2, '0');
}

/**
 * Función para obtener y mostrar la hora actual
 * RA3_d: Generar texto dinámicamente en el documento
 */
function actualizarReloj() {
    // RA3_e: Usar objeto Date predefinido
    const ahora = new Date();
    
    // RA3_e: Obtener horas, minutos y segundos
    const horas = formatearDosDigitos(ahora.getHours());
    const minutos = formatearDosDigitos(ahora.getMinutes());
    const segundos = formatearDosDigitos(ahora.getSeconds());
    
    // RA3_d: Formatear la hora como HH:MM:SS
    const horaFormateada = `${horas}:${minutos}:${segundos}`;
    
    // RA3_d: Actualizar el contenido del reloj usando innerHTML
    const elementoReloj = document.getElementById('reloj');
    if (elementoReloj) {
        elementoReloj.innerHTML = horaFormateada;
        elementoReloj.textContent = horaFormateada; // Alternativa más segura
    }
}

/**
 * Función para cerrar la ventana del reloj
 * RA3_f: Cerrar ventana emergente
 */
function cerrarVentana() {
    // RA3_h: Mensaje en consola antes de cerrar
    console.log("Cerrando ventana del reloj");
    
    // RA3_f: Cerrar la ventana actual
    window.close();
}

// RA3_e: Inicializar el reloj cuando se carga la página
document.addEventListener('DOMContentLoaded', function() {
    // RA3_h: Mensaje de inicialización
    console.log("Ventana del reloj cargada correctamente");
    
    // Actualizar el reloj inmediatamente
    actualizarReloj();
    
    // RA3_e: Configurar intervalo para actualizar cada segundo
    // Usando setInterval() del objeto window
    setInterval(actualizarReloj, 1000);
    
    // RA3_f: Asignar evento al botón de cerrar
    const btnCerrar = document.getElementById('btnCerrar');
    if (btnCerrar) {
        btnCerrar.addEventListener('click', cerrarVentana);
    }
});