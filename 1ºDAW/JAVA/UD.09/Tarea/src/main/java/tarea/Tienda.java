package tarea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa una sede corporativa o Tienda. Actúa como contenedor de la lista
 * de departamentos vinculados al comercio, implementando la interfaz Iterable
 * para facilitar recorridos seguros sobre ellos.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Tienda implements Iterable<Departamento> {

    /**
     * Denominación comercial o razón social de la tienda.
     */
    private String nombre;

    /**
     * Breve resumen de la naturaleza o actividad de la tienda.
     */
    private String descripcion;

    /**
     * Domicilio o lugar físico donde se encuentra la tienda.
     */
    private String direccion;

    /**
     * Colección privada que almacena los departamentos asignados a esta sede.
     */
    private List<Departamento> Departamentos;

    /**
     * Constructor principal de Tienda. Valida la integridad de la información
     * comercial entrante.
     *
     * * @param nombre El nombre comercial.
     * @param descripcion Descripción de actividad.
     * @param direccion Ubicación física.
     * @param Departamentos Lista inicial de departamentos a asignar.
     * @throws IllegalArgumentException Si algún argumento es nulo o si las
     * cadenas se encuentran vacías.
     */
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
        this.Departamentos = new ArrayList<>(Departamentos); // Uso de copia defensiva
    }

    /**
     * Vincula y añade un nuevo departamento a la colección interna de la
     * tienda.
     *
     * * @param Departamento El objeto de tipo Departamento a añadir.
     * @throws IllegalArgumentException Si el objeto pasado como parámetro es
     * nulo.
     */
    public void agregarDepartamento(Departamento Departamento) {
        if (Departamento == null) {
            throw new IllegalArgumentException("El Departamento no puede ser nulo.");
        }
        Departamentos.add(Departamento);
    }

    /**
     * Busca por código y suprime un departamento específico de la lista de la
     * tienda.
     *
     * * @param codigo El identificador numérico (en formato String) del
     * departamento a excluir.
     */
    public void eliminarDepartamento(String codigo) {
        Departamentos.removeIf(Departamento -> Departamento.getCodigo() == Integer.parseInt(codigo));
    }

    /**
     * Obtiene el nombre actual de la tienda.
     *
     * * @return El nombre comercial de la tienda.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sobrescribe el nombre de la tienda, previo chequeo de validez.
     *
     * * @param nombre Nueva cadena de texto para el nombre.
     * @throws IllegalArgumentException Si la cadena está vacía o es nula.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tienda no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción comercial de la tienda.
     *
     * * @return La descripción vigente.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sobrescribe la descripción operativa de la tienda, garantizando contenido
     * real.
     *
     * * @param descripcion Nueva descripción a asignar.
     * @throws IllegalArgumentException Si la descripción es nula o solo
     * contiene espacios.
     */
    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la tienda no puede estar vacía.");
        }
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el domicilio comercial de la entidad.
     *
     * * @return La dirección de la tienda.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece una nueva ubicación física para la tienda.
     *
     * * @param direccion Nueva dirección verificada.
     * @throws IllegalArgumentException Si el parámetro es nulo o en blanco.
     */
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección de la tienda no puede estar vacía.");
        }
        this.direccion = direccion;
    }

    /**
     * Expone una réplica de seguridad de la lista actual de departamentos,
     * evitando alteraciones directas en la memoria privada de la tienda.
     *
     * * @return Una nueva ArrayList conteniendo los departamentos.
     */
    public List<Departamento> getDepartamentos() {
        return new ArrayList<>(Departamentos); // Copia defensiva al retornar
    }

    /**
     * Habilita el comportamiento for-each para la instancia de Tienda,
     * proveyendo el iterador subyacente de la colección de departamentos.
     *
     * * @return Iterador de la colección tipo Departamento.
     */
    @Override
    public Iterator<Departamento> iterator() {
        return Departamentos.iterator();
    }

    /**
     * Serializa los datos principales de la tienda y su colección a un formato
     * legible por humanos.
     *
     * * @return Texto estructurado representando el estado de la Tienda.
     */
    @Override
    public String toString() {
        return "Tienda{"
                + "nombre='" + nombre + '\''
                + ", descripcion='" + descripcion + '\''
                + ", direccion='" + direccion + '\''
                + ", Departamentos=" + Departamentos
                + '}';
    }
}
