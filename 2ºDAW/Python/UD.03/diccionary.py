"""
# Diccionario de Alumno

Ejemplo básico de definición e iteración de un diccionario en Python.
Muestra cada clave y su valor correspondiente usando un bucle `for`.
"""

# Diccionario que representa la información de un alumno
alumno = {
    "nombre": "Benjamin",
    "edad": 23,
    "curso": "DAW"
}

# Iterar sobre las claves del diccionario e imprimir cada par clave-valor
for clave in alumno:
    print(clave, ":", alumno[clave])
