package Persona;

public class Persona {
    private String nombre;
    private int edad;
    
    //Constructor vacio
    public Persona() {
    }
    
    //Constructor con parametros
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    
    //Getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
    
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    
}
