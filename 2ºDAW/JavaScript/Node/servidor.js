/**
 * @file servidor.js
 * @description Servidor HTTP nativo de Node.js con rutas manuales
 * @author BenjaminDTS
 * @version 1.0.0
 * @module servidor
 */

'use strict';

const http = require('http');

const PORT = 3000;

/**
 * Maneja la ruta raíz '/'.
 * @param {http.IncomingMessage} req - Objeto de solicitud HTTP
 * @param {http.ServerResponse} res - Objeto de respuesta HTTP
 * @returns {void}
 */
const handleHome = (req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });
  res.end('Bienvenido a la página principal');
};

/**
 * Maneja la ruta '/about'.
 * @param {http.IncomingMessage} req - Objeto de solicitud HTTP
 * @param {http.ServerResponse} res - Objeto de respuesta HTTP
 * @returns {void}
 */
const handleAbout = (req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });
  res.end('Esta es la página sobre nosotros');
};

/**
 * Maneja la ruta '/contacto'.
 * @param {http.IncomingMessage} req - Objeto de solicitud HTTP
 * @param {http.ServerResponse} res - Objeto de respuesta HTTP
 * @returns {void}
 */
const handleContacto = (req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });
  res.end('Página de contacto');
};

/**
 * Maneja rutas no encontradas con respuesta 404.
 * @param {http.IncomingMessage} req - Objeto de solicitud HTTP
 * @param {http.ServerResponse} res - Objeto de respuesta HTTP
 * @returns {void}
 */
const handleNotFound = (req, res) => {
  res.writeHead(404, { 'Content-Type': 'text/plain; charset=utf-8' });
  res.end('Página no encontrada');
};

/**
 * Enruta las peticiones entrantes a su manejador correspondiente.
 * @param {http.IncomingMessage} req - Objeto de solicitud HTTP
 * @param {http.ServerResponse} res - Objeto de respuesta HTTP
 * @returns {void}
 */
const routeRequest = (req, res) => {
  const url = req.url;

  const routes = {
    '/': handleHome,
    '/about': handleAbout,
    '/contacto': handleContacto,
  };

  const handler = routes[url] || handleNotFound;
  handler(req, res);
};

/**
 * Crea e inicia el servidor HTTP.
 * @returns {http.Server} La instancia del servidor
 */
const iniciarServidor = () => {
  try {
    const server = http.createServer(routeRequest);

    server.on('error', (error) => {
      console.error(`[ERROR] Error en el servidor: ${error.message}`);
    });

    server.listen(PORT, () => {
      console.log(`Servidor funcionando correctamente en http://localhost:${PORT}`);
    });

    return server;
  } catch (error) {
    console.error(`[ERROR] No se pudo iniciar el servidor: ${error.message}`);
    throw error;
  }
};

iniciarServidor();
