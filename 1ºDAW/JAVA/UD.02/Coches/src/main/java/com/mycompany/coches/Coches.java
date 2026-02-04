package com.mycompany.coches;

import java.util.Scanner;

/**
 * Clase principal que gestiona la instanciación de objetos de tipo coche
 * mediante la entrada de datos por teclado.
 *
 * * @author BenjaminDTS
 */
public class Coches {

	/**
	 * Método principal de ejecución.
	 *
	 * @param args Argumentos del sistema.
	 */
	public static void main(String[] args) {
		Scanner leer = new Scanner(System.in);

		// --- Gestión del Primer Objeto ---
		System.out.println("Dime su marca: ");
		String marca = leer.nextLine();

		System.out.println("De qué color es: ");
		String color = leer.nextLine();

		System.out.println("Dime su velocidad: ");
		double velocidad = leer.nextDouble();

		// Creación del objeto modelo1
		coche modelo1 = new coche(marca, color, velocidad);

		System.out.println("La marca es " + modelo1.getmarca());
		System.out.println("El color es " + modelo1.getcolor());
		System.out.println("La velocidad es " + modelo1.getvelocidad());

		// Pruebas de modificación de velocidad
		System.out.println("Aumentando velocidad a: " + modelo1.aumentavelocidad());
		System.out.println("Disminuyendo velocidad a: " + modelo1.disminuyevelocidad());

		System.out.println("-------------------------------------------------");

		// Limpieza del buffer para evitar errores en la siguiente lectura
		leer.nextLine();

		// --- Gestión del Segundo Objeto ---
		coche modelo2;

		System.out.println("Dime la marca del segundo coche: ");
		marca = leer.nextLine(); // Simplificado: asignación directa es más eficiente que replace

		System.out.println("De qué color es: ");
		color = leer.nextLine();

		System.out.println("Dime su velocidad: ");
		velocidad = leer.nextDouble();

		modelo2 = new coche(marca, color, velocidad);

		System.out.println("Datos del modelo 2:");
		System.out.println("Marca: " + modelo2.getmarca() + " | Color: " + modelo2.getcolor());
		System.out.println("Velocidad final: " + modelo2.aumentavelocidad());

		leer.close();
	}
}
