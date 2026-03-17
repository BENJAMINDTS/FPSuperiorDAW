"""
# Módulo de Operaciones Aritméticas (versión alternativa)

Módulo secundario que importa `operaciones` y ofrece una interfaz
de calculadora con menú interactivo y validación de entrada.
"""

import operaciones


def entrada_operaciones():
    """Muestra el menú de operaciones y solicita los operandos al usuario.

    Valida que la opción seleccionada sea válida (1-4) y que los números
    introducidos sean de tipo float.

    Returns:
        tuple[str, float, float]: La opción elegida y los dos operandos.
    """
    print("Seleccione la operación que desea realizar:")
    print("1. Suma")
    print("2. Resta")
    print("3. Multiplicación")
    print("4. División")

    # Repetir hasta que el usuario elija una opción válida
    while True:
        eleccion = input("Ingrese el número de la operación (1/2/3/4): ")
        if eleccion in ['1', '2', '3', '4']:
            break
        else:
            print("Selección inválida. Por favor, elija una opción válida.")

    # Solicitar los operandos con validación de tipo
    while True:
        try:
            num1 = float(input("Ingrese el primer número: "))
            num2 = float(input("Ingrese el segundo número: "))
            break
        except ValueError:
            print("Entrada inválida. Por favor, ingrese números válidos.")

    return eleccion, num1, num2


def realizar_operacion(eleccion, num1, num2):
    """Ejecuta la operación aritmética indicada y muestra el resultado.

    Args:
        eleccion (str): Opción del menú ('1' suma, '2' resta, '3' multiplicación, '4' división).
        num1 (float): Primer operando.
        num2 (float): Segundo operando.
    """
    if eleccion == '1':
        resultado = operaciones.suma(num1, num2)
        operacion = "suma"
    elif eleccion == '2':
        resultado = operaciones.resta(num1, num2)
        operacion = "resta"
    elif eleccion == '3':
        resultado = operaciones.multiplicacion(num1, num2)
        operacion = "multiplicación"
    elif eleccion == '4':
        resultado = operaciones.division(num1, num2)
        operacion = "división"

    print(f"El resultado de la {operacion} entre {num1} y {num2} es: {resultado}")


# ---- Punto de entrada ----
eleccion, num1, num2 = entrada_operaciones()
realizar_operacion(eleccion, num1, num2)
