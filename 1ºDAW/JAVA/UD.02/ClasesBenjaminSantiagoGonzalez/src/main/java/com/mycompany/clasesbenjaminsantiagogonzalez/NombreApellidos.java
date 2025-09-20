package com.mycompany.clasesbenjaminsantiagogonzalez;

public class NombreApellidos {
   private int edad;    //Declaramos las variables 
   private String nombre;
   
   //Constructor
    public NombreApellidos(){
        this.nombre="Benjamin Santiago Gonzalez";
        this.edad=22;
        if (edad>100 || edad<0) {
            this.edad=0;
            this.nombre="PERSONA ERRONEA";
        }
    }
    
   public NombreApellidos(String nombre, int edad){
        this.edad=edad;
        this.nombre=nombre;
        if (edad>100 || edad<0) {
            this.edad=0;
            this.nombre="PERSONA ERRONEA";
        }
   }
 
    //Creamos los metodos setter
    public void setedad(int edad){
        this.edad=edad;
        if (edad>100 || edad<0) {
            this.edad=0;
            this.nombre="PERSONA ERRONEA";
        }
    }
    
    public void setnombre(String nombre){
        this.nombre=nombre; 
    }
    
    //Creamos los metodos getter
    public int getedad(){
        return edad;
    }
    
    public String getnombre(){    
       return nombre;    
    }

    //Creamos el metodo ayuda, que usaremos como auxiliar
    public static void ayuda(){
       System.out.println("Esta clase permite crear personas con un nombre "
               + "y una edad");
    }
}


