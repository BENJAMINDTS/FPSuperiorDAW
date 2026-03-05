package com.mycompany.peliculas;

/**
 * Clase principal para la ejecución y testeo del sistema de Películas.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Peliculas {

	/**
	 * Crea instancias de películas y verifica sus propiedades y similitudes.
	 * * @param args Argumentos de sistema.
	 */
	public static void main(String[] args) {

		// Instanciación de objetos Pelicula
		Pelicula pelicula1 = new Pelicula("Ghandi", "Richard A",
				Pelicula.Genero.DRAMA, 191, 1982, 8.0);

		Pelicula pelicula2 = new Pelicula("Thor", "Kenneth Branagh",
				Pelicula.Genero.DRAMA, 115, 2011, 8.0);

		// Ejecución de métodos de impresión
		pelicula1.Imprimir();
		System.out.println("---------------------------------------------------------------");
		pelicula2.Imprimir();
		System.out.println("---------------------------------------------------------------");

		// Comparación de similitud
		System.out.println("¿Son ambas películas similares en género y valoración? " +
				((pelicula1.esSimilar(pelicula2)) ? "Sí" : "No"));
	}
}