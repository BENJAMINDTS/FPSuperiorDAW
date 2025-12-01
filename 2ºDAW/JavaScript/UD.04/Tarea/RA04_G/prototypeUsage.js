// Importar la función constructora Smartphone
const Smartphone = require('./smartphone.js');

// Polyfill para alert en Node.js
if (typeof alert === 'undefined') {
    global.alert = console.log;
}

// Crear un objeto de tipo smartphone
const miSmartphone = new Smartphone();

// Configurar propiedades del smartphone
miSmartphone.marca = "Samsung";
miSmartphone.modelo = "Galaxy S23 Ultra";
miSmartphone.color = "Verde Esmeralda";
miSmartphone.tamanio = 6.8;

console.log("=== DEMOSTRACIÓN PATRÓN PROTOTIPO ===\n");

// 1. Usar los métodos originales
console.log("1. Métodos originales:");
miSmartphone.instalarAplicacion("Telegram");
miSmartphone.enviarCorreo("Probando el nuevo método prototipo");
miSmartphone.llamar("+34 600 123 456");
console.log("");

// 2. Usar el nuevo método añadido con prototipo
console.log("2. Nuevo método añadido con Prototipo:");
miSmartphone.obtenerDatosSmartphone();
console.log("");

// 3. Crear otro smartphone y usar el nuevo método
console.log("3. Creando otro smartphone:");
const otroSmartphone = new Smartphone();
otroSmartphone.marca = "Apple";
otroSmartphone.modelo = "iPhone 15 Pro";
otroSmartphone.color = "Titanio Natural";
otroSmartphone.tamanio = 6.1;

otroSmartphone.obtenerDatosSmartphone();
console.log("");

// 4. Demostración de que el método está en el prototipo
console.log("4. Verificación del prototipo:");
console.log("- ¿obtenerDatosSmartphone está en el objeto?:", 
    miSmartphone.hasOwnProperty('obtenerDatosSmartphone'));
console.log("- ¿obtenerDatosSmartphone está en el prototipo?:", 
    'obtenerDatosSmartphone' in Smartphone.prototype);
console.log("- ¿Ambos objetos comparten el mismo método?:", 
    miSmartphone.obtenerDatosSmartphone === otroSmartphone.obtenerDatosSmartphone);
console.log("");

console.log("=== DEMOSTRACIÓN COMPLETADA ===");