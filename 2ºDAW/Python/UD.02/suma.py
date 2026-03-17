"""
# Suma de Lista

Calcula la suma de los elementos de una lista de números.
Se comparan dos enfoques: bucle `for` manual y la función built-in `sum()`.
"""

numeros = [1, 3, 5, 7, 9]
suma = 0

# Calcular la suma iterando sobre cada elemento con un bucle for
for numero in numeros:
    suma += numero  # Acumular el valor en la variable suma

print("La suma es:", suma)

# Alternativa más concisa usando la función built-in sum()
suma_funcion = sum(numeros)
print("La suma usando la función sum() es:", suma_funcion)
