#RA04_A
# Lista de productos en una tienda
productos = ["Camisa", "Pantalón", "Zapatos", "Reloj", "Gorra"]

print("Lista completa de productos:", ", ".join(productos))

#El primer elemento de la lista ocupa el índice 0
print("Primer elemento:", productos[0])
#El último elemento de la lista ocupa el índice -1
print("Último elemento:", productos[-1])

# Agregar un nuevo producto a la lista
productos.append("Calcetines")

print("Lista actualizada de productos:", ", ".join(productos))

#RA04_B

# Ordenar la lista alfabéticamente
productos.sort()
print("Lista de productos ordenada alfabéticamente:", ", ".join(productos))

# Eliminar un producto específico
producto_a_eliminar = "Zapatos"
#Comprobar si el producto existe antes de eliminarlo
if producto_a_eliminar in productos:
  #Si existe, eliminarlo usar remove()
  productos.remove(producto_a_eliminar)
  print(f"Lista después de eliminar '{producto_a_eliminar}':", ", ".join(productos))
else:
  print(f"'{producto_a_eliminar}' no se encontró en la lista.")
