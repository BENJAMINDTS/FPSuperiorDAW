"""
@author BenjaminDTS

Modelos de la aplicación peliculas.

Define el modelo ``Pelicula`` con los campos título, director y año,
mapeado a la tabla ``peliculas_pelicula`` en la base de datos SQLite.

:doc-url: https://docs.djangoproject.com/en/6.0/topics/db/models/
"""

from django.db import models


class Pelicula(models.Model):
    """
    Modelo que representa una película en el catálogo.

    :param titulo: Título de la película, máximo 200 caracteres.
    :param director: Nombre del director, máximo 150 caracteres.
    :param anio: Año de estreno de la película (entero de 4 dígitos).
    """

    titulo = models.CharField(max_length=200, verbose_name="Título")
    director = models.CharField(max_length=150, verbose_name="Director")
    anio = models.IntegerField(verbose_name="Año")

    class Meta:
        verbose_name = "Película"
        verbose_name_plural = "Películas"
        ordering = ['-anio']

    def __str__(self):
        """Representación legible de la película en el panel de admin."""
        return f"{self.titulo} ({self.anio}) - {self.director}"
