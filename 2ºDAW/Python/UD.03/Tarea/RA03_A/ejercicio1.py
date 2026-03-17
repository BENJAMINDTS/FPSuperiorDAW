"""
# RA03_A - Ejercicio 1: Número Mayor

Solicita tres números enteros al usuario y determina cuál es el mayor.

**Autor:** Benjamin Santiago González
**Fecha:** 19/11/2025

**Ejemplo de salida esperada:**
```
Ingrese el primer número entero: 15
Ingrese el segundo número entero: 8
Ingrese el tercer número entero: 22
El número mayor es: 22
```
"""


def solicitar_numeros():
    """Solicita tres números enteros al usuario con validación de entrada.

    Utiliza recursión para repetir la solicitud si se introduce un valor
    que no puede convertirse a entero.

    Returns:
        tuple[int, int, int]: Los tres números enteros introducidos.
    """
    try:
        num1 = int(input("Ingrese el primer número entero: "))
        num2 = int(input("Ingrese el segundo número entero: "))
        num3 = int(input("Ingrese el tercer número entero: "))
        return num1, num2, num3
    except ValueError:
        print("Error: Ingrese números enteros válidos.")
        return solicitar_numeros()  # Llamada recursiva para reintentar


def encontrar_mayor(num1, num2, num3):
    """Determina cuál de los tres números es el mayor.

    Args:
        num1 (int): Primer número.
        num2 (int): Segundo número.
        num3 (int): Tercer número.

    Returns:
        int: El mayor de los tres números.
    """
    if num1 >= num2 and num1 >= num3:
        return num1
    elif num2 >= num1 and num2 >= num3:
        return num2
    else:
        return num3


# ---- Programa principal ----
print("ENCONTRAR EL NÚMERO MAYOR")
print("========================")

num1, num2, num3 = solicitar_numeros()
mayor = encontrar_mayor(num1, num2, num3)

# Mostrar el resultado final
print(f"\nDe los números {num1}, {num2} y {num3}:")
print(f"El número mayor es: {mayor}")
