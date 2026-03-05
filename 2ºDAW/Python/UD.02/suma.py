numeros =[1,3,5,7,9]
suma = 0
# Realizar la suma de los números en la lista usando un bucle for
for numero in numeros:
    suma += numero
print("La suma es:", suma)

#Se puede utilizar la función sum() para simplificar el código
suma_funcion = sum(numeros)
print("La suma usando la función sum() es:", suma_funcion)
