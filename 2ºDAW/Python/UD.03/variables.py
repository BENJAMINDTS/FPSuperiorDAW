#Nombre edad y estudiante
nombre = input("Ingrese su nombre: ")
edad = int(input("Ingrese su edad: "))
estudiante = input("¿Es usted estudiante? (si/no): ").strip().lower()
es_estudiante = estudiante == "si"
#Mostrar los valores
print("Nombre:", nombre)
print("Edad:", edad)
print("Es estudiante:", es_estudiante)