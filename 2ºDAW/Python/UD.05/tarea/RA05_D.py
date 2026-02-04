#xVamos a conectar a una base de datos SQLite y crear una tabla para almacenar productos.
import sqlite3
# Conexión a la base de datos (o creación si no existe)
def conectar_db(nombre_db ="tienda.db"):
    return sqlite3.connect(nombre_db)
# Desconexión de la base de datos
def deconectar_db(conexion):
    conexion.close()
# Crear tabla productos
def crear_tabla_productos(conexion):
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
        print("La tabla ya existe.")
    finally:
        cursor.close()
        conexion.commit()
    conexion.commit()
    


