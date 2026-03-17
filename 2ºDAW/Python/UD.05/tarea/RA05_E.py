"""
# RA05_E - Programa Principal con SQLite

Programa principal que usa las clases `Producto` (RA05_B) y `productoAlimenticio` (RA05_C)
junto con las funciones de base de datos de RA05_D para crear e insertar productos en SQLite.
"""

import sqlite3
from RA05_B import Producto
from RA05_C import productoAlimenticio


def conectar_db(nombre_db="tienda.db"):
    """Crea o abre una base de datos SQLite y devuelve la conexión.

    Args:
        nombre_db (str): Nombre del archivo de base de datos (por defecto 'tienda.db').

    Returns:
        sqlite3.Connection: Objeto de conexión a la base de datos.
    """
    return sqlite3.connect(nombre_db)


def deconectar_db(conexion):
    """Cierra la conexión a la base de datos SQLite.

    Args:
        conexion (sqlite3.Connection): Conexión activa a cerrar.
    """
    conexion.close()


def crear_tabla_productos(conexion):
    """Crea la tabla `productos` con soporte para fecha de caducidad.

    Columnas: id (PK), nombre (NOT NULL), precio (NOT NULL, ≥ 0) y fecha_caducidad (TEXT, opcional).

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
    except sqlite3.OperationalError:
        # La tabla ya existe; no hace falta volver a crearla
        print("La tabla ya existe.")
    finally:
        cursor.close()
        conexion.commit()
    conexion.commit()


def insertar_producto(conexion, producto):
    """Inserta un `Producto` en la tabla, sin fecha de caducidad.

    Args:
        conexion (sqlite3.Connection): Conexión activa a la base de datos.
        producto (Producto): Instancia de `Producto` a insertar.
    """
    try:
        cursor = conexion.cursor()
        cursor.execute("""
            INSERT INTO productos (id, nombre, precio)
            VALUES (?, ?, ?)
        """, (producto.id, producto.nombre, producto.get_precio()))
    except sqlite3.IntegrityError as e:
        # Se produce si el id ya existe o se viola alguna restricción
        print(f"Error al insertar el producto: {e}")
    finally:
        cursor.close()
        conexion.commit()


def mostrar_productos(conexion):
    """Consulta y muestra todos los productos de la tabla.

    Args:
        conexion (sqlite3.Connection): Conexión activa a la base de datos.
    """
    try:
        cursor = conexion.cursor()
        cursor.execute("SELECT * FROM productos")
        productos = cursor.fetchall()
        for producto in productos:
            print(f"ID: {producto[0]}, Nombre: {producto[1]}, Precio: {producto[2]}")
    except sqlite3.Error as e:
        print(f"Error al mostrar los productos: {e}")
    finally:
        cursor.close()


# ---- Punto de entrada ----
if __name__ == "__main__":
    conexion = conectar_db()
    crear_tabla_productos(conexion)

    # Crear e insertar productos
    producto1 = Producto(1, "Laptop", 1500)
    producto2 = Producto(2, "Smartphone", 800)
    insertar_producto(conexion, producto1)
    insertar_producto(conexion, producto2)

    print("Productos en la base de datos:")
    mostrar_productos(conexion)

    deconectar_db(conexion)
