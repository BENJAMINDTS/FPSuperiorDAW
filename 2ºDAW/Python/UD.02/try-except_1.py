print("=== CASO 1: Archivo que no existe ===")
try:
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
    print("Operacion finalizada.")

print("-----")

print("=== CASO 2: Lectura y escritura correcta ===")
try:
    with open("archivo.txt", "r") as f:
        texto = f.read()
        print(f"Contenido leido: {texto}")
    
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

print("=== CASO 3: Modo lectura/escritura correcto ===")
try:
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