
package com.mycompany.peliculas;


public class Pelicula {
    private String nombre;
    private String director;
    private Genero genero;
    private int duracion;
    private int año;
    private double calificacion;
    
    
    public enum Genero {
    ACCION, COMEDIA, DRAMA, SUSPENSO
    }

    public Pelicula(String nombre, String director, Genero genero, int duracion, 
            int año, double calificacion) {
        this.nombre = nombre;
        this.director = director;
        this.genero = genero;
        this.duracion = duracion;
        this.año = año;
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDirector() {
        return director;
    }

    public Genero getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getAño() {
        return año;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
    
    private boolean esPeliculaEpica(){
      return duracion >= 180;
    }
    
    public boolean esSimilar(Pelicula otraPelicula) {
        return (this.genero == otraPelicula.genero && calcularValoracion()
                == otraPelicula.calcularValoracion());
    }
    
    private String calcularValoracion() {
        if (calificacion >= 0 && calificacion <= 2) {
            return "Muy mala";
        } 
        else if (calificacion > 2 && calificacion <= 5) {
            return "Mala";
        }
        else if (calificacion > 5 && calificacion <= 7) {
            return "regular";
        }
        else if (calificacion > 7 && calificacion <= 8) {
            return "Buena";
        }
        else if (calificacion > 8 && calificacion <= 10) {
            return "Excelente";
        }
        else {
            return "Valoración no especificada";
        }
    }
   
    
    public void Imprimir() {
        System.out.println("Nuestra pelicula se llama " + getNombre());
        System.out.println("Su director es " + getDirector());
        System.out.println("Su genero es " + getGenero());
        System.out.println("Dura: " + getDuracion() + " min");
        System.out.println("Se rodo en el año " + getAño());
        System.out.println("Tiene una valoración de " + getCalificacion() + 
                " lo que equivale a que la pelicula es " + 
                calcularValoracion());
        System.out.println("En función de su duración, ¿Es larga la película? " 
                + (esPeliculaEpica()? "si" : "no"));

        
    }
    
}
