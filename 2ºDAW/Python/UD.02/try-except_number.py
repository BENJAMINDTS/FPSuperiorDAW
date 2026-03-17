"""
# Validación de Entrada Numérica

Solicita un número entero al usuario y captura el error `ValueError`
si la entrada no puede convertirse a entero.
"""

try:
    # Intentar convertir la entrada del usuario a entero
    numero = int(input("Por favor, ingresa un numero entero: "))
    print(f"Has ingresado el numero: {numero}")
except ValueError:
    # Se lanza ValueError si input() no contiene un entero válido
    print("Error: No has ingresado un numero entero válido.")
