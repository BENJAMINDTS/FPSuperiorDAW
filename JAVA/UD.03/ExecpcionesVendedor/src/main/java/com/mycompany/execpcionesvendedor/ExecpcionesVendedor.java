package com.mycompany.execpcionesvendedor;
import java.util.Scanner;
public class ExecpcionesVendedor {

    public static void main(String[] args) {
        String Nombre;
        String Apellidos;
        int Edad;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Indicame el nombre de nuestro vendedor");
        Nombre = sc.nextLine();
        System.out.println("Ahora dime su apellido");
        Apellidos = sc.nextLine();
        System.out.println("A continuacion dime su edad");
        Edad = sc.nextInt();
        
        Vendedor vendedor1 = new Vendedor(Nombre, Apellidos, Edad);
        
        try {
            vendedor1.Imprimir();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
