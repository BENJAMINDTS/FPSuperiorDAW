"""
# Operaciones con Listas

Crea una lista vacía, añade tres elementos introducidos por el usuario,
muestra el primero y el último, imprime su longitud y añade un cuarto elemento.
"""

# Crear una lista vacía para almacenar los elementos del usuario
mi_lista = []

# Añadir tres elementos solicitados al usuario
mi_lista.append(input("Ingrese el primer elemento de la lista: "))
mi_lista.append(input("Ingrese el segundo elemento de la lista: "))
mi_lista.append(input("Ingrese el tercer elemento de la lista: "))

# Acceder al primer y último elemento por índice
print("Primer elemento de la lista:", mi_lista[0])
print("Último elemento de la lista:", mi_lista[-1])  # -1 apunta siempre al último elemento

# Mostrar la longitud actual de la lista
print("Longitud de la lista:", len(mi_lista))

# Añadir un nuevo elemento y mostrar la lista actualizada
nuevo_elemento = input("Ingrese un nuevo elemento para agregar a la lista: ")
mi_lista.append(nuevo_elemento)
print("Lista actualizada:", mi_lista)
