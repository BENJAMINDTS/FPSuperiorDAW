import sqlite3

class BibliotecaDB:
    # Conexión a la base de datos (o creación si no existe)
    def conectar_db(self, nombre_db="tienda.db"):
        return sqlite3.connect(nombre_db)
    # Desconexión de la base de datos
    def desconectar_db(self, conexion):
        if conexion:
            conexion.close()
    # Crear tabla productos
    def crear_tabla_productos(self, conexion):
        cursor = conexion.cursor()
        # Crear tabla con manejo de errores
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
            print(f"Error creating table: {e}")
        finally:
            cursor.close()
            conexion.commit()
    # Insertar producto en la tabla
    def insertar_producto(self, conexion, producto, fecha_caducidad=None):
        cursor = conexion.cursor()
        try:
            cursor.execute("""
                INSERT INTO productos (id, nombre, precio, fecha_caducidad)
                VALUES (?, ?, ?, ?)
            """, (producto.id, producto.nombre, producto.get_precio(), fecha_caducidad))
            
            print(f"Product '{producto.nombre}' inserted successfully.")
            
        except sqlite3.IntegrityError as e:
            print(f"Error inserting product '{producto.nombre}': {e}")
        finally:
            cursor.close()
            conexion.commit()
    # Mostrar productos
    def mostrar_productos(self, conexion):
        try:
            cursor = conexion.cursor()
            cursor.execute("SELECT * FROM productos")
            productos = cursor.fetchall()
            # Print todos los productos
            print("\n--- Listing Products ---")
            for producto in productos:
                caducidad = producto[3] or "N/A" # Manejar valor None
                print(f"ID: {producto[0]} | Nombre: {producto[1]} | Precio: {producto[2]} | Caducidad: {caducidad}")
                
        except sqlite3.Error as e:
            print(f"Error showing products: {e}")
        finally:
            cursor.close()