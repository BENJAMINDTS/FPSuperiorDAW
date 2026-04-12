"""

Autor: BenjaminDTS
Fecha: 2026-04-12
Descripcion: Implementacion de generadores de numeros de la suerte
             utilizando la sentencia yield de Python.
"""


def numeros_suerte(limite):
    """
    Generador que produce numeros del 1 al limite indicado, uno a uno.

    Args:
        limite (int): Numero maximo hasta el que se generan los valores.

    Yields:
        int: Siguiente numero en la secuencia del 1 al limite.
    """
    numero = 1
    while numero <= limite:
        yield numero
        numero += 1


def numeros_suerte_pares(limite):
    """
    Generador que produce unicamente los numeros pares del 1 al limite.

    Args:
        limite (int): Numero maximo hasta el que se filtran los pares.

    Yields:
        int: Siguiente numero par en el intervalo [1, limite].
    """
    for numero in numeros_suerte(limite):
        if numero % 2 == 0:
            yield numero


# ---------------------------------------------------------------------------
# Programa principal
# ---------------------------------------------------------------------------
if __name__ == "__main__":
    NUMERO_DE_LISTA = 17  # <- Reemplaza con tu numero de lista en clase

    print(f"=== Numeros de la suerte del 1 al {NUMERO_DE_LISTA} ===")
    generador = numeros_suerte(NUMERO_DE_LISTA)
    for num in generador:
        print(num, end=" ")
    print()

    print(f"\n=== Numeros de la suerte PARES del 1 al {NUMERO_DE_LISTA} ===")
    generador_pares = numeros_suerte_pares(NUMERO_DE_LISTA)
    for num in generador_pares:
        print(num, end=" ")
    print()
