"""
# Ejercicio 5 - Clases Producto y Pedido

Demuestra la relación de composición entre `Pedido` y `Producto`:
un pedido contiene una lista de productos y calcula el total sumando sus precios.
"""


class Producto:
    """Representa un producto con nombre y precio.

    Attributes:
        nombre (str): Nombre del producto.
        precio (float): Precio unitario del producto.
    """

    def __init__(self, nombre, precio):
        """Inicializa el producto con su nombre y precio.

        Args:
            nombre (str): Nombre del producto.
            precio (float): Precio unitario.
        """
        self.nombre = nombre
        self.precio = precio

    def obtenerprecio(self):
        """Devuelve el precio del producto.

        Returns:
            float: El precio unitario del producto.
        """
        return self.precio


class Pedido:
    """Representa un pedido que agrupa varios productos.

    Attributes:
        productos (list[Producto]): Lista de productos incluidos en el pedido.
    """

    def __init__(self, productos):
        """Inicializa el pedido con una lista de productos.

        Args:
            productos (list[Producto]): Lista de instancias de `Producto`.
        """
        self.productos = productos

    def calcularTotal(self):
        """Calcula el precio total del pedido sumando todos los productos.

        Returns:
            float: La suma de los precios de todos los productos del pedido.
        """
        return sum(p.obtenerprecio() for p in self.productos)


# Crear productos individuales
producto1 = Producto("Laptop", 1000)
producto2 = Producto("Mouse", 50)
producto3 = Producto("Teclado", 80)

# Crear el pedido con los tres productos
pedido = Pedido([producto1, producto2, producto3])
total = pedido.calcularTotal()
print(f"El total del pedido es: ${total}")
