"""
PROGRAMA: Mostrar números hasta N
AUTOR: Benjamin Santiago González
FECHA: 19/11/2025

DESCRIPCIÓN:
Solicita un número entero positivo y muestra todos los números desde 1 hasta ese número.

EJEMPLO DE SALIDA ESPERADA:
Ingrese un número entero positivo: 5
1
2
3
4
5
"""

def solicitar_numero():
    """Solicita un número entero positivo al usuario"""
    try:
        num = int(input("Ingrese un número entero positivo: "))
        if num <= 0:
            print("Error: El número debe ser positivo.")
            return solicitar_numero()
        return num
    except ValueError:
        print("Error: Ingrese un número entero válido.")
        return solicitar_numero()

def mostrar_numeros_hasta(num):
    """Muestra todos los números desde 1 hasta el número ingresado"""
    print(f"\nNúmeros del 1 al {num}:")
    for i in range(1, num + 1):
        print(i)

# Programa principal
print("CONTADOR DE NÚMEROS")
print("===================")

numero = solicitar_numero()
mostrar_numeros_hasta(numero)

print(f"\nSe mostraron {numero} números.")