package com.mycompany.atleta;

public class Atleta {
    private int identificador;
    private String nombre;
    private double tiempo;
    static int contador=0;
    static String seleccion= "Colombia";
    static double tiempoEquipo;

    public static void main(String[] args) {
       //Intanciamos 4 objetos de nuestra clase
       Atleta atleta1 = new Atleta("Alejando Perlaza", 9.55); 
       Atleta atleta2 = new Atleta("Anthony Zambrano", 9.28);
       Atleta atleta3 = new Atleta("Diego Palomeque", 9.53);
       Atleta atleta4 = new Atleta("Gilmar Herrera", 9.29);
       
       //Cada Atleta corre 400m
       atleta1.correr400Metros();
       atleta2.correr400Metros();
       atleta3.correr400Metros();
       atleta4.correr400Metros();
       
       //Imprimimos la seleccion del equipo
       ImprimirSeleccion();
       
       //Imprimimos cada atleta
       atleta1.ImprimirAteleta();
       atleta2.ImprimirAteleta();
       atleta3.ImprimirAteleta();
       atleta4.ImprimirAteleta();
       
       //Imprimimos el tiempo total de la seleccion
       ImprimirTiempoTotal();
    }

    //Constructor
    public Atleta(String nombre, double tiempo) {
        contador++;
        this.identificador = contador;
        this.nombre = nombre;
        this.tiempo = tiempo;
        
    }
    //Metodo para correr los 400m 
    private void correr400Metros() {
        tiempoEquipo += this.tiempo;
    }
    
    //Metodo para obtener la seleccion
    public static String getSeleccion() {
        return seleccion;
    }
    
    //Metodo para obtener el tiempo del equipo
    public static double getTiempoEquipo() {
        return tiempoEquipo;
    }
    
    //Metodo para imprimir la selecion del equipo
    static private void ImprimirSeleccion(){
        System.out.println(getSeleccion());
    }
    
    //Metodo para obtener el tiempo total
    static private void ImprimirTiempoTotal(){
        System.out.println("El tiempo total del equipo es " + getTiempoEquipo());
    }
    
    //Metodo para imprimir los atributos del atleta
    private void ImprimirAteleta(){
        System.out.println("ID:" + this.identificador);
        System.out.println("Nombre:" + this.nombre);
        System.out.println("Tiempo:" + this.tiempo);}
    
}

