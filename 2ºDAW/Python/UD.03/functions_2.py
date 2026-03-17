"""
# Cálculo de Media con Argumentos Variables

Demuestra el uso de funciones con número fijo y variable de argumentos (`*args`)
para calcular la media aritmética de dos o más números.
"""


def media_de_dos_numeros(num1, num2):
    """Calcula la media aritmética de exactamente dos números.

    Args:
        num1 (float): Primer número.
        num2 (float): Segundo número.

    Returns:
        float: La media de `num1` y `num2`.
    """
    return (num1 + num2) / 2


def media_de_varios_numeros(*args):
    """Calcula la media aritmética de cualquier cantidad de números.

    Args:
        *args (float): Uno o más valores numéricos.

    Returns:
        float: La media aritmética de todos los argumentos, o 0 si no hay ninguno.
    """
    if len(args) == 0:
        return 0
    return sum(args) / len(args)


def entrada_numeros():
    """Solicita números al usuario hasta que introduzca 'fin'.

    Returns:
        list[float]: Lista con los números válidos introducidos.
    """
    numeros = []
    while True:
        entrada = input("Introduce un número (o 'fin' para terminar): ")
        if entrada.lower() == 'fin':
            break
        try:
            numero = float(entrada)
            numeros.append(numero)
        except ValueError:
            print("Por favor, introduce un número válido.")
    return numeros


def best_function(media_de_dos_numeros, media_de_varios_numeros, numeros):
    """Selecciona y aplica la función de media más adecuada según la cantidad de números.

    Usa `media_de_dos_numeros` si hay exactamente 2 valores,
    y `media_de_varios_numeros` si hay más de 2.

    Args:
        media_de_dos_numeros (callable): Función para calcular la media de dos números.
        media_de_varios_numeros (callable): Función para calcular la media de varios números.
        numeros (list[float]): Lista de números sobre los que calcular la media.
    """
    if len(numeros) == 2:
        media_dos = media_de_dos_numeros(numeros[0], numeros[1])
        print(f"La media los dos números ({numeros[0]} y {numeros[1]}) es: {media_dos}")
    elif len(numeros) > 2:
        media_varios = media_de_varios_numeros(*numeros)
        print(f"La media de todos los números introducidos es: {media_varios}")
    else:
        print("No se han introducido suficientes números para calcular la media")


# Recoger los números y calcular la media con la función apropiada
numeros = entrada_numeros()
best_function(media_de_dos_numeros, media_de_varios_numeros, numeros)
