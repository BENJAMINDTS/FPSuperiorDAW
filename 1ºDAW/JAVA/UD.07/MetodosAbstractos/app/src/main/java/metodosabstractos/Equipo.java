package metodosabstractos;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un equipo de ciclismo profesional.
 * Esta clase gestiona el conjunto de ciclistas que pertenecen al equipo,
 * permitiendo realizar búsquedas y llevar un control global de tiempos.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Equipo {
    /** Nombre identificativo del equipo */
    private String nombre;
    /** Acumulador estático de los tiempos de todos los integrantes del equipo */
    private static double totalTiempos;
    /** País de origen o sede del equipo */
    private String pais;
    /** Lista de ciclistas que conforman la plantilla del equipo */
    private final List<Ciclista> ciclistas;

    /**
     * Constructor para inicializar un equipo con su nombre y país.
     * Prepara la lista de ciclistas y reinicia el contador de tiempos.
     * * @param nombre Nombre del equipo.
     * 
     * @param pais País de procedencia.
     */
    public Equipo(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        Equipo.totalTiempos = 0;
        this.ciclistas = new ArrayList<>();
    }

    /**
     * Busca un ciclista dentro de la plantilla del equipo mediante su ID.
     * * @param identificacion ID numérica única del ciclista a buscar.
     */
    public void buscarCiclista(int identificacion) {
        for (Ciclista ciclista : ciclistas) {
            if (ciclista.getIdentificacion() == identificacion) {
                System.out.println("Ciclista encontrado: " + ciclista.getNombre());
                return;
            }
        }
        System.out.println("Ciclista no encontrado en este equipo.");
    }

    /**
     * Método estático para validar la existencia de un equipo por nombre.
     * Nota: Actualmente compara el parámetro consigo mismo; se recomienda
     * integrarlo con una lista maestra de equipos.
     * * @param nombre Nombre del equipo a verificar.
     */
    public static void buscarEquipo(String nombre) {
        // Lógica de búsqueda simplificada
        if (nombre != null && !nombre.isEmpty()) {
            System.out.println("Equipo " + nombre + " encontrado en el sistema.");
        } else {
            System.out.println("Nombre de equipo no válido.");
        }
    }

    // --- Métodos de Acceso (Getters y Setters) ---

    /** @return La lista de objetos Ciclista del equipo. */
    public List<Ciclista> getCiclistas() {
        return ciclistas;
    }

    /** @return El nombre del equipo. */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre Nuevo nombre para el equipo. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return El país del equipo. */
    public String getPais() {
        return pais;
    }

    /** @param pais Nuevo país de origen. */
    public void setPais(String pais) {
        this.pais = pais;
    }
}