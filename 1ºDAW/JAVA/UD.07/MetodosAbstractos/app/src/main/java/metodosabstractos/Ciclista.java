package metodosabstractos;

/**
 * Clase abstracta que representa a un ciclista genérico.
 * Define las propiedades básicas y los métodos de acceso para cualquier tipo de
 * competidor.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public abstract class Ciclista {
    /** Identificador único del ciclista */
    private int identificacion;
    /** Nombre completo del ciclista */
    private String nombre;
    /** Tiempo total acumulado en la competición */
    private int tiempoAcomulado;

    /**
     * Constructor para inicializar un ciclista con su identificación y nombre.
     * 
     * @param identificacion ID numérica del ciclista.
     * @param nombre         Nombre del deportista.
     */
    public Ciclista(int identificacion, String nombre) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.tiempoAcomulado = 0;
    }

    /**
     * Método abstracto que debe ser implementado por las subclases para retornar su
     * especialidad.
     * 
     * @return String con el tipo de ciclista.
     */
    public abstract String imprimirTipo();

    // Métodos Getter y Setter protegidos para encapsulamiento y herencia
    /**
     * Obtiene el tiempo acumulado del ciclista.
     * 
     * @return Tiempo acumulado en segundos.
     */
    protected int getTiempoAcomulado() {
        return tiempoAcomulado;
    }

    /**
     * Establece el tiempo acumulado del ciclista.
     * 
     * @param tiempoAcomulado El nuevo tiempo acumulado en segundos.
     */
    protected void setTiempoAcomulado(int tiempoAcomulado) {
        this.tiempoAcomulado = tiempoAcomulado;
    }

    /**
     * Obtiene la identificación del ciclista.
     * 
     * @return Identificación numérica del ciclista.
     */
    protected int getIdentificacion() {
        return identificacion;
    }

    /**
     * Establece la identificación del ciclista.
     * 
     * @param identificacion Nueva identificación numérica del ciclista.
     */
    protected void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Obtiene el nombre del ciclista.
     * 
     * @return Nombre completo del ciclista.
     */
    protected String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ciclista.
     * 
     * @param nombre Nuevo nombre completo del ciclista.
     */
    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }
}