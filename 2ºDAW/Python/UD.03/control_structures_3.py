"""
# Estructuras de Control - Filtrar Nombres

Recorre una lista de nombres e imprime únicamente los que tienen
más de 4 caracteres, usando la función `len()`.
"""

name_list = ["Ana", "Luis", "Carlos", "Marta", "Sofía"]

# Iterar sobre cada nombre y filtrar por longitud
for name in name_list:
    if len(name) > 4:  # Solo imprimir nombres con más de 4 letras
        print(name)
