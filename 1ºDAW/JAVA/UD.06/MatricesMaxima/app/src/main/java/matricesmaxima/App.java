package matricesmaxima;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que gestiona la comparación de dos matrices cuadradas.
 * Genera una matriz resultante (matrizMaxima) que contiene el valor más alto
 * de cada posición correspondiente entre las dos matrices de entrada.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada principal. Gestiona la lógica de usuario y el control de
	 * excepciones.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int matriz1[][];
		int matriz2[][];
		int matrizMaxima[][];
		int lado;

		try {
			// --- Configuración de dimensiones ---
			System.out.println("Ingrese el tamaño de la matriz 1:");
			lado = sc.nextInt();
			matriz1 = new int[lado][lado];
			matrizMaxima = new int[lado][lado];

			System.out.println("Ingrese el tamaño de la matriz 2 (debe ser igual a la matriz 1):");
			int lado2 = sc.nextInt();
			matriz2 = new int[lado2][lado2];

			// Validación de dimensiones iguales
			checkMatricesLength(matriz1, matriz2);
			System.out.println("Las matrices tienen la misma longitud.");

			// --- Captura de datos: Matriz 1 ---
			for (int i = 0; i < lado; i++) {
				for (int j = 0; j < lado; j++) {
					System.out.println("Ingrese el valor de la primera matriz [" + i + "][" + j + "]:");
					matriz1[i][j] = sc.nextInt();
				}
			}

			// --- Captura de datos: Matriz 2 ---
			for (int i = 0; i < lado; i++) {
				for (int j = 0; j < lado; j++) {
					System.out.println("Ingrese el valor de la segunda matriz [" + i + "][" + j + "]:");
					matriz2[i][j] = sc.nextInt();
				}
			}

		} catch (InputMismatchException e) {
			// Error si el usuario introduce un carácter no numérico
			System.err.println("Error: Debe ingresar un número entero.");
			sc.close();
			return;
		} catch (IllegalArgumentException e) {
			// Error si las dimensiones de las matrices no coinciden
			System.err.println(e.getMessage());
			sc.close();
			return;
		}

		/**
		 * Lógica de comparación de elementos:
		 * Recorre ambas matrices simultáneamente y asigna el valor mayor
		 * a la posición correspondiente en la matrizMaxima.
		 */
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				if (matriz1[i][j] > matriz2[i][j]) {
					matrizMaxima[i][j] = matriz1[i][j];
				} else {
					matrizMaxima[i][j] = matriz2[i][j];
				}
			}
		}

		// --- Presentación de la Matriz de Máximos ---
		System.out.println("\nMatriz con los valores máximos encontrados:");
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				System.out.print("[" + matrizMaxima[i][j] + "] ");
			}
			System.out.println();
		}

		sc.close();
	}

	/**
	 * Verifica que dos matrices tengan las mismas dimensiones (filas y columnas).
	 * * @param matriz1 Primera matriz a comparar.
	 * * @param matriz2 Segunda matriz a comparar.
	 * * @throws IllegalArgumentException Si las longitudes no coinciden.
	 */
	public static void checkMatricesLength(int[][] matriz1, int[][] matriz2) {
		if (matriz1.length != matriz2.length || matriz1[0].length != matriz2[0].length) {
			throw new IllegalArgumentException("Error: Las matrices no tienen la misma longitud.");
		}
	}
}