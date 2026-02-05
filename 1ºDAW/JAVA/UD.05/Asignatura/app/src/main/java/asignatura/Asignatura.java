package asignatura;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Representa una asignatura académica y permite su almacenamiento en archivos
 * binarios.
 * Implementa Serializable para permitir la conversión del objeto en un flujo de
 * bytes.
 * * @author BenjaminDTS
 */
public class Asignatura implements Serializable {
	/** @var int codigo Identificador numérico único de la asignatura */
	private int codigo;
	/** @var int creditos Número de créditos académicos */
	private int creditos;
	/** @var String nombre Nombre descriptivo de la materia */
	private String nombre;

	/**
	 * Constructor para inicializar una nueva Asignatura.
	 * * @param codigo Código de la asignatura.
	 * 
	 * @param creditos Créditos asignados.
	 * @param nombre   Nombre de la materia.
	 */
	public Asignatura(int codigo, int creditos, String nombre) {
		this.codigo = codigo;
		this.creditos = creditos;
		this.nombre = nombre;
	}

	// --- Métodos Accessores (Getters) ---
	/**
	 * 
	 * @return int El código de la asignatura.
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * 
	 * @return int El número de créditos de la asignatura.
	 */
	public int getCreditos() {
		return creditos;
	}
	/**
	 * 
	 * @return String El nombre de la asignatura.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Muestra la información de la asignatura por la consola estándar.
	 */
	public void imprimir() {
		System.out.println("--- Ficha de Asignatura ---");
		System.out.println("Nombre: " + nombre);
		System.out.println("Código: " + codigo);
		System.out.println("Créditos: " + creditos);
	}

	/**
	 * Serializa el objeto actual y lo guarda en la ruta especificada.
	 * * @param rutaArchivo Camino donde se guardará el archivo .dat
	 */
	public void escribirAsignatura(String rutaArchivo) {
		try (
				FileOutputStream fOut = new FileOutputStream(rutaArchivo);
				ObjectOutputStream oOut = new ObjectOutputStream(fOut)) {
			// Guardamos el objeto completo (this) en lugar de campos individuales
			oOut.writeObject(this);
			oOut.flush();
			System.out.println("Objeto guardado exitosamente en: " + rutaArchivo);
		} catch (IOException e) {
			System.err.println("Error al escribir el archivo: " + e.getMessage());
		}
	}

	/**
	 * Recupera un objeto Asignatura desde un archivo binario (Deserialización).
	 * * @param archivo Nombre o ruta del archivo a leer.
	 * 
	 * @return Asignatura El objeto recuperado o null si ocurre un error.
	 */
	public static Asignatura leerAsignatura(String archivo) {
		try (
				FileInputStream fIn = new FileInputStream(archivo);
				ObjectInputStream oIn = new ObjectInputStream(fIn)) {
			// Leemos el objeto y realizamos el casting a la clase Asignatura
			Asignatura asignatura = (Asignatura) oIn.readObject();
			System.out.println("Asignatura recuperada correctamente.");
			return asignatura;
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer del archivo: " + e.getMessage());
			return null;
		}
	}
}