"""
# Ejercicio 4 - CRUD de Empleados con MySQL

Demuestra operaciones básicas de creación y lectura (CRUD parcial)
sobre una tabla `empleados` en MySQL usando el módulo `database`.
"""

import database

# Establecer la conexión con la base de datos
mydb, mycursor = database.connect_to_database()

# Crear la tabla empleados si no existe en la base de datos
mycursor.execute("CREATE TABLE IF NOT EXISTS empleados (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), salario FLOAT)")


def crearEmpleado(mydb, mycursor):
    """Inserta un empleado de ejemplo en la tabla `empleados`.

    Args:
        mydb: Objeto de conexión MySQL activo.
        mycursor: Cursor MySQL activo.
    """
    sql = "INSERT INTO empleados (nombre, salario) VALUES (%s, %s)"
    val = ("Juan", 50000)
    mycursor.execute(sql, val)
    mydb.commit()  # Confirmar la transacción para persistir el registro
    print(mycursor.rowcount, "registro insertado.")


def leerEmpleados(mycursor):
    """Consulta y muestra todos los empleados de la tabla.

    Args:
        mycursor: Cursor MySQL activo.
    """
    mycursor.execute("SELECT * FROM empleados")
    empleados = mycursor.fetchall()  # Obtener todos los registros como lista de tuplas
    for empleado in empleados:
        print(f"ID: {empleado[0]}, Nombre: {empleado[1]}, Salario: {empleado[2]}")


# Ejecutar las operaciones CRUD
crearEmpleado(mydb, mycursor)
leerEmpleados(mycursor)

# Cerrar la conexión al finalizar
database.disconnect_from_database(mydb, mycursor)
