// Importar la función constructora Smartphone
const Smartphone = require('./smartphone.js');

// Añadir esto si quieres mantener alert en el código pero que funcione en Node.js
if (typeof alert === 'undefined') {
    global.alert = console.log;
}

// Crear un objeto de tipo smartphone
const miSmartphone = new Smartphone();

// Configurar propiedades del smartphone
miSmartphone.marca = "Samsung";
miSmartphone.modelo = "Galaxy S23";
miSmartphone.color = "Negro";
miSmartphone.tamanio = 6.1;

// Invocar a los métodos que posee
miSmartphone.instalarAplicacion("WhatsApp");
miSmartphone.enviarCorreo("Hola, ¿qué tal?");
miSmartphone.llamar("+34 612 345 678");