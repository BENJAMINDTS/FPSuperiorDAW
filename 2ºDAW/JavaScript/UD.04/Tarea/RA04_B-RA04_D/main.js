/**
 * @author Benjamin Santiago Gonzalez
 * @version 1.0
 * Programa para solicitar números, almacenarlos en array y ordenarlos
 * @module main
 * @description Programa que solicita números enteros positivos al usuario,
 * los almacena en un array y luego los ordena.
 */

const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

/**
 * Función que verifica si un valor es un número entero positivo
 * @function esEntero
 * @param {any} n - Valor a verificar
 * @returns {boolean} true si es entero positivo, false en caso contrario
 * @description Convierte el valor a número y verifica si es un entero positivo
 */
function esEntero(n) {
    const numero = Number(n);
    
    if (isNaN(numero) || !isFinite(numero) || !Number.isInteger(numero) || numero < 1) {
        return false;
    }
    
    return true;
}

/**
 * Solicita un número entero positivo al usuario
 * @async
 * @function solicitarNumeroEntero
 * @param {string} mensaje - Mensaje a mostrar al usuario
 * @returns {Promise<number>} Número entero positivo validado
 * @description Solicita al usuario un número entero positivo,
 *              validándolo antes de devolverlo
 */
function solicitarNumeroEntero(mensaje) {
    return new Promise((resolve) => {
        const preguntaRecursiva = () => {
            rl.question(mensaje, (respuesta) => {
                if (!esEntero(respuesta)) {
                    console.log("Error: Debe ser un número entero positivo mayor que cero.");
                    console.log("Por favor, inténtelo de nuevo.\n");
                    return preguntaRecursiva();
                }
                
                const numeroValidado = parseInt(respuesta, 10);
                resolve(numeroValidado);
            });
        };
        
        preguntaRecursiva();
    });
}

/**
 * Función principal del programa
 * @async
 * @function main
 * @description Ejecuta el flujo principal del programa para solicitar,
 * almacenar y ordenar números en un array
 */
async function main() {
    console.log("PROGRAMA DE ARRAYS: SOLICITAR, ALMACENAR Y ORDENAR");
    
    try {
        // Solicitar tamaño del array (N)
        const N = await solicitarNumeroEntero(
            "Introduzca un número entero positivo para el tamaño del array: "
        );
        
        console.log(`\nTamaño del array: ${N}\n`);
        
        // Solicitar N números y almacenarlos en array
        /** @type {number[]} Array para almacenar los números ingresados */
        const numeros = [];
        
        console.log(`Introduzca ${N} números enteros positivos:\n`);
        
        for (let i = 0; i < N; i++) {
            const numero = await solicitarNumeroEntero(
                `Numero ${i + 1}/${N}: `
            );
            numeros.push(numero);
        }
        
        // Mostrar array original
        console.log("\n========================================");
        console.log("ARRAY ORIGINAL:");
        console.log("========================================");
        console.log(`[${numeros.join(', ')}]`);
        
        // Ordenar el array (crear copia para no modificar el original)
        /** @type {number[]} Copia ordenada del array original */
        const numerosOrdenados = [...numeros];
        numerosOrdenados.sort((a, b) => a - b);
        
        // Mostrar array ordenado
        console.log("\n========================================");
        console.log("ARRAY ORDENADO (ascendente):");
        console.log("========================================");
        console.log(`[${numerosOrdenados.join(', ')}]`);
        
        // Mostrar ambos arrays para comparación
        console.log("\n========================================");
        console.log("COMPARACION:");
        console.log("========================================");
        console.log(`Original:  [${numeros.join(', ')}]`);
        console.log(`Ordenado:  [${numerosOrdenados.join(', ')}]`);
        
    } catch (error) {
        console.error(`Error: ${error.message}`);
    } finally {
        rl.close();
        console.log("\nPrograma finalizado.");
    }
}

// Ejecutar programa
main();