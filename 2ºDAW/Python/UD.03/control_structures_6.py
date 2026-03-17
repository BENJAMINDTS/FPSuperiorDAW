"""
# Estructuras de Control - Contador de Caracteres

Solicita un texto al usuario y cuenta cuántos caracteres contiene
recorriendo el string con un bucle `for`.
"""

texto = input("Ingrese un texto: ")
contador = 0  # Inicializar el contador a cero

# Contar cada carácter del texto (incluye espacios y signos)
for letra in texto:
    contador += 1

print("El texto tiene", contador, "letras.")
