package com.mycompany.execpcionesvendedor;

public class Vendedor {
    String Nombre;
    String Apellido;
    int Edad;

    public Vendedor(String Nombre, String Apellido, int Edad) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Edad = Edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public int getEdad() {
        VerificarEdad(Edad);
        return Edad;
    }
    
    public void VerificarEdad(int edad) {
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("La edad no puede ser negativa ni mayor a 120");
        }
        if (edad<18) {
            throw new IllegalArgumentException("Debe de ser mayor de edad para poder trabajar");
        }
    }
    public void  Imprimir() {
        System.out.println("Su nombre es " + getNombre());
        System.out.println("Su apellido es " +  getApellido());
        System.out.println("Su edad es " + getEdad());
    }
}
