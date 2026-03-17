"""
# RA03_C - Ejercicio 4: Área del Rectángulo

Calcula el área de un rectángulo solicitando base y altura al usuario.
Este ejercicio también documenta los fallos del código original y sus correcciones.

**Autor:** Benjamin Santiago González
**Fecha:** 19/11/2025

**Ejemplo de salida esperada:**
```
Introduce la base: 5
Introduce la altura: 3
El área es: 15.0
```
"""


# FALLO 1 CORREGIDO: Se añadieron los dos puntos (:) al final de la definición de la función
def area_rectangulo(base, altura):
    """Calcula el área de un rectángulo dado su base y altura.

    Args:
        base (float): Longitud de la base del rectángulo.
        altura (float): Altura del rectángulo.

    Returns:
        float: El área resultante (base × altura).
    """
    # FALLO 2 CORREGIDO: La fórmula del área es base * altura, no base ** altura
    # El operador ** es exponenciación, no multiplicación
    area = base * altura
    return area


# FALLO 3 CORREGIDO: input() devuelve str, por eso se convierte a float
base = float(input('Introduce la base: '))    # Convertir a float para admitir decimales
altura = float(input('Introduce la altura: '))

area = area_rectangulo(base, altura)

# FALLO 4 CORREGIDO: Se añadió la coma (,) para separar los elementos en print()
# FALLO 5 CORREGIDO: Se usa f-string (opción recomendada sobre concatenación)
print(f'El área es: {area}')
