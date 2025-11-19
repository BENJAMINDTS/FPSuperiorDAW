"""
PROGRAMA: Comparación de métodos para calcular suma de 1 a 1.000.000
AUTOR: Benjamin Santiago González
FECHA: 19/11/2025

DESCRIPCIÓN:
Compara el rendimiento entre usar bucle for vs función sum()
para calcular la suma de números del 1 al 1.000.000.

EJEMPLO DE SALIDA ESPERADA:
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
"""

import time

def suma_con_bucle():
    """Calcula la suma usando bucle for"""
    suma = 0
    for i in range(1, 1000001):
        suma += i
    return suma

def suma_con_funcion():
    """Calcula la suma usando función sum()"""
    return sum(range(1, 1000001))

# Medir tiempo del bucle for
inicio_bucle = time.time()
resultado_bucle = suma_con_bucle()
tiempo_bucle = time.time() - inicio_bucle

# Medir tiempo de la función sum()
inicio_funcion = time.time()
resultado_funcion = suma_con_funcion()
tiempo_funcion = time.time() - inicio_funcion

# Mostrar resultados
print("COMPARACIÓN DE MÉTODOS")
print("=" * 30)

print(f"\nBucle for:")
print(f"Resultado: {resultado_bucle:,}")
print(f"Tiempo: {tiempo_bucle:.6f} segundos")

print(f"\nFunción sum():")
print(f"Resultado: {resultado_funcion:,}")
print(f"Tiempo: {tiempo_funcion:.6f} segundos")

print(f"\nRESUMEN:")
print(f"Diferencia: {tiempo_bucle - tiempo_funcion:.6f} segundos")
print(f"Función sum() es {(tiempo_bucle - tiempo_funcion)/tiempo_bucle*100:.1f}% más rápida")
print(f"Resultados iguales: {resultado_bucle == resultado_funcion}")