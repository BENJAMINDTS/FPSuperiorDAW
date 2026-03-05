class Producto:
  # Constructor
  def __init__(self, id, nombre, precio):
    self.id = id
    self.nombre = nombre
    if precio < 0:
      raise ValueError("El precio no puede ser negativo.")
    else:
      self.__precio = precio
  #getter para precio
  def get_precio(self):
    return self.__precio
  #setter para precio con validación
  def set_precio(self, nuevo_precio):
    if nuevo_precio < 0:
      raise ValueError("El precio no puede ser negativo.")
    else:
      self.__precio = nuevo_precio
  # Método para mostrar información
  def mostrarInformacion(self):
    return f"ID: {self.id}, Nombre: {self.nombre}, Precio: {self.get_precio()}"

# Crear instancias de Producto
producto1 = Producto(1, "Laptop", 1500)
producto2 = Producto(2, "Smartphone", 800)
# Mostrar información de los productos
print(producto1.mostrarInformacion())
print(producto2.mostrarInformacion())