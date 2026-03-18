/**
 * @file app.js
 * @description Aplicación principal de Node.js - ejercicios módulos básicos
 * @author BenjaminDTS
 * @version 1.0.0
 */

'use strict';

const fs = require('fs');
const path = require('path');
const { sumar, multiplicar } = require('./operaciones');

// Mensaje de bienvenida
console.log('Hola, este es mi primer programa en Node.js');

/**
 * Lee el archivo texto.txt y muestra su contenido en consola.
 * @returns {void}
 */
const leerArchivo = () => {
  try {
    const rutaArchivo = path.join(__dirname, 'texto.txt');
    const contenido = fs.readFileSync(rutaArchivo, 'utf8');
    console.log('\n--- Contenido de texto.txt ---');
    console.log(contenido);
    console.log('--- Fin del archivo ---\n');
  } catch (error) {
    console.error(`[ERROR] No se pudo leer el archivo: ${error.message}`);
  }
};

/**
 * Ejecuta y muestra los resultados de las operaciones matemáticas.
 * @returns {void}
 */
const mostrarOperaciones = () => {
  try {
    const resultadoSuma = sumar(5, 3);
    const resultadoMultiplicacion = multiplicar(5, 3);
    console.log(`5 + 3 = ${resultadoSuma}`);
    console.log(`5 * 3 = ${resultadoMultiplicacion}`);
  } catch (error) {
    console.error(`[ERROR] Error en operaciones: ${error.message}`);
  }
};

// Ejecutar
leerArchivo();
mostrarOperaciones();
