import operaciones

def entrada_operaciones():
    print("Seleccione la operación que desea realizar:")
    print("1. Suma")
    print("2. Resta")
    print("3. Multiplicación")
    print("4. División")
    while True:
        eleccion = input("Ingrese el número de la operación (1/2/3/4): ")
        if eleccion in ['1', '2', '3', '4']:
            break
        else:
            print("Selección inválida. Por favor, elija una opción válida.")
    while True:
        try:
            num1 = float(input("Ingrese el primer número: "))
            num2 = float(input("Ingrese el segundo número: "))
            break
        except ValueError:
            print("Entrada inválida. Por favor, ingrese números válidos.")
    return eleccion, num1, num2

def realizar_operacion(eleccion, num1, num2):
    if eleccion == '1':
        resultado = operaciones.suma(num1, num2)
        operacion = "suma"
    elif eleccion == '2':
        resultado = operaciones.resta(num1, num2)
        operacion = "resta"
    elif eleccion == '3':
        resultado = operaciones.multiplicacion(num1, num2)
        operacion = "multiplicación"
    elif eleccion == '4':
        resultado = operaciones.division(num1, num2)
        operacion = "división"
    print(f"El resultado de la {operacion} entre {num1} y {num2} es: {resultado}")
  
eleccion, num1, num2 = entrada_operaciones()
realizar_operacion(eleccion, num1, num2)