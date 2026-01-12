#RA04_D
#Lista de productos
productos = ["Camisa", "Pantalón", "Zapatos", "Reloj", "Gorra"]
# Tupla de productos
productos_t = ("Camisa", "Pantalón", "Zapatos", "Reloj", "Gorra", "Calcetines")

print("\n--- Diferencias entre Listas y Tuplas ---")
print("Lista de productos (variable 'productos'):", productos)
print("Tupla de productos (variable 'productos_t'):", productos_t)

print("\nExplicación de diferencias:")
print("1. Mutabilidad:")
print("   - Listas (como 'productos') son mutables: sus elementos pueden ser modificados, añadidos o eliminados después de su creación.")
print("     Ejemplo: productos.append('Bufanda') es válido.")
print("   - Tuplas (como 'productos_t') son inmutables: una vez creadas, sus elementos no pueden ser modificados, añadidos o eliminados.")
print("     Ejemplo: productos_t.append('Bufanda') generaría un error.")

print("\n2. Rendimiento:")
print("   - Las tuplas suelen ser ligeramente más rápidas que las listas para iterar o acceder a elementos, ya que su tamaño es fijo.")

print("\n3. Uso de memoria:")
print("   - Las tuplas pueden consumir un poco menos de memoria que las listas debido a su inmutabilidad.")

print("\n¿Cuándo sería mejor usar una tupla en lugar de una lista?")
print("Es mejor usar una tupla cuando tienes una colección de elementos que no esperas que cambien a lo largo de la ejecución del programa.")
print("Son ideales para representar colecciones de datos que deben permanecer constantes.")

print("\nEjemplo concreto de cuándo usar una tupla:")
print("Imagina que estás almacenando las coordenadas geográficas (latitud, longitud) de un punto específico.")
print("Estas coordenadas son fijas y no deberían cambiar. En este caso, una tupla es la elección ideal.")

coordenadas_ciudad = (40.7128, -74.0060) # Latitud y Longitud de Nueva York
print(f"Coordenadas de la ciudad: {coordenadas_ciudad}")
print(f"Latitud: {coordenadas_ciudad[0]}, Longitud: {coordenadas_ciudad[1]}")

print("\nIncluso si intentamos modificar la tupla, obtendremos un error llamado TypeError: 'tuple' object does not support item assignment.")
# Ejemplo de intento de modificación (descomentarlo para ver el error)
# coordenadas_ciudad[0] = 41.0000  # Esto generará un error