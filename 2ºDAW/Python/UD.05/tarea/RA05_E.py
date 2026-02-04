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
                precio REAL NOT NULL CHECK(precio >= 0),
                fecha_caducidad TEXT
            )
        """)
    except sqlite3.OperationalError:
        print("La tabla ya existe.")
    finally:
        cursor.close()
        conexion.commit()
    conexion.commit()
# Insertar producto en la tabla
def insertar_producto(conexion, producto):
    try:
        cursor = conexion.cursor()
        cursor.execute("""
            INSERT INTO productos (id, nombre, precio)
            VALUES (?, ?, ?)
        """, (producto.id, producto.nombre, producto.get_precio()))
    except sqlite3.IntegrityError as e:
        print(f"Error al insertar el producto: {e}")
    finally:
        cursor.close()
        conexion.commit()
        
def mostrar_productos(conexion):
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

# Ejemplo de uso
if __name__ == "__main__":
    conexion = conectar_db()
    crear_tabla_productos(conexion)
    from RA05_B import Producto
    producto1 = Producto(1, "Laptop", 1500)
    producto2 = Producto(2, "Smartphone", 800)
    insertar_producto(conexion, producto1)
    insertar_producto(conexion, producto2)
    print("Productos en la base de datos:")
    mostrar_productos(conexion)
    deconectar_db(conexion)