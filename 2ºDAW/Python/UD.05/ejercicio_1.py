"""
# Ejercicio 1 - Clase Usuario con MySQL

Define la clase `Usuario` y demuestra cómo persistir un objeto
en una tabla MySQL usando el módulo `database`.
"""

import database


class Usuario:
    """Representa un usuario con nombre y edad.

    Attributes:
        nombre (str): Nombre del usuario.
        edad (int): Edad del usuario.
    """

    def __init__(self, nombre, edad):
        """Inicializa el usuario con nombre y edad.

        Args:
            nombre (str): Nombre del usuario.
            edad (int): Edad del usuario.
        """
        self.nombre = nombre
        self.edad = edad


# Crear una instancia de Usuario
u = Usuario("Ana", 20)

# Conectar a la base de datos usando el módulo database
mydb, mycursor = database.connect_to_database()

# Crear la tabla usuarios si no existe
mycursor.execute("CREATE TABLE IF NOT EXISTS usuarios (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), edad INT)")

# Preparar e insertar el usuario usando parámetros para evitar SQL injection
sql = "INSERT INTO usuarios (nombre, edad) VALUES (%s, %s)"
val = (u.nombre, u.edad)
mycursor.execute(sql, val)

# Confirmar la transacción para guardar los cambios en la BD
mydb.commit()

print(mycursor.rowcount, "registro insertado.")

# Cerrar la conexión cuando ya no se necesite
database.disconnect_from_database(mydb, mycursor)
