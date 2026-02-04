package com.mycompany.pruebadeexepciones;

/**
 * Representa un Vendedor dentro del sistema con capacidad de autovalidación.
 * Esta clase demuestra el uso de la cláusula 'throws' para delegar la gestión
 * de errores.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Vendedor {
	String nombre;
	String apellido;
	int edad;

	/**
	 * Constructor para inicializar los datos básicos del vendedor.
	 * * @param nombre Nombre del sujeto.
	 * 
	 * @param apellido Apellido del sujeto.
	 * @param edad     Edad cronológica para validar.
	 */
	public Vendedor(String nombre, String apellido, int edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	/**
	 * Valida si la edad del vendedor se encuentra en el rango laboral permitido
	 * (18-120).
	 * * @throws Exception Lanza una excepción genérica si la edad es fuera de
	 * rango.
	 * 
	 * @return String Mensaje confirmando que la edad es válida.
	 */
	public String verificarEdad() throws Exception {
		if (edad < 18 || edad > 120) {
			throw new Exception();
		} else {
			return "La edad es correcta";
		}
	}
}