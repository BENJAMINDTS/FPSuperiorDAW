package com.mycompany.comprobacion_y_transformacion_de_horas;

import java.util.Scanner;

/**
 * Clase que permite validar una entrada de tiempo en formato de 24 horas
 * y realizar la conversión a formato AM/PM si el usuario lo requiere.
 * * @author BenjaminDTS
 *
 * @version 1.0
 */
public class Comprobacion_y_Transformacion_de_Horas {

	/**
	 * Método principal que gestiona la lógica de validación y transformación.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce una hora con el formato horario hh:mm");
		String hora = sc.nextLine();

		// Validación inicial de la estructura de la cadena (longitud y separador)
		if (hora.length() != 5 || hora.charAt(2) != ':') {
			System.out.println("El formato estructural es incorrecto.");
		}

		int horas;
		int minutos;

		/**
		 * Extracción de valores numéricos mediante subcadenas.
		 * Se ajusta el rango de la hora de (0,1) a (0,2) para capturar dos dígitos.
		 */
		horas = Integer.parseInt(hora.substring(0, 2));
		minutos = Integer.parseInt(hora.substring(3, 5));

		// Validación de rangos lógicos (Horas 0-23, Minutos 0-59)
		boolean formatoValido = (horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59);

		if (formatoValido) {
			System.out.println("El formato introducido es correcto");
		} else {
			System.out.println("El formato introducido es incorrecto (valores fuera de rango)");
			System.exit(0); // Finaliza si la hora es lógica pero inválida
		}

		System.out.println("¿Quieres ver la hora que has introducido? (si/no)");
		String eleccion = sc.nextLine();

		if (eleccion.equalsIgnoreCase("no")) {
			System.exit(0);
		} else {
			System.out.println("¿Quieres verlo en formato 24h o en formato am/pm?");
			eleccion = sc.nextLine();

			if (eleccion.equalsIgnoreCase("24h")) {
				System.out.println("Hora en 24h: " + hora);
			} else {
				// Lógica de transformación a formato 12 horas (AM/PM)
				String ampm;
				if (horas == 0) {
					ampm = 12 + ":" + String.format("%02d", minutos) + " am";
				} else if (horas == 12) {
					ampm = 12 + ":" + String.format("%02d", minutos) + " pm";
				} else if (horas > 12) {
					ampm = (horas - 12) + ":" + String.format("%02d", minutos) + " pm";
				} else {
					ampm = horas + ":" + String.format("%02d", minutos) + " am";
				}
				System.out.println("Hora en formato AM/PM: " + ampm);
			}
		}
		sc.close();
	}
}