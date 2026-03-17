"""
# RA05_B - Clase Producto con Precio Privado

Extiende la clase `Producto` añadiendo encapsulación del precio mediante
atributo privado (`__precio`) con getter y setter con validación.
"""


class Producto:
    """Representa un producto con precio encapsulado y validado.

    El precio se almacena como atributo privado para evitar valores negativos,
    accesible únicamente a través de métodos getter y setter.

    Attributes:
        id (int): Identificador único del producto.
        nombre (str): Nombre del producto.
        __precio (float): Precio privado del producto (no negativo).
    """

    def __init__(self, id, nombre, precio):
        """Inicializa el producto validando que el precio no sea negativo.

        Args:
            id (int): Identificador único.
            nombre (str): Nombre del producto.
            precio (float): Precio del producto (debe ser ≥ 0).

        Raises:
            ValueError: Si el precio es negativo.
        """
        self.id = id
        self.nombre = nombre
        if precio < 0:
            raise ValueError("El precio no puede ser negativo.")
        else:
            self.__precio = precio

    def get_precio(self):
        """Devuelve el precio del producto.

        Returns:
            float: El precio actual del producto.
        """
        return self.__precio

    def set_precio(self, nuevo_precio):
        """Actualiza el precio del producto con validación.

        Args:
            nuevo_precio (float): Nuevo precio a asignar (debe ser ≥ 0).

        Raises:
            ValueError: Si el nuevo precio es negativo.
        """
        if nuevo_precio < 0:
            raise ValueError("El precio no puede ser negativo.")
        else:
            self.__precio = nuevo_precio

    def mostrarInformacion(self):
        """Devuelve una cadena con el ID, nombre y precio del producto.

        Returns:
            str: Información formateada del producto.
        """
        return f"ID: {self.id}, Nombre: {self.nombre}, Precio: {self.get_precio()}"


# Crear instancias y mostrar su información
producto1 = Producto(1, "Laptop", 1500)
producto2 = Producto(2, "Smartphone", 800)
print(producto1.mostrarInformacion())
print(producto2.mostrarInformacion())
