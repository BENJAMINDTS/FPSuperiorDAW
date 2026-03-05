package gestionpersonasbinario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase principal para la gestión de archivos binarios mediante flujos de datos
 * primitivos.
 * Demuestra la escritura y lectura secuencial de Strings, enteros y decimales.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

	/**
	 * Punto de entrada de la aplicación.
	 * Utiliza la estructura try-with-resources para garantizar que los flujos
	 * se cierren correctamente, incluso si ocurre una excepción.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {

		// Definición de la ruta del archivo de datos
		String ruta = "C:\\Users\\Usuario\\Desktop\\Hola_Mundo\\GestionDePersonas.dat";

		try (
				// --- Flujos de Salida (Escritura) ---
				FileOutputStream fOut = new FileOutputStream(ruta);
				DataOutputStream dataOut = new DataOutputStream(fOut);

				// --- Flujos de Entrada (Lectura) ---
				FileInputStream fIn = new FileInputStream(ruta);
				DataInputStream dataIn = new DataInputStream(fIn)) {
			/**
			 * Escritura de datos:
			 * Es fundamental recordar el orden, ya que la lectura debe ser idéntica.
			 */
			dataOut.writeUTF("Maria"); // Escribe un String en formato UTF-8
			dataOut.writeInt(26); // Escribe un entero de 4 bytes
			dataOut.writeDouble(1.65); // Escribe un double de 8 bytes

			// Forzamos el volcado de datos desde el búfer al archivo físico
			dataOut.flush();

			/**
			 * Lectura de datos:
			 * Debemos usar los métodos correspondientes al tipo de dato escrito
			 * originalmente.
			 */
			System.out.println("Nombre recuperado: " + dataIn.readUTF());
			System.out.println("Edad recuperada: " + dataIn.readInt());
			System.out.println("Estatura recuperada: " + dataIn.readDouble());

		} catch (IOException e) {
			/**
			 * Captura de errores de Entrada/Salida.
			 * Proporcionamos un mensaje descriptivo del fallo técnico.
			 */
			System.err.println("Error en la gestión del fichero: " + e.getMessage());
		}
	}
}