"""
# RA05_A - Clase Producto Básica

Define la clase `Producto` con atributos públicos (id, nombre, precio)
y un método para mostrar su información.
"""


class Producto:
    """Representa un producto con identificador, nombre y precio.

    Attributes:
        id (int): Identificador único del producto.
        nombre (str): Nombre del producto.
        precio (float): Precio del producto.
    """

    def __init__(self, id, nombre, precio):
        """Inicializa el producto con sus datos básicos.

        Args:
            id (int): Identificador único.
            nombre (str): Nombre del producto.
            precio (float): Precio del producto.
        """
        self.id = id
        self.nombre = nombre
        self.precio = precio

    def mostrarInformacion(self):
        """Devuelve una cadena con el ID, nombre y precio del producto.

        Returns:
            str: Información formateada del producto.
        """
        return f"ID: {self.id}, Nombre: {self.nombre}, Precio: {self.precio}"


# Crear instancias y mostrar su información
producto1 = Producto(1, "Laptop", 1500)
producto2 = Producto(2, "Smartphone", 800)
print(producto1.mostrarInformacion())
print(producto2.mostrarInformacion())
