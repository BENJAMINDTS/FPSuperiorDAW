class Producto:
  # Constructor
  def __init__(self, id, nombre, precio):
    self.id = id
    self.nombre = nombre
    self.precio = precio
  # Método para mostrar información
  def mostrarInformacion(self):
    return f"ID: {self.id}, Nombre: {self.nombre}, Precio: {self.precio}"
  
# Crear instancias de Producto
producto1 = Producto(1, "Laptop", 1500)
producto2 = Producto(2, "Smartphone", 800)
# Mostrar información de los productos
print(producto1.mostrarInformacion())
print(producto2.mostrarInformacion())