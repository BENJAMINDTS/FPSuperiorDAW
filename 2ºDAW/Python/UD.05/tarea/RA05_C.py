from RA05_B import Producto
import datetime
class productoAlimenticio(Producto):
  #Rescribimos el constructor para añadir nuevos atributos
  def __init__(self, id, nombre, precio, fecha_caducidad):
    super().__init__(id, nombre, precio)
    self.fecha_caducidad = fecha_caducidad
  # Método para verificar si el producto está caducado con la fecha actual
  def caducado(self):
    fecha_actual = datetime.date.today()
    fecha_caducidad = datetime.datetime.strptime(self.fecha_caducidad, "%Y-%m-%d").date()
    return fecha_actual > fecha_caducidad
  # Rescribimos el método para mostrar información
  def mostrarInformacion(self):
    info_base = super().mostrarInformacion()
    return f"{info_base}, Fecha de caducidad: {self.fecha_caducidad}"