package com.mycompany.peliculas;

/**
 * Entidad que representa una obra cinematográfica.
 * Incluye lógica para determinar si una película es épica y comparar
 * similitudes entre títulos.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Pelicula {
	private String nombre;
	private String director;
	private Genero genero;
	private int duracion;
	private int año;
	private double calificacion;

	/**
	 * Enumeración para restringir los géneros cinematográficos permitidos.
	 */
	public enum Genero {
		ACCION, COMEDIA, DRAMA, SUSPENSO
	}

	/**
	 * Constructor para inicializar una película con todos sus atributos.
	 * * @param nombre Título de la película.
	 * 
	 * @param director     Persona que dirigió la obra.
	 * @param genero       Categoría de la película (Tipo Genero).
	 * @param duracion     Tiempo en minutos.
	 * @param año          Año de estreno.
	 * @param calificacion Nota numérica del 0 al 10.
	 */
	public Pelicula(String nombre, String director, Genero genero, int duracion,
			int año, double calificacion) {
		this.nombre = nombre;
		this.director = director;
		this.genero = genero;
		this.duracion = duracion;
		this.año = año;
		this.calificacion = calificacion;
	}

	// --- Métodos Getter y Setter ---

	/**
	 * 
	 * @return String Nombre de la película
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * 
	 * @return String Director de la película
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * 
	 * @return Genero Género de la película
	 */
	public Genero getGenero() {
		return genero;
	}
	/**
	 * 
	 * @return int Duración en minutos
	 */
	public int getDuracion() {
		return duracion;
	}
	/**
	 * 
	 * @return int Año de estreno
	 */
	public int getAño() {
		return año;
	}
	/**
	 * 
	 * @return double Calificación numérica
	 */
	public double getCalificacion() {
		return calificacion;
	}
	/**
	 * Establece el nombre de la película.
	 * 
	 * @param nombre Título a asignar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Establece el director de la película.
	 * 
	 * @param director Nombre del director.
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * Establece el género de la película.
	 * 
	 * @param genero Género a asignar.
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	/**
	 * Establece la duración de la película.
	 * 
	 * @param duracion Tiempo en minutos.
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	/**
	 * Establece el año de estreno de la película.
	 * 
	 * @param año Año a asignar.
	 */
	public void setAño(int año) {
		this.año = año;
	}
	/**
	 * Establece la calificación numérica de la película.
	 * 
	 * @param calificacion Nota del 0 al 10.
	 */
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	/**
	 * Determina si la película se considera épica según su duración.
	 * 
	 * @return boolean True si dura 180 minutos o más.
	 */
	private boolean esPeliculaEpica() {
		return duracion >= 180;
	}

	/**
	 * Compara esta película con otra para ver si comparten género y valoración
	 * cualitativa.
	 * * @param otraPelicula Objeto de tipo Pelicula a comparar.
	 * 
	 * @return boolean True si son similares.
	 */
	public boolean esSimilar(Pelicula otraPelicula) {
		return (this.genero == otraPelicula.genero &&
				this.calcularValoracion().equals(otraPelicula.calcularValoracion()));
	}

	/**
	 * Transforma la calificación numérica en una etiqueta descriptiva.
	 * * @return String Categoría de la valoración.
	 */
	private String calcularValoracion() {
		if (calificacion >= 0 && calificacion <= 2)
			return "Muy mala";
		else if (calificacion > 2 && calificacion <= 5)
			return "Mala";
		else if (calificacion > 5 && calificacion <= 7)
			return "regular";
		else if (calificacion > 7 && calificacion <= 8)
			return "Buena";
		else if (calificacion > 8 && calificacion <= 10)
			return "Excelente";
		else
			return "Valoración no especificada";
	}

	/**
	 * Muestra la ficha técnica completa de la película por consola.
	 */
	public void Imprimir() {
		System.out.println("Nuestra película se llama: " + getNombre());
		System.out.println("Su director es: " + getDirector());
		System.out.println("Su género es: " + getGenero());
		System.out.println("Duración: " + getDuracion() + " min");
		System.out.println("Año de rodaje: " + getAño());
		System.out.println("Calificación: " + getCalificacion() +
				" (" + calcularValoracion() + ")");
		System.out.println("¿Es una película épica? " + (esPeliculaEpica() ? "Sí" : "No"));
	}
}