package metodosabstractos;

import java.util.List;
import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private static double totalTiempos;
    private String pais;

    private List<Ciclista> ciclistas;
    List<Ciclista> List = new ArrayList<Ciclista>();

    public List<Ciclista> getCiclistas() {
        return ciclistas;
    }

    public void buscarCiclista(int identificacion) {
        for (Ciclista ciclista : ciclistas) {
            if (ciclista.getIdentificacion() == identificacion) {
                System.out.println("Ciclista encontrado");
                ciclista.getNombre();
                return;
            }
        }
        System.out.println("Ciclista no encontrado");
    }

    public Equipo(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.totalTiempos = 0;
        this.ciclistas = new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public static void buscarEquipo(String nombre) {
        if (nombre.equals(nombre)) {
            System.out.println("Equipo encontrado");
        } else {
            System.out.println("Equipo no encontrado");
        }
    }

    
}
