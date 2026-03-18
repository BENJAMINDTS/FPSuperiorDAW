# Node.js Exercises / Ejercicios de Node.js

> **Author / Autor:** BenjaminDTS
> **Version:** 1.0.0
> **Runtime:** Node.js + Express 5

---

## 🇬🇧 English

### About the project

A hands-on collection of Node.js exercises that covers the core fundamentals:
native modules (`fs`, `http`), custom modules, Express.js routing, query parameters, JSON POST requests and a complete REST API.

### Prerequisites

- [Node.js](https://nodejs.org/) v18 or higher
- npm (bundled with Node.js)

### Installation

```bash
# Clone or download the project folder, then install dependencies
npm install
```

### Project structure

| File | Exercises | Description |
|---|---|---|
| `app.js` | 1 · 2 · 3 | Welcome message, reads `texto.txt` with `fs`, imports `operaciones.js` |
| `operaciones.js` | 3 | Module that exports `sumar()` and `multiplicar()` |
| `texto.txt` | 2 | Plain-text file read by `app.js` |
| `servidor.js` | 4 · 5 | Native HTTP server with manual routing |
| `express-server.js` | 6 · 7 · 8 | Basic Express server: root route, URL params, arithmetic |
| `datos-server.js` | 9 · 10 · 11 | Express: POST users, GET products list, GET product by ID |
| `express-app.js` | 6 – 11 | All exercises 6–11 combined in a single Express server |
| `peliculas.js` | Final | Full REST API — CRUD for a movies collection |

### How to run each file

```bash
# Exercises 1 · 2 · 3 — runs in the terminal, no server
node app.js

# Exercises 4 · 5 — native HTTP server → http://localhost:3000
node servidor.js

# Exercises 6 · 7 · 8 — Express server → http://localhost:3001
node express-server.js

# Exercises 9 · 10 · 11 — Express server → http://localhost:3002
node datos-server.js

# Exercises 6–11 combined → http://localhost:3001
node express-app.js

# Final project — REST API → http://localhost:3002
node peliculas.js
```

> Stop any server with **Ctrl + C** in the terminal.

### Available endpoints

#### `servidor.js` — port 3000

| Method | URL | Response |
|---|---|---|
| GET | `/` | `Bienvenido a la página principal` |
| GET | `/about` | `Esta es la página sobre nosotros` |
| GET | `/contacto` | `Página de contacto` |

#### `express-server.js` / `express-app.js` — port 3001

| Method | URL | Example response |
|---|---|---|
| GET | `/` | `Bienvenido a mi servidor con Express` |
| GET | `/saludo/:nombre` | `Hola Ana, bienvenido a Node.js` |
| GET | `/suma/:num1/:num2` | `La suma es 13` |
| GET | `/productos` | JSON array of products |
| GET | `/productos/:id` | JSON single product |
| POST | `/usuarios` | Logs `{ nombre, edad }` to console |

#### `datos-server.js` — port 3002

| Method | URL | Body / Response |
|---|---|---|
| POST | `/usuarios` | `{ "nombre": "Ana", "edad": 25 }` |
| GET | `/productos` | JSON array of products |
| GET | `/productos/:id` | JSON single product |

#### `peliculas.js` — port 3002 (final project)

| Method | URL | Description |
|---|---|---|
| GET | `/peliculas` | List all movies |
| GET | `/peliculas/:id` | Get one movie |
| POST | `/peliculas` | Create a movie `{ titulo, director, año }` |
| PUT | `/peliculas/:id` | Update a movie |
| DELETE | `/peliculas/:id` | Delete a movie |

### Testing the REST API (curl examples)

```bash
# Get all movies
curl http://localhost:3002/peliculas

# Create a movie
curl -X POST http://localhost:3002/peliculas \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Inception","director":"Christopher Nolan","año":2010}'

# Update a movie
curl -X PUT http://localhost:3002/peliculas/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo":"El Padrino II"}'

# Delete a movie
curl -X DELETE http://localhost:3002/peliculas/1
```

---

## 🇪🇸 Español

### Acerca del proyecto

Colección práctica de ejercicios de Node.js que cubre los fundamentos esenciales:
módulos nativos (`fs`, `http`), módulos propios, enrutamiento con Express.js, parámetros de URL, peticiones POST con JSON y una API REST completa.

### Requisitos previos

- [Node.js](https://nodejs.org/) v18 o superior
- npm (incluido con Node.js)

### Instalación

```bash
# Clona o descarga la carpeta del proyecto, luego instala dependencias
npm install
```

### Estructura del proyecto

| Archivo | Ejercicios | Descripción |
|---|---|---|
| `app.js` | 1 · 2 · 3 | Mensaje de bienvenida, lee `texto.txt` con `fs`, importa `operaciones.js` |
| `operaciones.js` | 3 | Módulo que exporta `sumar()` y `multiplicar()` |
| `texto.txt` | 2 | Archivo de texto plano leído por `app.js` |
| `servidor.js` | 4 · 5 | Servidor HTTP nativo con rutas manuales |
| `express-server.js` | 6 · 7 · 8 | Express básico: ruta raíz, parámetros de URL, aritmética |
| `datos-server.js` | 9 · 10 · 11 | Express: POST usuarios, GET lista de productos, GET producto por ID |
| `express-app.js` | 6 – 11 | Ejercicios 6–11 combinados en un único servidor Express |
| `peliculas.js` | Final | API REST completa — CRUD de películas |

### Cómo ejecutar cada archivo

```bash
# Ejercicios 1 · 2 · 3 — se ejecuta en terminal, sin servidor
node app.js

# Ejercicios 4 · 5 — servidor HTTP nativo → http://localhost:3000
node servidor.js

# Ejercicios 6 · 7 · 8 — servidor Express → http://localhost:3001
node express-server.js

# Ejercicios 9 · 10 · 11 — servidor Express → http://localhost:3002
node datos-server.js

# Ejercicios 6–11 combinados → http://localhost:3001
node express-app.js

# Proyecto final — API REST → http://localhost:3002
node peliculas.js
```

> Para detener cualquier servidor usa **Ctrl + C** en la terminal.

### Endpoints disponibles

#### `servidor.js` — puerto 3000

| Método | URL | Respuesta |
|---|---|---|
| GET | `/` | `Bienvenido a la página principal` |
| GET | `/about` | `Esta es la página sobre nosotros` |
| GET | `/contacto` | `Página de contacto` |

#### `express-server.js` / `express-app.js` — puerto 3001

| Método | URL | Ejemplo de respuesta |
|---|---|---|
| GET | `/` | `Bienvenido a mi servidor con Express` |
| GET | `/saludo/:nombre` | `Hola Ana, bienvenido a Node.js` |
| GET | `/suma/:num1/:num2` | `La suma es 13` |
| GET | `/productos` | Array JSON de productos |
| GET | `/productos/:id` | JSON de un producto |
| POST | `/usuarios` | Muestra `{ nombre, edad }` en consola |

#### `datos-server.js` — puerto 3002

| Método | URL | Body / Respuesta |
|---|---|---|
| POST | `/usuarios` | `{ "nombre": "Ana", "edad": 25 }` |
| GET | `/productos` | Array JSON de productos |
| GET | `/productos/:id` | JSON de un producto |

#### `peliculas.js` — puerto 3002 (proyecto final)

| Método | URL | Descripción |
|---|---|---|
| GET | `/peliculas` | Listar todas las películas |
| GET | `/peliculas/:id` | Obtener una película |
| POST | `/peliculas` | Crear película `{ titulo, director, año }` |
| PUT | `/peliculas/:id` | Actualizar película |
| DELETE | `/peliculas/:id` | Eliminar película |

### Probar la API REST (ejemplos con curl)

```bash
# Obtener todas las películas
curl http://localhost:3002/peliculas

# Crear una película
curl -X POST http://localhost:3002/peliculas \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Inception","director":"Christopher Nolan","año":2010}'

# Actualizar una película
curl -X PUT http://localhost:3002/peliculas/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo":"El Padrino II"}'

# Eliminar una película
curl -X DELETE http://localhost:3002/peliculas/1
```
