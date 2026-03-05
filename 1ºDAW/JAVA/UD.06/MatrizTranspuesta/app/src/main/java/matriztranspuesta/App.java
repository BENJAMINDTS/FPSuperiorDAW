package matriztranspuesta;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que permite realizar la transposición de una matriz.
 * La transpuesta se obtiene intercambiando filas por columnas, de modo que
 * el elemento en la posición [i][j] pasa a la posición [j][i].
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada principal. Gestiona la captura de datos, la lógica de
	 * transposición y la visualización de ambas matrices.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		int filas, columnas;
		int matriz[][];
		int transpuesta[][];

		/**
		 * Uso de try-with-resources para asegurar el cierre del Scanner.
		 */
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Ingrese las filas de la matriz:");
			filas = sc.nextInt();
			System.out.println("Ingrese las columnas de la matriz:");
			columnas = sc.nextInt();

			// Inicialización de la matriz original
			matriz = new int[filas][columnas];
			// La transpuesta invierte las dimensiones: filas por columnas y viceversa
			transpuesta = new int[columnas][filas];

			// --- Llenado de la matriz original ---
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					System.out.println("Ingrese el valor de la posición [" + i + "][" + j + "]:");
					matriz[i][j] = sc.nextInt();
				}
			}

			/**
			 * Lógica de Transposición:
			 * Recorremos la matriz original y asignamos sus valores a la transpuesta
			 * invirtiendo los índices.
			 */
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					transpuesta[j][i] = matriz[i][j];
				}
			}

			// --- Impresión de la Matriz Original ---
			System.out.println("\nMatriz original:");
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					System.out.print("[" + matriz[i][j] + "] ");
				}
				System.out.println();
			}

			// --- Impresión de la Matriz Transpuesta ---
			System.out.println("\nMatriz transpuesta:");
			for (int i = 0; i < columnas; i++) {
				for (int j = 0; j < filas; j++) {
					System.out.print("[" + transpuesta[i][j] + "] ");
				}
				System.out.println();
			}

		} catch (InputMismatchException e) {
			System.err.println("Error: Debe ingresar un número entero válido.");
		}
	}
}