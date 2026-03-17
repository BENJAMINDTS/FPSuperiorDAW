"""
# RA05_C - Clase ProductoAlimenticio (herencia)

Extiende `Producto` de RA05_B añadiendo una fecha de caducidad
y un método para comprobar si el producto ha caducado.
"""

from RA05_B import Producto
import datetime


class productoAlimenticio(Producto):
    """Producto alimenticio con fecha de caducidad.

    Hereda de `Producto` (RA05_B) y añade la comprobación de caducidad
    comparando la fecha de caducidad con la fecha actual del sistema.

    Attributes:
        fecha_caducidad (str): Fecha de caducidad en formato 'YYYY-MM-DD'.
    """

    def __init__(self, id, nombre, precio, fecha_caducidad):
        """Inicializa el producto alimenticio con los datos de `Producto` más la fecha de caducidad.

        Args:
            id (int): Identificador único del producto.
            nombre (str): Nombre del producto.
            precio (float): Precio del producto (debe ser ≥ 0).
            fecha_caducidad (str): Fecha de caducidad en formato 'YYYY-MM-DD'.
        """
        super().__init__(id, nombre, precio)
        self.fecha_caducidad = fecha_caducidad

    def caducado(self):
        """Comprueba si el producto ha superado su fecha de caducidad.

        Returns:
            bool: `True` si la fecha actual es posterior a la fecha de caducidad, `False` en caso contrario.
        """
        fecha_actual = datetime.date.today()
        # Convertir la cadena de texto a objeto date para comparar
        fecha_caducidad = datetime.datetime.strptime(self.fecha_caducidad, "%Y-%m-%d").date()
        return fecha_actual > fecha_caducidad

    def mostrarInformacion(self):
        """Devuelve la información del producto incluyendo la fecha de caducidad.

        Returns:
            str: Información heredada de `Producto` más la fecha de caducidad.
        """
        info_base = super().mostrarInformacion()
        return f"{info_base}, Fecha de caducidad: {self.fecha_caducidad}"
