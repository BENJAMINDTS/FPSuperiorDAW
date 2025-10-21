# Clasificación de edad
# El usuario ingresa su edad y el programa clasifica su etapa de vida
# según los rangos establecidos.

# Definir una función para clasificar la edad
def clasificar_edad(edad):
  # Clasificar según los rangos dados
  if 1 <= edad <= 15:
    print("Eres niño/a")
  elif 16 <= edad <= 21:
    print("Eres adolescente")
  elif 22 <= edad <= 35:
    print("Eres joven")
  elif 36 <= edad <= 50:
    print("Eres maduro/a")
  elif 51 <= edad <= 60:
    print("Eres de mediana edad")
  elif 61 <= edad <= 80:
    print("Eres mayor")
  elif 81 <= edad <= 100:
    print("Eres viejo/a")
  else:
    print("Eres centenario/a")

# Solicitar al usuario que ingrese su edad hasta que sea válida
while True:
  # Manejar posibles errores de entrada
  try:
    edad = int(input("Introduce tu edad (entre 1 y 120): ")) # Solicitar edad
    if 1 <= edad <= 120: # Validar rango de edad
      clasificar_edad(edad)
      break
    else:
      print("Edad fuera de rango. Inténtalo de nuevo.") # Pedir reingreso
  except ValueError:
    print("Entrada no válida. Por favor, introduce un número.") # Manejar error de conversión

