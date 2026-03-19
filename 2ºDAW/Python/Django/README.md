# web_django

> **Autor / Author:** BenjaminDTS
> **Django:** 6.0.3 · **Python:** 3.x · **DB:** SQLite

---

## 🇪🇸 Español

- [Descripción](#descripción)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Migraciones y Base de Datos](#migraciones-y-base-de-datos)
- [Ejecución](#ejecución)
- [URLs y Endpoints](#urls-y-endpoints)
- [Panel de Administración](#panel-de-administración)
- [Modelos](#modelos)
- [Arquitectura](#arquitectura)
- [Seguridad en Producción](#seguridad-en-producción)

---

## 🇺🇸 English

- [Description](#description)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Migrations & Database](#migrations--database)
- [Running the App](#running-the-app)
- [URLs & Endpoints](#urls--endpoints)
- [Admin Panel](#admin-panel)
- [Models](#models)
- [Architecture](#architecture)
- [Production Security](#production-security)

---

---

# 🇪🇸 Español

## Descripción

`web_django` es un proyecto educativo construido con **Django 6** que enseña los conceptos fundamentales del framework a través de ejercicios prácticos progresivos. El proyecto contiene dos aplicaciones principales:

| Aplicación | Descripción |
|-----------|-------------|
| **`tienda`** | Gestión de libros. Cubre los ejercicios 3–14: vistas básicas, plantillas, parámetros en URL y operaciones CRUD. |
| **`peliculas`** | Catálogo de películas. Proyecto final con CRUD completo, plantillas con herencia y panel de administración. |

**Características destacadas:**
- Interfaz responsive Mobile-First con diseño personalizado (sin frameworks externos)
- Sistema de plantillas con herencia (`base.html`)
- Panel de administración de Django completamente configurado
- Base de datos SQLite lista para desarrollo

---

## Estructura del Proyecto

```
web_django/                  ← Directorio raíz del proyecto
│
├── web_django/              ← Configuración del proyecto Django
│   ├── settings.py          ← Configuración global
│   ├── urls.py              ← Enrutamiento principal
│   ├── wsgi.py              ← Punto de entrada WSGI
│   └── asgi.py              ← Punto de entrada ASGI
│
├── tienda/                  ← App: Tienda de libros (Ejercicios 3–14)
│   ├── models.py            ← Modelo Libro
│   ├── views.py             ← 10 vistas
│   ├── urls.py              ← 10 patrones URL
│   ├── admin.py             ← Configuración del admin
│   ├── migrations/          ← Migraciones de base de datos
│   └── templates/tienda/    ← Plantillas HTML
│
├── peliculas/               ← App: Catálogo de películas (Proyecto final)
│   ├── models.py            ← Modelo Pelicula
│   ├── views.py             ← 5 vistas CRUD
│   ├── urls.py              ← 5 patrones URL
│   ├── admin.py             ← Configuración del admin
│   ├── migrations/          ← Migraciones de base de datos
│   └── templates/peliculas/ ← Plantillas HTML
│
├── templates/
│   └── base.html            ← Plantilla base global con navbar y estilos
│
├── manage.py                ← CLI de Django
└── db.sqlite3               ← Base de datos SQLite
```

---

## Requisitos

- **Python** 3.10 o superior
- **pip** (gestor de paquetes de Python)
- **Git** (opcional, para clonar el repositorio)

> No se requieren dependencias externas más allá de Django. No hay base de datos externa, no hay Redis, no hay Docker obligatorio.

---

## Instalación

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd web_django
```

### 2. Crear y activar el entorno virtual

**Windows:**
```bash
python -m venv venv
venv\Scripts\activate
```

**macOS / Linux:**
```bash
python3 -m venv venv
source venv/bin/activate
```

### 3. Instalar Django

```bash
pip install django==6.0.3
```

> Si existe un archivo `requirements.txt`, usa en su lugar:
> ```bash
> pip install -r requirements.txt
> ```

---

## Configuración

El archivo de configuración principal es [`web_django/settings.py`](web_django/settings.py).

| Variable | Valor actual | Descripción |
|----------|-------------|-------------|
| `DEBUG` | `True` | Activar solo en desarrollo |
| `SECRET_KEY` | Valor por defecto | **Cambiar en producción** |
| `ALLOWED_HOSTS` | `[]` | Añadir dominios en producción |
| `DATABASES` | SQLite | Archivo `db.sqlite3` en la raíz |
| `LANGUAGE_CODE` | `en-us` | Idioma del panel admin |
| `TIME_ZONE` | `UTC` | Zona horaria del servidor |

> **Recomendación de seguridad:** En producción, mueve el `SECRET_KEY` a una variable de entorno `.env` y nunca lo expongas en el repositorio.

---

## Migraciones y Base de Datos

Ejecuta las migraciones para crear las tablas en SQLite:

```bash
python manage.py migrate
```

Para crear un superusuario y acceder al panel de administración:

```bash
python manage.py createsuperuser
```

---

## Ejecución

```bash
python manage.py runserver
```

La aplicación estará disponible en: **http://127.0.0.1:8000/**

Para usar un puerto diferente:

```bash
python manage.py runserver 8080
```

---

## URLs y Endpoints

### Tienda de Libros (`/`)

| URL | Método | Descripción | Ejercicio |
|-----|--------|-------------|-----------|
| `/inicio` | GET | Página de bienvenida | 3 |
| `/saludo/<nombre>` | GET | Saludo personalizado por nombre | 4 |
| `/suma/<num1>/<num2>` | GET | Suma de dos enteros | 5 |
| `/inicio-template` | GET | Página de inicio con plantilla HTML | 6 |
| `/saludo-template` | GET | Plantilla con variable de contexto | 7 |
| `/frutas` | GET | Lista de frutas con bucle en plantilla | 8 |
| `/libros` | GET | Listado de libros desde la base de datos | 11 |
| `/libro/<id>` | GET | Detalle de un libro por ID | 12 |
| `/libro/<id>/actualizar` | GET | Actualiza el precio del libro a 25€ | 13 |
| `/libro/<id>/eliminar` | GET | Elimina un libro por ID | 14 |

### Películas (`/peliculas/`)

| URL | Método | Descripción |
|-----|--------|-------------|
| `/peliculas/` | GET | Listado de todas las películas |
| `/peliculas/insertar` | GET | Inserta una película de ejemplo |
| `/peliculas/<id>` | GET | Detalle de una película por ID |
| `/peliculas/<id>/actualizar` | GET | Actualiza el título de la película |
| `/peliculas/<id>/eliminar` | GET | Elimina una película por ID |

### Panel de Administración

| URL | Descripción |
|-----|-------------|
| `/admin/` | Panel de administración de Django |

---

## Panel de Administración

Accede en **http://127.0.0.1:8000/admin/** con el superusuario creado.

**Configuración de `tienda`:**
- Columnas visibles: Título, Autor, Precio
- Búsqueda por: Título, Autor
- Filtros por: Autor

**Configuración de `peliculas`:**
- Columnas visibles: Título, Director, Año
- Búsqueda por: Título, Director
- Filtros por: Año, Director

---

## Modelos

### `Libro` (app `tienda`)

| Campo | Tipo | Detalles |
|-------|------|----------|
| `id` | BigAutoField | Clave primaria (automático) |
| `titulo` | CharField | max_length=200 |
| `autor` | CharField | max_length=150 |
| `precio` | DecimalField | max_digits=8, decimal_places=2 |

Ordenado por: `titulo` (ascendente)

### `Pelicula` (app `peliculas`)

| Campo | Tipo | Detalles |
|-------|------|----------|
| `id` | BigAutoField | Clave primaria (automático) |
| `titulo` | CharField | max_length=200 |
| `director` | CharField | max_length=150 |
| `anio` | IntegerField | Año de estreno |

Ordenado por: `anio` (descendente)

---

## Arquitectura

El proyecto sigue el patrón **MVT** (Model–View–Template) de Django con separación clara de responsabilidades:

- **Models:** Definen la estructura de la base de datos
- **Views:** Contienen la lógica de negocio y procesan peticiones
- **Templates:** Se encargan de la presentación HTML con herencia via `base.html`
- **URLs:** Enrutan las peticiones HTTP a las vistas correspondientes

---

## Seguridad en Producción

Antes de desplegar en producción, realiza los siguientes cambios:

```python
# settings.py
DEBUG = False
SECRET_KEY = os.environ.get('SECRET_KEY')
ALLOWED_HOSTS = ['tudominio.com']
```

Pasos recomendados:
1. Crea un archivo `.env` con `SECRET_KEY` y otras variables sensibles
2. Usa `python-decouple` o `django-environ` para cargar variables de entorno
3. Configura HTTPS (certificado SSL)
4. Reemplaza SQLite con PostgreSQL o MySQL
5. Configura archivos estáticos con `collectstatic` y un servidor como Nginx

---
---

# 🇺🇸 English

## Description

`web_django` is an educational project built with **Django 6** that teaches the core concepts of the framework through progressive practical exercises. The project contains two main applications:

| App | Description |
|-----|-------------|
| **`tienda`** | Book store management. Covers exercises 3–14: basic views, templates, URL parameters, and CRUD operations. |
| **`peliculas`** | Movie catalog. Final capstone project with full CRUD, template inheritance, and admin panel. |

**Key features:**
- Responsive Mobile-First interface with custom design (no external frameworks)
- Template inheritance system (`base.html`)
- Fully configured Django admin panel
- SQLite database ready for development

---

## Project Structure

```
web_django/                  ← Project root directory
│
├── web_django/              ← Django project configuration
│   ├── settings.py          ← Global settings
│   ├── urls.py              ← Main URL routing
│   ├── wsgi.py              ← WSGI entry point
│   └── asgi.py              ← ASGI entry point
│
├── tienda/                  ← App: Book store (Exercises 3–14)
│   ├── models.py            ← Libro model
│   ├── views.py             ← 10 views
│   ├── urls.py              ← 10 URL patterns
│   ├── admin.py             ← Admin configuration
│   ├── migrations/          ← Database migrations
│   └── templates/tienda/    ← HTML templates
│
├── peliculas/               ← App: Movie catalog (Final project)
│   ├── models.py            ← Pelicula model
│   ├── views.py             ← 5 CRUD views
│   ├── urls.py              ← 5 URL patterns
│   ├── admin.py             ← Admin configuration
│   ├── migrations/          ← Database migrations
│   └── templates/peliculas/ ← HTML templates
│
├── templates/
│   └── base.html            ← Global base template with navbar and styles
│
├── manage.py                ← Django CLI
└── db.sqlite3               ← SQLite database
```

---

## Requirements

- **Python** 3.10 or higher
- **pip** (Python package manager)
- **Git** (optional, to clone the repository)

> No external dependencies beyond Django are required. No external database, no Redis, no Docker needed.

---

## Installation

### 1. Clone the repository

```bash
git clone <repository-url>
cd web_django
```

### 2. Create and activate a virtual environment

**Windows:**
```bash
python -m venv venv
venv\Scripts\activate
```

**macOS / Linux:**
```bash
python3 -m venv venv
source venv/bin/activate
```

### 3. Install Django

```bash
pip install django==6.0.3
```

> If a `requirements.txt` file is present, use instead:
> ```bash
> pip install -r requirements.txt
> ```

---

## Configuration

The main configuration file is [`web_django/settings.py`](web_django/settings.py).

| Variable | Current value | Description |
|----------|--------------|-------------|
| `DEBUG` | `True` | Enable in development only |
| `SECRET_KEY` | Default value | **Must be changed in production** |
| `ALLOWED_HOSTS` | `[]` | Add your domains in production |
| `DATABASES` | SQLite | `db.sqlite3` file at the root |
| `LANGUAGE_CODE` | `en-us` | Admin panel language |
| `TIME_ZONE` | `UTC` | Server timezone |

> **Security note:** In production, move `SECRET_KEY` to a `.env` environment variable and never expose it in the repository.

---

## Migrations & Database

Run migrations to create the database tables in SQLite:

```bash
python manage.py migrate
```

To create a superuser for the admin panel:

```bash
python manage.py createsuperuser
```

---

## Running the App

```bash
python manage.py runserver
```

The application will be available at: **http://127.0.0.1:8000/**

To use a different port:

```bash
python manage.py runserver 8080
```

---

## URLs & Endpoints

### Book Store (`/`)

| URL | Method | Description | Exercise |
|-----|--------|-------------|----------|
| `/inicio` | GET | Welcome page | 3 |
| `/saludo/<nombre>` | GET | Personalized greeting by name | 4 |
| `/suma/<num1>/<num2>` | GET | Sum of two integers | 5 |
| `/inicio-template` | GET | Welcome page rendered with HTML template | 6 |
| `/saludo-template` | GET | Template with context variable | 7 |
| `/frutas` | GET | Fruit list with template loop | 8 |
| `/libros` | GET | List all books from the database | 11 |
| `/libro/<id>` | GET | Book detail by ID | 12 |
| `/libro/<id>/actualizar` | GET | Update book price to 25€ | 13 |
| `/libro/<id>/eliminar` | GET | Delete a book by ID | 14 |

### Movies (`/peliculas/`)

| URL | Method | Description |
|-----|--------|-------------|
| `/peliculas/` | GET | List all movies |
| `/peliculas/insertar` | GET | Insert a sample movie |
| `/peliculas/<id>` | GET | Movie detail by ID |
| `/peliculas/<id>/actualizar` | GET | Update movie title |
| `/peliculas/<id>/eliminar` | GET | Delete a movie by ID |

### Admin Panel

| URL | Description |
|-----|-------------|
| `/admin/` | Django administration panel |

---

## Admin Panel

Access at **http://127.0.0.1:8000/admin/** using the superuser credentials.

**`tienda` configuration:**
- Visible columns: Title, Author, Price
- Search by: Title, Author
- Filters by: Author

**`peliculas` configuration:**
- Visible columns: Title, Director, Year
- Search by: Title, Director
- Filters by: Year, Director

---

## Models

### `Libro` (app `tienda`)

| Field | Type | Details |
|-------|------|---------|
| `id` | BigAutoField | Primary key (auto) |
| `titulo` | CharField | max_length=200 |
| `autor` | CharField | max_length=150 |
| `precio` | DecimalField | max_digits=8, decimal_places=2 |

Ordered by: `titulo` (ascending)

### `Pelicula` (app `peliculas`)

| Field | Type | Details |
|-------|------|---------|
| `id` | BigAutoField | Primary key (auto) |
| `titulo` | CharField | max_length=200 |
| `director` | CharField | max_length=150 |
| `anio` | IntegerField | Release year |

Ordered by: `anio` (descending)

---

## Architecture

The project follows Django's **MVT** (Model–View–Template) pattern with clear separation of concerns:

- **Models:** Define the database structure
- **Views:** Contain business logic and process HTTP requests
- **Templates:** Handle HTML presentation with inheritance via `base.html`
- **URLs:** Route HTTP requests to the corresponding views

---

## Production Security

Before deploying to production, apply the following changes:

```python
# settings.py
DEBUG = False
SECRET_KEY = os.environ.get('SECRET_KEY')
ALLOWED_HOSTS = ['yourdomain.com']
```

Recommended steps:
1. Create a `.env` file with `SECRET_KEY` and other sensitive variables
2. Use `python-decouple` or `django-environ` to load environment variables
3. Configure HTTPS (SSL certificate)
4. Replace SQLite with PostgreSQL or MySQL
5. Configure static files with `collectstatic` and a server like Nginx

---

*© 2026 BenjaminDTS — Educational Django Project*
