"""
PROGRAMA: Cálculo del área de un rectángulo
AUTOR: Benjamin Santiago González
FECHA: 19/11/2025

DESCRIPCIÓN:
Calcula el área de un rectángulo solicitando base y altura al usuario.

EJEMPLO DE SALIDA ESPERADA:
Introduce la base: 5
Introduce la altura: 3
El área es: 15.0
"""

# FALLO 1 CORREGIDO: Se añadieron los dos puntos (:) al final de la definición de la función
def area_rectangulo(base, altura):
    # FALLO 2 CORREGIDO: La fórmula del área es base * altura, no base ** altura
    # ** es el operador de exponenciación, no de multiplicación
    area = base * altura
    return area

# FALLO 3 CORREGIDO: input() devuelve strings, necesitamos convertirlos a números
base = float(input('Introduce la base: '))  # Convertir a float para decimales
altura = float(input('Introduce la altura: '))

area = area_rectangulo(base, altura)

# FALLO 4 CORREGIDO: Se añadió la coma (,) para separar los elementos en print()
# FALLO 5 CORREGIDO: Se usa f-string (opción recomendada)
print(f'El área es: {area}')