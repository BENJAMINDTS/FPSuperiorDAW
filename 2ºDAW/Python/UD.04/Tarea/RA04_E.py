#RA04_E
# Diccionario anidado 'almacen'
almacen = {
    "Camisa": {"precio": 25.00, "stock": 50},
    "Pantalón": {"precio": 40.00, "stock": 30},
    "Zapatos": {"precio": 60.00, "stock": 20},
    "Reloj": {"precio": 120.00, "stock": 5},
    "Gorra": {"precio": 15.00, "stock": 40},
    "Calcetines": {"precio": 8.00, "stock": 60}
}

print("\n--- Gestión de Almacén (Diccionario Anidado) ---")

# Mostrar el precio de un producto concreto
producto_consultar = "Pantalón"
if producto_consultar in almacen:
    print(f"El precio de '{producto_consultar}' es: ${almacen[producto_consultar]['precio']:.2f}")
else:
    print(f"'{producto_consultar}' no se encuentra en el almacén.")

# Mostrar todos los productos con stock inferior a un valor dado
stock_minimo_mostrar = 25
print(f"\nProductos con stock inferior a {stock_minimo_mostrar} unidades:")
productos_bajo_stock = False
for producto, detalles in almacen.items():
    if detalles["stock"] < stock_minimo_mostrar:
        print(f"- {producto}: {detalles['stock']} unidades (Precio: ${detalles['precio']:.2f})")
        productos_bajo_stock = True
if not productos_bajo_stock:
    print("No hay productos con stock inferior a la cantidad especificada.")

# Calcular el valor total del stock (precio × cantidad)
valor_total_stock = 0
for producto, detalles in almacen.items():
    valor_total_stock += detalles["precio"] * detalles["stock"]

print(f"\nValor total de todo el stock en el almacén: ${valor_total_stock:.2f}")
