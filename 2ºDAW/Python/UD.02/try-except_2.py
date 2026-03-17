"""
# Try-Except en Iteración de Listas

Demuestra el manejo de excepciones al iterar sobre una lista.
Usa `sys.exit()` para terminar el programa en caso de error crítico.
"""

import sys

lista = [1, 2, 3, 4]

try:
    # Iterar sobre cada elemento de la lista
    for elemento in lista:
        print(f"Elemento: {elemento}")
except IndexError as e:
    # Captura errores de índice fuera de rango
    print(f"Error: {e}")
    sys.exit(1)  # Termina el programa con código de error 1
finally:
    # Se ejecuta siempre, independientemente de si hubo excepción
    print("Finalizando el acceso a la lista.")
