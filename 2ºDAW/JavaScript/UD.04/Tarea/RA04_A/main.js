
// Importación de la clase Producto desde el módulo producto.js
// La ruta './producto.js' indica que el archivo está en el mismo directorio
import Producto from './producto.js';

/**
 * Ejemplo de uso de la clase Producto con manejo de descuentos
 * @function
 * @description
 * Esta función crea instancias de la clase Producto, aplica descuentos
 * y maneja posibles errores durante la aplicación de los mismos.
 * @example
 * // Crear un producto y aplicar un descuento
 * demostrarUsoProducto();
 */
function demostrarUsoProducto() {
    // Creación de una instancia de Producto
    // Se instancia un nuevo objeto usando el constructor con parámetros específicos
    // @param {string} "Laptop" - Nombre del producto
    // @param {number} 1500 - Precio inicial del producto
    // @param {number} 10 - Cantidad inicial en stock
    const producto1 = new Producto("Laptop", 1500, 10);
    
    console.log("=== DEMOSTRACIÓN DE USO DE LA CLASE PRODUCTO ===\n");
    
    // Mostrar información inicial del producto
    console.log("Producto inicial:");
    console.log(producto1.mostrarInfo());
    
    console.log("\n" + "=".repeat(50) + "\n");
    
    // Aplicación de un descuento válido del 15%
    // Bloque try-catch para manejar posibles errores
    try {
        console.log("Aplicando descuento válido del 15%:");
        // @param {number} 15 - Porcentaje de descuento a aplicar
        producto1.aplicarDescuento(15);
        console.log(producto1.mostrarInfo());
    } catch (error) {
        // @param {Error} error - Objeto error capturado si la operación falla
        console.error(`Error al aplicar descuento: ${error.message}`);
    }
    
    console.log("\n" + "=".repeat(50) + "\n");
    
    // Aplicación de un descuento inválido para demostrar manejo de errores
    try {
        console.log("Intentando aplicar descuento inválido del 150%:");
        // Este intento debería lanzar un error porque 150 > 100
        // @param {number} 150 - Porcentaje inválido (excede el 100%)
        producto1.aplicarDescuento(150); // Esto lanzará un error
    } catch (error) {
        // Manejo del error: muestra el mensaje sin interrumpir la ejecución
        console.error(`Error capturado: ${error.message}`);
    }
    
    console.log("\n" + "=".repeat(50) + "\n");
    
    // Mostrar la información final del producto después de todas las operaciones
    console.log("Estado final del producto:");
    console.log(producto1.mostrarInfo());
    
    console.log("\n" + "=".repeat(50) + "\n");
    
    // Ejemplo adicional: intentar aplicar un descuento negativo
    try {
        console.log("Intentando aplicar descuento negativo (-10%):");
        // Esto también debería lanzar un error porque -10 < 0
        producto1.aplicarDescuento(-10);
    } catch (error) {
        console.error(`Error capturado: ${error.message}`);
    }
    
    console.log("\n" + "=".repeat(50) + "\n");
    
    // Ejemplo adicional: intentar aplicar un descuento con tipo de dato incorrecto
    try {
        console.log("Intentando aplicar descuento con tipo incorrecto ('20%'):");
        // Esto debería lanzar un error porque '20%' no es un número
        producto1.aplicarDescuento('20%');
    } catch (error) {
        console.error(`Error capturado: ${error.message}`);
    }
}

// Ejecutar la demostración
demostrarUsoProducto();