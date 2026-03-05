class Producto:
    def __init__(self, nombre, precio):
        self.nombre = nombre
        self.precio = precio
        
    def obtenerprecio (self):
        return self.precio
class Pedido:
    def __init__(self, productos):
        self.productos = productos
        
    def calcularTotal(self):
        return sum(p.obtenerprecio() for p in self.productos)
      
#Crear productos
producto1 = Producto("Laptop", 1000)
producto2 = Producto("Mouse", 50)
producto3 = Producto("Teclado", 80)

#Crear pedido
pedido = Pedido([producto1, producto2, producto3])
total = pedido.calcularTotal()
print(f"El total del pedido es: ${total}")