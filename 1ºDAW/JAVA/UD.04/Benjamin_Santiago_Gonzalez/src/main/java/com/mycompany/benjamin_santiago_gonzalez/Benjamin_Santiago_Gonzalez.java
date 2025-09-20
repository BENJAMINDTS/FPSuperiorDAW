package com.mycompany.benjamin_santiago_gonzalez;

//Importamos el paquete persona
import Persona.*;

public class Benjamin_Santiago_Gonzalez {

    public static void main(String[] args) {
        
        //Creamos un objeto de la clase Persona
        Persona p1 = new Persona("Benjamin", 22);
        
        //Creamos dos objetos de la clase Camarero
        Camarero c1 = new Camarero(1200, "Jose", 24);
        Camarero c2 = new Camarero(1547, "Antonio", 37);
        
       //Imprimimos todo
        System.out.println("La persona es:");
        System.out.println("-" + p1.getNombre());
        System.out.println("-" + p1.getEdad());
        System.out.println("------------------------------------------------");
        System.out.println("El camarero numero 1:");
        System.out.println("-Nombbre: " + c1.getNombre());
        System.out.println("-Edad: " + c1.getEdad());
        System.out.println("-Sueldo: " + c1.getSueldo());
        System.out.println("El camarero numero 2:");
        System.out.println("-Nombbre: " + c2.getNombre());
        System.out.println("-Edad: " + c2.getEdad());
        System.out.println("-Sueldo: " + c2.getSueldo());
        System.out.println("------------------------------------------------");
        System.out.println("Tenemos: " + Camarero.getContador() + " camareros");    
    }
}
