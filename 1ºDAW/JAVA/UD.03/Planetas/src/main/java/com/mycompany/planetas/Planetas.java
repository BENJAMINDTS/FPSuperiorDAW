package com.mycompany.planetas;

import com.mycompany.planetas.Metodos.tipo_Planeta;

/**
 * Clase principal que inicializa objetos de tipo Metodos (Planetas)
 * y ejecuta la presentación de sus datos.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Planetas {

	/**
	 * Punto de entrada de la aplicación.
	 * * @param args Argumentos de sistema.
	 */
	public static void main(String[] args) {

		// Creación del objeto Tierra
		Metodos tierra = new Metodos("Tierra", 1, 5.972e24,
				1.08321e12, 149600000, 150, true,
				tipo_Planeta.TERRESTRE);

		// Creación de un exoplaneta (OGLE-2016-BLG-1195Lb)
		Metodos ogle2016blg1195lb = new Metodos("OGLE-2016-BLG-1195Lb", 0,
				2e12, 1e10, 2000000000, 1300000, false,
				tipo_Planeta.GASEOSO);

		// Impresión de resultados
		tierra.imprimir();
		ogle2016blg1195lb.imprimir();
	}
}