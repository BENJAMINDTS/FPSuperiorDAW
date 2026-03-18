/**
 * @file peliculas.js
 * @description API REST para gestionar una colección de películas.
 * Soporta GET, POST, PUT y DELETE sobre el recurso /peliculas.
 * @author BenjaminDTS
 * @version 1.0.0
 */

'use strict';

const express = require('express');

const app = express();
const PORT = 3002;

/** Middleware para parsear JSON en el body de las peticiones */
app.use(express.json());

/**
 * @typedef  {Object} Pelicula
 * @property {number} id       - Identificador único
 * @property {string} titulo   - Título de la película
 * @property {string} director - Director de la película
 * @property {number} año      - Año de estreno
 */

/** @type {Pelicula[]} Datos en memoria (sin base de datos) */
let peliculas = [
  { id: 1, titulo: 'El Padrino',         director: 'Francis Ford Coppola', año: 1972 },
  { id: 2, titulo: 'Pulp Fiction',        director: 'Quentin Tarantino',    año: 1994 },
  { id: 3, titulo: 'El Señor de los Anillos', director: 'Peter Jackson',   año: 2001 },
];

/** Contador simple para generar IDs únicos */
let nextId = peliculas.length + 1;

// ─── GET /peliculas ───────────────────────────────────────────────────────────

/**
 * GET /peliculas
 * Devuelve la lista completa de películas.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.get('/peliculas', (req, res) => {
  res.json({ success: true, data: peliculas });
});

// ─── GET /peliculas/:id ───────────────────────────────────────────────────────

/**
 * GET /peliculas/:id
 * Devuelve la película con el id especificado.
 * Responde 404 si no existe.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.get('/peliculas/:id', (req, res) => {
  const id = Number(req.params.id);
  const pelicula = peliculas.find((p) => p.id === id);

  if (!pelicula) {
    return res.status(404).json({ success: false, message: 'Película no encontrada' });
  }

  res.json({ success: true, data: pelicula });
});

// ─── POST /peliculas ──────────────────────────────────────────────────────────

/**
 * POST /peliculas
 * Crea una nueva película con los datos del body: {titulo, director, año}.
 * Responde 400 si faltan campos obligatorios.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.post('/peliculas', (req, res) => {
  const { titulo, director, año } = req.body;

  if (!titulo || !director || !año) {
    return res.status(400).json({ success: false, message: 'Se requieren titulo, director y año' });
  }

  const nueva = { id: nextId++, titulo, director, año };
  peliculas.push(nueva);

  res.status(201).json({ success: true, data: nueva, message: 'Película creada correctamente' });
});

// ─── PUT /peliculas/:id ───────────────────────────────────────────────────────

/**
 * PUT /peliculas/:id
 * Actualiza los datos de la película con el id especificado.
 * Solo sobreescribe los campos enviados en el body.
 * Responde 404 si la película no existe.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.put('/peliculas/:id', (req, res) => {
  const id = Number(req.params.id);
  const index = peliculas.findIndex((p) => p.id === id);

  if (index === -1) {
    return res.status(404).json({ success: false, message: 'Película no encontrada' });
  }

  // Fusiona los campos existentes con los enviados; el id no se modifica
  peliculas[index] = { ...peliculas[index], ...req.body, id };

  res.json({ success: true, data: peliculas[index], message: 'Película actualizada correctamente' });
});

// ─── DELETE /peliculas/:id ────────────────────────────────────────────────────

/**
 * DELETE /peliculas/:id
 * Elimina la película con el id especificado.
 * Responde 404 si no existe.
 * @param {import('express').Request}  req
 * @param {import('express').Response} res
 * @returns {void}
 */
app.delete('/peliculas/:id', (req, res) => {
  const id = Number(req.params.id);
  const index = peliculas.findIndex((p) => p.id === id);

  if (index === -1) {
    return res.status(404).json({ success: false, message: 'Película no encontrada' });
  }

  const eliminada = peliculas.splice(index, 1)[0];

  res.json({ success: true, data: eliminada, message: 'Película eliminada correctamente' });
});

// ─── Arranque del servidor ─────────────────────────────────────────────────────

app.listen(PORT, () => {
  console.log(`[INFO] API de películas escuchando en http://localhost:${PORT}`);
});
