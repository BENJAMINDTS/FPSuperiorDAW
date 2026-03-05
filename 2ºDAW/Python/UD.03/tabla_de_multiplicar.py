def pedirNumeroEntero(prompt):
    while True:
        try:
            valor = int(input(prompt))
            return valor
        except ValueError:
            print("Por favor, ingresa un número entero válido.")
  
def multiplicar_tabla(numero, hasta=10):
    tabla = []
    for i in range(1, hasta + 1):
        tabla.append(f"{numero} x {i} = {numero * i}")
    return tabla
  
def imprimir_tabla(tabla):
    for linea in tabla:
        print(linea)
if __name__ == "__main__":
    numero = pedirNumeroEntero("Introduce el número para generar su tabla de multiplicar: ")
    hasta = pedirNumeroEntero("¿Hasta qué número quieres multiplicar? ")
    tabla = multiplicar_tabla(numero, hasta)
    imprimir_tabla(tabla)