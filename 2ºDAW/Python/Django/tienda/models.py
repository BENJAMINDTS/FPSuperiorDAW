"""
@author BenjaminDTS

Modelos de la aplicación tienda.

Define el modelo ``Libro`` con sus campos título, autor y precio,
que se mapea a la tabla ``tienda_libro`` en la base de datos SQLite.

:doc-url: https://docs.djangoproject.com/en/6.0/topics/db/models/
"""

from django.db import models


class Libro(models.Model):
    """
    Modelo que representa un libro en la tienda.

    :param titulo: Título del libro, máximo 200 caracteres.
    :param autor: Nombre del autor, máximo 150 caracteres.
    :param precio: Precio del libro en euros, con dos decimales.
    """

    titulo = models.CharField(max_length=200, verbose_name="Título")
    autor = models.CharField(max_length=150, verbose_name="Autor")
    precio = models.DecimalField(
        max_digits=8,
        decimal_places=2,
        verbose_name="Precio (€)"
    )

    class Meta:
        verbose_name = "Libro"
        verbose_name_plural = "Libros"
        ordering = ['titulo']

    def __str__(self):
        """Representación legible del libro para el panel de admin."""
        return f"{self.titulo} - {self.autor} - {self.precio}€"
