"""
@author BenjaminDTS

Registro de modelos en el panel de administración de tienda.

Registra el modelo ``Libro`` con una clase personalizada ``LibroAdmin``
que mejora la visualización en el panel de Django Admin.

:doc-url: https://docs.djangoproject.com/en/6.0/ref/contrib/admin/
"""

from django.contrib import admin

from .models import Libro


@admin.register(Libro)
class LibroAdmin(admin.ModelAdmin):
    """
    Configuración del panel de administración para el modelo Libro.

    Muestra columnas útiles en el listado y habilita la búsqueda
    por título y autor para facilitar la gestión de registros.
    """

    list_display = ('titulo', 'autor', 'precio')
    search_fields = ('titulo', 'autor')
    list_filter = ('autor',)
    ordering = ('titulo',)
