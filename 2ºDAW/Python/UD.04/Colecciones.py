# 1. Gestión de una lista de estudiantes
#Creación de la lista inicial de estudiantes
estudiantes = ["Ana", "Luis", "Marta"]
print("Lista inicial de estudiantes:", estudiantes)

#Añadir un nuevo alumno a la lista
nuevo_alumno = input("Introduce el nombre de un nuevo alumno: ")
estudiantes.append(nuevo_alumno)
print("Lista después de añadir un alumno:", estudiantes)

#Eliminar un alumno de la lista
alumno_a_eliminar = input("Introduce el nombre del alumno a eliminar: ")
if alumno_a_eliminar in estudiantes:
    estudiantes.remove(alumno_a_eliminar)
    print("Lista después de eliminar un alumno:", estudiantes)
else:
    print(f"{alumno_a_eliminar} no se encuentra en la lista.")

#Ordenar la lista alfabéticamente
estudiantes.sort()
print("Lista ordenada alfabéticamente:", estudiantes)

# 2. Gestión de un diccionario de calificaciones
calificaciones = {
    "Ana": 8.5,
    "Luis": 7.0,
    "Marta": 9.2
}

#Añadir o actualizar una nota para un alumno
nombre_alumno_nota = input("Introduce el nombre del alumno para añadir/actualizar su nota: ")
try:
  #Pedir la nota del alumno
    nota_alumno = float(input(f"Introduce la nota de {nombre_alumno_nota} (0-10): "))
    #Validar que la nota esté entre 0 y 10
    if 0 <= nota_alumno <= 10:
        calificaciones[nombre_alumno_nota] = nota_alumno
        print(f"Nota de {nombre_alumno_nota} actualizada a {nota_alumno}.")
    else:
        print("La nota debe estar entre 0 y 10.")
except ValueError:
    print("Entrada inválida para la nota. Debe ser un número.")

#Consultar la nota de un alumno
alumno_a_consultar = input("Introduce el nombre del alumno para consultar su nota: ")
#Obtener la nota del alumno
nota_consultada = calificaciones.get(alumno_a_consultar, "No encontrado")
#Mostrar la nota
if nota_consultada != "No encontrado":
    print(f"La nota de {alumno_a_consultar} es: {nota_consultada}")
else:
    print(f"El alumno {alumno_a_consultar} no tiene una nota registrada.")

print("\nTodos los alumnos y sus notas:")
for alumno, nota in calificaciones.items():
    print(f"{alumno} - {nota}")

#Calcular la nota media
if calificaciones:
    total_notas = sum(calificaciones.values())
    media = total_notas / len(calificaciones)
    print(f"\nNota media de la clase: {media:.2f}")
else:
    print("\nNo hay calificaciones para calcular la media.")

# 3. Guardar información en un archivo
try:
    with open("alumnos.txt", "w") as archivo:
        for alumno, nota in calificaciones.items():
            archivo.write(f"{alumno} - {nota}\n")
    print("\nInformación de alumnos y calificaciones guardada en 'alumnos.txt'.")
except IOError:
    print("Error al intentar escribir en el archivo 'alumnos.txt'.")