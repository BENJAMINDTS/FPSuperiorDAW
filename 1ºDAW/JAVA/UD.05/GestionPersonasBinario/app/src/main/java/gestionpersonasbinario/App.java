package gestionpersonasbinario;
//Importamos las clases necesarias
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        // Bloque try-with-resources para asegurar el cierre automático de los flujos
        try (
            // Crear un flujo de salida para escribir datos en el archivo
            FileOutputStream fOut = new FileOutputStream("C:\\Users\\Usuario\\Desktop\\Hola_Mundo\\GestionDePersonas.dat");
            // Crear un flujo de entrada para leer datos del archivo
            FileInputStream fIn = new FileInputStream("C:\\Users\\Usuario\\Desktop\\Hola_Mundo\\GestionDePersonas.dat");
            // Envolver el flujo de salida en un DataOutputStream para escribir datos primitivos
            DataOutputStream DataOut = new DataOutputStream(fOut);
            // Envolver el flujo de entrada en un DataInputStream para leer datos primitivos
            DataInputStream DataIn = new DataInputStream(fIn)
        ) {
            DataOut.writeUTF("Maria");
            DataOut.writeInt(26);
            DataOut.writeDouble(1.65);

            // Asegurar que todos los datos se escriban en el archivo
            DataOut.flush();

            System.out.println(DataIn.readUTF());
            System.out.println(DataIn.readInt());
            System.out.println(DataIn.readDouble());
        } catch (IOException e) {
            // Manejo de exepciones
            System.out.println("Error al seleccionar el fichero");

        }
    }
}
