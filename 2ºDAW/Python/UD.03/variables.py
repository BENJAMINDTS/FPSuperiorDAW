"""
# Variables y Tipos Básicos

Demuestra el uso de variables con distintos tipos de datos (str, int, bool)
y la conversión de una respuesta de texto a valor booleano.
"""

# Solicitar nombre, edad y estado de estudiante al usuario
nombre = input("Ingrese su nombre: ")
edad = int(input("Ingrese su edad: "))
estudiante = input("¿Es usted estudiante? (si/no): ").strip().lower()

# Convertir la respuesta textual a booleano: True solo si es "si"
es_estudiante = estudiante == "si"

# Mostrar los valores almacenados en cada variable
print("Nombre:", nombre)
print("Edad:", edad)
print("Es estudiante:", es_estudiante)
