package matricesmaximairregulares;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String matriz1[][];
        String matriz2[][];
        String matrizmaxima[][];
        int filas1, columnas1, filas2, columnas2;
        String numero;
        try {
            System.out.println("Ingrese el número de filas de la matriz 1: ");
            filas1 = sc.nextInt();
            System.out.println("Ingrese el número de columnas de la matriz 1: ");
            columnas1 = sc.nextInt();
            matriz1 = new String[filas1][columnas1];

            System.out.println("Ingrese el número de filas de la matriz 2: ");
            filas2 = sc.nextInt();
            System.out.println("Ingrese el número de columnas de la matriz 2: ");
            columnas2 = sc.nextInt();
            sc.nextLine();
            matriz2 = new String[filas2][columnas2];

            for (int i = 0; i < matriz1.length; i++) {
                for (int j = 0; j < matriz1[i].length; j++) {
                    System.out.println("Ingrese el valor de la matriz 1 en la posición [" + i + "][" + j + "]: ");
                    numero = sc.nextLine();
                    matriz1[i][j] = numero.isEmpty() ? null : numero;
                }
            }

            for (int i = 0; i < matriz2.length; i++) {
                for (int j = 0; j < matriz2[i].length; j++) {
                    System.out.println("Ingrese el valor de la matriz 2 en la posición [" + i + "][" + j + "]: ");
                    numero = sc.nextLine();
                    matriz2[i][j] = numero.isEmpty() ? null : numero;
                }
            }

            int maxFilas = Math.max(filas1, filas2);
            int maxColumnas = Math.max(columnas1, columnas2);
            matrizmaxima = new String[maxFilas][maxColumnas];

            for (int i = 0; i < maxFilas; i++) {
                for (int j = 0; j < maxColumnas; j++) {
                    String valor1 = (i < filas1 && j < columnas1) ? matriz1[i][j] : null;
                    String valor2 = (i < filas2 && j < columnas2) ? matriz2[i][j] : null;

                    if (valor1 == null || valor2 == null) {
                        matrizmaxima[i][j] = (valor1 != null) ? valor1 : valor2;
                    } else if (Integer.parseInt(valor1) > Integer.parseInt(valor2)) {
                        matrizmaxima[i][j] = valor1;
                    } else {
                        matrizmaxima[i][j] = valor2;
                    }
                }
            }

            for (int i = 0; i < matrizmaxima.length; i++) {
                for (int j = 0; j < matrizmaxima[i].length; j++) {
                    if (matrizmaxima[i][j] == null) {
                        System.out.print("");
                    } else {
                        System.out.print("["+matrizmaxima[i][j] + "]");
                        
                    }
                }
                System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero.");
        } finally {
            sc.close();
        }
    }
}
