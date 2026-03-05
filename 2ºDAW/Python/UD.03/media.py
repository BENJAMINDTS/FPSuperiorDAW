##Pedir al usuario numeros hasta que diga fin
def pedirNumeroEntero(prompt):
    while True:
        try:
            valor = int(input(prompt))
            return valor
        except ValueError:
            print("Por favor, ingresa un número entero válido.")

def calcular_media(numeros):
    if len(numeros) == 0:
        return 0
    return sum(numeros) / len(numeros)
  
if __name__ == "__main__":
    numeros = []
    print("Introduce números para calcular la media. Escribe 'fin' para terminar.")
    while True:
        entrada = input("Número: ")
        if entrada.lower() == 'fin':
            break
        try:
            numero = int(entrada)
            numeros.append(numero)
        except ValueError:
            print("Por favor, ingresa un número entero válido o 'fin' para terminar.")
  
    media = calcular_media(numeros)
    print(f"La media de los números introducidos es: {media}")