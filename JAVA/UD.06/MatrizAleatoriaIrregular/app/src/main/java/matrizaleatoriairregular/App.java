package matrizaleatoriairregular;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class App {

    public static void main(String[] args) {

        Random r = new Random();
        int matriz1[][];
        int filas1, columnas1, numeromaximo;
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Introduce el número maximo de filas de la matriz 1");
            filas1 = sc.nextInt();
            System.out.println("Introduce el número maximo de columnas de la matriz 1");
            columnas1 = sc.nextInt();
            System.out.println("Introduce el número maximo de la matriz");
            numeromaximo = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error en la entrada de datos");
            return;
        }
        matriz1 = new int[r.nextInt(numeromaximo)+1][r.nextInt(numeromaximo)+1];
        for (int i = 0; i < filas1; i++) {
            for (int j = 0; j < columnas1; j++) {
                matriz1[i][j] = r.nextInt(numeromaximo);
            }
        }
        for (int i = 0; i < filas1; i++) {
            for (int j = 0; j < columnas1; j++) {
                System.out.print("[" + +matriz1[i][j] + "]");
            }
            System.out.println();
        }

    }
}
