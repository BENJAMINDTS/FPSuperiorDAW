package com.mycompany.ejercicios_1;
import java.util.Scanner;
public class Ejercicios_1 {
    
    public static void main(String[] args) {
        System.out.println("Dime una frase");
        Scanner scanner = new Scanner(System.in);
        String frase=scanner.nextLine();
        int longitud= frase.length();
        int cantidad_de_palabras=0;
        int cantidad_de_letras=0;
        for (int i = 0; i < longitud; i++) {
            char caracter = frase.charAt(i);
            if (caracter==' ') {
               cantidad_de_palabras++; 
            }
            else {
                cantidad_de_letras++;
            }
        }
        cantidad_de_palabras++;
        System.out.println(cantidad_de_palabras);
        System.out.println(cantidad_de_letras);
    }
}
