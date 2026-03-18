/**
 * @file datos-server.js
 * @description Servidor Express con rutas de usuarios y productos en memoria
 * @author BenjaminDTS
 * @version 1.0.0
 */

'use strict';

const express = require('express');

const app = express();
const PORT = 3002;

app.use(express.json());

/**
 * @typedef {Object} Producto
 * @property {number} id - Identificador único del producto
 * @property {string} nombre - Nombre del producto
 * @property {number} precio - Precio del producto en euros
 */

/**
 * Array en memoria con los productos disponibles.
 * @type {Producto[]}
 */
const productos = [
  { id: 1, nombre: 'ordenador', precio: 800 },
  { id: 2, nombre: 'ratón', precio: 20 },
  { id: 3, nombre: 'teclado', precio: 50 },
];

/**
 * Recibe datos de un usuario y los muestra en consola.
 * POST /usuarios — Body: { nombre, edad }
 * @param {import('express').Request} req - Objeto de solicitud Express
 * @param {import('express').Response} res - Objeto de respuesta Express
 * @returns {void}
 */
const crearUsuario = (req, res) => {
  try {
    const { nombre, edad } = req.body;

    if (!nombre || edad === undefined || edad === null) {
      return res.status(400).json({
        success: false,
        data: null,
        message: 'nombre y edad son requeridos',
      });
    }

    console.log(`Nuevo usuario: nombre=${nombre}, edad=${edad}`);

    res.status(201).json({
      success: true,
      data: { nombre, edad },
      message: 'Usuario recibido correctamente',
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
 * Devuelve todos los productos disponibles.
 * GET /productos
 * @param {import('express').Request} req - Objeto de solicitud Express
 * @param {import('express').Response} res - Objeto de respuesta Express
 * @returns {void}
 */
const obtenerProductos = (req, res) => {
  try {
    res.status(200).json({
      success: true,
      data: productos,
      message: 'Productos obtenidos correctamente',
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
 * Devuelve un producto por su ID.
 * GET /productos/:id
 * @param {import('express').Request} req - Objeto de solicitud Express
 * @param {import('express').Response} res - Objeto de respuesta Express
 * @returns {void}
 */
const obtenerProductoPorId = (req, res) => {
  try {
    const id = parseInt(req.params.id, 10);

    if (isNaN(id)) {
      return res.status(400).json({
        success: false,
        data: null,
        message: 'El ID debe ser un número válido',
      });
    }

    const producto = productos.find((p) => p.id === id);

    if (!producto) {
      return res.status(404).json({
        success: false,
        data: null,
        message: 'Producto no encontrado',
      });
    }

    res.status(200).json({
      success: true,
      data: producto,
      message: 'Producto encontrado',
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
 * @param {import('express').Request} req
 * @param {import('express').Response} res
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
 * Middleware centralizado de manejo de errores.
 * @param {Error} err
 * @param {import('express').Request} req
 * @param {import('express').Response} res
 * @param {import('express').NextFunction} next
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
app.post('/usuarios', crearUsuario);
app.get('/productos', obtenerProductos);
app.get('/productos/:id', obtenerProductoPorId);

// Middlewares finales
app.use(handleNotFound);
app.use(handleError);

// Inicio del servidor
app.listen(PORT, () => {
  console.log(`Servidor de datos iniciado en http://localhost:${PORT}`);
});
