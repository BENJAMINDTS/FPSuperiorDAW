"""
# Manejo de Excepciones en Archivos

Demuestra el uso de bloques `try/except/finally` en operaciones de lectura
y escritura de archivos. Se muestran tres casos prácticos.
"""

# ---- CASO 1: Intentar abrir un archivo que no existe ----
print("=== CASO 1: Archivo que no existe ===")
try:
    # Intentar leer un archivo inexistente
    with open("archivo1.txt", "r") as f:
        texto = f.read()
    with open("archivo1.txt", "a") as f:
        f.write("Nueva linea de texto.\n")
except FileNotFoundError:
    print("Archivo no encontrado.")
except PermissionError:
    print("Permiso denegado para acceder al archivo.")
except Exception as e:
    print(f"Error inesperado: {e}")
finally:
    # El bloque finally siempre se ejecuta, haya error o no
    print("Operacion finalizada.")

print("-----")

# ---- CASO 2: Lectura y escritura en modo separado ----
print("=== CASO 2: Lectura y escritura correcta ===")
try:
    # Leer el contenido del archivo
    with open("archivo.txt", "r") as f:
        texto = f.read()
        print(f"Contenido leido: {texto}")

    # Añadir una nueva línea al archivo (modo append)
    with open("archivo.txt", "a") as f:
        f.write("Nueva linea de texto.\n")
        print("Linea escrita correctamente")

except FileNotFoundError:
    print("Archivo no encontrado.")
except PermissionError:
    print("Permiso denegado para acceder al archivo.")
except Exception as e:
    print(f"Error: {e}")
finally:
    print("Operacion finalizada.")

print("-----")

# ---- CASO 3: Lectura y escritura en modo combinado (r+) ----
print("=== CASO 3: Modo lectura/escritura correcto ===")
try:
    # Abrir en modo r+ permite leer y escribir en el mismo archivo
    with open("archivo.txt", "r+") as f:
        texto = f.read()
        print(f"Contenido leido: {texto}")
        f.write("Otra linea de texto.\n")
        print("Linea escrita correctamente")

except FileNotFoundError:
    print("Archivo no encontrado.")
except PermissionError:
    print("Permiso denegado para acceder al archivo.")
except ValueError:
    print("Modo de apertura invalido.")
except Exception as e:
    print(f"Error: {e}")
finally:
    print("Operacion finalizada.")
