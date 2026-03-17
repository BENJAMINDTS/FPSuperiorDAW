"""
# RA04_A-B - Lista de Productos

Demuestra operaciones básicas sobre listas:
acceso por índice, `append()`, `sort()` y `remove()`.
"""

# ---- RA04_A: Operaciones de acceso y adición ----

# Lista inicial de productos de una tienda
productos = ["Camisa", "Pantalón", "Zapatos", "Reloj", "Gorra"]

print("Lista completa de productos:", ", ".join(productos))

# El primer elemento ocupa el índice 0
print("Primer elemento:", productos[0])
# El último elemento se accede con el índice -1
print("Último elemento:", productos[-1])

# Añadir un nuevo producto al final de la lista
productos.append("Calcetines")
print("Lista actualizada de productos:", ", ".join(productos))

# ---- RA04_B: Ordenación y eliminación ----

# Ordenar la lista alfabéticamente en orden ascendente
productos.sort()
print("Lista de productos ordenada alfabéticamente:", ", ".join(productos))

# Eliminar un producto específico si existe en la lista
producto_a_eliminar = "Zapatos"
if producto_a_eliminar in productos:
    # Eliminar usando remove() que borra la primera coincidencia
    productos.remove(producto_a_eliminar)
    print(f"Lista después de eliminar '{producto_a_eliminar}':", ", ".join(productos))
else:
    print(f"'{producto_a_eliminar}' no se encontró en la lista.")
