
# Actividad 1
class Libro:
    def __init__(self, titulo, autor, isbn):
        self.__titulo = titulo
        self.__autor = autor
        self.__isbn = isbn

    def get_titulo(self):
        return self.__titulo
    
    def get_autor(self):
        return self.__autor
    
    def mostrar_info(self):
        return f"Título: {self.__titulo}, Autor: {self.__autor}, ISBN: {self.__isbn}"
    
# Crear instancia de Libro
libro1 = Libro("Cien Años de Soledad", "Gabriel García Márquez", "978-3-16-148410-0")
libro2 = Libro("1984", "George Orwell", "978-0-452-28423-4")
libro1_info = libro1.mostrar_info()
libro2_info = libro2.mostrar_info()

print(libro1_info)
print(libro2_info)

# Actividad 2

class LibroDigital(Libro):
    def __init__(self, titulo, autor, isbn, tamaño_mb):
        super().__init__(titulo, autor, isbn)
        self.__tamaño_mb = tamaño_mb

    def mostrar_info(self):
        info_base = super().mostrar_info()
        return f"{info_base}, tamaño_mb: {self.__tamaño_mb}"
    
# Crear instancia de LibroDigital
libro_digital1 = LibroDigital("El Principito", "Antoine de Saint-Exupéry", "978-0-15-601219-5", 2)
libro_digital2 = LibroDigital("Sapiens", "Yuval Noah Harari", "978-0-06-231609-7", 5)
libro_digital1_info = libro_digital1.mostrar_info()
libro_digital2_info = libro_digital2.mostrar_info()

print(libro_digital1_info)
print(libro_digital2_info)

# Actividad 3
import sqlite3
class BibliotecaDB:

    def __init__(self, nombre_db="biblioteca.db"):
        self.nombre_db = nombre_db
        self.conexion = sqlite3.connect(self.nombre_db)
        self.cursor = self.conexion.cursor()
        self.crear_tabla()
    def crear_tabla(self):
        self.cursor.execute("""
            CREATE TABLE IF NOT EXISTS libros (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT NOT NULL,
                autor TEXT NOT NULL,
                isbn TEXT NOT NULL
            )
        """)
        self.conexion.commit()
    def insertar_libro(self, libro):
        self.cursor.execute("""
            INSERT INTO libros (titulo, autor, isbn)
            VALUES (?, ?, ?)
        """, (libro.get_titulo(), libro.get_autor(), libro._Libro__isbn))  # accedemos al atributo privado
        self.conexion.commit()
    def mostrar_libros(self):
        self.cursor.execute("SELECT id, titulo, autor, isbn FROM libros")
        libros = self.cursor.fetchall()
        print("\n----- Libros en la base de datos -----")
        for l in libros:
            print(f"ID: {l[0]}, Título: {l[1]}, Autor: {l[2]}, ISBN: {l[3]}")
    def cerrar(self):
        self.conexion.close()
biblioteca = BibliotecaDB()
biblioteca.insertar_libro(libro1)
biblioteca.insertar_libro(libro2)
biblioteca.insertar_libro(libro_digital1)
biblioteca.insertar_libro(libro_digital2)
biblioteca.mostrar_libros()
biblioteca.cerrar()
