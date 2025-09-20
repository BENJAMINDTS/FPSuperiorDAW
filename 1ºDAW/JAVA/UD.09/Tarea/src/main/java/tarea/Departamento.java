package tarea;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Departamento implements Serializable, Comparable<Departamento> {
    private static final long serialVersionUID = 1L;

    @Id
    private int codigo;

    private String nombre;
    private int idLocalizacion;
    private int idManager;

    // Constructor sin parámetros
    public Departamento() {
        this.codigo = 0;
        this.nombre = "";
        this.idLocalizacion = 0;
        this.idManager = 0;
    }

    // Constructor con parámetros
    public Departamento(int codigo, String nombre, int idLocalizacion, int idManager) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder los 50 caracteres.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.idLocalizacion = idLocalizacion;
        this.idManager = idManager;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder los 50 caracteres.");
        }
        this.nombre = nombre;
    }

    public int getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(int idLocalizacion) {
        if (idLocalizacion <= 0) {
            throw new IllegalArgumentException("El idLocalizacion debe ser un número positivo.");
        }
        
        this.idLocalizacion = idLocalizacion;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        if (idManager <= 0) {
            throw new IllegalArgumentException("El idManager debe ser un número positivo.");
            
        }
        this.idManager = idManager;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", idLocalizacion=" + idLocalizacion +
                ", idManager=" + idManager +
                '}';
    }

    @Override
    public int compareTo(Departamento o) {
        return Integer.compare(this.codigo, o.codigo);
    }
}