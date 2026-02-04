package com.mycompany.execpcionesvendedor;

/**
 * Entidad que representa a un Vendedor con validaciones de integridad de datos.
 * Utiliza excepciones para asegurar que la edad cumpla con los requisitos
 * legales y lógicos.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Vendedor {
	/** @var String Nombre del vendedor */
	String Nombre;
	/** @var String Apellido del vendedor */
	String Apellido;
	/** @var int Edad del vendedor */
	int Edad;

	/**
	 * Constructor para inicializar un objeto Vendedor.
	 * * @param Nombre Nombre de pila.
	 * 
	 * @param Apellido Apellido familiar.
	 * @param Edad     Edad cronológica.
	 */
	public Vendedor(String Nombre, String Apellido, int Edad) {
		this.Nombre = Nombre;
		this.Apellido = Apellido;
		this.Edad = Edad;
	}

	/**
	 * Obtiene el nombre del vendedor.
	 * 
	 * @return String Nombre.
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * Obtiene el apellido del vendedor.
	 * 
	 * @return String Apellido.
	 */
	public String getApellido() {
		return Apellido;
	}

	/**
	 * Devuelve la edad tras pasar por un proceso de verificación.
	 * * @throws IllegalArgumentException Si la edad no es válida.
	 * 
	 * @return int Edad verificada.
	 */
	public int getEdad() {
		VerificarEdad(this.Edad);
		return Edad;
	}

	/**
	 * Valida si la edad se encuentra dentro del rango permitido (18-120).
	 * * @param edad El valor numérico a comprobar.
	 * 
	 * @throws IllegalArgumentException Si la edad es negativa, mayor a 120 o menor
	 *                                  de edad.
	 */
	public void VerificarEdad(int edad) {
		if (edad < 0 || edad > 120) {
			throw new IllegalArgumentException("Error: La edad no puede ser negativa ni mayor a 120.");
		}
		if (edad < 18) {
			throw new IllegalArgumentException("Error: El vendedor debe ser mayor de edad para trabajar.");
		}
	}

	/**
	 * Muestra por consola los datos del vendedor.
	 * Este método puede propagar una excepción si la edad no es válida al llamar a
	 * getEdad().
	 */
	public void Imprimir() {
		System.out.println("Su nombre es " + getNombre());
		System.out.println("Su apellido es " + getApellido());
		System.out.println("Su edad es " + getEdad());
	}
}