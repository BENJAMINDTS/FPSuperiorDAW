/**
 * @file express-server.js
 * @description Servidor Express básico con rutas dinámicas y operaciones
 * @author BenjaminDTS
 * @version 1.0.0
 */

'use strict';

const express = require('express');

const app = express();
const PORT = 3001;

app.use(express.json());

/**
 * Maneja la ruta raíz GET /.
 * @param {import('express').Request} req - Objeto de solicitud Express
 * @param {import('express').Response} res - Objeto de respuesta Express
 * @returns {void}
 */
const handleHome = (req, res) => {
  res.status(200).json({
    success: true,
    data: null,
    message: 'Bienvenido a mi servidor con Express',
  });
};

/**
 * Maneja el saludo personalizado GET /saludo/:nombre.
 * @param {import('express').Request} req - Objeto de solicitud Express
 * @param {import('express').Response} res - Objeto de respuesta Express
 * @returns {void}
 */
const handleSaludo = (req, res) => {
  try {
    const { nombre } = req.params;
    res.status(200).json({
      success: true,
      data: { nombre },
      message: `Hola ${nombre}, bienvenido a Node.js`,
    });
  } catch (error) {
    res.status(500).json({
      success: false,
      data: null,
      message: `Error interno: ${error.message}`,
    });
  }
};

/**
 * Maneja la operación de suma GET /suma/:num1/:num2.
 * @param {import('express').Request} req - Objeto de solicitud Express
 * @param {import('express').Response} res - Objeto de respuesta Express
 * @returns {void}
 * @throws {Error} Si los parámetros no son números válidos
 */
const handleSuma = (req, res) => {
  try {
    const num1 = parseFloat(req.params.num1);
    const num2 = parseFloat(req.params.num2);

    if (isNaN(num1) || isNaN(num2)) {
      return res.status(400).json({
        success: false,
        data: null,
        message: 'Los parámetros deben ser números válidos',
      });
    }

    const resultado = num1 + num2;
    res.status(200).json({
      success: true,
      data: { num1, num2, resultado },
      message: `La suma es ${resultado}`,
    });
  } catch (error) {
    res.status(500).json({
      success: false,
      data: null,
      message: `Error interno: ${error.message}`,
    });
  }
};

/**
 * Manejador de rutas no encontradas (404).
 * @param {import('express').Request} req - Objeto de solicitud Express
 * @param {import('express').Response} res - Objeto de respuesta Express
 * @returns {void}
 */
const handleNotFound = (req, res) => {
  res.status(404).json({
    success: false,
    data: null,
    message: 'Ruta no encontrada',
  });
};

/**
 * Middleware de manejo centralizado de errores.
 * @param {Error} err - Error capturado
 * @param {import('express').Request} req - Objeto de solicitud Express
 * @param {import('express').Response} res - Objeto de respuesta Express
 * @param {import('express').NextFunction} next - Función next de Express
 * @returns {void}
 */
const handleError = (err, req, res, next) => { // eslint-disable-line no-unused-vars
  console.error(`[ERROR] ${err.message}`);
  res.status(500).json({
    success: false,
    data: null,
    message: `Error interno del servidor: ${err.message}`,
  });
};

// Registro de rutas
app.get('/', handleHome);
app.get('/saludo/:nombre', handleSaludo);
app.get('/suma/:num1/:num2', handleSuma);

// Middlewares finales
app.use(handleNotFound);
app.use(handleError);

// Inicio del servidor
app.listen(PORT, () => {
  console.log(`Servidor Express iniciado en http://localhost:${PORT}`);
});
