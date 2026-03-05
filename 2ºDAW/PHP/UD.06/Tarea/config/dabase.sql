DROP DATABASE IF EXISTS Libros;
CREATE DATABASE Libros CHARACTER SET utf8mb4;
USE Libros;

-- Crear tabla Autor
CREATE TABLE Autor (
    id INT PRIMARY KEY,
    nombre VARCHAR(15),
    apellidos VARCHAR(25),
    nacionalidad VARCHAR(10)
);

-- Crear tabla Libro
CREATE TABLE Libro (
    id INT PRIMARY KEY,
    titulo VARCHAR(50),
    f_publicacion DATE,
    id_autor INT,
    FOREIGN KEY (id_autor) REFERENCES Autor(id) ON DELETE CASCADE
);

-- Insertar datos en Autor
INSERT INTO Autor (id, nombre, apellidos, nacionalidad) VALUES 
(0, 'J. R. R.', 'Tolkien', 'Inglaterra'),
(1, 'Isaac', 'Asimov', 'Rusia');

-- Insertar datos en Libro
INSERT INTO Libro (id, titulo, f_publicacion, id_autor) VALUES 
(0, 'El Hobbit', '1937-09-21', 0),
(1, 'La Comunidad del Anillo', '1954-07-29', 0),
(2, 'Las dos torres', '1954-11-11', 0),
(3, 'El retorno del Rey', '1955-10-20', 0),
(4, 'Un guijarro en el cielo', '1950-01-19', 1),
(5, 'Fundación', '1951-06-01', 1),
(6, 'Yo, robot', '1950-12-02', 1);