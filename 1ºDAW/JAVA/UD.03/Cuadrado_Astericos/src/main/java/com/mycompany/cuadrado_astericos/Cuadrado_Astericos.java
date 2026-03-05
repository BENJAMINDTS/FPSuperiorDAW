package com.mycompany.cuadrado_astericos;

import java.util.Scanner;

/**
 * Clase que genera diferentes representaciones visuales de cuadrados
 * utilizando bucles anidados y caracteres (asteriscos).
 * * @author BenjaminDTS
 *
 * @version 1.0
 */
public class Cuadrado_Astericos {

	/**
	 * Método principal que dibuja cuadrados rellenos, huecos y decrecientes.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la longitud del lado del cuadrado");
		int lado = sc.nextInt();

		// --- SECCIÓN 1: CUADRADO RELLENO ---
		/**
		 * Dibuja un cuadrado sólido.
		 * El bucle externo 'i' gestiona las filas y el interno 'j' las columnas.
		 */
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}

		System.out.println("------------------------------------------------------");

		// --- SECCIÓN 2: CUADRADO HUECO ---
		/**
		 * Dibuja un cuadrado mostrando solo los bordes.
		 * La condición evalúa si estamos en la primera/última fila o columna.
		 */
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				// Si es borde exterior, imprime asterisco; si es centro, espacio en blanco.
				if ((j == 0 || j == lado - 1) || (i == 0 || i == lado - 1)) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}

		System.out.println("------------------------------------------------------");

		// --- SECCIÓN 3: CUADRADO RELLENO CON LADO DECRECIENTE ---
		/**
		 * Genera figuras reduciendo el valor de la variable 'lado' en cada iteración.
		 */
		for (int i = 0; i < lado;) {
			for (int j = 0; j < lado; j++) {
				System.out.print("*");
			}
			System.out.println(" ");
			lado--; // Reducción del tamaño de la figura
		}

		// --- SECCIÓN 4: CUADRADO HUECO CON LADO DECRECIENTE ---
		/**
		 * Aplica la lógica de bordes a una figura que reduce su tamaño dinámicamente.
		 */
		for (int i = 0; i < lado;) {
			for (int j = 0; j < lado; j++) {
				if ((j == 0 || j == lado - 1) || (i == 0 || i == lado - 1)) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println(" ");
			lado--;
		}

		System.out.println("------------------------------------------------------");
		sc.close();
	}
}