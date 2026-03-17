# Laravel — Ejercicios Básicos

Proyecto de ejercicios introductorios con **Laravel 12**, cubriendo rutas, controladores, vistas Blade y operaciones CRUD básicas.

## Tecnologías

| Herramienta     | Versión              |
| --------------- | -------------------- |
| PHP             | >= 8.2               |
| Laravel         | 12.x                 |
| Base de datos   | SQLite (por defecto) |
| Motor de vistas | Blade                |

---

## Requisitos previos

- PHP >= 8.2 con extensiones: `pdo`, `pdo_sqlite`, `mbstring`, `openssl`, `tokenizer`, `xml`
- [Composer](https://getcomposer.org/) instalado globalmente

---

## Instalación y despliegue

```bash
# 1. Clonar el repositorio (si no lo tienes ya)
git clone <url-del-repo>
cd ejercicios-basicos

# 2. Instalar dependencias PHP
composer install

# 3. Copiar el fichero de entorno
cp .env.example .env

# 4. Generar la clave de la aplicación
php artisan key:generate

# 5. Crear la base de datos SQLite y ejecutar migraciones
touch database/database.sqlite
php artisan migrate

# 6. Levantar el servidor de desarrollo
php artisan serve
```

La aplicación estará disponible en `http://127.0.0.1:8000`.

> También puedes usar el script personalizado: `composer run dev`

---

## Ejercicios

### Rutas directas (sin controlador)

| #  | Ruta                        | Descripción                               |
| -- | --------------------------- | ----------------------------------------- |
| 1  | `GET /inicio`               | Devuelve JSON con mensaje de bienvenida   |
| 2  | `GET /saludo/{nombre}`      | Saluda al nombre pasado por parámetro     |
| 3  | `GET /suma/{num1}/{num2}`   | Devuelve la suma de dos números en JSON   |

### ProductoController

| #  | Ruta                  | Descripción                       |
| -- | --------------------- | --------------------------------- |
| 4  | `GET /productos`      | Lista de productos en JSON        |
| 5  | `GET /producto/{id}`  | Detalle de un producto por ID     |

### Vistas Blade

| #  | Ruta                  | Descripción                       |
| -- | --------------------- | --------------------------------- |
| 6  | `GET /bienvenida`     | Vista estática de bienvenida      |
| 7  | `GET /saludo-vista`   | Vista con variable dinámica       |
| 8  | `GET /frutas`         | Vista con listado iterado         |

### LibroController — CRUD básico

| #  | Ruta                      | Descripción                   |
| -- | ------------------------- | ----------------------------- |
| 11 | `GET /libros`             | Listar todos los libros       |
| 12 | `GET /libro/{id}`         | Ver detalle de un libro       |
| 13 | `POST /libro`             | Crear un nuevo libro          |
| 14 | `PUT/PATCH /libro/{id}`   | Actualizar un libro           |
| 14 | `DELETE /libro/{id}`      | Eliminar un libro             |

> Panel de pruebas interactivo: `GET /panel-libros`

---

## Estructura del proyecto

```text
ejercicios-basicos/
├── app/
│   └── Http/
│       └── Controllers/
│           ├── ProductoController.php   # Ejercicios 4-5
│           └── LibroController.php      # Ejercicios 11-14
├── routes/
│   └── web.php                          # Definición de todas las rutas
├── resources/
│   └── views/                           # Plantillas Blade
├── database/
│   └── migrations/                      # Migraciones de la BD
├── .env.example
└── composer.json
```

---

## Variables de entorno relevantes

```dotenv
APP_ENV=local
APP_DEBUG=true
APP_URL=http://localhost

DB_CONNECTION=sqlite
# DB_DATABASE=/absolute/path/to/database.sqlite
```

---

## Comandos útiles

```bash
# Ver todas las rutas registradas
php artisan route:list

# Limpiar caché de la aplicación
php artisan cache:clear
php artisan config:clear
php artisan view:clear

# Ejecutar tests
php artisan test
# o
composer run test
```
