package arraymaximos;

import java.util.Scanner;

/**
 * Clase encargada de comparar dos arreglos de enteros (que pueden tener
 * distintos tamaños)
 * y generar un tercer arreglo que contenga los valores máximos de cada
 * posición.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada principal. Solicita datos al usuario y ejecuta la lógica de
	 * comparación.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int array1[];
		int array2[];
		int arraymaximos[];

		// --- Inicialización del primer arreglo ---
		System.out.println("Introduce el tamaño del primer array: ");
		array1 = new int[sc.nextInt()];
		for (int i = 0; i < array1.length; i++) {
			System.out.println("Introduce el valor de la posición " + i + " del primer array: ");
			array1[i] = sc.nextInt();
		}

		// --- Inicialización del segundo arreglo ---
		System.out.println("Introduce el tamaño del segundo array: ");
		array2 = new int[sc.nextInt()];
		for (int i = 0; i < array2.length; i++) {
			System.out.println("Introduce el valor de la posición " + i + " del segundo array: ");
			array2[i] = sc.nextInt();
		}

		/**
		 * Lógica de comparación de máximos:
		 * Se determina el tamaño del arreglo resultante basándose en el arreglo más
		 * largo.
		 */
		if (array1.length > array2.length) {
			// Caso: El primer arreglo es más largo
			arraymaximos = new int[array1.length];
			System.arraycopy(array1, 0, arraymaximos, 0, array1.length);

			for (int i = 0; i < array2.length; i++) {
				if (array2[i] > arraymaximos[i]) {
					arraymaximos[i] = array2[i];
				}
			}
		} else if (array2.length > array1.length) {
			// Caso: El segundo arreglo es más largo
			arraymaximos = new int[array2.length];
			System.arraycopy(array2, 0, arraymaximos, 0, array2.length);

			for (int i = 0; i < array1.length; i++) {
				if (array1[i] > arraymaximos[i]) {
					arraymaximos[i] = array1[i];
				}
			}
		} else {
			// Caso: Ambos arreglos tienen el mismo tamaño
			arraymaximos = new int[array1.length];
			for (int i = 0; i < array1.length; i++) {
				if (array1[i] > array2[i]) {
					arraymaximos[i] = array1[i];
				} else {
					arraymaximos[i] = array2[i];
				}
			}
		}

		// --- Impresión de resultados ---
		System.out.println("\nEl array con los valores máximos es: ");
		for (int i : arraymaximos) {
			System.out.print("[" + i + "] ");
		}

		sc.close();
	}
}