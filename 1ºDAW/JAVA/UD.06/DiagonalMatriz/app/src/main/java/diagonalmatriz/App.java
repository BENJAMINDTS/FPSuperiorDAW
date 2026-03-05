package diagonalmatriz;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que permite la creación de una matriz cuadrada y la extracción visual
 * de sus diagonales principal e invertida.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada principal. Gestiona la lectura de datos con control de
	 * excepciones
	 * y aplica algoritmos de recorrido de matrices.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Inicialización de la matriz con tamaño cero
		int Matriz[][] = new int[0][0];

		try {
			System.out.println("Ingrese el tamaño de la matriz (N x N):");
			int lado = sc.nextInt();
			Matriz = new int[lado][lado];

			// Llenado de la matriz mediante bucles anidados
			for (int i = 0; i < Matriz.length; i++) {
				for (int j = 0; j < Matriz[i].length; j++) {
					System.out.println("Ingrese el valor de la posición [" + i + "][" + j + "]:");
					Matriz[i][j] = sc.nextInt();
				}
			}
		} catch (InputMismatchException e) {
			// Gestión de error si el usuario introduce caracteres no numéricos
			System.err.println("Error: Ingrese un número entero válido.");
			sc.close();
			return; // Finaliza la ejecución para evitar procesar una matriz vacía
		}

		/**
		 * Representación de la Diagonal Principal:
		 * Se cumple la condición lógica donde el índice de fila es igual al de columna
		 * (i == j).
		 */
		System.out.println("\nDiagonal de la matriz:");
		for (int i = 0; i < Matriz.length; i++) {
			for (int j = 0; j < Matriz[i].length; j++) {
				if (i == j) {
					System.out.print("[" + Matriz[i][j] + "]");
				} else {
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}

		/**
		 * Representación de la Diagonal Invertida (Secundaria):
		 * Se cumple la condición lógica donde la suma de los índices es igual
		 * al tamaño de la matriz menos uno (i + j == lado - 1).
		 */
		System.out.println("\nDiagonal invertida de la matriz:");
		for (int i = 0; i < Matriz.length; i++) {
			for (int j = 0; j < Matriz[i].length; j++) {
				if (i + j == Matriz.length - 1) {
					System.out.print("[" + Matriz[i][j] + "]");
				} else {
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}

		sc.close();
	}
}