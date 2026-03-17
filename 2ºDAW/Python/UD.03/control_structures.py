"""
# Estructuras de Control - Clasificación de Edad

Demuestra el uso de `try/except/else` para validar entrada y clasificar
la edad del usuario en una categoría (menor, adulto o mayor).
"""

try:
    # Intentar convertir la entrada del usuario a entero
    edad = int(input("Ingrese su edad: "))

except ValueError:
    # Se ejecuta si la entrada no es un número entero válido
    print("Por favor, ingrese un número válido para la edad.")
else:
    # Se ejecuta solo si no se produjo ninguna excepción en el bloque try
    if edad < 0:
        print("La edad no puede ser negativa.")
    elif edad < 18:
        print("Eres menor de edad.")
    elif 18 > edad < 65:
        print("Eres un adulto.")
    else:
        print("Eres mayor.")
