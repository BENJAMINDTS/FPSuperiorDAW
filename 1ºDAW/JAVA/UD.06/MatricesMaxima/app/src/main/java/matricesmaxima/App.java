package matricesmaxima;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int matriz1[][];
        int matriz2[][];
        int matrizMaxima[][];
        int lado;
        try {
            System.out.println("Ingrese el tamaño de la matriz 1");
            lado = sc.nextInt();
            matriz1 = new int[lado][lado];
            matrizMaxima = new int[lado][lado];
            System.out.println("Ingrese el tamaño de la matriz 2, tenga en cuenta que deben de ser iguales");
            lado = sc.nextInt();
            matriz2 = new int[lado][lado];
            checkMatricesLength(matriz1, matriz2);
            System.out.println("Las matrices tienen la misma longitud.");
            for (int i = 0; i < lado; i++) {
                for (int j = 0; j < lado; j++) {
                    System.out.println("Ingrese el valor de la posición de la primera matriz [" + i + "][" + j + "]");
                    matriz1[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < lado; i++) {
                for (int j = 0; j < lado; j++) {
                    System.out.println("Ingrese el valor de la posición de la segunda matriz [" + i + "][" + j + "]");
                    matriz2[i][j] = sc.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un número entero");
            sc.close();
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            sc.close();
            return;
        }
        for (int i = 0; i < lado; i++) {
            for (int j = 0; j < lado; j++) {
                if (matriz1[i][j] > matriz2[i][j]) {
                    matrizMaxima[i][j] = matriz1[i][j];
                } else {
                    matrizMaxima[i][j] = matriz2[i][j];
                }
            }
        }
        for (int i = 0; i < lado; i++) {
            for (int j = 0; j < lado; j++) {
                System.out.print("[" + matrizMaxima[i][j] + "]");
            }
            System.out.println();
        }
        sc.close();
    }

    public static void checkMatricesLength(int[][] matriz1, int[][] matriz2) {
        if (matriz1.length != matriz2.length || matriz1[0].length != matriz2[0].length) {
            throw new IllegalArgumentException("Las matrices no tienen la misma longitud.");
        }
    }
}
