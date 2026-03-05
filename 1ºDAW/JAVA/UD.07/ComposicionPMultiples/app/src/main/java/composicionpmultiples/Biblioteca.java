package composicionpmultiples;

import java.util.ArrayList;

/**
 * Representa una entidad de gestión bibliotecaria.
 * Esta clase actúa como contenedor para una colección de objetos de tipo
 * Libros,
 * permitiendo la administración del inventario de la biblioteca.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Biblioteca {
    /** Nombre identificativo de la biblioteca */
    private String nombre;

    /** Lista dinámica que contiene la colección de libros disponibles */
    private ArrayList<Libros> Libros;

    /**
     * Constructor para crear una nueva instancia de Biblioteca.
     * * @param nombre Nombre de la institución o sucursal.
     * 
     * @param Libros Lista inicial de objetos Libros (ArrayList).
     */
    public Biblioteca(String nombre, ArrayList<Libros> Libros) {
        this.nombre = nombre;
        this.Libros = Libros;
    }

    // --- Métodos de Acceso (Getters y Setters) ---

    /** @return El nombre de la biblioteca */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre Define o cambia el nombre de la biblioteca */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return La lista completa de libros almacenados */
    public ArrayList<Libros> getLibros() {
        return Libros;
    }

    /** @param Libros Asigna una nueva lista de libros a la biblioteca */
    public void setLibros(ArrayList<Libros> Libros) {
        this.Libros = Libros;
    }

    // --- Métodos de Gestión de Inventario ---

    /**
     * Añade un nuevo ejemplar a la lista de libros proporcionada.
     * * @param libros La lista de destino donde se guardará el libro.
     * 
     * @param libro El objeto Libro que se desea registrar.
     */
    public static void añadirLibro(ArrayList<Libros> libros, Libros libro) {
        if (libros != null && libro != null) {
            libros.add(libro);
        }
    }

    /**
     * Recorre la lista de libros y muestra por consola los títulos disponibles.
     * Este método facilita una vista rápida del catálogo.
     * * @param libros La lista de libros a procesar para el listado.
     */
    public static void listarLibros(ArrayList<Libros> libros) {
        if (libros == null || libros.isEmpty()) {
            System.out.println("La biblioteca no contiene libros actualmente.");
            return;
        }

        System.out.println("--- Catálogo de Títulos ---");
        for (int i = 0; i < libros.size(); i++) {
            System.out.println((i + 1) + ". Título: " + libros.get(i).getTitulo());
        }
    }
}