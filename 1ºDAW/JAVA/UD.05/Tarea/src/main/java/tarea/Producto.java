package tarea;

import java.io.Serializable;

/**
 * Modelo de datos que representa un Producto en el inventario.
 * Implementa Serializable para permitir el almacenamiento de objetos en
 * archivos.
 * * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Producto implements Serializable {
  /** Identificador de versión para la serialización */
  private static final long serialVersionUID = 1L;

  private int codigo;
  private String nombre;
  private int cantidad;
  private double precio;
  private String descripcion;

  /**
   * Constructor completo de la clase Producto.
   * * * @param codigo ID único del producto.
   * * @param nombre Nombre comercial.
   * * @param cantidad Stock disponible.
   * * @param precio Coste unitario.
   * * @param descripcion Detalles adicionales.
   */
  public Producto(int codigo, String nombre, int cantidad, double precio, String descripcion) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.cantidad = cantidad;
    this.precio = precio;
    this.descripcion = descripcion;
  }

  // --- Getters y Setters con propósitos de documentación ---

  /**
   * 
   * @return int El código único del producto.
   */
  public int getCodigo() {
    return codigo;
  }

  /**
   * 
   * @return String El nombre comercial del producto.
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * 
   * @return int La cantidad disponible del producto.
   */
  public int getCantidad() {
    return cantidad;
  }

  /**
   * 
   * @return double El precio unitario del producto.
   */
  public double getPrecio() {
    return precio;
  }

  /**
   * 
   * @return String La descripción detallada del producto.
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * 
   * @param codigo Nuevo código del producto.
   */
  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  /**
   * 
   * @param nombre Nuevo nombre comercial.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * 
   * @param cantidad Nueva cantidad disponible.
   */
  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  /**
   * 
   * @param precio Nuevo precio unitario.
   */
  public void setPrecio(double precio) {
    this.precio = precio;
  }

  /**
   * 
   * @param descripcion Nueva descripción detallada.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}