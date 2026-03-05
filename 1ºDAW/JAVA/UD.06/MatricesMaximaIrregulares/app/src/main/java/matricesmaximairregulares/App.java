package matricesmaximairregulares;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que gestiona la comparación de dos matrices que pueden tener
 * dimensiones distintas.
 * La matriz resultante toma el tamaño máximo de ambas y compara los valores
 * numéricos
 * almacenados como cadenas de texto.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada principal. Ejecuta la lógica de captura, comparación
	 * irregular y visualización de resultados.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String matriz1[][];
		String matriz2[][];
		String matrizmaxima[][];
		int filas1, columnas1, filas2, columnas2;
		String numero;

		try {
			// --- Configuración de dimensiones para la Matriz 1 ---
			System.out.println("Ingrese el número de filas de la matriz 1: ");
			filas1 = sc.nextInt();
			System.out.println("Ingrese el número de columnas de la matriz 1: ");
			columnas1 = sc.nextInt();
			matriz1 = new String[filas1][columnas1];

			// --- Configuración de dimensiones para la Matriz 2 ---
			System.out.println("Ingrese el número de filas de la matriz 2: ");
			filas2 = sc.nextInt();
			System.out.println("Ingrese el número de columnas de la matriz 2: ");
			columnas2 = sc.nextInt();
			sc.nextLine(); // Limpieza de buffer tras leer enteros
			matriz2 = new String[filas2][columnas2];

			// --- Llenado de la Matriz 1 ---
			for (int i = 0; i < matriz1.length; i++) {
				for (int j = 0; j < matriz1[i].length; j++) {
					System.out.println("Matriz 1 - Posición [" + i + "][" + j + "]: ");
					numero = sc.nextLine();
					// Almacena null si la entrada está vacía
					matriz1[i][j] = numero.isEmpty() ? null : numero;
				}
			}

			// --- Llenado de la Matriz 2 ---
			for (int i = 0; i < matriz2.length; i++) {
				for (int j = 0; j < matriz2[i].length; j++) {
					System.out.println("Matriz 2 - Posición [" + i + "][" + j + "]: ");
					numero = sc.nextLine();
					matriz2[i][j] = numero.isEmpty() ? null : numero;
				}
			}

			/**
			 * Definición de la Matriz Máxima:
			 * Se calculan las dimensiones máximas para cubrir ambas matrices.
			 */
			int maxFilas = Math.max(filas1, filas2);
			int maxColumnas = Math.max(columnas1, columnas2);
			matrizmaxima = new String[maxFilas][maxColumnas];

			/**
			 * Lógica de Comparación Irregular:
			 * Se recorre el área total. Si una posición no existe en una de las matrices,
			 * se trata como nula.
			 */
			for (int i = 0; i < maxFilas; i++) {
				for (int j = 0; j < maxColumnas; j++) {
					String valor1 = (i < filas1 && j < columnas1) ? matriz1[i][j] : null;
					String valor2 = (i < filas2 && j < columnas2) ? matriz2[i][j] : null;

					// Si alguno es nulo, se toma el valor que no lo sea
					if (valor1 == null || valor2 == null) {
						matrizmaxima[i][j] = (valor1 != null) ? valor1 : valor2;
					}
					// Si ambos existen, se comparan convirtiendo el String a Integer
					else if (Integer.parseInt(valor1) > Integer.parseInt(valor2)) {
						matrizmaxima[i][j] = valor1;
					} else {
						matrizmaxima[i][j] = valor2;
					}
				}
			}

			// --- Impresión de la Matriz Resultante ---
			System.out.println("\nResultado de la comparación (Matriz Máxima):");
			for (int i = 0; i < matrizmaxima.length; i++) {
				for (int j = 0; j < matrizmaxima[i].length; j++) {
					if (matrizmaxima[i][j] != null) {
						System.out.print("[" + matrizmaxima[i][j] + "] ");
					} else {
						System.out.print("[ ] "); // Representación visual de huecos
					}
				}
				System.out.println();
			}

		} catch (InputMismatchException e) {
			System.err.println("Error: Debe ingresar un número entero para las dimensiones.");
		} catch (NumberFormatException e) {
			System.err.println("Error: Uno de los valores ingresados en las celdas no es un número válido.");
		} finally {
			sc.close();
		}
	}
}