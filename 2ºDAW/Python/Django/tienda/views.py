"""
@author BenjaminDTS

Vistas de la aplicación tienda.

Contiene las vistas para los ejercicios 3 al 14:

- Vistas simples de texto (ejercicios 3–5)
- Vistas con templates (ejercicios 6–8)
- Vistas CRUD sobre el modelo Libro (ejercicios 11–14)

:doc-url: https://docs.djangoproject.com/en/6.0/topics/http/views/
"""

from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse

from .models import Libro


def inicio(request):
    """
    Ejercicio 3: Vista simple que muestra un mensaje de bienvenida.

    :param request: Objeto HttpRequest de Django.
    :return: HttpResponse con el mensaje de bienvenida.
    """
    return HttpResponse("Bienvenido a mi primera aplicación Django")


def saludo(request, nombre):
    """
    Ejercicio 4: Vista con parámetro de URL que personaliza el saludo.

    :param request: Objeto HttpRequest de Django.
    :param nombre: Nombre de la persona extraído de la URL ``/saludo/<nombre>``.
    :return: HttpResponse con el saludo personalizado.
    """
    return HttpResponse(f"Hola {nombre}, bienvenido a Django")


def suma(request, num1, num2):
    """
    Ejercicio 5: Vista que recibe dos números y devuelve su suma.

    :param request: Objeto HttpRequest de Django.
    :param num1: Primer número entero extraído de la URL ``/suma/<num1>/<num2>``.
    :param num2: Segundo número entero extraído de la URL.
    :return: HttpResponse con el resultado de la suma.
    """
    resultado = num1 + num2
    return HttpResponse(f"La suma es {resultado}")


def inicio_template(request):
    """
    Ejercicio 6: Vista que renderiza el template inicio.html.

    :param request: Objeto HttpRequest de Django.
    :return: TemplateResponse renderizando ``tienda/inicio.html``.
    """
    return render(request, 'tienda/inicio.html')


def saludo_template(request):
    """
    Ejercicio 7: Vista que pasa la variable ``nombre`` al template.

    :param request: Objeto HttpRequest de Django.
    :return: TemplateResponse con la variable nombre en el contexto.
    """
    contexto = {'nombre': 'Carlos'}
    return render(request, 'tienda/saludo.html', contexto)


def lista_frutas(request):
    """
    Ejercicio 8: Vista que pasa una lista de frutas al template.

    :param request: Objeto HttpRequest de Django.
    :return: TemplateResponse con la lista de frutas en el contexto.
    """
    frutas = ["manzana", "pera", "plátano", "naranja"]
    return render(request, 'tienda/frutas.html', {'frutas': frutas})


def lista_libros(request):
    """
    Ejercicio 11: Vista que muestra todos los libros almacenados.

    Recupera todos los registros ``Libro`` de la base de datos.

    :param request: Objeto HttpRequest de Django.
    :return: TemplateResponse con el queryset de libros.
    """
    libros = Libro.objects.all()
    return render(request, 'tienda/libros.html', {'libros': libros})


def detalle_libro(request, libro_id):
    """
    Ejercicio 12: Vista que muestra un libro específico por su ID.

    Utiliza ``get_object_or_404`` para retornar 404 si el libro no existe.

    :param request: Objeto HttpRequest de Django.
    :param libro_id: ID entero del libro a mostrar.
    :return: TemplateResponse con los datos del libro.
    """
    libro = get_object_or_404(Libro, pk=libro_id)
    return render(request, 'tienda/detalle_libro.html', {'libro': libro})


def actualizar_precio(request, libro_id):
    """
    Ejercicio 13: Vista que actualiza el precio de un libro a 25.

    Modifica el campo ``precio`` del libro y persiste el cambio.

    :param request: Objeto HttpRequest de Django.
    :param libro_id: ID del libro a actualizar.
    :return: HttpResponse confirmando la actualización.
    """
    libro = get_object_or_404(Libro, pk=libro_id)
    libro.precio = 25
    libro.save()
    return HttpResponse(
        f"Precio del libro '{libro.titulo}' actualizado a 25€"
    )


def eliminar_libro(request, libro_id):
    """
    Ejercicio 14: Vista que elimina el libro con el ID indicado.

    :param request: Objeto HttpRequest de Django.
    :param libro_id: ID del libro a eliminar.
    :return: HttpResponse confirmando la eliminación.
    """
    libro = get_object_or_404(Libro, pk=libro_id)
    titulo = libro.titulo
    libro.delete()
    return HttpResponse(f"Libro '{titulo}' eliminado correctamente")
