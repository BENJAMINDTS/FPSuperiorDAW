"""
# Contar Vocales

Cuenta el número de vocales en una cadena de texto.
Se muestran dos enfoques: bucle `for` manual y comprensión de listas.
"""

texto = "Hola, aqui programando en python"

# Inicializar el contador de vocales a cero
contador_vocales = 0
VOCALES = "aeiouAEIOU"  # Cadena de referencia con todas las vocales (mayúsculas y minúsculas)

# Recorrer cada carácter del texto y verificar si es vocal
for caracter in texto:
    # Verificar si el carácter actual está en la cadena de vocales
    if caracter in VOCALES:
        contador_vocales += 1

print("El numero de VOCALES en el texto es:", contador_vocales)

# Alternativa más concisa usando comprensión de listas con len()
contador_vocales_funcion = len([caracter for caracter in texto if caracter in VOCALES])
print("El numero de VOCALES usando comprension de listas es:", contador_vocales_funcion)
