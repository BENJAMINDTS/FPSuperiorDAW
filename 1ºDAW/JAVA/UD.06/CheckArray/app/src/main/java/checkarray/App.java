package checkarray;

import java.util.Scanner;

/**
 * Clase que permite filtrar un arreglo de cadenas de texto basándose en su
 * carácter inicial.
 * El usuario define el tamaño del arreglo, introduce los valores y selecciona
 * la letra de búsqueda.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada de la aplicación. Gestiona la entrada de datos y la lógica
	 * de filtrado.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// --- Inicialización y llenado del arreglo ---
		System.out.println("Introduce el tamaño del array: ");
		int tamano = sc.nextInt();
		String[] strings = new String[tamano];

		for (int i = 0; i < strings.length; i++) {
			System.out.println("Introduce el valor de la posición " + i + ": ");
			strings[i] = sc.next();
		}

		// --- Configuración del criterio de búsqueda ---
		System.out.println("Introduce la letra a buscar: ");
		/**
		 * Leemos la entrada como String y extraemos el primer carácter (índice 0)
		 * para obtener el tipo char necesario para la comparación.
		 */
		char letra = sc.next().charAt(0);

		/**
		 * Recorrido del arreglo mediante un bucle for-each.
		 * Compara el primer carácter de cada cadena con la letra proporcionada.
		 */
		for (String string : strings) {
			// Verificamos que la cadena no esté vacía para evitar errores de índice
			if (!string.isEmpty() && string.charAt(0) == letra) {
				System.out.println(string + " empieza por " + letra);
			}
		}

		// Cerramos el recurso Scanner al finalizar toda la lógica del programa
		sc.close();
	}
}