#RA04_C
# Diccionario de stock de productos
stock = {
  "Camisa": 50,
  "Pantalón": 30,
  "Zapatos": 20,
  "Reloj": 5,
  "Gorra": 40,
  "Calcetines": 60
}

# Función para calcular el número total de productos disponibles
def total_productos_disponibles(stock_dict):
  #usamos sum() para sumar los valores del diccionario con .values() para obtener las cantidades
  total = sum(stock_dict.values())
  return total

# Función para mostrar productos con stock superior a una cantidad dada
def productos_con_stock_superior(stock_dict, cantidad_minima):
  # Crear un nuevo diccionario para almacenar los productos que cumplen la condición
  productos_superiores = {}
  # Iterar sobre los elementos del diccionario usamos .items() para obtener clave y valor
  for producto, cantidad in stock_dict.items():
    if cantidad > cantidad_minima:
      productos_superiores[producto] = cantidad
  return productos_superiores

print("\n--- Gestión de Stock ---")

# Calcular y mostrar el total de productos disponibles
total = total_productos_disponibles(stock)
print(f"Número total de productos disponibles: {total}")

# Mostrar productos con stock superior a 10
cantidad_minima_stock = 10
productos_altos_stock = productos_con_stock_superior(stock, cantidad_minima_stock)
print(f"Productos con stock superior a {cantidad_minima_stock}:")
if productos_altos_stock:
  for producto, cantidad in productos_altos_stock.items():
    print(f"- {producto}: {cantidad} unidades")
else:
  print("No hay productos con stock superior a la cantidad especificada.")
