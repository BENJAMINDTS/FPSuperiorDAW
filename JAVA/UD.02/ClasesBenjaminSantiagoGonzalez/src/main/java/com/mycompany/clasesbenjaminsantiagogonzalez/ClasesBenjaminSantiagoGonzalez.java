package com.mycompany.clasesbenjaminsantiagogonzalez;
public class ClasesBenjaminSantiagoGonzalez {

    public static void main(String[] args) {
        String yo="Benjamin Santiago Gonzalez"; //Declaramos las valiables
        int tamaño;
        char primer_caracter;
        char ultimo_caracter;
        String hipocoristico;
        String change;
        /*Asignamos valor a nuestras variables usando 
        el usando los metodos de la clase String*/
        tamaño=yo.length();                                                                                                   
        primer_caracter = yo.charAt(0); 
        ultimo_caracter = yo.charAt(tamaño-1);
        hipocoristico = yo.substring(0,5);
        change = yo.replace("a","_");
        //Imprimimos los resultados
        System.out.println("El tamanio de la cadena es " + (tamaño));
        System.out.println("El primer caracter de la cadena es " 
                + primer_caracter);
        System.out.println("El ultimo caracter de la cadena es "
                + ultimo_caracter);
        System.out.println("Mi hipocoristico es " + hipocoristico);
        System.out.println("Al cambiar las `a` por `_` loa cadena queda asi " 
                + change);
        
        //Creamos objetos distintos para ver como funciona
        
        //Creamos un objeto sin parametros
        NombreApellidos me;
        me = new NombreApellidos();
        System.out.println("Mi edad es " + me.getedad());
        System.out.println("Mi nombre es " + me.getnombre());
        
        //Creamos un objeto al que le asignamos parametros
        NombreApellidos Juan;
        Juan = new NombreApellidos("Juan Pablo", 37);
        System.out.println("Mi edad es " + Juan.getedad());
        System.out.println("Mi nombre es " + Juan.getnombre());
        
        /*Creamos un objetos al cual asignamos parametros no 
        admitidos en el metodo*/
        NombreApellidos intentodeerror;
        intentodeerror = new NombreApellidos("Pepe", 134);
        System.out.println(intentodeerror.getedad());
        System.out.println(intentodeerror.getnombre());
        
       NombreApellidos.ayuda();
    }
}


