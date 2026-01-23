class Usuario:
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad

u= Usuario("Ana", 20)

#Usamos PDo para guardar el objeto en mysql
import database
mydb, mycursor = database.connect_to_database()


#Creamos la tabla si no existe
mycursor.execute("CREATE TABLE IF NOT EXISTS usuarios (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), edad INT)")

sql = "INSERT INTO usuarios (nombre, edad) VALUES (%s, %s)"
val = (u.nombre, u.edad)
mycursor.execute(sql, val)

mydb.commit()

print(mycursor.rowcount, "registro insertado.")

database.disconnect_from_database(mydb, mycursor)