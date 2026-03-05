package tarea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa una sede física o lógica de un comercio, actuando como contenedor
 * de productos. Implementa la interfaz Iterable para facilitar el recorrido en
 * bucles (foreach).
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Tienda implements Iterable<Producto> {

    /**
     * Denominación comercial de la tienda
     */
    private String nombre;
    /**
     * Reseña o descripción del enfoque comercial
     */
    private String descripcion;
    /**
     * Ubicación física de la sucursal
     */
    private String direccion;
    /**
     * Inventario interno de productos
     */
    private List<Producto> productos;

    /**
     * Constructor para instanciar una tienda con todas sus propiedades
     * estructurales y un inventario inicial.
     *
     * * @param nombre Nombre comercial.
     * @param descripcion Detalles de la tienda.
     * @param direccion Ubicación.
     * @param productos Lista inicial de artículos para cargar en el sistema.
     * @throws IllegalArgumentException Si algún argumento vital es nulo o está
     * vacío.
     */
    public Tienda(String nombre, String descripcion, String direccion, List<Producto> productos) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre vacío.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("Descripción vacía.");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("Dirección vacía.");
        }
        if (productos == null) {
            throw new IllegalArgumentException("La lista de productos es nula.");
        }

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.productos = new ArrayList<>(productos); // Uso de copia defensiva para proteger el estado interno
    }

    /**
     * Incorpora un nuevo artículo al catálogo de esta sucursal específica.
     *
     * * @param producto El objeto a registrar en la tienda.
     */
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        productos.add(producto);
    }

    /**
     * Remueve un producto del inventario mediante la verificación de su código
     * identificador. Utiliza expresiones lambda para el borrado funcional.
     *
     * * @param codigo ID del producto que se desea dar de baja.
     */
    public void eliminarProducto(String codigo) {
        productos.removeIf(producto -> producto.getCodigo().equals(codigo));
    }

    // --- Métodos Getters y Setters con validaciones ---
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre vacío.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("Descripción vacía.");
        }
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("Dirección vacía.");
        }
        this.direccion = direccion;
    }

    /**
     * Recupera el catálogo de artículos.
     *
     * * @return Una nueva lista (Copia defensiva) para evitar la alteración
     * externa de la colección privada.
     */
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    /**
     * Implementación obligatoria de la interfaz Iterable.
     *
     * * @return Iterador nativo de la lista de productos de Java.
     */
    @Override
    public Iterator<Producto> iterator() {
        return productos.iterator();
    }

    @Override
    public String toString() {
        return "Tienda{nombre='" + nombre + "', descripcion='" + descripcion
                + "', direccion='" + direccion + "', productos=" + productos + '}';
    }
}
