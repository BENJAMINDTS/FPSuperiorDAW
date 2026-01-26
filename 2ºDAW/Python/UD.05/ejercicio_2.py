class Persona:
    def __init__(self,nombre, edad, dni):
        self.nombre = nombre
        self._edad = edad
        self.__dni = dni

p = Persona("Ariel", 22, "00000000Z")

print(p.nombre)  # Acceso directo al atributo público
print(p._edad)   # Acceso al atributo protegido (convención, pero posible)
# print(p.__dni)  # Esto causará un error porque __dni es privado

#Es mejor acceder a los atributos protegidos y privados mediante métodos
class PersonaConMetodos:
    def __init__(self,nombre, edad, dni):
        self.nombre = nombre
        self._edad = edad
        self.__dni = dni

    def get_edad(self):
        return self._edad

    def get_dni(self):
        return self.__dni
      
pc = PersonaConMetodos("Ariel", 22, "00000000Z")

print(pc.nombre)  # Acceso directo al atributo público
print(pc.get_edad())  # Acceso al atributo protegido mediante método
print(pc.get_dni())   # Acceso al atributo privado mediante método
