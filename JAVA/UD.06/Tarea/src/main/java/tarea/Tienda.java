package tarea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tienda implements Iterable<Producto> { 
    private String nombre;
    private String descripcion;
    private String direccion;
    private List<Producto> productos;

    public Tienda(String nombre, String descripcion, String direccion, List<Producto> productos) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tienda no puede estar vacío.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la tienda no puede estar vacía.");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección de la tienda no puede estar vacía.");
        }
        if (productos == null) {
            throw new IllegalArgumentException("La lista de productos no puede ser nula.");
        }

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.productos = new ArrayList<>(productos); // Copia defensiva
    }

    // Método para agregar un producto
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        productos.add(producto);
    }

    // Método para eliminar un producto por código
    public void eliminarProducto(String codigo) {
        productos.removeIf(producto -> producto.getCodigo().equals(codigo));
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

    public List<Producto> getProductos() {
        return new ArrayList<>(productos); // Copia defensiva
    }

    @Override
    public Iterator<Producto> iterator() {
        return productos.iterator();
    }

    @Override
    public String toString() {
        return "Tienda{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", productos=" + productos +
                '}';
    }
}