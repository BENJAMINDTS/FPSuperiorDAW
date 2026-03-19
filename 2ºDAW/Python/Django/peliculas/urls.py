"""
@author BenjaminDTS

Configuración de URLs de la aplicación peliculas (ejercicio final).

Mapea las rutas CRUD de películas a sus vistas correspondientes.
Todas las rutas tienen el prefijo ``/peliculas/`` definido en el urls.py raíz.

:doc-url: https://docs.djangoproject.com/en/6.0/topics/http/urls/
"""

from django.urls import path

from . import views

urlpatterns = [
    # Insertar una película de ejemplo en la base de datos
    path('insertar', views.insertar_pelicula, name='insertar_pelicula'),

    # Mostrar todas las películas almacenadas
    path('', views.lista_peliculas, name='lista_peliculas'),

    # Mostrar una película específica por su ID
    path('<int:pelicula_id>', views.detalle_pelicula, name='detalle_pelicula'),

    # Actualizar el título de la película con el ID dado
    path(
        '<int:pelicula_id>/actualizar',
        views.actualizar_titulo,
        name='actualizar_titulo'
    ),

    # Eliminar la película con el ID dado
    path(
        '<int:pelicula_id>/eliminar',
        views.eliminar_pelicula,
        name='eliminar_pelicula'
    ),
]
