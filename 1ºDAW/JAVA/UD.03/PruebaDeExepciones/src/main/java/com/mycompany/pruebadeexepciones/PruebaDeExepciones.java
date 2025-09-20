package com.mycompany.pruebadeexepciones;
import java.util.Scanner;

public class PruebaDeExepciones {

    public static void main(String[] args)  {
        String nombre;
        String apellido;
        int edad;
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Introduce el nombre del vendedor");
        nombre=sc.nextLine();
        System.out.println("Introduce el apellido del vendedor");
        apellido=sc.nextLine();
        System.out.println("Introduce la edad del vendedor");
        edad=sc.nextInt();
        Vendedor v1= new Vendedor(nombre, apellido, edad);
        System.out.println("Muy bien ahora vamos a verificar su edad");
        try {
            v1.verificarEdad();
        } catch (Exception ex) {
            System.out.println("La edad es incorrecta");
        } finally{
            System.out.println("La edad es correcta");
        }

    }
}
