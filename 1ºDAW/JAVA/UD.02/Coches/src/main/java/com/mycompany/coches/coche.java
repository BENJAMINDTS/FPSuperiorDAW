package com.mycompany.coches;

/**
 * Representa la entidad Coche con sus atributos básicos y acciones de
 * movimiento.
 *
 * * @author BenjaminDTS
 *
 * @version 1.0
 */
public class coche {

	/**
	 * @var String marca Nombre del fabricante del coche
	 */
	String marca;
	/**
	 * @var String color Tono cromático de la carrocería
	 */
	String color;
	/**
	 * @var double velocidad Magnitud actual de rapidez en km/h
	 */
	double velocidad;

	/**
	 * Constructor para inicializar un objeto coche con valores específicos.
	 *
	 * * @param marca Marca del vehículo.
	 * 
	 * @param color     Color del vehículo.
	 * @param velocidad Velocidad inicial.
	 */
	public coche(String marca, String color, double velocidad) {
		this.marca = marca;
		this.color = color;
		this.velocidad = velocidad;
	}

	/**
	 * Establece la marca del coche.
	 *
	 * @param marca Nombre de la marca.
	 */
	public void setmarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Establece el color del coche.
	 *
	 * @param color Nombre del color.
	 */
	public void setdicolor(String color) {
		this.color = color;
	}

	/**
	 * Actualiza la velocidad actual del coche.
	 *
	 * @param velocidad Nueva velocidad en double.
	 */
	public void setvelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	/**
	 * Obtiene la marca almacenada.
	 *
	 * @return String marca.
	 */
	public String getmarca() {
		return marca;
	}

	/**
	 * Obtiene el color del vehículo.
	 *
	 * @return String color.
	 */
	public String getcolor() {
		return color;
	}

	/**
	 * Obtiene la velocidad actual registrada.
	 *
	 * @return double velocidad.
	 */
	public double getvelocidad() {
		return velocidad;
	}

	/**
	 * Incrementa la velocidad actual en 10 unidades.
	 *
	 * @return double Nueva velocidad tras el aumento.
	 */
	public double aumentavelocidad() {
		this.velocidad += 10;
		return velocidad;
	}

	/**
	 * Reduce la velocidad actual en 5 unidades.
	 *
	 * @return double Nueva velocidad tras la disminución.
	 */
	public double disminuyevelocidad() {
		this.velocidad -= 5;
		return velocidad;
	}
}
