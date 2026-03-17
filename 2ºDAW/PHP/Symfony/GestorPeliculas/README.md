# Symfony — Gestor de Películas

Aplicación CRUD completa para gestionar películas, construida con **Symfony 7.4**, usando **Doctrine ORM** para la persistencia y **Symfony Forms** con **Validator** para el procesamiento de formularios.

## Tecnologías

| Herramienta | Versión |
|-------------|---------|
| PHP | >= 8.2 |
| Symfony | 7.4.x |
| Base de datos | MySQL 8.0 |
| ORM | Doctrine |
| Motor de vistas | Twig |
| Formularios | Symfony Form + Validator |

---

## Requisitos previos

- PHP >= 8.2 con extensiones: `pdo`, `pdo_mysql`, `mbstring`, `openssl`, `tokenizer`, `xml`, `intl`
- [Composer](https://getcomposer.org/) instalado globalmente
- MySQL 8.0+ en ejecución
- (Opcional) [Symfony CLI](https://symfony.com/download)

---

## Instalación y despliegue

```bash
# 1. Clonar el repositorio (si no lo tienes ya)
git clone <url-del-repo>
cd GestorPeliculas

# 2. Instalar dependencias PHP
composer install

# 3. Configurar variables de entorno
# Edita el fichero .env y ajusta DATABASE_URL con tus credenciales MySQL
```

Edita `.env`:
```dotenv
DATABASE_URL="mysql://root:TU_PASSWORD@127.0.0.1:3306/gestor_peliculas?serverVersion=8.0.32&charset=utf8mb4"
```

```bash
# 4. Crear la base de datos
php bin/console doctrine:database:create

# 5. Ejecutar las migraciones
php bin/console doctrine:migrations:migrate

# 6. Levantar el servidor de desarrollo
symfony server:start
# o sin Symfony CLI:
php -S localhost:8000 -t public/
```

La aplicación estará disponible en `http://127.0.0.1:8000`.

---

## Rutas disponibles

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/peliculas` | Listado de todas las películas |
| `GET` | `/pelicula/{id}` | Detalle de una película |
| `GET\|POST` | `/pelicula/insertar` | Formulario para crear una película |
| `GET\|POST` | `/pelicula/{id}/editar` | Formulario para editar una película |
| `GET` | `/pelicula/{id}/eliminar` | Eliminar una película |

---

## Estructura del proyecto

```
GestorPeliculas/
├── src/
│   ├── Controller/
│   │   └── PeliculaController.php   # CRUD completo con formularios
│   ├── Entity/
│   │   └── Pelicula.php             # Entidad mapeada a tabla `pelicula`
│   ├── Form/
│   │   └── PeliculaType.php         # Definición del formulario Symfony
│   └── Repository/
│       └── PeliculaRepository.php
├── templates/                        # Plantillas Twig
│   └── pelicula/
│       ├── index.html.twig          # Listado
│       ├── show.html.twig           # Detalle
│       └── form.html.twig           # Formulario crear/editar
├── migrations/                       # Migraciones de Doctrine
├── config/
│   └── routes.yaml
├── .env                              # Variables de entorno (¡no commitear con credenciales reales!)
└── composer.json
```

---

## Flujo de la aplicación

```
Usuario → /peliculas (listado)
             ├── → /pelicula/{id}        (ver detalle)
             ├── → /pelicula/insertar    (crear: GET muestra form, POST procesa)
             ├── → /pelicula/{id}/editar (editar: GET carga datos, POST guarda)
             └── → /pelicula/{id}/eliminar (elimina y redirige al listado)
```

---

## Variables de entorno relevantes

```dotenv
APP_ENV=dev
APP_SECRET=cambia_esto_por_un_valor_seguro

DATABASE_URL="mysql://root:@127.0.0.1:3306/gestor_peliculas?serverVersion=8.0.32&charset=utf8mb4"
```

> En producción cambia `APP_ENV=prod`, genera un `APP_SECRET` seguro y usa credenciales de BD apropiadas.

---

## Comandos útiles

```bash
# Ver todas las rutas registradas
php bin/console debug:router

# Ver el estado de las migraciones
php bin/console doctrine:migrations:status

# Generar una nueva migración tras modificar una entidad
php bin/console make:migration

# Aplicar migraciones pendientes
php bin/console doctrine:migrations:migrate

# Limpiar caché
php bin/console cache:clear

# Verificar el esquema de la BD contra las entidades
php bin/console doctrine:schema:validate
```
