import database
mydb, mycursor = database.connect_to_database()

#Creamos la tabla si no existe
mycursor.execute("CREATE TABLE IF NOT EXISTS empleados (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), salario FLOAT)")

#Insertar empleado

def crearEmpleado(mydb, mycursor):
    sql = "INSERT INTO empleados (nombre, salario) VALUES (%s, %s)"
    val = ("Juan", 50000)
    mycursor.execute(sql, val)

    mydb.commit()

    print(mycursor.rowcount, "registro insertado.")



#Leer empleados
def leerEmpleados(mycursor):
    mycursor.execute("SELECT * FROM empleados")

    empleados = mycursor.fetchall()

    for empleado in empleados:
        print(f"ID: {empleado[0]}, Nombre: {empleado[1]}, Salario: {empleado[2]}")

#Llamar a las funciones
crearEmpleado(mydb, mycursor)
leerEmpleados(mycursor)

database.disconnect_from_database(mydb, mycursor)