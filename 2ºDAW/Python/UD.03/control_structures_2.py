"""
# Estructuras de Control - Par o Impar

Recorre un rango de números e indica si cada uno es par o impar
usando el operador módulo (%).
"""

# range(1, 2, 1) genera solo el número 1; para recorrer más valores ampliar el rango
for i in range(1, 2, 1):
    if i % 2 == 0:
        # El resto de dividir por 2 es 0 → número par
        print(i, "es un número par")
    else:
        # El resto de dividir por 2 es distinto de 0 → número impar
        print(i, "es un número impar")
