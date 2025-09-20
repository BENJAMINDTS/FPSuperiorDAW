package matriztranspuesta;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        int filas, columnas;
        int matriz[][];
        int transpuesta[][];
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese las filas de la matriz : ");
            filas = sc.nextInt();
            System.out.println("Ingrese las columnas de la matriz : ");
            columnas = sc.nextInt();
            matriz = new int[filas][columnas];
            transpuesta = new int[columnas][filas];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.println("Ingrese el valor de la posición [" + i + "][" + j + "]: ");
                    matriz[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    transpuesta[j][i] = matriz[i][j];
                }                
            }
            System.out.println("Matriz original: ");
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print("[" + matriz[i][j] + "]");
                }
                System.out.println();
            }
            System.out.println("Matriz transpuesta: ");
            for (int i = 0; i < columnas; i++) {
                for (int j = 0; j < filas; j++) {
                    System.out.print("[" + transpuesta[i][j] + "]");
                }
                System.out.println();
            }    
        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un número entero.");
        }

    }
}
