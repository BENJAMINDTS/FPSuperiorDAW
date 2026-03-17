"""
# Funciones con Validación de Entrada

Demuestra la definición de funciones que solicitan datos al usuario,
incluyendo validación de tipo para la edad con `try/except`.
"""


def presentar(nombre, edad):
    """Imprime un saludo con el nombre y la edad del usuario.

    Args:
        nombre (str): Nombre del usuario.
        edad (int): Edad del usuario.
    """
    print(f"Hola, mi nombre es {nombre} y tengo {edad} años.")


def entrada():
    """Solicita nombre y edad al usuario, validando que la edad sea un entero.

    Repite la solicitud de edad en bucle hasta obtener un valor numérico válido.

    Returns:
        tuple[str, int]: El nombre y la edad introducidos.
    """
    nombre = input("Introduce tu nombre: ")
    while True:
        try:
            edad = int(input("Introduce tu edad: "))
            break  # Salir del bucle si la conversión fue exitosa
        except ValueError:
            print("Por favor, introduce un número válido para la edad.")
            False  # Línea sin efecto; el bucle continúa de todas formas
    return nombre, edad


# Solicitar los datos y mostrar el saludo
nombre, edad = entrada()
presentar(nombre, edad)
