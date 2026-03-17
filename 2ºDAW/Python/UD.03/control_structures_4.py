"""
# Estructuras de Control - Suma de Pares

Calcula la suma de todos los números pares del 1 al 20
usando un bucle `while` y el operador módulo.
"""

i = 1         # Contador que inicia en 1
resultado = 0 # Acumulador de la suma de pares

while i <= 20:
    if i % 2 == 0:     # Comprobar si el número es par
        resultado += i  # Acumular el valor par
    i += 1             # Incrementar el contador en cada iteración

print("La suma de los números pares del 1 al 20 es:", resultado)
