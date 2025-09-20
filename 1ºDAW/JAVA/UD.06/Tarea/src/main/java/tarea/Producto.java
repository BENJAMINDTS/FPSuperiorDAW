package tarea;

import java.io.Serializable;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Categoria {
        ELECTRONICA, ROPA, ALIMENTOS, HOGAR
    }

    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private String descripcion;
    private Categoria categoria;

    // Constructor
    public Producto(String codigo, String nombre, int cantidad, double precio, String descripcion, Categoria categoria) {
        if (!validarFormatoCodigo(codigo)) {
            throw new IllegalArgumentException("El código no cumple con el formato requerido (2 o 3 letras mayúsculas seguidas de un número).");
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

    // Método para validar el formato del código
    private boolean validarFormatoCodigo(String codigo) {
        // Expresión regular para validar el formato: 2 o 3 letras mayúsculas seguidas de un número entero
        String regex = "^[A-Z]{2,3}\\d+$";
        return codigo.matches(regex);
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (!validarFormatoCodigo(codigo)) {
            throw new IllegalArgumentException("El código no cumple con el formato requerido (2 o 3 letras mayúsculas seguidas de un número).");
        }
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula.");
        }
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}