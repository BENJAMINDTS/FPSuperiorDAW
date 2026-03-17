"""
# Ejercicio 3 - Propiedad con Getter y Setter

Demuestra el uso del decorador `@property` para crear atributos con
acceso controlado (getter y setter) en una clase de cuenta bancaria.
"""


class Cuenta:
    """Representa una cuenta bancaria con saldo con acceso controlado.

    El saldo se almacena como atributo privado y se expone mediante
    una propiedad que impide asignar valores negativos.

    Attributes:
        __saldo (float): Saldo actual de la cuenta (privado).
    """

    def __init__(self, saldo):
        """Inicializa la cuenta con el saldo inicial.

        Args:
            saldo (float): Saldo inicial de la cuenta.
        """
        self.__saldo = saldo  # Atributo privado; solo accesible mediante la propiedad

    @property
    def saldo(self):
        """Getter: devuelve el saldo actual de la cuenta.

        Returns:
            float: El saldo de la cuenta.
        """
        return self.__saldo

    @saldo.setter
    def saldo(self, nuevo_saldo):
        """Setter: actualiza el saldo validando que no sea negativo.

        Args:
            nuevo_saldo (float): Nuevo valor del saldo a asignar.
        """
        if nuevo_saldo >= 0:
            self.__saldo = nuevo_saldo
        else:
            print("El saldo no puede ser negativo.")


# Crear cuenta con saldo inicial y consultar el saldo mediante la propiedad
c = Cuenta(1000)
print(c.saldo)  # Llama al getter
