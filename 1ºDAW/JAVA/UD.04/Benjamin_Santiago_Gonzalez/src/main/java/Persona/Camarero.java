package Persona;

/**
 * Representa a un empleado de tipo Camarero que hereda los atributos de
 * Persona.
 * Incluye un contador estático para el control de personal.
 * * @author BenjaminDTS
 */
public class Camarero extends Persona {
	private double sueldo;
	private static int contador = 0; // Atributo compartido por todas las instancias de Camarero

	/**
	 * Constructor para la clase Camarero.
	 * * @param sueldo Remuneración del camarero.
	 * 
	 * @param nombre Nombre heredado de la clase Persona.
	 * @param edad   Edad heredada de la clase Persona.
	 */
	public Camarero(double sueldo, String nombre, int edad) {
		// Invocación al constructor de la clase padre (Persona)
		super(nombre, edad);
		this.sueldo = sueldo;
		contador++; // Incremento del contador global de empleados
	}

	// --- Getters ---

	/** @return double El sueldo actual */
	public double getSueldo() {
		return sueldo;
	}

	/** @return int El número total de camareros instanciados */
	public static int getContador() {
		return contador;
	}

	// --- Setters ---

	/** @param sueldo Nueva remuneración */
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
}