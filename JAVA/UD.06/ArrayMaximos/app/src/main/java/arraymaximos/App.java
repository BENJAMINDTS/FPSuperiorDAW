package arraymaximos;

import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int array1[];
        int array2[];
        int arraymaximos[];
        System.out.println("Introduce el tamaño del primer array: ");
        array1= new int[sc.nextInt()];
        for (int i = 0; i < array1.length; i++) {
            System.out.println("Introduce el valor de la posición "+i+" del primer array: ");
            array1[i]=sc.nextInt();
            
        }
        System.out.println("Introduce el tamaño del segundo array: ");
        array2 = new int[sc.nextInt()];
        for (int i = 0; i < array2.length; i++) {
            System.out.println("Introduce el valor de la posición "+i+" del segundo array: ");
            array2[i]=sc.nextInt();
        }
        if (array1.length>array2.length) {
            arraymaximos = new int[array1.length];
            arraymaximos = array1;
            for (int i = 0; i < array2.length; i++) {
                if (array2[i]>arraymaximos[i]) {
                    arraymaximos[i]=array2[i];
                }
            } 
        }else if (array2.length>array1.length) {
            arraymaximos = new int[array2.length];
            arraymaximos = array2;
            for (int i = 0; i < array1.length; i++) {
                if (array1[i]>arraymaximos[i]) {
                    arraymaximos[i]=array1[i];
                }
            }
        }else{
            arraymaximos = new int[array1.length];
            for (int i = 0; i < array1.length; i++) {
                if (array1[i]>array2[i]) {
                    arraymaximos[i]=array1[i];
                }else{
                    arraymaximos[i]=array2[i];
                }
            }
        }
        System.out.println("El array con los valores máximos es: ");
        for (int i : arraymaximos) {
            System.out.print( "["+i + "]");
        }
        sc.close();
    }
}
