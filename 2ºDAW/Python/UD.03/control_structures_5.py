"""
# Estructuras de Control - Tabla de Multiplicar

Solicita un número al usuario y muestra su tabla de multiplicar del 1 al 10.
"""

# Solicitar el número al usuario y convertirlo a entero
num = int(input("Ingrese un numero para ver su tabla de multiplicar: "))

# Generar e imprimir la tabla del 1 al 10
for i in range(1, 11):
    print(f"{num} x {i} = {num * i}")
