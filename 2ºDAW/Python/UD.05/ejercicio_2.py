"""
# Ejercicio 2 - Encapsulación de Atributos

Demuestra los tres niveles de visibilidad de atributos en Python:
público, protegido (_) y privado (__), y cómo acceder a ellos correctamente.
"""


class Persona:
    """Clase básica que muestra los tres niveles de acceso a atributos.

    Attributes:
        nombre (str): Atributo público, accesible directamente.
        _edad (int): Atributo protegido (convención), accesible pero no recomendado.
        __dni (str): Atributo privado, no accesible directamente desde fuera.
    """

    def __init__(self, nombre, edad, dni):
        """Inicializa la persona con nombre, edad y DNI.

        Args:
            nombre (str): Nombre de la persona.
            edad (int): Edad de la persona.
            dni (str): Número de DNI.
        """
        self.nombre = nombre    # Público: acceso directo
        self._edad = edad       # Protegido: acceso posible pero por convención limitado
        self.__dni = dni        # Privado: solo accesible dentro de la clase


p = Persona("Ariel", 22, "00000000Z")

print(p.nombre)  # Acceso directo al atributo público
print(p._edad)   # Acceso al atributo protegido (convención, pero posible)
# print(p.__dni)  # Esto causará AttributeError porque __dni es privado


class PersonaConMetodos:
    """Clase Persona con métodos getter para acceder a atributos protegidos y privados.

    Attributes:
        nombre (str): Atributo público.
        _edad (int): Atributo protegido.
        __dni (str): Atributo privado.
    """

    def __init__(self, nombre, edad, dni):
        """Inicializa la persona con nombre, edad y DNI.

        Args:
            nombre (str): Nombre de la persona.
            edad (int): Edad de la persona.
            dni (str): Número de DNI.
        """
        self.nombre = nombre
        self._edad = edad
        self.__dni = dni

    def get_edad(self):
        """Devuelve la edad de la persona (atributo protegido).

        Returns:
            int: La edad.
        """
        return self._edad

    def get_dni(self):
        """Devuelve el DNI de la persona (atributo privado).

        Returns:
            str: El número de DNI.
        """
        return self.__dni


pc = PersonaConMetodos("Ariel", 22, "00000000Z")

print(pc.nombre)      # Acceso directo al atributo público
print(pc.get_edad())  # Acceso al atributo protegido mediante getter
print(pc.get_dni())   # Acceso al atributo privado mediante getter
