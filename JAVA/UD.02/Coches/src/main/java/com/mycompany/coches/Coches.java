
package com.mycompany.coches;

import java.util.Scanner; //importamos la clase escaner

public class Coches {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);

        coche modelo1; //Creamos un nuevo objeto
        
        System.out.println("Dime su marca ");
        String marca=leer.nextLine(); //Asignamos la marca
        
        System.out.println("De que color es ");
        String color=leer.nextLine(); //Asignamos el color
        
        System.out.println("Dime su velocidad ");
        double velocidad=leer.nextDouble(); //Asignamos la velocidad
        
        modelo1=new coche(marca,color,velocidad); 
        //Asignamos los valores correspondientes al objeto
        
        System.out.println("La marca es " + modelo1.getmarca()); 
        System.out.println("La color es " + modelo1.getcolor()); 
        System.out.println("La velocidad es " + modelo1.getvelocidad()); 
        //Imprimimos sus atributos llamando a los metodos correspondientes
        
        System.out.println("Ahora vamos a aumentar su velocidad 10 km/h por lo "
                + "que su velocidad sera: " + modelo1.aumentavelocidad()); 
        System.out.println("Ahora vamos a disminuir su velocidad 5 km/h por lo "
                + "que su velocidad sera: " + modelo1.disminuyevelocidad()); 
        /*Aumentamos y disminuimosel atributo velocidad llamando a los
        metodos correspondientes*/
        
        System.out.println("-------------------------------------------------");
        
        leer.nextLine(); // Consumir el salto de línea
        
        coche modelo2; //Creamos un nuevo objeto y repetimos el proceso
        
        System.out.println("Dime su marca ");
        marca = marca.replace(marca, leer.nextLine());
        
        System.out.println("De que color es ");
        color = color.replace(color, leer.nextLine());
        
        System.out.println("Dime su velocidad ");
        velocidad=leer.nextDouble();
        
        modelo2=new coche(marca,color,velocidad);
        
        System.out.println("La marca es " + modelo2.getmarca());
        System.out.println("La color es " + modelo2.getcolor());
        System.out.println("La velocidad es " + modelo2.getvelocidad());
        
        System.out.println("Ahora vamos a aumentar su velocidad 10 km/h por lo "
                + "que su velocidad sera: " + modelo2.aumentavelocidad());
        System.out.println("Ahora vamos a disminuir su velocidad 5 km/h por lo "
                + "que su velocidad sera: " + modelo2.disminuyevelocidad());
    }   
       
}
