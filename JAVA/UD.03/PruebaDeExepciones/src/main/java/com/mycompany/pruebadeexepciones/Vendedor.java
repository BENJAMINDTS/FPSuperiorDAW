package com.mycompany.pruebadeexepciones;

public class Vendedor {
    String nombre;
    String apellido;
    int edad;

    public Vendedor(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    
    public String verificarEdad () throws Exception{
        if (edad<18 || edad>120) {
           throw new Exception();
        } else {
           return "La edad es correcta"; 
        }
        
 
    }
}
