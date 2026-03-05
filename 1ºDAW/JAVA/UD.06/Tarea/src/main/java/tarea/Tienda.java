package tarea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un establecimiento comercial.
 * Gestiona una colección de productos y permite su iteración.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Tienda implements Iterable<Producto> {
    private String nombre;
    private String descripcion;
    private String direccion;
    private List<Producto> productos;

    /**
     * Constructor con validaciones de integridad de datos.
     * * @param nombre Nombre de la tienda (no nulo/vacío).
     * 
     * @param descripcion Descripción del negocio.
     * @param direccion   Ubicación física.
     * @param productos   Lista inicial de existencias.
     */
    public Tienda(String nombre, String descripcion, String direccion, List<Producto> productos) {
        validarString(nombre, "nombre");
        validarString(descripcion, "descripción");
        validarString(direccion, "dirección");
        if (productos == null)
            throw new IllegalArgumentException("La lista no puede ser nula.");

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.productos = new ArrayList<>(productos); // Copia defensiva
    }

    private void validarString(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El " + campo + " no puede estar vacío.");
        }
    }

    /**
     * Añade un producto a la lista de existencias.
     * * @param producto Objeto Producto validado.
     */
    public void agregarProducto(Producto producto) {
        if (producto == null)
            throw new IllegalArgumentException("Producto nulo.");
        productos.add(producto);
    }

    /**
     * Elimina productos cuyo código coincida con el parámetro.
     * * @param codigo Código alfanumérico del producto.
     */
    public void eliminarProducto(String codigo) {
        productos.removeIf(p -> p.getCodigo().equals(codigo));
    }

    @Override
    public Iterator<Producto> iterator() {
        return productos.iterator();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @return Descripción del negocio.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @return Dirección física.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * 
     * @return Lista de productos.
     */
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }
}