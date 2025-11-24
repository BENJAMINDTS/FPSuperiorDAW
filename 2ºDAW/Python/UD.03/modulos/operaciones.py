def sumar (a, b):
    """Devuelve la suma de dos números."""
    return a + b

def restar (a, b):
    """Devuelve la resta de dos números."""
    return a - b
  
def multiplicar (a, b):
    """Devuelve el producto de dos números."""
    return a * b
  
def dividir (a, b):
    """Devuelve la división de dos números. Lanza una excepción si el divisor es cero."""
    if b == 0:
        raise ValueError("No se puede dividir por cero.")
    return a / b