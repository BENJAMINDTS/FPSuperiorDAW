"""
# Módulo BibliotecaDB - Gestión SQLite de Productos

Proporciona la clase `BibliotecaDB` con métodos orientados a objetos
para conectar, crear tablas, insertar y consultar productos en SQLite.
"""

import sqlite3


class BibliotecaDB:
    """Gestiona las operaciones de base de datos SQLite para la tabla `productos`.

    Encapsula todas las operaciones CRUD sobre la tabla `productos`,
    que incluye columnas de id, nombre, precio y fecha de caducidad opcional.
    """

    def conectar_db(self, nombre_db="tienda.db"):
        """Crea o abre una base de datos SQLite y devuelve la conexión.

        Args:
            nombre_db (str): Ruta del archivo SQLite (por defecto 'tienda.db').

        Returns:
            sqlite3.Connection: Objeto de conexión a la base de datos.
        """
        return sqlite3.connect(nombre_db)

    def desconectar_db(self, conexion):
        """Cierra la conexión a la base de datos si está activa.

        Args:
            conexion (sqlite3.Connection | None): Conexión a cerrar.
        """
        if conexion:
            conexion.close()

    def crear_tabla_productos(self, conexion):
        """Crea la tabla `productos` si no existe.

        La tabla incluye: id (PK), nombre (NOT NULL), precio (NOT NULL, ≥ 0)
        y fecha_caducidad (TEXT, opcional).

        Args:
            conexion (sqlite3.Connection): Conexión activa a la base de datos.
        """
        cursor = conexion.cursor()
        try:
            cursor.execute("""
                CREATE TABLE productos (
                    id INTEGER PRIMARY KEY,
                    nombre TEXT NOT NULL,
                    precio REAL NOT NULL CHECK(precio >= 0),
                    fecha_caducidad TEXT
                )
            """)
        except sqlite3.OperationalError as e:
            # La tabla ya existe; se captura el error para no interrumpir el flujo
            print(f"Error creating table: {e}")
        finally:
            cursor.close()
            conexion.commit()

    def insertar_producto(self, conexion, producto, fecha_caducidad=None):
        """Inserta un producto en la tabla `productos`.

        Args:
            conexion (sqlite3.Connection): Conexión activa a la base de datos.
            producto (Producto): Instancia con atributos `id`, `nombre` y método `get_precio()`.
            fecha_caducidad (str | None): Fecha de caducidad en formato 'YYYY-MM-DD', o `None`.
        """
        cursor = conexion.cursor()
        try:
            cursor.execute("""
                INSERT INTO productos (id, nombre, precio, fecha_caducidad)
                VALUES (?, ?, ?, ?)
            """, (producto.id, producto.nombre, producto.get_precio(), fecha_caducidad))

            print(f"Product '{producto.nombre}' inserted successfully.")

        except sqlite3.IntegrityError as e:
            # Se produce si el id ya existe o se viola alguna restricción (ej. precio negativo)
            print(f"Error inserting product '{producto.nombre}': {e}")
        finally:
            cursor.close()
            conexion.commit()

    def mostrar_productos(self, conexion):
        """Consulta y muestra todos los productos almacenados en la tabla.

        Args:
            conexion (sqlite3.Connection): Conexión activa a la base de datos.
        """
        try:
            cursor = conexion.cursor()
            cursor.execute("SELECT * FROM productos")
            productos = cursor.fetchall()

            print("\n--- Listing Products ---")
            for producto in productos:
                # Mostrar 'N/A' si la fecha de caducidad es None
                caducidad = producto[3] or "N/A"
                print(f"ID: {producto[0]} | Nombre: {producto[1]} | Precio: {producto[2]} | Caducidad: {caducidad}")

        except sqlite3.Error as e:
            print(f"Error showing products: {e}")
        finally:
            cursor.close()
