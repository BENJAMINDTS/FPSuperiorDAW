"""
# RA03_B - Ejercicio 3: Calculadora Básica

Calculadora que realiza operaciones básicas (+, -, *, /) con dos números.

**Autor:** Benjamin Santiago González
**Fecha:** 19/11/2025

**Ejemplo de salida esperada:**
```
Ingrese el primer número: 10
Ingrese el segundo número: 5
Ingrese la operación (+, -, *, /): +
El resultado de la operación es: 15.0
```
"""


def solicitar_datos():
    """Solicita dos números y una operación aritmética al usuario.

    Valida que la operación sea una de las permitidas (+, -, *, /) y que
    los valores introducidos sean numéricos. Usa recursión para reintentar.

    Returns:
        tuple[float, float, str]: Los dos operandos y el símbolo de la operación.
    """
    try:
        # Solicitar los dos operandos
        num1 = float(input("Ingrese el primer número: "))
        num2 = float(input("Ingrese el segundo número: "))

        # Solicitar y validar el operador
        operacion = input("Ingrese la operación (+, -, *, /): ")
        if operacion not in ['+', '-', '*', '/']:
            print("Operación inválida. Use: +, -, *, /")
            return solicitar_datos()  # Llamada recursiva para volver a intentar

        return num1, num2, operacion

    except ValueError:
        print("Error: Ingrese números válidos.")
        return solicitar_datos()  # Llamada recursiva para volver a intentar


def calcular(num1, num2, operacion):
    """Realiza la operación aritmética indicada sobre los dos operandos.

    Args:
        num1 (float): Primer operando.
        num2 (float): Segundo operando.
        operacion (str): Operador aritmético: '+', '-', '*' o '/'.

    Returns:
        float | str: El resultado numérico o un mensaje de error si se divide por cero.
    """
    if operacion == '+':
        return num1 + num2
    elif operacion == '-':
        return num1 - num2
    elif operacion == '*':
        return num1 * num2
    elif operacion == '/':
        if num2 != 0:
            return num1 / num2
        else:
            return "Error: No se puede dividir por cero"


# ---- Programa principal ----
print("CALCULADORA BÁSICA")
print("==================")

num1, num2, operacion = solicitar_datos()
resultado = calcular(num1, num2, operacion)

# Mostrar el resultado de la operación
print(f"El resultado de {num1} {operacion} {num2} es: {resultado}")
