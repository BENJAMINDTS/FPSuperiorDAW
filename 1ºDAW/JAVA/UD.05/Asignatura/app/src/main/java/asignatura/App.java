package asignatura;

/**
 * Clase principal que coordina la creación, persistencia y recuperación
 * de objetos de tipo Asignatura.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {
	/**
	 * Punto de entrada del programa.
	 * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		// Crear un objeto Asignatura con datos de ejemplo
		Asignatura poo = new Asignatura(4100547, 4, "Programación Orientada a Objetos");

		// Definimos la ruta del archivo binario
		String archivo = "asignatura.dat";

		// Escribir el objeto en el archivo utilizando el método de instancia
		poo.escribirAsignatura(archivo);

		// Leer el objeto desde el archivo utilizando el método estático
		Asignatura asignaturaLeida = Asignatura.leerAsignatura(archivo);

		// Si la lectura fue exitosa, mostramos los datos por pantalla
		if (asignaturaLeida != null) {
			asignaturaLeida.imprimir();
		}
	}
}