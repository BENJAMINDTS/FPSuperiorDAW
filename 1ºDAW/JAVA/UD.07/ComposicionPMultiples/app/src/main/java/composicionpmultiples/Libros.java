package composicionpmultiples;

public class Libros {
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String editorial;
    private String refBibliografica;

    public Libros(String titulo, String autor, int anioPublicacion, String editorial, String refBibliografica) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.refBibliografica = refBibliografica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getRefBibliografica() {
        return refBibliografica;
    }

    public void setRefBibliografica(String refBibliografica) {
        this.refBibliografica = refBibliografica;
    }

    public static void imprimir (Libros[] libros) {
        for (int i = 0; i < libros.length; i++) {
            System.out.println("Titulo: " + libros[i].getTitulo());
            System.out.println("Autor: " + libros[i].getAutor());
            System.out.println("Año de publicación: " + libros[i].getAnioPublicacion());
            System.out.println("Editorial: " + libros[i].getEditorial());
            System.out.println("Referencia bibliográfica: " + libros[i].getRefBibliografica());
            System.out.println();
        }
    }

}

        
   