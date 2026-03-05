#Creamos una lista para imprimir el primer y ultimo elemento, ver su longitud y agregar un nuevo elemento
mi_lista = []
#Agregar elementos a la lista
mi_lista.append(input("Ingrese el primer elemento de la lista: "))
mi_lista.append(input("Ingrese el segundo elemento de la lista: "))
mi_lista.append(input("Ingrese el tercer elemento de la lista: "))
#Imprimir el primer y ultimo elemento
print("Primer elemento de la lista:", mi_lista[0])
print("Último elemento de la lista:", mi_lista[-1])
#Imprimir la longitud de la lista
print("Longitud de la lista:", len(mi_lista))
#Agregar un nuevo elemento a la lista
nuevo_elemento = input("Ingrese un nuevo elemento para agregar a la lista: ")
mi_lista.append(nuevo_elemento)
print("Lista actualizada:", mi_lista)