
package com.mycompany.numeroentero;
import java.util.Scanner;
public class NumeroEntero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int resultado = 0;
        int numero;

        while (true) {
            System.out.print("Introduce un número entero: ");
            numero = scanner.nextInt();

            if (numero <= 0) {
                break;
            }

            resultado =resultado + numero;
        }

        System.out.println(resultado);


    }
}


