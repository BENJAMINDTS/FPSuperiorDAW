"""
# RA05_F - Script Principal de Gestión de Productos

Script que usa la clase `BibliotecaDB` (database.py) junto con las clases
`Producto` y `productoAlimenticio` para insertar y mostrar productos en SQLite.

Añade el directorio padre al path para poder importar RA05_B y RA05_C.
"""

import sys
import os

# Añadir el directorio padre al sys.path para acceder a RA05_B y RA05_C,
# que se encuentran un nivel por encima del directorio actual
current_dir = os.path.dirname(os.path.abspath(__file__))
parent_dir = os.path.dirname(current_dir)
sys.path.append(parent_dir)

import database
from RA05_B import Producto
from RA05_C import productoAlimenticio

# Crear la instancia de BibliotecaDB y establecer la conexión
biblioteca_db = database.BibliotecaDB()
conexion = biblioteca_db.conectar_db()

# Crear la tabla productos si no existe
biblioteca_db.crear_tabla_productos(conexion)

# Crear instancias de Producto y ProductoAlimenticio
producto1 = Producto(1, "Laptop", 1500)
producto2 = productoAlimenticio(2, "Smartphone", 800, "2023-12-31")

# Insertar los productos en la base de datos
# Para el ProductoAlimenticio se pasa también la fecha de caducidad
biblioteca_db.insertar_producto(conexion, producto1)
biblioteca_db.insertar_producto(conexion, producto2, producto2.fecha_caducidad)

# Mostrar todos los productos almacenados
biblioteca_db.mostrar_productos(conexion)

# Cerrar la conexión a la base de datos
biblioteca_db.desconectar_db(conexion)
