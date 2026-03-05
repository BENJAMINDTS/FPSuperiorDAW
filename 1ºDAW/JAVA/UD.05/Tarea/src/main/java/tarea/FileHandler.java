package tarea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la lectura y escritura de archivos binarios de objetos.
 * Se encarga de la creación automática del archivo si no existe.
 * * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class FileHandler {
	private File file;

	/**
	 * Constructor que define la ruta del archivo de persistencia.
	 */
	public FileHandler() {
		this.file = new File("src/main/java/tarea/20889654L.txt");
		createFileIfNotExists();
	}

	private void createFileIfNotExists() {
		try {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
		} catch (IOException e) {
			System.err.println("Error creación archivo: " + e.getMessage());
		}
	}

	/**
	 * Lee la lista de productos desde el archivo.
	 * * @return List de productos recuperados.
	 */
	@SuppressWarnings("unchecked")
	public List<Producto> readProducts() throws IOException, ClassNotFoundException {
		if (!file.exists() || file.length() == 0)
			return new ArrayList<>();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (List<Producto>) ois.readObject();
		}
	}

	/**
	 * Sobrescribe el archivo con la lista actualizada de productos.
	 * * @param productos Lista a serializar.
	 */
	public void writeProducts(List<Producto> productos) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(productos);
		}
	}
}