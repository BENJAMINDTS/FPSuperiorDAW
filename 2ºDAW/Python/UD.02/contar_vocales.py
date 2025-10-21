texto = "Hola, aqui programando en python"
# Inicializar el contador de VOCALES
contador_vocales = 0
VOCALES = "aeiouAEIOU"
# Recorrer cada carácter en el texto
for caracter in texto:
    # Verificar si el carácter es una vocal
    if caracter in VOCALES:
        contador_vocales += 1
print("El numero de VOCALES en el texto es:", contador_vocales)

# Se puede utilizar una comprensión de listas junto con la función len() para simplificar el código
contador_vocales_funcion = len([caracter for caracter in texto if caracter in VOCALES])
print("El numero de VOCALES usando comprension de listas es:", contador_vocales_funcion)