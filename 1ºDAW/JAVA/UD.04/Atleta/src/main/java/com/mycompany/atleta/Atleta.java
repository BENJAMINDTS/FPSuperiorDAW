package com.mycompany.atleta;

/**
 * Representa a un deportista en una competición de atletismo.
 * Esta clase demuestra el uso de variables estáticas para gestionar datos
 * globales del equipo, como el tiempo total y el contador de integrantes.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Atleta {

	// Atributos de instancia (únicos para cada objeto)
	private int identificador;
	private String nombre;
	private double tiempo;

	// Atributos estáticos (compartidos por todos los objetos de la clase)
	static int contador = 0;
	static String seleccion = "Colombia";
	static double tiempoEquipo;

	/**
	 * Punto de entrada del programa. Coordina la creación del equipo y la
	 * ejecución de la prueba de relevos.
	 * * @param args Argumentos de sistema.
	 */
	public static void main(String[] args) {
		// Instanciamos 4 objetos de nuestra clase
		Atleta atleta1 = new Atleta("Alejando Perlaza", 9.55);
		Atleta atleta2 = new Atleta("Anthony Zambrano", 9.28);
		Atleta atleta3 = new Atleta("Diego Palomeque", 9.53);
		Atleta atleta4 = new Atleta("Gilmar Herrera", 9.29);

		// Cada Atleta corre su tramo de 400m
		atleta1.correr400Metros();
		atleta2.correr400Metros();
		atleta3.correr400Metros();
		atleta4.correr400Metros();

		// Imprimimos la selección del equipo
		ImprimirSeleccion();

		System.out.println("---------------------------");

		// Imprimimos la ficha individual de cada atleta
		atleta1.ImprimirAteleta();
		atleta2.ImprimirAteleta();
		atleta3.ImprimirAteleta();
		atleta4.ImprimirAteleta();

		System.out.println("---------------------------");

		// Imprimimos el tiempo acumulado de la selección
		ImprimirTiempoTotal();
	}

	/**
	 * Constructor de la clase Atleta.
	 * Incrementa el contador global y asigna un ID único automáticamente.
	 * * @param nombre Nombre completo del deportista.
	 * 
	 * @param tiempo Tiempo registrado en su marca.
	 */
	public Atleta(String nombre, double tiempo) {
		contador++;
		this.identificador = contador;
		this.nombre = nombre;
		this.tiempo = tiempo;
	}

	/**
	 * Acumula el tiempo individual del atleta en la variable estática del equipo.
	 */
	private void correr400Metros() {
		tiempoEquipo += this.tiempo;
	}

	/**
	 * Obtiene el nombre de la selección nacional.
	 * 
	 * @return String seleccion.
	 */
	public static String getSeleccion() {
		return seleccion;
	}

	/**
	 * Obtiene la suma de los tiempos de todos los atletas que han corrido.
	 * 
	 * @return double tiempo total acumulado.
	 */
	public static double getTiempoEquipo() {
		return tiempoEquipo;
	}

	/**
	 * Muestra en consola el nombre de la selección.
	 */
	static private void ImprimirSeleccion() {
		System.out.println("Selección: " + getSeleccion());
	}

	/**
	 * Muestra en consola el tiempo acumulado del equipo.
	 */
	static private void ImprimirTiempoTotal() {
		System.out.println("El tiempo total del equipo es: " + getTiempoEquipo() + " seg");
	}

	/**
	 * Muestra la información detallada (ID, Nombre, Tiempo) del atleta actual.
	 */
	private void ImprimirAteleta() {
		System.out.println("ID: " + this.identificador + " | Nombre: " + this.nombre + " | Tiempo: " + this.tiempo + "s");
	}
}