package tarea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private File file;

    public FileHandler() {
        this.file = new File("src/main/java/tarea/20889654L.txt");
        System.out.println("Ruta del archivo: " + file.getAbsolutePath());
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("Archivo creado: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public List<Producto> readProducts() throws IOException, ClassNotFoundException {
        if (!file.exists()) {
            System.out.println("El archivo no existe: " + file.getAbsolutePath());
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }
    }

    public void writeProducts(List<Producto> productos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            throw e;
        }
    }

    public boolean fileExists() {
        return file.exists();
    }
}