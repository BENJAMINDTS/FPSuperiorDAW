package matrizaleatoriairregular;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que genera y visualiza una matriz de dimensiones definidas por el
 * usuario,
 * poblándola con valores enteros aleatorios dentro de un rango específico.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada principal. Gestiona la captura de dimensiones, la
	 * generación de datos aleatorios y la impresión de la matriz.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {

		Random r = new Random();
		int matriz1[][];
		int filas1, columnas1, numeromaximo;

		/**
		 * Uso de try-with-resources para la gestión del Scanner.
		 * Captura dimensiones y el límite superior para los números aleatorios.
		 */
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Introduce el número de filas de la matriz:");
			filas1 = sc.nextInt();

			System.out.println("Introduce el número de columnas de la matriz:");
			columnas1 = sc.nextInt();

			System.out.println("Introduce el valor máximo permitido para los elementos:");
			numeromaximo = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Error: La entrada de datos debe ser un número entero.");
			return;
		}

		/**
		 * Inicialización de la matriz con las dimensiones capturadas.
		 * Se asegura que la matriz tenga el tamaño exacto solicitado por el usuario.
		 */
		matriz1 = new int[filas1][columnas1];

		/**
		 * Lógica de llenado:
		 * Recorre cada celda y asigna un valor aleatorio entre 0 y numeromaximo
		 * (exclusive).
		 */
		for (int i = 0; i < filas1; i++) {
			for (int j = 0; j < columnas1; j++) {
				matriz1[i][j] = r.nextInt(numeromaximo);
			}
		}

		// --- Visualización de la Matriz por Consola ---
		System.out.println("\nMatriz Aleatoria Resultante:");
		for (int i = 0; i < filas1; i++) {
			for (int j = 0; j < columnas1; j++) {
				System.out.print("[" + matriz1[i][j] + "] ");
			}
			System.out.println(); // Salto de línea para dar formato de tabla
		}
	}
}