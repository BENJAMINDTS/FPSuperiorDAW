package tarea;

import java.io.Serializable;

/**
 * Representa un artículo comercial con validación de código por formato.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
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

    /**
     * Constructor con validación de formato RegEx para el código.
     * * @param codigo Formato: 2-3 letras mayúsculas + número entero (Ej: ABC101).
     */
    public Producto(String codigo, String nombre, int cantidad, double precio, String descripcion,
            Categoria categoria) {
        if (!validarFormatoCodigo(codigo)) {
            throw new IllegalArgumentException("Formato de código inválido (RegEx: ^[A-Z]{2,3}\\d+$).");
        }
        // ... resto de validaciones de negocio ...
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    /**
     * Valida el código mediante una expresión regular.
     * * @param codigo Cadena a validar.
     * 
     * @return boolean True si cumple el patrón.
     */
    private boolean validarFormatoCodigo(String codigo) {
        String regex = "^[A-Z]{2,3}\\d+$";
        return codigo != null && codigo.matches(regex);
    }

    // Getters y Setters con validaciones internas
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (!validarFormatoCodigo(codigo))
            throw new IllegalArgumentException("Código inválido.");
        this.codigo = codigo;
    }
}