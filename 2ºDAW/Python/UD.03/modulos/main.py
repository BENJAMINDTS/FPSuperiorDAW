import operaciones

def pedirNumeros():
    num1 = float(input("Ingrese el primer número: "))
    num2 = float(input("Ingrese el segundo número: "))
    return num1, num2
  
def mostrarMenu():
    print("Seleccione la operación que desea realizar:")
    print("1. Sumar")
    print("2. Restar")
    print("3. Multiplicar")
    print("4. Dividir")
    eleccion = input("Ingrese el número de la operación (1/2/3/4): ")
    return eleccion

def elegirOperacion(eleccion, num1, num2):
    if eleccion == '1':
        resultado = operaciones.sumar(num1, num2)
        print(f"La suma de {num1} y {num2} es: {resultado}")
    elif eleccion == '2':
        resultado = operaciones.restar(num1, num2)
        print(f"La resta de {num1} y {num2} es: {resultado}")
    elif eleccion == '3':
        resultado = operaciones.multiplicar(num1, num2)
        print(f"La multiplicación de {num1} y {num2} es: {resultado}")
    elif eleccion == '4':
        try:
            resultado = operaciones.dividir(num1, num2)
            print(f"La división de {num1} entre {num2} es: {resultado}")
        except ValueError as e:
            print(e)
    else:
        print("Operación no válida.")
    num1, num2 = pedirNumeros()

def main():
    num1, num2 = pedirNumeros()
    eleccion = mostrarMenu()
    elegirOperacion(eleccion, num1, num2)