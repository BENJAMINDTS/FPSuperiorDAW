"""
# RA05_D - Funciones de Base de Datos SQLite para Productos

Módulo con funciones procedurales para conectar a una BD SQLite
y gestionar la tabla `productos` (crear, insertar y mostrar registros).
"""

import sqlite3


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
    """Crea la tabla `productos` en la base de datos si no existe.

    La tabla incluye: id (PK), nombre (NOT NULL) y precio (NOT NULL, ≥ 0).
    Usa `try/except` para ignorar el error si la tabla ya existe.

    Args:
        conexion (sqlite3.Connection): Conexión activa a la base de datos.
    """
    cursor = conexion.cursor()
    try:
        cursor.execute("""
            CREATE TABLE productos (
                id INTEGER PRIMARY KEY,
                nombre TEXT NOT NULL,
                precio REAL NOT NULL CHECK(precio >= 0)
            )
        """)
    except sqlite3.OperationalError:
        # La tabla ya existe; no es necesario volver a crearla
        print("La tabla ya existe.")
    finally:
        cursor.close()
        conexion.commit()
    conexion.commit()
