package tarea;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Entidad que representa un área organizativa o Departamento. Mapea la
 * estructura de la base de datos y contiene reglas de negocio para la
 * validación de sus atributos.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
@Entity
public class Departamento implements Serializable, Comparable<Departamento> {

    /**
     * Identificador de versión para la serialización de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Código numérico único que identifica al departamento (Clave Primaria).
     */
    @Id
    private int codigo;

    /**
     * Nombre descriptivo del departamento.
     */
    private String nombre;

    /**
     * Identificador numérico de la ubicación física del departamento.
     */
    private int idLocalizacion;

    /**
     * Identificador numérico del gerente a cargo del departamento.
     */
    private int idManager;

    /**
     * Constructor por defecto. Inicializa los atributos con valores neutros
     * (ceros y cadenas vacías).
     */
    public Departamento() {
        this.codigo = 0;
        this.nombre = "";
        this.idLocalizacion = 0;
        this.idManager = 0;
    }

    /**
     * Constructor con parámetros. Inicializa el departamento validando las
     * reglas de negocio.
     *
     * * @param codigo Código numérico único.
     * @param nombre Nombre del departamento (no vacío, máx. 50 caracteres).
     * @param idLocalizacion ID de la ubicación.
     * @param idManager ID del gerente.
     * @throws IllegalArgumentException Si el nombre es nulo, vacío o supera el
     * límite de longitud.
     */
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

    /**
     * Obtiene el código único del departamento.
     *
     * * @return El código numérico del departamento.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece un nuevo código para el departamento.
     *
     * * @param codigo El nuevo valor numérico del código.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre descriptivo del departamento.
     *
     * * @return El nombre del departamento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para el departamento, validando su longitud y
     * contenido.
     *
     * * @param nombre El nuevo nombre a asignar.
     * @throws IllegalArgumentException Si el nombre es nulo, está vacío o
     * excede los 50 caracteres.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder los 50 caracteres.");
        }
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador de la localización geográfica del departamento.
     *
     * * @return El ID de la localización.
     */
    public int getIdLocalizacion() {
        return idLocalizacion;
    }

    /**
     * Establece un nuevo identificador de localización, asegurando que sea un
     * valor positivo.
     *
     * * @param idLocalizacion El nuevo ID de localización a asignar.
     * @throws IllegalArgumentException Si el ID proporcionado es menor o igual
     * a cero.
     */
    public void setIdLocalizacion(int idLocalizacion) {
        if (idLocalizacion <= 0) {
            throw new IllegalArgumentException("El idLocalizacion debe ser un número positivo.");
        }
        this.idLocalizacion = idLocalizacion;
    }

    /**
     * Obtiene el identificador del gerente a cargo del departamento.
     *
     * * @return El ID del manager responsable.
     */
    public int getIdManager() {
        return idManager;
    }

    /**
     * Establece un nuevo identificador de gerente, asegurando que sea un valor
     * positivo.
     *
     * * @param idManager El nuevo ID del manager a asignar.
     * @throws IllegalArgumentException Si el ID proporcionado es menor o igual
     * a cero.
     */
    public void setIdManager(int idManager) {
        if (idManager <= 0) {
            throw new IllegalArgumentException("El idManager debe ser un número positivo.");
        }
        this.idManager = idManager;
    }

    /**
     * Genera una representación textual completa del estado del objeto
     * Departamento.
     *
     * * @return Una cadena de texto con los valores de todos los atributos.
     */
    @Override
    public String toString() {
        return "Departamento{"
                + "codigo=" + codigo
                + ", nombre='" + nombre + '\''
                + ", idLocalizacion=" + idLocalizacion
                + ", idManager=" + idManager
                + '}';
    }

    /**
     * Define el criterio de ordenamiento natural comparando los departamentos
     * según su código numérico.
     *
     * * @param o El objeto Departamento a comparar contra la instancia actual.
     * @return Un entero negativo, cero, o positivo si este código es menor,
     * igual o mayor al proporcionado.
     */
    @Override
    public int compareTo(Departamento o) {
        return Integer.compare(this.codigo, o.codigo);
    }
}
