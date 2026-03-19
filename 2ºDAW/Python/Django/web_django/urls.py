"""
@author BenjaminDTS

Configuración principal de URLs del proyecto web_django.

Enruta las peticiones hacia los módulos de URLs de cada aplicación
y expone el panel de administración de Django.

:doc-url: https://docs.djangoproject.com/en/6.0/topics/http/urls/
"""

from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    # Panel de administración de Django
    path('admin/', admin.site.urls),

    # Rutas de la aplicación tienda (ejercicios 3–14)
    path('', include('tienda.urls')),

    # Rutas de la aplicación peliculas (ejercicio final)
    path('peliculas/', include('peliculas.urls')),
]
