"""
# Cálculo de Media Interactivo

Solicita números enteros al usuario hasta que introduzca 'fin'
y calcula la media aritmética de los valores recogidos.
"""


def pedirNumeroEntero(prompt):
    """Solicita un número entero al usuario en bucle hasta obtener un valor válido.

    Args:
        prompt (str): Mensaje mostrado al usuario para pedir el número.

    Returns:
        int: El número entero introducido por el usuario.
    """
    while True:
        try:
            valor = int(input(prompt))
            return valor
        except ValueError:
            print("Por favor, ingresa un número entero válido.")


def calcular_media(numeros):
    """Calcula la media aritmética de una lista de números.

    Args:
        numeros (list[int | float]): Lista de valores numéricos.

    Returns:
        float: La media aritmética, o 0 si la lista está vacía.
    """
    if len(numeros) == 0:
        return 0
    return sum(numeros) / len(numeros)


if __name__ == "__main__":
    numeros = []
    print("Introduce números para calcular la media. Escribe 'fin' para terminar.")

    while True:
        entrada = input("Número: ")
        if entrada.lower() == 'fin':
            break  # Terminar el bucle cuando el usuario escriba 'fin'
        try:
            numero = int(entrada)
            numeros.append(numero)
        except ValueError:
            print("Por favor, ingresa un número entero válido o 'fin' para terminar.")

    media = calcular_media(numeros)
    print(f"La media de los números introducidos es: {media}")
