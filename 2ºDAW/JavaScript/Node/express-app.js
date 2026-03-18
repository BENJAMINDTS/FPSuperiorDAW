/**
 * @file express-app.js
 * @description Servidor Express con rutas para ejercicios 6 al 11.
 * Cubre: ruta raíz, parámetros de URL, cálculo, POST de usuarios y CRUD de productos.
 * @author BenjaminDTS
 * @version 1.0.0
 */

'use strict';

const express = require('express');

const app = express();
const PORT = 3001;

/** Middleware para parsear JSON en el body de las peticiones */
app.use(express.json());

/** @type {Array<{id:number, nombre:string, precio:number}>} */
const productos = [
  { id: 1, nombre: 'ordenador', precio: 800 },
  { id: 2, nombre: 'ratón',     precio: 20  },
  { id: 3, nombre: 'teclado',   precio: 50  },
];

// ─── Ejercicio 6: Ruta raíz ───────────────────────────────────────────────────

/**
 * GET /
 * Devuelve mensaje de bienvenida al servidor Express.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.get('/', (req, res) => {
  res.send('Bienvenido a mi servidor con Express');
});

// ─── Ejercicio 7: Ruta con parámetro ─────────────────────────────────────────

/**
 * GET /saludo/:nombre
 * Saluda al nombre recibido como parámetro de URL.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.get('/saludo/:nombre', (req, res) => {
  const { nombre } = req.params;
  res.send(`Hola ${nombre}, bienvenido a Node.js`);
});

// ─── Ejercicio 8: Ruta con cálculo ───────────────────────────────────────────

/**
 * GET /suma/:num1/:num2
 * Suma dos números recibidos como parámetros de URL.
 * Devuelve 400 si alguno no es numérico.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.get('/suma/:num1/:num2', (req, res) => {
  const num1 = Number(req.params.num1);
  const num2 = Number(req.params.num2);

  if (isNaN(num1) || isNaN(num2)) {
    return res.status(400).json({ success: false, message: 'Los parámetros deben ser números' });
  }

  res.send(`La suma es ${num1 + num2}`);
});

// ─── Ejercicio 9: POST /usuarios ─────────────────────────────────────────────

/**
 * POST /usuarios
 * Recibe un objeto JSON con {nombre, edad} y lo muestra en consola.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.post('/usuarios', (req, res) => {
  const { nombre, edad } = req.body;

  if (!nombre || edad === undefined) {
    return res.status(400).json({ success: false, message: 'Se requieren nombre y edad' });
  }

  console.log(`[INFO] Usuario recibido → nombre: ${nombre}, edad: ${edad}`);
  res.status(201).json({ success: true, data: { nombre, edad }, message: 'Usuario recibido correctamente' });
});

// ─── Ejercicio 10: GET /productos ─────────────────────────────────────────────

/**
 * GET /productos
 * Devuelve el array completo de productos en formato JSON.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.get('/productos', (req, res) => {
  res.json({ success: true, data: productos });
});

// ─── Ejercicio 11: GET /productos/:id ─────────────────────────────────────────

/**
 * GET /productos/:id
 * Devuelve el producto cuyo id coincide con el parámetro.
 * Responde 404 si no existe.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.get('/productos/:id', (req, res) => {
  const id = Number(req.params.id);
  const producto = productos.find((p) => p.id === id);

  if (!producto) {
    return res.status(404).json({ success: false, message: 'Producto no encontrado' });
  }

  res.json({ success: true, data: producto });
});

// ─── Arranque del servidor ─────────────────────────────────────────────────────

app.listen(PORT, () => {
  console.log(`[INFO] Servidor Express escuchando en http://localhost:${PORT}`);
});
