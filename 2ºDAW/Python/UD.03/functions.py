
def presentar(nombre, edad):
    print(f"Hola, mi nombre es {nombre} y tengo {edad} años.")


def entrada():
    nombre = input("Introduce tu nombre: ")
    while True:
        try:
            edad = int(input("Introduce tu edad: "))
            break
        except ValueError:
            print("Por favor, introduce un número válido para la edad.")
            False
    return nombre,edad

nombre, edad = entrada()
presentar(nombre, edad)