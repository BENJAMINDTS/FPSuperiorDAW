# Symfony — Ejercicios Básicos

Proyecto de ejercicios introductorios con **Symfony 7.4**, cubriendo rutas con atributos PHP, controladores, vistas Twig, y operaciones CRUD con **Doctrine ORM** sobre MySQL.

## Tecnologías

| Herramienta | Versión |
|-------------|---------|
| PHP | >= 8.2 |
| Symfony | 7.4.x |
| Base de datos | MySQL 8.0 |
| ORM | Doctrine |
| Motor de vistas | Twig |

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
cd EjerciciosSymfony

# 2. Instalar dependencias PHP
composer install

# 3. Configurar variables de entorno
# Edita el fichero .env y ajusta DATABASE_URL con tus credenciales MySQL
```

Edita `.env`:
```dotenv
DATABASE_URL="mysql://root:TU_PASSWORD@127.0.0.1:3306/ejercicios_symfony?serverVersion=8.0.32&charset=utf8mb4"
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

## Ejercicios

### InicioController — Rutas y vistas básicas

| # | Ruta | Descripción |
|---|------|-------------|
| 1 | `GET /inicio` | Página de bienvenida (respuesta directa) |
| 2 | `GET /saludo/{nombre}` | Saludo con parámetro de ruta |
| 3 | `GET /multiplicar/{num1}/{num2}` | Operación aritmética en respuesta JSON |
| 6 | `GET /bienvenida` | Vista Twig estática |
| 7 | `GET /hola` | Vista Twig con variable dinámica |
| 8 | `GET /ciudades` | Vista Twig con iteración de array |

### ProductoController

| # | Ruta | Descripción |
|---|------|-------------|
| 4 | `GET /productos` | Listado de productos (array estático) |
| 5 | `GET /producto/{id}` | Detalle de un producto por ID |

### LibroController — CRUD con Doctrine

| # | Ruta | Descripción |
|---|------|-------------|
| 10 | `GET /libro/insertar` | Inserta un libro de prueba en la BD |
| 11 | `GET /libros` | Lista todos los libros de la BD |
| 12 | `GET /libro/{id}` | Detalle de un libro (404 si no existe) |
| 13 | `GET /libro/{id}/actualizar` | Actualiza el precio de un libro |
| 14 | `GET /libro/{id}/eliminar` | Elimina un libro de la BD |

---

## Estructura del proyecto

```
EjerciciosSymfony/
├── src/
│   ├── Controller/
│   │   ├── InicioController.php     # Ejercicios 1-3, 6-8
│   │   ├── ProductoController.php   # Ejercicios 4-5
│   │   └── LibroController.php      # Ejercicios 10-14 (Doctrine)
│   ├── Entity/
│   │   └── Libro.php                # Entidad mapeada a tabla `libro`
│   └── Repository/
│       └── LibroRepository.php
├── templates/                        # Plantillas Twig
├── migrations/                       # Migraciones de Doctrine
├── config/
│   └── routes.yaml
├── .env                              # Variables de entorno (¡no commitear con credenciales reales!)
└── composer.json
```

---

## Variables de entorno relevantes

```dotenv
APP_ENV=dev
APP_SECRET=cambia_esto_por_un_valor_seguro

DATABASE_URL="mysql://root:@127.0.0.1:3306/ejercicios_symfony?serverVersion=8.0.32&charset=utf8mb4"
```

> En producción cambia `APP_ENV=prod`, genera un `APP_SECRET` seguro y usa credenciales de BD apropiadas.

---

## Comandos útiles

```bash
# Ver todas las rutas registradas
php bin/console debug:router

# Ver el estado de las migraciones
php bin/console doctrine:migrations:status

# Generar una nueva migración tras cambiar una entidad
php bin/console make:migration

# Aplicar migraciones pendientes
php bin/console doctrine:migrations:migrate

# Limpiar caché
php bin/console cache:clear

# Listar todos los servicios del contenedor
php bin/console debug:container
```
