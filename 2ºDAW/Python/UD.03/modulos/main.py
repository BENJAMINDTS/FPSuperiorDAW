"""
# Módulo Principal - Calculadora

Módulo principal que usa las funciones definidas en `operaciones.py`
para realizar operaciones aritméticas según la elección del usuario.
"""

import operaciones


def pedirNumeros():
    """Solicita dos números de punto flotante al usuario.

    Returns:
        tuple[float, float]: Los dos números introducidos.
    """
    num1 = float(input("Ingrese el primer número: "))
    num2 = float(input("Ingrese el segundo número: "))
    return num1, num2


def mostrarMenu():
    """Muestra el menú de operaciones y devuelve la opción elegida.

    Returns:
        str: El carácter de la opción seleccionada ('1', '2', '3' o '4').
    """
    print("Seleccione la operación que desea realizar:")
    print("1. Sumar")
    print("2. Restar")
    print("3. Multiplicar")
    print("4. Dividir")
    eleccion = input("Ingrese el número de la operación (1/2/3/4): ")
    return eleccion


def elegirOperacion(eleccion, num1, num2):
    """Ejecuta la operación seleccionada usando el módulo `operaciones`.

    Args:
        eleccion (str): Opción del menú elegida por el usuario.
        num1 (float): Primer operando.
        num2 (float): Segundo operando.
    """
    if eleccion == '1':
        resultado = operaciones.sumar(num1, num2)
        print(f"La suma de {num1} y {num2} es: {resultado}")
    elif eleccion == '2':
        resultado = operaciones.restar(num1, num2)
        print(f"La resta de {num1} y {num2} es: {resultado}")
    elif eleccion == '3':
        resultado = operaciones.multiplicar(num1, num2)
        print(f"La multiplicación de {num1} y {num2} es: {resultado}")
    elif eleccion == '4':
        try:
            resultado = operaciones.dividir(num1, num2)
            print(f"La división de {num1} entre {num2} es: {resultado}")
        except ValueError as e:
            print(e)  # Mensaje de error si se intenta dividir por cero
    else:
        print("Operación no válida.")
    num1, num2 = pedirNumeros()


def main():
    """Función principal: solicita números, muestra el menú y ejecuta la operación."""
    num1, num2 = pedirNumeros()
    eleccion = mostrarMenu()
    elegirOperacion(eleccion, num1, num2)
