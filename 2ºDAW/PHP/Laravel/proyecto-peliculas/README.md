# Laravel — Proyecto Películas

Aplicación de gestión de películas construida con **Laravel 12** que expone una **API REST** completa con panel de control web.

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
cd proyecto-peliculas

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

---

## Endpoints de la API

El controlador `PeliculaController` gestiona todos los recursos mediante `apiResource`.

| Método        | Ruta                | Acción    | Descripción                    |
| ------------- | ------------------- | --------- | ------------------------------ |
| `GET`         | `/peliculas`        | `index`   | Listar todas las películas     |
| `POST`        | `/peliculas`        | `store`   | Crear una nueva película       |
| `GET`         | `/peliculas/{id}`   | `show`    | Ver detalle de una película    |
| `PUT/PATCH`   | `/peliculas/{id}`   | `update`  | Actualizar una película        |
| `DELETE`      | `/peliculas/{id}`   | `destroy` | Eliminar una película          |

### Rutas web

| Método  | Ruta      | Descripción                    |
| ------- | --------- | ------------------------------ |
| `GET`   | `/`       | Redirige al panel              |
| `GET`   | `/panel`  | Panel de control interactivo   |

---

## Estructura del proyecto

```text
proyecto-peliculas/
├── app/
│   └── Http/
│       └── Controllers/
│           └── PeliculaController.php   # CRUD completo de películas
├── routes/
│   └── web.php                          # Rutas web y apiResource
├── resources/
│   └── views/                           # Plantillas Blade (panel)
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

## Pruebas con la API

Puedes probar los endpoints con [Postman](https://www.postman.com/), [Insomnia](https://insomnia.rest/) o `curl`:

```bash
# Listar películas
curl http://127.0.0.1:8000/peliculas

# Crear una película (ejemplo)
curl -X POST http://127.0.0.1:8000/peliculas \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"titulo": "El Padrino", "anio": 1972, "director": "Francis Ford Coppola"}'

# Ver detalle
curl http://127.0.0.1:8000/peliculas/1

# Actualizar
curl -X PUT http://127.0.0.1:8000/peliculas/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo": "El Padrino (Actualizado)"}'

# Eliminar
curl -X DELETE http://127.0.0.1:8000/peliculas/1
```

---

## Comandos útiles

```bash
# Ver todas las rutas registradas
php artisan route:list

# Limpiar caché
php artisan cache:clear
php artisan config:clear
php artisan view:clear

# Ejecutar tests
php artisan test
```
