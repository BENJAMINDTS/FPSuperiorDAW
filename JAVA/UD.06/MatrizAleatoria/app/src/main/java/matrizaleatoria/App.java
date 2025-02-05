package matrizaleatoria;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class App {

    public static void main(String[] args) {
        int lado, numeromaximo,numeroaleatorio;
        Random r = new Random();
        int matrizaleatoria[][];
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese el tamaño de la matriz cuadrada");
            lado = sc.nextInt();
            matrizaleatoria = new int[lado][lado];
            System.out.println("Ingrese el número máximo para los elementos de la matriz");
            numeromaximo = sc.nextInt();
            for (int i = 0; i < lado; i++) {
                for (int j = 0; j < lado; j++) {
                    numeroaleatorio = r.nextInt(numeromaximo + 1);
                    matrizaleatoria[i][j] = numeroaleatorio;
                }    
            }

        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un número entero");
            return;
        }
        for (int i = 0; i < lado; i++) {
            for (int j = 0; j < lado; j++) {
                System.out.print("[" + matrizaleatoria[i][j] + "]");
            }
            System.out.println();
        }
    }
}
