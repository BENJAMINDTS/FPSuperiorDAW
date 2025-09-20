
package com.mycompany.peliculas;
import com.mycompany.peliculas.Pelicula.Genero;
public class Peliculas {

    public static void main(String[] args) {
    
        Pelicula pelicula1 = new Pelicula ("Ghandi", "Richard A", 
            Pelicula.Genero.DRAMA,191,1982,8.);
        Pelicula pelicula2 = new Pelicula ("Thor", "Kenneth Branagh", 
            Pelicula.Genero.DRAMA, 115, 2011, 8.0);
        pelicula1.Imprimir();
        System.out.println("---------------------------------------------------------------");
        pelicula2.Imprimir();
        System.out.println("---------------------------------------------------------------");
        System.out.println("¿Son ambas peliculas iguales en genero "
        + "y calificacion " + ((pelicula1.esSimilar(pelicula2)) ? "si" : "no"));
    }       
}
