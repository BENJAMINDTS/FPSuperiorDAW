try:
  edad = int(input("Ingrese su edad: "))

except ValueError:
  print("Por favor, ingrese un número válido para la edad.")
else:
    if edad < 0:
      print("La edad no puede ser negativa.")
    elif edad < 18:
      print("Eres menor de edad.")
    elif 18> edad < 65:
      print("Eres un adulto.")
    else:
      print("Eres mayor.")