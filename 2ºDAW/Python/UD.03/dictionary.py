"""
# Gestor de Personas

Sistema interactivo de gestión de personas usando diccionarios y listas.
Permite añadir personas, consultar sus datos y ver estadísticas a través
de un menú por consola.
"""

# Lista global para almacenar múltiples diccionarios de personas
lista_personas = []


def añadir_persona():
    """Solicita los datos de una nueva persona y la añade a `lista_personas`.

    Valida que el nombre no esté vacío, que la edad sea un entero entre
    0 y 120, y que la respuesta de estudiante sea 's' o 'n'.

    Raises:
        ValueError: Si la edad introducida no es un entero válido.
    """
    try:
        nombre = input("Introduce tu nombre: ").strip()
        if not nombre:
            print("Error: El nombre no puede estar vacío.")
            return

        edad = input("Introduce tu edad: ")
        edad_int = int(edad)
        if edad_int < 0 or edad_int > 120:
            print("Error: La edad debe ser un número válido entre 0 y 120.")
            return

        # Validar la respuesta hasta que sea 's' o 'n'
        estudiante_input = input("¿Eres estudiante? (s/n): ").strip().lower()
        while estudiante_input not in ['s', 'n']:
            print("Por favor, responde con 's' para sí o 'n' para no.")
            estudiante_input = input("¿Eres estudiante? (s/n): ").strip().lower()

        es_estudiante = estudiante_input in ['s']

        # Crear nuevo diccionario para esta persona
        nueva_persona = {
            "nombre": nombre,
            "edad": edad_int,
            "estudiante": es_estudiante
        }

        # Añadir a la lista global
        lista_personas.append(nueva_persona)
        print(f"✓ Persona '{nombre}' añadida correctamente. Total de personas: {len(lista_personas)}")

    except ValueError:
        print("Error: La edad debe ser un número entero.")


def ver_todas_las_personas():
    """Muestra la información completa de todas las personas registradas."""
    if not lista_personas:
        print("No hay personas registradas. Usa la opción 1 para añadir personas.")
        return

    print("\n" + "=" * 40)
    print("          TODAS LAS PERSONAS")
    print("=" * 40)

    for indice, persona in enumerate(lista_personas, 1):
        print(f"\n--- Persona {indice} ---")
        for clave, valor in persona.items():
            if clave == "estudiante":
                valor_str = "Sí" if valor else "No"
            elif clave == "edad":
                # Añadir mensaje descriptivo según la edad
                edad = valor
                mensaje = gestionarEdad(edad)
                valor_str = f"{valor} años{mensaje}"
            else:
                valor_str = valor
            print(f"{clave.capitalize()}: {valor_str}")


def ver_todos_los_nombres():
    """Muestra únicamente los nombres de todas las personas registradas."""
    if not lista_personas:
        print("No hay personas registradas. Usa la opción 1 para añadir personas.")
        return

    print("\n" + "=" * 30)
    print("     TODOS LOS NOMBRES")
    print("=" * 30)

    for indice, persona in enumerate(lista_personas, 1):
        print(f"{indice}. {persona['nombre']}")


def gestionarEdad(edad):
    """Devuelve un mensaje descriptivo según el rango de edad.

    Args:
        edad (int): Edad de la persona.

    Returns:
        str: Mensaje con la categoría etaria correspondiente.
    """
    if edad < 18:
        return " (Eres menor de edad)"
    elif 18 <= edad <= 25:
        return " (Eres muy joven)"
    elif 26 <= edad <= 40:
        return " (Eres joven)"
    else:
        return " (Ya no eres joven)"


def ver_todas_las_edades():
    """Muestra las edades de todas las personas con su categoría etaria."""
    if not lista_personas:
        print("No hay personas registradas. Usa la opción 1 para añadir personas.")
        return

    print("\n" + "=" * 30)
    print("     TODAS LAS EDADES")
    print("=" * 30)

    for indice, persona in enumerate(lista_personas, 1):
        edad = persona['edad']
        nombre = persona['nombre']
        mensaje = gestionarEdad(edad)
        print(f"{indice}. {nombre}: {edad} años{mensaje}")


def ver_todos_los_estudiantes():
    """Muestra el estado de estudiante de cada persona registrada."""
    if not lista_personas:
        print("No hay personas registradas. Usa la opción 1 para añadir personas.")
        return

    print("\n" + "=" * 35)
    print("     ESTADO DE ESTUDIANTE")
    print("=" * 35)

    for indice, persona in enumerate(lista_personas, 1):
        estado = "Sí" if persona['estudiante'] else "No"
        print(f"{indice}. {persona['nombre']}: {estado}")


def ver_estadisticas():
    """Calcula y muestra estadísticas generales de las personas registradas.

    Muestra el total de personas, el número de estudiantes y la edad media.
    """
    if not lista_personas:
        print("No hay personas registradas.")
        return

    total_personas = len(lista_personas)
    total_estudiantes = sum(1 for persona in lista_personas if persona['estudiante'])
    promedio_edad = sum(persona['edad'] for persona in lista_personas) / total_personas

    print("\n" + "=" * 30)
    print("     ESTADÍSTICAS")
    print("=" * 30)
    print(f"Total de personas: {total_personas}")
    print(f"Total de estudiantes: {total_estudiantes}")
    print(f"Promedio de edad: {promedio_edad:.1f} años")


def imprimirMenu():
    """Muestra el menú principal y devuelve la opción seleccionada.

    Returns:
        str: El carácter introducido por el usuario ('1'-'7').
    """
    while True:
        print("\n" + "=" * 40)
        print("          MENÚ INTERACTIVO")
        print("=" * 40)
        print("¿Qué quieres hacer?")
        print("1. Añadir nueva persona")
        print("2. Ver todas las personas")
        print("3. Ver todos los nombres")
        print("4. Ver todas las edades")
        print("5. Ver estado de estudiante")
        print("6. Ver estadísticas")
        print("7. Salir")

        opcion = input("\nSelecciona una opción (1-7): ").strip()
        return opcion


def ejecutar_opcion(opcion):
    """Ejecuta la acción correspondiente a la opción del menú elegida.

    Args:
        opcion (str): Opción seleccionada por el usuario ('1'-'7').
    """
    if opcion == '1':
        añadir_persona()
    elif opcion == '2':
        ver_todas_las_personas()
    elif opcion == '3':
        ver_todos_los_nombres()
    elif opcion == '4':
        ver_todas_las_edades()
    elif opcion == '5':
        ver_todos_los_estudiantes()
    elif opcion == '6':
        ver_estadisticas()
    elif opcion == '7':
        print("Saliendo del programa. ¡Hasta luego!")
        exit()
    else:
        print("Opción no válida. Por favor, selecciona una opción del 1 al 7.")


def menu():
    """Bucle principal del programa: muestra el menú y procesa las opciones."""
    while True:
        opcion = imprimirMenu()
        ejecutar_opcion(opcion)


if __name__ == "__main__":
    print("Bienvenido al gestor de personas")
    print("Puedes añadir múltiples personas al sistema\n")
    menu()
