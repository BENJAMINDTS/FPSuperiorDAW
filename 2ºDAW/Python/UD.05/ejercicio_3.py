class Cuenta:
    def __init__(self, saldo):
        self.__saldo = saldo  # Atributo privado
    @property
    def saldo(self):
        return self.__saldo
      
    @saldo.setter
    def saldo(self, nuevo_saldo):
        if nuevo_saldo >= 0:
            self.__saldo = nuevo_saldo
        else:
            print("El saldo no puede ser negativo.")
            
c = Cuenta(1000)
print(c.saldo)