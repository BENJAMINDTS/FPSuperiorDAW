/**
 * @file operaciones.js
 * @description Módulo con operaciones matemáticas básicas
 * @author BenjaminDTS
 * @version 1.0.0
 */

'use strict';

/**
 * Suma dos números.
 * @param {number} a - Primer operando
 * @param {number} b - Segundo operando
 * @returns {number} La suma de a y b
 */
const sumar = (a, b) => a + b;

/**
 * Multiplica dos números.
 * @param {number} a - Primer operando
 * @param {number} b - Segundo operando
 * @returns {number} El producto de a y b
 */
const multiplicar = (a, b) => a * b;

module.exports = { sumar, multiplicar };
