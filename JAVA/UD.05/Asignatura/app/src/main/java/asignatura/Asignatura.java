package asignatura;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Asignatura implements Serializable {
    private int codigo, creditos;
    private String nombre;
    
    
    public Asignatura(int codigo, int creditos, String nombre) {
        this.codigo = codigo;
        this.creditos = creditos;
        this.nombre = nombre;
    }
    

    public int getCodigo() {
        return codigo;
    }


    public int getCreditos() {
        return creditos;
    }


    public String getNombre() {
        return nombre;
    }


    public void imprimir(){
        System.out.println("Asignatura: " + nombre);
        System.out.println("Codigo: " + codigo);
        System.out.println("Creditos: " + creditos);
    }
    public void escribirAsignatura(){
        try (
            FileOutputStream fOut = new FileOutputStream("C:\\Users\\Usuario\\Desktop\\Hola_Mundo\\Asignaturas.dat");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut)
            ) 
        {
            oOut.writeObject(getNombre());
            oOut.writeObject(getCodigo());
            oOut.writeObject(getCreditos());
            oOut.flush();
            oOut.close();
        }catch(IOException e){
            System.out.println("Error");
        }
    }
    public static Asignatura leerAsignatura(String archivo) {
        try (FileInputStream fIn = new FileInputStream(archivo);
             ObjectInputStream oIn = new ObjectInputStream(fIn)) {
            Asignatura asignatura = (Asignatura) oIn.readObject();
            System.out.println("Asignatura leída del archivo " + archivo);
            return asignatura;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer del archivo: " + e.getMessage());
            return null;
        }
    }
    
}
