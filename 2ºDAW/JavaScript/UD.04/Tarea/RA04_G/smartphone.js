/**
 * @author Benjamin Santiago Gonzalez
 * @version 1.1.0
 * Función constructora para crear objetos de la clase Smartphone
 * @constructor
 * @function Smartphone
 */
function Smartphone() {
    // Propiedades de la clase Smartphone
    this.marca = "";
    this.modelo = "";
    this.color = "";
    this.tamanio = 0; // en pulgadas
    
    /**
     * Método para instalar una aplicación
     * @method instalarAplicacion
     * @param {string} apli - Nombre de la aplicación a instalar
     */
    this.instalarAplicacion = function(apli) {
        alert("Aplicación " + apli + " instalada con éxito en smartphone " + this.marca + " " + this.modelo);
    };
    
    /**
     * Método para enviar un correo
     * @method enviarCorreo
     * @param {string} mensa - Texto del mensaje a enviar
     */
    this.enviarCorreo = function(mensa) {
        alert("Mensaje: " + mensa + " enviado con éxito");
    };
    
    /**
     * Método para realizar una llamada
     * @method llamar
     * @param {string} num - Número de teléfono al que llamar
     */
    this.llamar = function(num) {
        alert("Llamando al " + num + "... desde mi smartphone con tamaño " + this.tamanio + " pulgadas");
    };
}

/**
 * Método añadido usando el Patrón Prototipo
 * @method obtenerDatosSmartphone
 * @description Muestra todas las propiedades del objeto Smartphone
 */
Smartphone.prototype.obtenerDatosSmartphone = function() {
    const mensaje = 
        "=== DATOS DEL SMARTPHONE ===\n\n" +
        "Marca: " + this.marca + "\n" +
        "Modelo: " + this.modelo + "\n" +
        "Color: " + this.color + "\n" +
        "Tamaño: " + this.tamanio + " pulgadas\n\n" +
        "==========================";
    
    alert(mensaje);
    console.log(mensaje); // Para Node.js también mostramos en consola
};

// Exportar para Node.js
module.exports = Smartphone;