package com.mycompany.planetas;

/**
 * Define la estructura y el comportamiento de un objeto planetario.
 * Contiene atributos físicos y métodos de cálculo para densidad y ubicación
 * espacial.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Metodos {
	String nombre;
	int cantidad_Santelites = 0;
	double masa = 0;
	double volumen = 0;
	int distancia_Km = 0;
	int distancia_Sol = 0;
	boolean observable = false;
	tipo_Planeta planeta;

	/**
	 * Enumeración para clasificar los planetas según su composición y tamaño.
	 */
	public enum tipo_Planeta {
		GASEOSO, TERRESTRE, ENANO;
	}

	/**
	 * Constructor para inicializar un planeta con sus propiedades físicas y
	 * orbitales.
	 * * @param nombre Nombre del planeta.
	 * 
	 * @param cantidad_Santelites Número de satélites naturales.
	 * @param masa                Masa del planeta en kg.
	 * @param volumen             Volumen del planeta en litros o km³.
	 * @param distancia_Km        Diámetro o distancia orbital en Km.
	 * @param distancia_Sol       Distancia respecto al sol en millones de Km.
	 * @param observable          Indica si es visible a simple vista o con
	 *                            telescopios básicos.
	 * @param planeta             Clasificación del tipo de planeta (Enum).
	 */
	public Metodos(String nombre, int cantidad_Santelites, double masa,
			double volumen, int distancia_Km, int distancia_Sol,
			boolean observable, tipo_Planeta planeta) {
		this.nombre = nombre;
		this.cantidad_Santelites = cantidad_Santelites;
		this.masa = masa;
		this.volumen = volumen;
		this.distancia_Km = distancia_Km;
		this.distancia_Sol = distancia_Sol;
		this.observable = observable;
		this.planeta = planeta;
	}

	// --- Métodos Getter y Setter ---

	public String getNombre() {
		return nombre;
	}

	public int getCantidad_Santelites() {
		return cantidad_Santelites;
	}

	public double getMasa() {
		return masa;
	}

	public double getVolumen() {
		return volumen;
	}

	public int getDistancia_Km() {
		return distancia_Km;
	}

	public int getDistancia_Sol() {
		return distancia_Sol;
	}

	public boolean isObservable() {
		return observable;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCantidad_Santelites(int cantidad_Santelites) {
		this.cantidad_Santelites = cantidad_Santelites;
	}

	public void setMasa(double masa) {
		this.masa = masa;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public void setDistancia_Km(int distancia_Km) {
		this.distancia_Km = distancia_Km;
	}

	public void setDistancia_Sol(int distancia_Sol) {
		this.distancia_Sol = distancia_Sol;
	}

	public void setObservable(boolean observable) {
		this.observable = observable;
	}

	/**
	 * Muestra por consola la ficha técnica detallada del planeta.
	 */
	public void imprimir() {
		System.out.println("El nombre del planeta es: " + nombre);
		System.out.println("Nuestro planeta tiene " + cantidad_Santelites + " satélites/asteroides a su alrededor.");
		System.out.println("La masa de nuestro planeta es: " + masa + " kg.");
		System.out.println("El volumen de nuestro planeta es: " + volumen + " L.");
		System.out.println("Tiene una distancia total de: " + distancia_Km + " Km.");
		System.out.println("Se encuentra a una distancia del sol de: " + distancia_Sol + " millones de Km.");
		System.out.println("Tu planeta es de tipo: " + planeta);
		System.out.println("¿Es observable? " + (observable ? "Sí" : "No"));
		System.out.println("La densidad del planeta es: " + densidad(volumen, masa));
		System.out.println("¿Está dentro del sistema solar? " + (sistema_solar(distancia_Sol) ? "Sí" : "No"));
		System.out.println("--------------------------------------------------");
	}

	/**
	 * Calcula la densidad del planeta basada en la relación masa/volumen.
	 * $$ \rho = \frac{m}{V} $$
	 * * @param volumen Espacio ocupado por el cuerpo celesta.
	 * 
	 * @param masa Cantidad de materia del planeta.
	 * @return double Valor de la densidad.
	 */
	public static double densidad(double volumen, double masa) {
		return masa / volumen;
	}

	/**
	 * Verifica si el planeta pertenece al sistema solar según su distancia al Sol.
	 * * @param distancia_Sol Distancia en millones de Km.
	 * 
	 * @return boolean True si cumple con los rangos definidos.
	 */
	public static boolean sistema_solar(double distancia_Sol) {
		return distancia_Sol <= 314155527 || distancia_Sol >= 508632758;
	}
}