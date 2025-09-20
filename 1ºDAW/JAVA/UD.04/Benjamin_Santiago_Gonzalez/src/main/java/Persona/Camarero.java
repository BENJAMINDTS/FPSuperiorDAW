package Persona;

public class Camarero extends Persona {
    private double sueldo;
    private static int contador=0;
    
    //Constructor
    public Camarero(double sueldo, String nombre, int edad) {
        super(nombre, edad);
        this.sueldo = sueldo;
        contador++;
    }
    
    //Getters
    public double getSueldo() {
        return sueldo;
    }
    
    public static int getContador() {
        return contador;
    }
    
    //Setters
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
    
    
    
}
