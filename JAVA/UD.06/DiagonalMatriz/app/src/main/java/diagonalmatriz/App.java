package diagonalmatriz;
import java.util.InputMismatchException;
import java.util.Scanner;
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Matriz[][] = new int[0][0];
        try {
            System.out.println("Ingrese el tamaño de la matriz");
            int lado = sc.nextInt();
            Matriz = new int[lado][lado];
            for (int i = 0; i < Matriz.length; i++) {
                for (int j = 0; j < Matriz[i].length; j++) {
                    System.out.println("Ingrese el valor de la posición [" + i + "][" + j + "]");
                    Matriz[i][j] = sc.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un número entero");
        }
        System.out.println("Diagonal de la matriz");
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[i].length; j++) {
                if (i==j){
                    System.out.print("[" + Matriz[i][j] + "]");
                }else {
                    System.out.print("[ ]");
                }
                
            }
            System.out.println();
        }
        System.out.println("Diagonal invertida de la matriz");
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[i].length; j++) {
                if (i+j==Matriz.length-1){
                    System.out.print("[" + Matriz[i][j] + "]");
                }else {
                    System.out.print("[ ]");
                }
                
            }
            System.out.println();
        }
        sc.close();
    }
}
