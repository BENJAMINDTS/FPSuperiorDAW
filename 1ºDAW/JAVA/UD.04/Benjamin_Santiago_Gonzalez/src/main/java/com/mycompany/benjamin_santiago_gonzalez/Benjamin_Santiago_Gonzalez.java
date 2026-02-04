package com.mycompany.benjamin_santiago_gonzalez;

// Importamos el paquete persona para acceder a las clases Persona y Camarero
import Persona.Camarero;
import Persona.Persona;

/**
 * Clase principal que actúa como punto de entrada para probar la jerarquía
 * de herencia entre Persona y Camarero.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Benjamin_Santiago_Gonzalez {

	/**
	 * Ejecuta la instanciación de objetos y la muestra de datos por consola.
	 * * @param args Argumentos de sistema.
	 */
	public static void main(String[] args) {

		// Creamos un objeto de la clase base Persona
		Persona p1 = new Persona("Benjamin", 22);

		// Creamos dos objetos de la clase derivada Camarero
		// Se observa el uso de polimorfismo implícito en la construcción
		Camarero c1 = new Camarero(1200, "Jose", 24);
		Camarero c2 = new Camarero(1547, "Antonio", 37);

		// --- Bloque de Impresión de Resultados ---
		System.out.println("La persona es:");
		System.out.println("-" + p1.getNombre());
		System.out.println("-" + p1.getEdad());

		System.out.println("------------------------------------------------");

		System.out.println("El camarero numero 1:");
		System.out.println("-Nombre: " + c1.getNombre());
		System.out.println("-Edad: " + c1.getEdad());
		System.out.println("-Sueldo: " + c1.getSueldo());

		System.out.println("El camarero numero 2:");
		System.out.println("-Nombre: " + c2.getNombre());
		System.out.println("-Edad: " + c2.getEdad());
		System.out.println("-Sueldo: " + c2.getSueldo());

		System.out.println("------------------------------------------------");

		// Acceso al método estático para obtener el conteo global de camareros
		System.out.println("Tenemos un total de: " + Camarero.getContador() + " camareros registrados.");
	}
}