"""
# RA04_C - Gestión de Stock con Diccionario

Demuestra el uso de diccionarios para gestionar el stock de una tienda.
Incluye funciones para calcular el total de unidades disponibles y
filtrar productos con stock superior a un umbral dado.
"""

# Diccionario de stock: clave = nombre del producto, valor = cantidad en stock
stock = {
    "Camisa": 50,
    "Pantalón": 30,
    "Zapatos": 20,
    "Reloj": 5,
    "Gorra": 40,
    "Calcetines": 60
}


def total_productos_disponibles(stock_dict):
    """Calcula el número total de unidades disponibles en el stock.

    Args:
        stock_dict (dict[str, int]): Diccionario con productos y sus cantidades.

    Returns:
        int: La suma de todas las cantidades del diccionario.
    """
    # sum() con .values() suma directamente todos los valores del diccionario
    total = sum(stock_dict.values())
    return total


def productos_con_stock_superior(stock_dict, cantidad_minima):
    """Filtra los productos cuyo stock supera una cantidad mínima dada.

    Args:
        stock_dict (dict[str, int]): Diccionario con productos y sus cantidades.
        cantidad_minima (int): Umbral mínimo de stock para incluir el producto.

    Returns:
        dict[str, int]: Diccionario con los productos que superan el umbral.
    """
    productos_superiores = {}
    # Iterar sobre cada par clave-valor con .items()
    for producto, cantidad in stock_dict.items():
        if cantidad > cantidad_minima:
            productos_superiores[producto] = cantidad
    return productos_superiores


print("\n--- Gestión de Stock ---")

# Calcular y mostrar el total de unidades en stock
total = total_productos_disponibles(stock)
print(f"Número total de productos disponibles: {total}")

# Filtrar y mostrar productos con stock superior a 10
cantidad_minima_stock = 10
productos_altos_stock = productos_con_stock_superior(stock, cantidad_minima_stock)
print(f"Productos con stock superior a {cantidad_minima_stock}:")
if productos_altos_stock:
    for producto, cantidad in productos_altos_stock.items():
        print(f"- {producto}: {cantidad} unidades")
else:
    print("No hay productos con stock superior a la cantidad especificada.")
