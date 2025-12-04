// script.js - Código con error intencionado para demostración

$(document).ready(function() {
    console.log("1. Documento listo - jQuery cargado correctamente");
    addConsoleMessage("1. Documento listo - jQuery cargado correctamente", "success");
    
    // Configuración inicial completada
    console.log("2. Configuración inicial completada");
    addConsoleMessage("2. Configuración inicial completada", "success");
    
    console.log("3. Esperando interacción del usuario...");
    addConsoleMessage("3. Esperando interacción del usuario...", "success");
    
    // Evento para el botón de prueba
    $("#testBtn").click(function() {
        console.log("4. Botón clickeado - Iniciando función...");
        addConsoleMessage("4. Botón clickeado - Iniciando función...", "success");
        
        // Primera parte del código ejecutándose correctamente
        let mensaje = "Este es un mensaje de prueba para depuración";
        console.log("5. Variable 'mensaje' creada:", mensaje);
        addConsoleMessage("5. Variable 'mensaje' creada: " + mensaje, "success");
        
        // Segunda variable correcta
        let numero = 42;
        console.log("6. Variable 'numero' creada:", numero);
        addConsoleMessage("6. Variable 'numero' creada: " + numero, "success");
        
        // --- AQUÍ INTRODUCIMOS EL ERROR INTENCIONADO ---
        // Variable mal escrita - 'mensae' en lugar de 'mensaje'
        console.log("7. Intentando operación con variable mal escrita...");
        addConsoleMessage("7. Intentando operación con variable mal escrita...", "warning");
        
        let resultado = mensae * numero; // ERROR: 'mensae' no existe
        
        // Estas líneas NO se ejecutarán debido al error anterior
        console.log("8. ESTE MENSAJE NO DEBERIA APARECER - Despues del error");
        addConsoleMessage("8. ESTE MENSAJE NO DEBERIA APARECER - Despues del error", "error");
        
        console.log("9. ESTE TAMPOCO DEBERIA APARECER");
        addConsoleMessage("9. ESTE TAMPOCO DEBERIA APARECER", "error");
    });
    
    // Evento para el botón de reinicio
    $("#resetBtn").click(function() {
        $("#consoleOutput").empty();
        console.clear();
        console.log("Consola reiniciada - Listo para nueva prueba");
        addConsoleMessage("Consola reiniciada - Listo para nueva prueba", "success");
    });
    
    // Función para añadir mensajes a la consola visual
    function addConsoleMessage(message, type) {
        const timestamp = new Date().toLocaleTimeString();
        const messageElement = $('<div class="console-line"></div>')
            .addClass(type)
            .html('<span class="time">[' + timestamp + ']</span> ' + message);
        
        $("#consoleOutput").append(messageElement);
        $("#consoleOutput").scrollTop($("#consoleOutput")[0].scrollHeight);
    }
    
    // Mensaje final de configuración
    console.log("Todo configurado correctamente");
    addConsoleMessage("Todo configurado correctamente", "success");
});