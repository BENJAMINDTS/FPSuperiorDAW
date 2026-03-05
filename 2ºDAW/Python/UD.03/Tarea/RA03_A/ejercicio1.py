"""
PROGRAMA: Encontrar el número mayor
AUTOR: Benjamin Santiago González
FECHA: 19/11/2025

DESCRIPCIÓN:
Solicita tres números enteros y determina cuál es el mayor.

EJEMPLO DE SALIDA ESPERADA:
Ingrese el primer número entero: 15
Ingrese el segundo número entero: 8
Ingrese el tercer número entero: 22
El número mayor es: 22
"""

def solicitar_numeros():
    """Solicita tres números enteros al usuario"""
    try:
        num1 = int(input("Ingrese el primer número entero: "))
        num2 = int(input("Ingrese el segundo número entero: "))
        num3 = int(input("Ingrese el tercer número entero: "))
        return num1, num2, num3
    except ValueError:
        print("Error: Ingrese números enteros válidos.")
        return solicitar_numeros()

def encontrar_mayor(num1, num2, num3):
    """Determina cuál de los tres números es el mayor"""
    if num1 >= num2 and num1 >= num3:
        return num1
    elif num2 >= num1 and num2 >= num3:
        return num2
    else:
        return num3

# Programa principal
print("ENCONTRAR EL NÚMERO MAYOR")
print("========================")

num1, num2, num3 = solicitar_numeros()
mayor = encontrar_mayor(num1, num2, num3)

# Mostrar el resultado
print(f"\nDe los números {num1}, {num2} y {num3}:")
print(f"El número mayor es: {mayor}")