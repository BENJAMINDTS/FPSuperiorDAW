package listadodeficheros;

import java.io.File;

/**
 * Clase que permite explorar y catalogar el contenido de un directorio en el
 * sistema.
 * Identifica si los elementos son carpetas o archivos y extrae la extensión de
 * estos últimos.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class ListadoDeFicheros {

	/**
	 * Método principal que escanea una ruta específica y muestra su contenido.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		// Arreglo para almacenar solo los nombres de los archivos
		String[] NFicheros;
		// Arreglo para almacenar objetos File (con metadatos como permisos, tipo, etc.)
		File[] TFichero;

		// Definición de la ruta del directorio a explorar
		File f = new File("C:\\Users\\Usuario\\Desktop\\Hola_Mundo");

		// Obtenemos la lista de nombres y la lista de objetos File
		NFicheros = f.list();
		TFichero = f.listFiles();

		/**
		 * Verificamos que el directorio exista y no esté vacío para evitar
		 * errores de puntero nulo (NullPointerException).
		 */
		if (TFichero != null) {
			for (int i = 0; i < NFicheros.length; i++) {

				// Determinamos si el elemento actual es una carpeta
				if (TFichero[i].isDirectory()) {
					System.out.println("[DIR]  " + NFicheros[i]);
				}
				// Si es un archivo, procedemos a identificar su tipo
				else {
					System.out.println("[FILE] " + NFicheros[i] + " | Extensión: " + getFileExtension(TFichero[i]));
				}
			}
		} else {
			System.out.println("La ruta especificada no es válida o está vacía.");
		}
	}

	/**
	 * Extrae la extensión de un archivo basándose en la posición del último punto.
	 * * @param file Objeto File del cual extraer la extensión.
	 * 
	 * @return String La extensión del archivo o "desconocido" si no posee.
	 */
	private static String getFileExtension(File file) {
		String name = file.getName();

		/**
		 * Buscamos el índice del último carácter '.'
		 * Si el archivo es "documento.pdf", lastIndexOf será 9.
		 */
		int lastIndexOf = name.lastIndexOf(".");

		// Caso en el que el archivo no tiene punto (ej. un binario o archivo de
		// sistema)
		if (lastIndexOf == -1) {
			return "desconocido";
		}

		// Retornamos la subcadena que va después del punto
		return name.substring(lastIndexOf + 1);
	}
}