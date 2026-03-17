"""
# Módulo de Conexión a MySQL

Proporciona funciones para conectar y desconectar de una base de datos MySQL
usando el conector `mysql.connector`.
"""

import mysql.connector


def connect_to_database():
    """Establece una conexión con la base de datos MySQL y devuelve la conexión y el cursor.

    Returns:
        tuple: Un par `(mydb, mycursor)` donde `mydb` es el objeto de conexión
               y `mycursor` es el cursor listo para ejecutar consultas.
    """
    mydb = mysql.connector.connect(
        host="localhost",
        user="root",
        password="",  # Añadir la contraseña real si se tiene configurada
        database="python"
    )
    mycursor = mydb.cursor()
    print("Successfully connected to the database!")

    # Retornar conexión y cursor para usarlos en otros módulos
    return mydb, mycursor


def disconnect_from_database(mydb, mycursor):
    """Cierra el cursor y la conexión a la base de datos.

    Args:
        mydb: Objeto de conexión MySQL activo.
        mycursor: Cursor MySQL activo.
    """
    mycursor.close()
    mydb.close()
    print("Database connection closed.")


# Llamada de prueba para verificar la conexión al importar el módulo
db, cursor = connect_to_database()
