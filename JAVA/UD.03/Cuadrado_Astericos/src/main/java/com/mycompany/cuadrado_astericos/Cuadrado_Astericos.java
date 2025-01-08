package com.mycompany.cuadrado_astericos;
import java.util.Scanner;
public class Cuadrado_Astericos {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Introduce la longitud del lado del cuadrado");
        int lado=sc.nextInt();
        for (int i = 0; i < lado; i++) {
            for (int j = 0; j < lado; j++) {
                System.out.print("*");
                
            }
            System.out.println("");
        }
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < lado; i++) {
            for (int j = 0; j < lado; j++) {
                if ((j==0 || j==lado-1) || (i==0 || i==lado-1) ) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
                
            }
            System.out.println("");
        }
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < lado; ) {
            
            for (int j = 0; j<lado;j++ ) {
                System.out.print("*");
            }
            System.out.println(" ");
            lado--;
            
        }
        for (int i = 0; i < lado; ) {
            
            for (int j = 0; j<lado;j++ ) {
                if ((j==0 || j==lado-1) || (i==0 || i==lado-1) ) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
                
            }
            System.out.println(" ");
            lado--;
            
        }
        System.out.println("------------------------------------------------------");
        
    }
}
