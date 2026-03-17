"""
# RA03_D - Ejercicio 5: Comparación de Métodos de Suma

Compara el rendimiento entre el uso de un bucle `for` y la función built-in `sum()`
para calcular la suma de los números del 1 al 1.000.000.

**Autor:** Benjamin Santiago González
**Fecha:** 19/11/2025

**Ejemplo de salida esperada:**
```
COMPARACIÓN DE MÉTODOS
==============================

Bucle for:
Resultado: 500,000,500,000
Tiempo: 0.048123 segundos

Función sum():
Resultado: 500,000,500,000
Tiempo: 0.013456 segundos

RESUMEN:
Diferencia: 0.034667 segundos
Función sum() es 72.0% más rápida
Resultados iguales: True
```
"""

import time


def suma_con_bucle():
    """Calcula la suma de 1 a 1.000.000 usando un bucle `for`.

    Returns:
        int: La suma acumulada de todos los enteros en el rango [1, 1.000.000].
    """
    suma = 0
    for i in range(1, 1000001):
        suma += i
    return suma


def suma_con_funcion():
    """Calcula la suma de 1 a 1.000.000 usando la función built-in `sum()`.

    Returns:
        int: La suma de todos los enteros en el rango [1, 1.000.000].
    """
    return sum(range(1, 1000001))


# Medir el tiempo de ejecución del bucle for
inicio_bucle = time.time()
resultado_bucle = suma_con_bucle()
tiempo_bucle = time.time() - inicio_bucle

# Medir el tiempo de ejecución de la función sum()
inicio_funcion = time.time()
resultado_funcion = suma_con_funcion()
tiempo_funcion = time.time() - inicio_funcion

# Mostrar los resultados de cada método
print("COMPARACIÓN DE MÉTODOS")
print("=" * 30)

print(f"\nBucle for:")
print(f"Resultado: {resultado_bucle:,}")
print(f"Tiempo: {tiempo_bucle:.6f} segundos")

print(f"\nFunción sum():")
print(f"Resultado: {resultado_funcion:,}")
print(f"Tiempo: {tiempo_funcion:.6f} segundos")

# Mostrar el resumen comparativo
print(f"\nRESUMEN:")
print(f"Diferencia: {tiempo_bucle - tiempo_funcion:.6f} segundos")
print(f"Función sum() es {(tiempo_bucle - tiempo_funcion)/tiempo_bucle*100:.1f}% más rápida")
print(f"Resultados iguales: {resultado_bucle == resultado_funcion}")
