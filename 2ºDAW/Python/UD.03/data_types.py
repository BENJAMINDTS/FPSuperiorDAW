"""
# Tipos de Datos y Casting

Demuestra los tipos de datos básicos en Python y cómo convertirlos
entre sí mediante casting explícito con `int()`, `float()` y `str()`.
"""

# ---- Ejercicio 1: Casting desde float ----
print("Tipos de datos y casting en Python")
print("Ejercicio 1")

# input() siempre devuelve str, se convierte a float con float()
decimal = float(input("Ingrese un número decimal: "))
print("El número decimal ingresado es de tipo:", type(decimal), "y su valor es:", decimal)

# Convertir el float a int (se trunca la parte decimal)
print("El número decimal ingresado es despues de hacerle casting a int es:", int(decimal))
# Convertir el float a str
print("El número decimal ingresado es despues de hacerle casting a String es:", str(decimal))

print("---------------------------------------------------------------------------")

# ---- Ejercicio 2: Casting desde str ----
print("Ejercicio 2")

# input() devuelve str; aquí se conserva como str para mostrar su tipo
entero = input("Ingrese un número entero: ")
print("El número entero ingresado es de tipo:", type(entero), "y su valor es:", entero)

# Convertir la cadena a int para operaciones aritméticas
print("El número entero ingresado es despues de hacerle casting a int es:", int(entero))
print("Vamos a realizar una opracion de suma entre el número entero y 10:", int(entero) + 10)
