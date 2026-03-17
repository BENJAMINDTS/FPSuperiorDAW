"""
# Tabla de Multiplicar

Genera e imprime la tabla de multiplicar de un número hasta un límite
indicado por el usuario, con validación de entrada.
"""


def pedirNumeroEntero(prompt):
    """Solicita un número entero al usuario en bucle hasta obtener un valor válido.

    Args:
        prompt (str): Mensaje mostrado al usuario.

    Returns:
        int: El número entero introducido.
    """
    while True:
        try:
            valor = int(input(prompt))
            return valor
        except ValueError:
            print("Por favor, ingresa un número entero válido.")


def multiplicar_tabla(numero, hasta=10):
    """Genera la tabla de multiplicar de un número hasta el límite indicado.

    Args:
        numero (int): El número cuya tabla se quiere generar.
        hasta (int): Hasta qué multiplicador generar la tabla (por defecto 10).

    Returns:
        list[str]: Lista de cadenas con cada línea de la tabla.
    """
    tabla = []
    for i in range(1, hasta + 1):
        tabla.append(f"{numero} x {i} = {numero * i}")
    return tabla


def imprimir_tabla(tabla):
    """Imprime cada línea de la tabla de multiplicar.

    Args:
        tabla (list[str]): Lista de cadenas generada por `multiplicar_tabla`.
    """
    for linea in tabla:
        print(linea)


if __name__ == "__main__":
    numero = pedirNumeroEntero("Introduce el número para generar su tabla de multiplicar: ")
    hasta = pedirNumeroEntero("¿Hasta qué número quieres multiplicar? ")
    tabla = multiplicar_tabla(numero, hasta)
    imprimir_tabla(tabla)
