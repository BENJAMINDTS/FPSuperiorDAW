"""
PROGRAMA: Calculadora básica
AUTOR: Benjamin Santiago González
FECHA: 19/11/2025

DESCRIPCIÓN:
Calculadora que realiza operaciones básicas (+, -, *, /) con dos números.

EJEMPLO DE SALIDA ESPERADA:
Ingrese el primer número: 10
Ingrese el segundo número: 5
Ingrese la operación (+, -, *, /): +
El resultado de la operación es: 15.0
"""

def solicitar_datos():
    """Solicita dos números y una operación al usuario"""
    try:
        # Solicitar dos números
        num1 = float(input("Ingrese el primer número: "))
        num2 = float(input("Ingrese el segundo número: "))
        
        # Solicitar la operación
        operacion = input("Ingrese la operación (+, -, *, /): ")
        
        # Validar la operación
        if operacion not in ['+', '-', '*', '/']:
            print("Operación inválida. Use: +, -, *, /")
            return solicitar_datos()  # Llamada recursiva para volver a intentar
            
        return num1, num2, operacion
        
    except ValueError:
        print("Error: Ingrese números válidos.")
        return solicitar_datos()  # Llamada recursiva para volver a intentar

def calcular(num1, num2, operacion):
    """Realiza la operación matemática solicitada"""
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

# Programa principal
print("CALCULADORA BÁSICA")
print("==================")

num1, num2, operacion = solicitar_datos()
resultado = calcular(num1, num2, operacion)

# Mostrar el resultado
print(f"El resultado de {num1} {operacion} {num2} es: {resultado}")