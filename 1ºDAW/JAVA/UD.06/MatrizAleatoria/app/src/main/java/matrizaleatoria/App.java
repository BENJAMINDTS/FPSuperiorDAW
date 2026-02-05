package matrizaleatoria;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que genera una matriz cuadrada de tamaño definido por el usuario,
 * completando cada celda con valores enteros aleatorios dentro de un rango
 * específico.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada principal. Gestiona la entrada de datos, la lógica de
	 * generación aleatoria y la visualización de la matriz resultante.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		int lado, numeromaximo, numeroaleatorio;
		Random r = new Random();
		int matrizaleatoria[][];

		/**
		 * Uso de try-with-resources para garantizar el cierre automático del Scanner.
		 * Captura posibles errores si el usuario introduce datos no numéricos.
		 */
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Ingrese el tamaño de la matriz cuadrada (N x N):");
			lado = sc.nextInt();
			matrizaleatoria = new int[lado][lado];

			System.out.println("Ingrese el valor máximo para los elementos aleatorios:");
			numeromaximo = sc.nextInt();

			/**
			 * Lógica de llenado de la matriz:
			 * Recorre cada fila (i) y columna (j) generando un valor entre 0 y
			 * numeromaximo.
			 */
			for (int i = 0; i < lado; i++) {
				for (int j = 0; j < lado; j++) {
					// nextInt(n) genera un valor entre 0 (inclusive) y n (exclusive).
					// Sumamos 1 al máximo para que el límite superior sea inclusive.
					numeroaleatorio = r.nextInt(numeromaximo + 1);
					matrizaleatoria[i][j] = numeroaleatorio;
				}
			}

		} catch (InputMismatchException e) {
			System.err.println("Error: Debe ingresar un número entero válido.");
			return; // Finaliza la ejecución ante datos inválidos
		}

		// --- Visualización de la Matriz Generada ---
		System.out.println("\nMatriz Aleatoria Generada:");
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				System.out.print("[" + matrizaleatoria[i][j] + "] ");
			}
			System.out.println(); // Salto de línea al finalizar cada fila
		}
	}
}