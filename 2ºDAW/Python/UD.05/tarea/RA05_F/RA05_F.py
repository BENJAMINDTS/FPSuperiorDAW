import sys
import os
# PATH CONFIGURATION

# We need to add the parent directory to sys.path to import
# RA05_B and RA05_C, which are located one level up.
current_dir = os.path.dirname(os.path.abspath(__file__))
parent_dir = os.path.dirname(current_dir)
sys.path.append(parent_dir)
import database
from RA05_B import Producto
from RA05_C import productoAlimenticio

# Conexión a la base de datos
biblioteca_db = database.BibliotecaDB()
conexion = biblioteca_db.conectar_db()

# Crear tabla productos
biblioteca_db.crear_tabla_productos(conexion)

# Crear instancias de Producto
producto1 = Producto(1, "Laptop", 1500)
producto2 = productoAlimenticio(2, "Smartphone", 800, "2023-12-31")

# Insertar productos en la tabla
biblioteca_db.insertar_producto(conexion, producto1)
biblioteca_db.insertar_producto(conexion, producto2, producto2.fecha_caducidad)

biblioteca_db.mostrar_productos(conexion)

# Desconectar de la base de datos
biblioteca_db.desconectar_db(conexion)