package composicionpmultiples;

/**
 * Representa un libro dentro del sistema bibliográfico.
 * Esta clase almacena metadatos esenciales como el título, autor y editorial,
 * y proporciona métodos para la gestión y visualización de colecciones.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Libros {
    /** Título de la obra literaria o técnica */
    private String titulo;
    /** Nombre del autor o autores del libro */
    private String autor;
    /** Año en que el libro fue editado o publicado */
    private int anioPublicacion;
    /** Empresa encargada de la edición del libro */
    private String editorial;
    /**
     * Código o cadena que identifica la referencia bibliográfica (ISBN, ISSN, etc.)
     */
    private String refBibliografica;

    /**
     * Constructor para inicializar un objeto Libro con todos sus atributos.
     * * @param titulo Nombre del libro.
     * 
     * @param autor            Escritor de la obra.
     * @param anioPublicacion  Año de salida al mercado.
     * @param editorial        Casa editorial.
     * @param refBibliografica Identificador de referencia bibliográfica.
     */
    public Libros(String titulo, String autor, int anioPublicacion, String editorial, String refBibliografica) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.refBibliografica = refBibliografica;
    }

    // --- Métodos Getter y Setter ---

    /** @return El título del libro */
    public String getTitulo() {
        return titulo;
    }

    /** @param titulo El nuevo título a asignar */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** @return El autor del libro */
    public String getAutor() {
        return autor;
    }

    /** @param autor El nuevo autor a asignar */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /** @return El año de publicación */
    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    /** @param anioPublicacion El nuevo año a asignar */
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    /** @return La editorial del libro */
    public String getEditorial() {
        return editorial;
    }

    /** @param editorial La nueva editorial a asignar */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /** @return La referencia bibliográfica */
    public String getRefBibliografica() {
        return refBibliografica;
    }

    /** @param refBibliografica La nueva referencia a asignar */
    public void setRefBibliografica(String refBibliografica) {
        this.refBibliografica = refBibliografica;
    }

    /**
     * Imprime en la consola de salida estándar la información detallada
     * de un arreglo de objetos Libros.
     * * @param libros array de objetos de la clase Libros a procesar.
     */
    public static void imprimir(Libros[] libros) {
        if (libros == null)
            return;

        for (Libros libro : libros) {
            if (libro != null) {
                System.out.println("----- Información del Libro -----");
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Año de publicación: " + libro.getAnioPublicacion());
                System.out.println("Editorial: " + libro.getEditorial());
                System.out.println("Referencia bibliográfica: " + libro.getRefBibliografica());
                System.out.println("----------------------------------");
                System.out.println();
            }
        }
    }
}