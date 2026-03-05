def media_de_dos_numeros(num1, num2):
    return (num1 + num2) / 2
  
def media_de_varios_numeros(*args):
    if len(args) == 0:
        return 0
    return sum(args) / len(args)
  
def entrada_numeros():
    numeros = []
    while True:
        entrada = input("Introduce un número (o 'fin' para terminar): ")
        if entrada.lower() == 'fin':
            break
        try:
            numero = float(entrada)
            numeros.append(numero)
        except ValueError:
            print("Por favor, introduce un número válido.")
    return numeros

def best_function(media_de_dos_numeros, media_de_varios_numeros, numeros):
    if len(numeros) == 2:
        media_dos = media_de_dos_numeros(numeros[0], numeros[1])
        print(f"La media los dos números ({numeros[0]} y {numeros[1]}) es: {media_dos}")
    elif len(numeros) > 2:
      media_varios = media_de_varios_numeros(*numeros)
      print(f"La media de todos los números introducidos es: {media_varios}")
    else:
        print("No se han introducido suficientes números para calcular la media")

numeros = entrada_numeros()

best_function(media_de_dos_numeros, media_de_varios_numeros, numeros)