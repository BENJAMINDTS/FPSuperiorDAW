package com.mycompany.numeroentero;

import java.util.Scanner;

/**
 * Clase diseñada para realizar la suma acumulada de números enteros positivos.
 * El programa se detiene y muestra el resultado cuando se introduce un número
 * menor o igual a cero.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class NumeroEntero {

	/**
	 * Método principal que gestiona el bucle de captura y acumulación.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Variable para almacenar la suma total
		int resultado = 0;
		// Variable para capturar la entrada actual del usuario
		int numero;

		/**
		 * Bucle infinito controlado.
		 * Se mantendrá activo hasta que se cumpla la condición de salida (break).
		 */
		while (true) {
			System.out.print("Introduce un número entero (0 o negativo para finalizar): ");
			numero = scanner.nextInt();

			/**
			 * Condición de parada:
			 * Si el usuario introduce un número <= 0, el bucle se interrumpe
			 * inmediatamente.
			 */
			if (numero <= 0) {
				break;
			}

			// Operación de acumulación: suma el número actual al total anterior
			resultado = resultado + numero;
		}

		/**
		 * Impresión del valor acumulado final tras salir del bucle.
		 */
		System.out.println("El resultado de la suma acumulada es: " + resultado);

		// Cierre del recurso Scanner para evitar fugas de memoria
		scanner.close();
	}
}