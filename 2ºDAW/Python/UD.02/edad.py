"""
# Clasificación de Edad

Solicita la edad al usuario y la clasifica en una etapa de vida.
Incluye validación de entrada con `try/except` y comprobación de rango.
"""


def clasificar_edad(edad):
    """Imprime la etapa de vida correspondiente a la edad indicada.

    Args:
        edad (int): Edad del usuario (valor entre 1 y 120).
    """
    # Clasificar según los rangos de edad establecidos
    if 1 <= edad <= 15:
        print("Eres niño/a")
    elif 16 <= edad <= 21:
        print("Eres adolescente")
    elif 22 <= edad <= 35:
        print("Eres joven")
    elif 36 <= edad <= 50:
        print("Eres maduro/a")
    elif 51 <= edad <= 60:
        print("Eres de mediana edad")
    elif 61 <= edad <= 80:
        print("Eres mayor")
    elif 81 <= edad <= 100:
        print("Eres viejo/a")
    else:
        print("Eres centenario/a")


# Solicitar al usuario que ingrese su edad hasta que sea válida
while True:
    # Manejar posibles errores de conversión (entrada no numérica)
    try:
        edad = int(input("Introduce tu edad (entre 1 y 120): "))  # Solicitar edad
        if 1 <= edad <= 120:  # Validar que la edad esté dentro del rango permitido
            clasificar_edad(edad)
            break
        else:
            print("Edad fuera de rango. Inténtalo de nuevo.")  # Pedir reingreso
    except ValueError:
        print("Entrada no válida. Por favor, introduce un número.")  # Manejar error de conversión
