package com.mycompany.ejercicios_1;

import java.util.Scanner;

/**
 * Clase encargada de analizar una cadena de texto para contabilizar
 * el número de palabras y letras que la componen.
 * * @author BenjaminDTS
 *
 * @version 1.0
 */
public class Ejercicios_1 {

	/**
	 * Método principal que procesa la frase introducida por el usuario.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		// Inicialización del escáner para lectura de datos
		System.out.println("Dime una frase:");
		Scanner scanner = new Scanner(System.in);

		// Captura de la frase completa
		String frase = scanner.nextLine();

		// Variables de control y estadísticas
		int longitud = frase.length();
		int cantidad_de_palabras = 0;
		int cantidad_de_letras = 0;

		/**
		 * Bucle iterativo que recorre cada posición de la cadena.
		 * Analiza si el carácter actual es un espacio o parte de una palabra.
		 */
		for (int i = 0; i < longitud; i++) {
			char caracter = frase.charAt(i);

			// Si el carácter es un espacio, asumimos que finaliza una palabra
			if (caracter == ' ') {
				cantidad_de_palabras++;
			}
			// Si no es un espacio, se contabiliza como letra
			else {
				cantidad_de_letras++;
			}
		}

		/**
		 * Ajuste final: Dado que el número de palabras suele ser el número
		 * de espacios + 1 (la última palabra no suele ir seguida de espacio).
		 */
		cantidad_de_palabras++;

		// Salida de resultados
		System.out.println("Cantidad de palabras estimadas: " + cantidad_de_palabras);
		System.out.println("Cantidad total de letras (sin espacios): " + cantidad_de_letras);

		scanner.close();
	}
}