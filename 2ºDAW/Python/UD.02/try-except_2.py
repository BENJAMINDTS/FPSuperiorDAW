import sys

lista = [1, 2, 3, 4]
try:
    for elemento in lista:
        print(f"Elemento: {elemento}")
except IndexError as e:
    print(f"Error: {e}")
    sys.exit(1)
finally:
    print("Finalizando el acceso a la lista.")