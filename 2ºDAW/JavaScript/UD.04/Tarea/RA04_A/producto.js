/**
 * @author Benjamin Santiago González
 * @version 1.0.0
 * Clase que representa un producto con sus propiedades básicas
 * y métodos para manipular su información.
 * @class
 */
export default class Producto {
    /**
     * Constructor de la clase Producto
     * @constructor
     * @param {string} nombre - Nombre del producto
     * @param {number} precio - Precio del producto en unidades monetarias
     * @param {number} stock - Cantidad disponible en inventario
     */
    constructor(nombre, precio, stock) {
        /** @property {string} nombre - Nombre del producto */
        this.nombre = nombre;
        
        /** @property {number} precio - Precio del producto */
        this.precio = precio;
        
        /** @property {number} stock - Cantidad en inventario */
        this.stock = stock;
    }

    /**
     * Aplica un descuento porcentual al precio del producto
     * @method
     * @param {number} porcentaje - Porcentaje de descuento a aplicar (0-100)
     * @returns {number} Nuevo precio después del descuento
     * @throws {Error} Si el porcentaje no es un número válido entre 0 y 100
     * @example
     * // Reduce el precio en un 20%
     * producto.aplicarDescuento(20);
     * @description
     * Este método calcula el nuevo precio del producto después de aplicar
     * un descuento basado en el porcentaje proporcionado. Si el porcentaje
     * es inválido (menor que 0 o mayor que 100), lanza un error.
     */
    aplicarDescuento(porcentaje) {
        // Validación del tipo y rango del parámetro porcentaje
        // typeof verifica que sea de tipo 'number'
        // porcentaje < 0 verifica que no sea negativo
        // porcentaje > 100 verifica que no exceda el 100%
        if (typeof porcentaje !== 'number' || porcentaje < 0 || porcentaje > 100) {
            throw new Error("El porcentaje debe ser un número entre 0 y 100");
        }
        
        // Cálculo del monto del descuento
        // Multiplica el precio actual por el porcentaje dividido entre 100
        const descuento = this.precio * (porcentaje / 100);
        
        // Aplicación del descuento al precio actual
        // Operador de asignación con resta: -= equivalente a this.precio = this.precio - descuento
        this.precio -= descuento;
        
        // Validación de precio mínimo
        // Garantiza que el precio nunca sea negativo, estableciéndolo a 0 si lo fuera
        if (this.precio < 0) {
            this.precio = 0;
        }
        
        // Feedback al usuario/desarrollador
        // Muestra en consola el resultado de la operación
        console.log(`Descuento del ${porcentaje}% aplicado. Nuevo precio: $${this.precio.toFixed(2)}`);
        
        // Retorna el nuevo precio para posible uso posterior
        return this.precio;
    }

    /**
     * Genera una cadena con la información formateada del producto
     * @method
     * @returns {string} Información del producto formateada
     * @example
     * // Retorna: "Producto: Laptop | Precio: $1200.00 | Stock: 15 unidades"
     * @description
     * Este método devuelve una representación en cadena de texto
     * que incluye el nombre, precio y stock del producto.
     */
    mostrarInfo() {
        return `Producto: ${this.nombre} | Precio: $${this.precio.toFixed(2)} | Stock: ${this.stock} unidades`;
    }
}