"""
@author BenjaminDTS

Configuración de URLs de la aplicación tienda.

Mapea cada ruta a su vista correspondiente, cubriendo los ejercicios
3 al 14: vistas simples, con parámetros, con templates y CRUD de libros.

:doc-url: https://docs.djangoproject.com/en/6.0/topics/http/urls/
"""

from django.urls import path

from . import views

urlpatterns = [
    # --- Ejercicios de vistas (3-5) ---
    # Ejercicio 3: mensaje de bienvenida simple
    path('inicio', views.inicio, name='inicio'),

    # Ejercicio 4: saludo personalizado con parámetro nombre
    path('saludo/<str:nombre>', views.saludo, name='saludo'),

    # Ejercicio 5: suma de dos números enteros pasados por URL
    path('suma/<int:num1>/<int:num2>', views.suma, name='suma'),

    # --- Ejercicios con templates (6-8) ---
    # Ejercicio 6: vista que carga inicio.html
    path('inicio-template', views.inicio_template, name='inicio_template'),

    # Ejercicio 7: vista que pasa variable nombre al template
    path('saludo-template', views.saludo_template, name='saludo_template'),

    # Ejercicio 8: vista que muestra lista de frutas con bucle en template
    path('frutas', views.lista_frutas, name='lista_frutas'),

    # --- Ejercicios con base de datos (11-14) ---
    # Ejercicio 11: listado de todos los libros
    path('libros', views.lista_libros, name='lista_libros'),

    # Ejercicio 12: detalle de un libro por ID
    path('libro/<int:libro_id>', views.detalle_libro, name='detalle_libro'),

    # Ejercicio 13: actualizar precio del libro con ID dado a 25€
    path(
        'libro/<int:libro_id>/actualizar',
        views.actualizar_precio,
        name='actualizar_precio'
    ),

    # Ejercicio 14: eliminar el libro con el ID dado
    path(
        'libro/<int:libro_id>/eliminar',
        views.eliminar_libro,
        name='eliminar_libro'
    ),
]
