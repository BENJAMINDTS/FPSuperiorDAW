"""
# OPT05 - Sistema de Biblioteca con SQLite

Implementa un sistema de biblioteca con tres actividades progresivas:

1. Clase `Libro` con encapsulación de atributos privados.
2. Clase `LibroDigital` que hereda de `Libro` y añade el tamaño en MB.
3. Clase `BibliotecaDB` que persiste los libros en una base de datos SQLite.
"""

import sqlite3


# ---- Actividad 1: Clase Libro ----

class Libro:
    """Representa un libro físico con título, autor e ISBN.

    Los atributos son privados y se accede a ellos mediante métodos getter.

    Attributes:
        __titulo (str): Título del libro.
        __autor (str): Nombre del autor.
        __isbn (str): Código ISBN del libro.
    """

    def __init__(self, titulo, autor, isbn):
        """Inicializa un libro con sus datos básicos.

        Args:
            titulo (str): Título del libro.
            autor (str): Nombre del autor.
            isbn (str): Código ISBN.
        """
        self.__titulo = titulo
        self.__autor = autor
        self.__isbn = isbn

    def get_titulo(self):
        """Devuelve el título del libro.

        Returns:
            str: El título del libro.
        """
        return self.__titulo

    def get_autor(self):
        """Devuelve el nombre del autor.

        Returns:
            str: El nombre del autor.
        """
        return self.__autor

    def mostrar_info(self):
        """Devuelve una cadena con la información completa del libro.

        Returns:
            str: Cadena con título, autor e ISBN.
        """
        return f"Título: {self.__titulo}, Autor: {self.__autor}, ISBN: {self.__isbn}"


# Crear instancias de Libro y mostrar su información
libro1 = Libro("Cien Años de Soledad", "Gabriel García Márquez", "978-3-16-148410-0")
libro2 = Libro("1984", "George Orwell", "978-0-452-28423-4")
print(libro1.mostrar_info())
print(libro2.mostrar_info())


# ---- Actividad 2: Clase LibroDigital (herencia) ----

class LibroDigital(Libro):
    """Representa un libro digital, extendiendo `Libro` con el tamaño en MB.

    Attributes:
        __tamaño_mb (int | float): Tamaño del archivo digital en megabytes.
    """

    def __init__(self, titulo, autor, isbn, tamaño_mb):
        """Inicializa un libro digital con los datos de `Libro` más el tamaño.

        Args:
            titulo (str): Título del libro.
            autor (str): Nombre del autor.
            isbn (str): Código ISBN.
            tamaño_mb (int | float): Tamaño del archivo en MB.
        """
        super().__init__(titulo, autor, isbn)
        self.__tamaño_mb = tamaño_mb

    def mostrar_info(self):
        """Devuelve la información del libro digital incluyendo el tamaño.

        Returns:
            str: Cadena con los datos heredados de `Libro` más el tamaño en MB.
        """
        info_base = super().mostrar_info()
        return f"{info_base}, tamaño_mb: {self.__tamaño_mb}"


# Crear instancias de LibroDigital y mostrar su información
libro_digital1 = LibroDigital("El Principito", "Antoine de Saint-Exupéry", "978-0-15-601219-5", 2)
libro_digital2 = LibroDigital("Sapiens", "Yuval Noah Harari", "978-0-06-231609-7", 5)
print(libro_digital1.mostrar_info())
print(libro_digital2.mostrar_info())


# ---- Actividad 3: Persistencia en SQLite ----

class BibliotecaDB:
    """Gestiona la persistencia de libros en una base de datos SQLite.

    Attributes:
        nombre_db (str): Ruta del archivo de base de datos SQLite.
        conexion: Objeto de conexión a SQLite.
        cursor: Cursor para ejecutar sentencias SQL.
    """

    def __init__(self, nombre_db="biblioteca.db"):
        """Crea la conexión a la BD y la tabla `libros` si no existe.

        Args:
            nombre_db (str): Nombre del archivo SQLite (por defecto 'biblioteca.db').
        """
        self.nombre_db = nombre_db
        self.conexion = sqlite3.connect(self.nombre_db)
        self.cursor = self.conexion.cursor()
        self.crear_tabla()

    def crear_tabla(self):
        """Crea la tabla `libros` en la base de datos si no existe previamente."""
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
        """Inserta un objeto `Libro` o `LibroDigital` en la base de datos.

        Accede al atributo privado `__isbn` mediante name mangling para obtenerlo.

        Args:
            libro (Libro): Instancia de `Libro` o subclase a persistir.
        """
        self.cursor.execute("""
            INSERT INTO libros (titulo, autor, isbn)
            VALUES (?, ?, ?)
        """, (libro.get_titulo(), libro.get_autor(), libro._Libro__isbn))  # name mangling para acceder al atributo privado
        self.conexion.commit()

    def mostrar_libros(self):
        """Consulta y muestra todos los libros almacenados en la base de datos."""
        self.cursor.execute("SELECT id, titulo, autor, isbn FROM libros")
        libros = self.cursor.fetchall()
        print("\n----- Libros en la base de datos -----")
        for l in libros:
            print(f"ID: {l[0]}, Título: {l[1]}, Autor: {l[2]}, ISBN: {l[3]}")

    def cerrar(self):
        """Cierra la conexión a la base de datos."""
        self.conexion.close()


# Instanciar la BD, insertar libros y mostrar el contenido
biblioteca = BibliotecaDB()
biblioteca.insertar_libro(libro1)
biblioteca.insertar_libro(libro2)
biblioteca.insertar_libro(libro_digital1)
biblioteca.insertar_libro(libro_digital2)
biblioteca.mostrar_libros()
biblioteca.cerrar()
