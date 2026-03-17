"""
# RA03_A - Ejercicio 2: Contador hasta N

Solicita un número entero positivo y muestra todos los números desde 1 hasta ese número.

**Autor:** Benjamin Santiago González
**Fecha:** 19/11/2025

**Ejemplo de salida esperada:**
```
Ingrese un número entero positivo: 5
1
2
3
4
5
```
"""


def solicitar_numero():
    """Solicita un número entero positivo al usuario con validación.

    Usa recursión para repetir la solicitud si el valor no es válido
    (no numérico o no positivo).

    Returns:
        int: Un número entero mayor que cero.
    """
    try:
        num = int(input("Ingrese un número entero positivo: "))
        if num <= 0:
            print("Error: El número debe ser positivo.")
            return solicitar_numero()  # Reintentar si el valor no es positivo
        return num
    except ValueError:
        print("Error: Ingrese un número entero válido.")
        return solicitar_numero()  # Reintentar si la entrada no es numérica


def mostrar_numeros_hasta(num):
    """Imprime todos los números enteros desde 1 hasta `num` (inclusive).

    Args:
        num (int): Límite superior del conteo.
    """
    print(f"\nNúmeros del 1 al {num}:")
    for i in range(1, num + 1):
        print(i)


# ---- Programa principal ----
print("CONTADOR DE NÚMEROS")
print("===================")

numero = solicitar_numero()
mostrar_numeros_hasta(numero)

print(f"\nSe mostraron {numero} números.")
