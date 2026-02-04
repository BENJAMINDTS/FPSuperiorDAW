package com.mycompany.execpcionesvendedor;

import java.util.Scanner;

/**
 * Clase principal que gestiona la interfaz de usuario para la creación de
 * vendedores.
 * Implementa bloques try-catch para gestionar posibles errores de validación.
 * * @author BenjaminDTS
 */
public class ExecpcionesVendedor {

	/**
	 * Punto de entrada de la aplicación.
	 * * @param args Argumentos de sistema.
	 */
	public static void main(String[] args) {
		String Nombre;
		String Apellidos;
		int Edad;

		Scanner sc = new Scanner(System.in);

		// Recopilación de datos por teclado
		System.out.println("Indícame el nombre de nuestro vendedor:");
		Nombre = sc.nextLine();

		System.out.println("Ahora dime su apellido:");
		Apellidos = sc.nextLine();

		System.out.println("A continuación dime su edad:");
		Edad = sc.nextInt();

		// Creación de la instancia del vendedor
		Vendedor vendedor1 = new Vendedor(Nombre, Apellidos, Edad);

		/**
		 * Intento de impresión de datos.
		 * Se encierra en un bloque try para capturar excepciones de validación de edad.
		 */
		try {
			vendedor1.Imprimir();
		} catch (IllegalArgumentException e) {
			// Captura y muestra el mensaje definido en el throw de la clase Vendedor
			System.err.println("Se ha producido una excepción: " + e.getMessage());
		} finally {
			sc.close(); // Cierre del recurso Scanner
		}
	}
}