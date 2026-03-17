"""
# Módulo de Operaciones Matemáticas

Librería de funciones aritméticas básicas (suma, resta, multiplicación y división)
diseñada para ser importada por otros módulos.
"""


def sumar(a, b):
    """Devuelve la suma de dos números.

    Args:
        a (float): Primer sumando.
        b (float): Segundo sumando.

    Returns:
        float: La suma de `a` y `b`.
    """
    return a + b


def restar(a, b):
    """Devuelve la resta de dos números.

    Args:
        a (float): Minuendo.
        b (float): Sustraendo.

    Returns:
        float: La diferencia `a - b`.
    """
    return a - b


def multiplicar(a, b):
    """Devuelve el producto de dos números.

    Args:
        a (float): Primer factor.
        b (float): Segundo factor.

    Returns:
        float: El producto de `a` y `b`.
    """
    return a * b


def dividir(a, b):
    """Devuelve la división de dos números.

    Args:
        a (float): Dividendo.
        b (float): Divisor.

    Returns:
        float: El cociente de `a` entre `b`.

    Raises:
        ValueError: Si `b` es cero (división por cero no permitida).
    """
    if b == 0:
        raise ValueError("No se puede dividir por cero.")
    return a / b
