"""
@author BenjaminDTS

Registro de modelos en el panel de administración de peliculas.

Registra el modelo ``Pelicula`` con una clase ``PeliculaAdmin``
que optimiza la visualización y búsqueda en Django Admin.

:doc-url: https://docs.djangoproject.com/en/6.0/ref/contrib/admin/
"""

from django.contrib import admin

from .models import Pelicula


@admin.register(Pelicula)
class PeliculaAdmin(admin.ModelAdmin):
    """
    Configuración del panel de administración para el modelo Pelicula.

    Muestra título, director y año en el listado, con búsqueda
    por título y director, y filtro lateral por año.
    """

    list_display = ('titulo', 'director', 'anio')
    search_fields = ('titulo', 'director')
    list_filter = ('anio', 'director')
    ordering = ('-anio',)
