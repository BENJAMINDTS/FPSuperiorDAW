package tarea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tienda implements Iterable<Departamento> { 
    private String nombre;
    private String descripcion;
    private String direccion;
    private List<Departamento> Departamentos;

    public Tienda(String nombre, String descripcion, String direccion, List<Departamento> Departamentos) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tienda no puede estar vacío.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la tienda no puede estar vacía.");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección de la tienda no puede estar vacía.");
        }
        if (Departamentos == null) {
            throw new IllegalArgumentException("La lista de Departamentos no puede ser nula.");
        }

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.Departamentos = new ArrayList<>(Departamentos); // Copia defensiva
    }

    // Método para agregar un Departamento
    public void agregarDepartamento(Departamento Departamento) {
        if (Departamento == null) {
            throw new IllegalArgumentException("El Departamento no puede ser nulo.");
        }
        Departamentos.add(Departamento);
    }

    // Método para eliminar un Departamento por código
    public void eliminarDepartamento(String codigo) {
        Departamentos.removeIf(Departamento -> Departamento.getCodigo() == Integer.parseInt(codigo));
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tienda no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la tienda no puede estar vacía.");
        }
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección de la tienda no puede estar vacía.");
        }
        this.direccion = direccion;
    }

    public List<Departamento> getDepartamentos() {
        return new ArrayList<>(Departamentos); // Copia defensiva
    }

    @Override
    public Iterator<Departamento> iterator() {
        return Departamentos.iterator();
    }

    @Override
    public String toString() {
        return "Tienda{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", Departamentos=" + Departamentos +
                '}';
    }
}