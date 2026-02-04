package Persona;

/**
 * Clase base que define los atributos comunes para cualquier individuo.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Persona {
	private String nombre;
	private int edad;

	/**
	 * Constructor vacío por defecto.
	 */
	public Persona() {
	}

	/**
	 * Constructor con parámetros para inicializar una persona.
	 * * @param nombre Identificador nominal.
	 * 
	 * @param edad Años de vida.
	 */
	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	// --- Getters ---

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	// --- Setters ---

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
}