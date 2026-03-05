try:
    numero = int(input("Por favor, ingresa un numero entero: "))
    print(f"Has ingresado el numero: {numero}")
except ValueError:
    print("Error: No has ingresado un numero entero válido.")