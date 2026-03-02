package tarea;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Entidad que representa un artículo comercial dentro del inventario. Contiene
 * reglas de negocio y validación de atributos. Mapeada para JPA. Implementa
 * Comparable para ordenar las listas primariamente por Categoría y
 * secundariamente por Nombre.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
@Entity
public class Producto implements Serializable, Comparable<Producto> {

    private static final long serialVersionUID = 1L;

    /**
     * Tipos de clasificación de inventario soportados
     */
    public enum Categoria {
        ELECTRONICA, ROPA, ALIMENTOS, HOGAR
    }

    @Id
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private String descripcion;
    private Categoria categoria;

    /**
     * Constructor por defecto requerido por el estándar JPA. Inicializa los
     * atributos con valores neutros y una categoría por defecto.
     */
    public Producto() {
        this.codigo = "";
        this.nombre = "";
        this.cantidad = 0;
        this.precio = 0.0;
        this.descripcion = "";
        this.categoria = Categoria.ELECTRONICA;
    }

    /**
     * Constructor que inicializa el producto validando integralmente cada
     * atributo de negocio.
     *
     * * @param codigo Identificador de formato estricto (2 o 3 letras
     * mayúsculas seguidas de número).
     * @param nombre Nombre comercial del producto.
     * @param cantidad Stock disponible en inventario.
     * @param precio Valor unitario del producto.
     * @param descripcion Detalles adicionales sobre el artículo.
     * @param categoria Sector al que pertenece el producto.
     * @throws IllegalArgumentException Si algún parámetro incumple las reglas
     * de integridad de datos.
     */
    public Producto(String codigo, String nombre, int cantidad, double precio, String descripcion, Categoria categoria) {
        if (!validarFormatoCodigo(codigo)) {
            throw new IllegalArgumentException("Formato de código inválido.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    /**
     * Evalúa si un código cumple con el estándar de nomenclatura del
     * inventario.
     *
     * * @param codigo La cadena a validar.
     * @return true si el formato es correcto, false en caso contrario.
     */
    private boolean validarFormatoCodigo(String codigo) {
        return codigo != null && codigo.matches("^[A-Z]{2,3}\\d+$");
    }

    // --- Métodos de Acceso (Getters y Setters) con Validaciones ---
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (!validarFormatoCodigo(codigo)) {
            throw new IllegalArgumentException("Formato de código inválido.");
        }
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre vacío.");
        }
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("Cantidad negativa.");
        }
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("Precio negativo.");
        }
        this.precio = precio;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoría nula.");
        }
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{codigo='" + codigo + "', nombre='" + nombre + "', cantidad=" + cantidad
                + ", precio=" + precio + ", descripcion='" + descripcion + "', categoria=" + categoria + '}';
    }

    /**
     * Lógica de comparación para el ordenamiento de listas de Productos. Ordena
     * primariamente por la enumeración de Categoría, y en caso de empate,
     * alfabéticamente por nombre (o por código).
     *
     * * @param o El producto a comparar contra la instancia actual.
     * @return Un entero negativo, cero, o positivo según la jerarquía de orden
     * natural.
     */
    @Override
    public int compareTo(Producto o) {
        if (categoria.compareTo(o.categoria) == 0) {
            return nombre.compareTo(o.nombre) == 0 ? codigo.compareTo(o.codigo) : nombre.compareTo(o.nombre);
        } else {
            return categoria.compareTo(o.categoria);
        }
    }
}
