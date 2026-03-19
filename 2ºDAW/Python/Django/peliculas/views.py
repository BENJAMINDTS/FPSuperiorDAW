"""
@author BenjaminDTS

Vistas de la aplicación peliculas (ejercicio final).

Implementa las operaciones CRUD completas sobre el modelo Pelicula:

- Insertar una película
- Mostrar todas las películas
- Mostrar una película por ID
- Actualizar el título de una película
- Eliminar una película

:doc-url: https://docs.djangoproject.com/en/6.0/topics/http/views/
"""

from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse

from .models import Pelicula


def insertar_pelicula(request):
    """
    Vista que inserta una película de ejemplo en la base de datos.

    Crea un registro fijo para demostrar la operación de inserción.
    En un proyecto real se usaría un formulario con datos del usuario.

    :param request: Objeto HttpRequest de Django.
    :return: HttpResponse confirmando la inserción con el ID generado.
    """
    pelicula = Pelicula.objects.create(
        titulo="El Padrino",
        director="Francis Ford Coppola",
        anio=1972
    )
    return HttpResponse(
        f"Película insertada: '{pelicula.titulo}' (ID: {pelicula.pk})"
    )


def lista_peliculas(request):
    """
    Vista que muestra todas las películas almacenadas.

    Recupera todo el queryset de Pelicula ordenado por año descendente.

    :param request: Objeto HttpRequest de Django.
    :return: TemplateResponse con el queryset de películas.
    """
    peliculas = Pelicula.objects.all()
    return render(
        request,
        'peliculas/lista.html',
        {'peliculas': peliculas}
    )


def detalle_pelicula(request, pelicula_id):
    """
    Vista que muestra una película específica por su ID.

    Retorna 404 si la película con el ID indicado no existe.

    :param request: Objeto HttpRequest de Django.
    :param pelicula_id: ID entero de la película a mostrar.
    :return: TemplateResponse con los datos de la película.
    """
    pelicula = get_object_or_404(Pelicula, pk=pelicula_id)
    return render(
        request,
        'peliculas/detalle.html',
        {'pelicula': pelicula}
    )


def actualizar_titulo(request, pelicula_id):
    """
    Vista que actualiza el título de la película indicada.

    Cambia el título a un valor de demostración y guarda el cambio.
    En un proyecto real se recogería el nuevo título desde un formulario.

    :param request: Objeto HttpRequest de Django.
    :param pelicula_id: ID de la película cuyo título se actualizará.
    :return: HttpResponse confirmando el cambio de título.
    """
    pelicula = get_object_or_404(Pelicula, pk=pelicula_id)
    titulo_anterior = pelicula.titulo
    pelicula.titulo = "El Padrino: Edición Especial"
    pelicula.save()
    return HttpResponse(
        f"Título actualizado: '{titulo_anterior}' → '{pelicula.titulo}'"
    )


def eliminar_pelicula(request, pelicula_id):
    """
    Vista que elimina la película con el ID indicado.

    :param request: Objeto HttpRequest de Django.
    :param pelicula_id: ID de la película a eliminar.
    :return: HttpResponse confirmando la eliminación.
    """
    pelicula = get_object_or_404(Pelicula, pk=pelicula_id)
    titulo = pelicula.titulo
    pelicula.delete()
    return HttpResponse(f"Película '{titulo}' eliminada correctamente")
