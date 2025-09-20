package composicionpmultiples;
import java.util.ArrayList;

public class Biblioteca {
    private String nombre;
    private ArrayList<Libros> Libros;

    public Biblioteca(String nombre, ArrayList<Libros> Libros) {
        this.nombre = nombre;
        this.Libros = Libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Libros> getLibros() {
        return Libros;
    }

    public void setLibros(ArrayList<Libros> Libros) {
        this.Libros = Libros;
    }

    public static void añadirLibro (ArrayList<Libros> libros, Libros libro) {
        libros.add(libro);
    }   

    public static void listarLibros (ArrayList<Libros> libros) {
        for (int i = 0; i < libros.size(); i++) {
            System.out.println("Titulo: " + libros.get(i).getTitulo());
        }
    }
    
}

