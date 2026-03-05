package com.mycompany.potencia;

/**
 * Clase encargada de realizar el cálculo de potencias de números enteros.
 * Implementa validaciones mediante excepciones y aserciones para controlar los
 * rangos de entrada.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class calcularPotencia {

	/**
	 * Método principal que ejecuta la lógica de potenciación con control de
	 * errores.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		// Definición de parámetros de la operación
		int base = 11;
		int exponente = 5;
		int resultado = 1;

		try {
			/**
			 * Validación de valores negativos.
			 * Lanza una excepción de argumento ilegal si los valores no son naturales.
			 */
			if (base < 0 || exponente < 0) {
				throw new IllegalArgumentException("La base y el exponente deben ser mayores o iguales a 0");
			}

			/**
			 * Uso de aserciones para el control de rangos durante el desarrollo.
			 * Nota: Deben habilitarse con el parámetro -ea en la JVM.
			 */
			assert base >= 0 && base <= 10 : "La base debe estar entre 0 y 10";
			assert exponente >= 0 && exponente <= 20 : "El exponente debe estar entre 0 y 20";

			// Casos especiales: Cualquier base elevada a 0 o base 1 siempre resulta en 1
			if (base == 1 || exponente == 0) {
				resultado = 1;
			} else {
				/**
				 * Cálculo mediante bucle iterativo.
				 * Se multiplica la base sucesivamente según indique el exponente.
				 */
				for (int i = 0; i < exponente; i++) {
					resultado *= base;

					// Control de desbordamiento visual: Límite de 1000
					if (resultado > 1000) {
						System.out.println("El resultado parcial ha superado el límite de 1000.");
						return; // Finaliza la ejecución si excede el límite
					}
				}
			}

			// Impresión del resultado final si no superó el límite dentro del bucle
			if (resultado <= 1000) {
				System.out.println("El resultado de " + base + " elevado a " + exponente + " es: " + resultado);
			}

		} catch (IllegalArgumentException e) {
			// Captura de errores de lógica de negocio (valores negativos)
			System.out.println("Error de validación: " + e.getMessage());
		} catch (Exception e) {
			// Captura de cualquier otra anomalía no prevista
			System.out.println("Se produjo un error inesperado: " + e.getMessage());
		}
	}
}