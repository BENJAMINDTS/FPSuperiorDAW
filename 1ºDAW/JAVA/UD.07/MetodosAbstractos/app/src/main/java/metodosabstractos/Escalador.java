package metodosabstractos;

/**
 * Representa a un ciclista especialista en ascensos de montaña (Escalador).
 * Esta clase extiende de Ciclista e incluye atributos específicos para el
 * rendimiento en pendientes, como la aceleración en subida y el grado de
 * inclinación.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Escalador extends Ciclista {

    /** Aceleración promedio del ciclista en pendientes ascendentes (m/s²) */
    private double aceleracion;

    /**
     * Grado de inclinación o rampa máximo que el ciclista puede gestionar
     * eficientemente
     */
    private double gradoRampa;

    /**
     * Constructor para inicializar un Escalador con sus datos de identificación y
     * métricas de montaña.
     * * @param identificacion ID numérica única del ciclista.
     * 
     * @param nombre      Nombre completo del ciclista.
     * @param aceleracion Valor de aceleración en ascenso.
     * @param gradoRampa  Grado de inclinación de la rampa.
     */
    Escalador(int identificacion, String nombre, double aceleracion, double gradoRampa) {
        super(identificacion, nombre); // Llama al constructor de la clase abstracta Ciclista
        this.aceleracion = aceleracion;
        this.gradoRampa = gradoRampa;
    }

    /**
     * Implementación del método abstracto de Ciclista para definir el tipo de
     * especialidad.
     * * @return Una cadena con el texto "Escalador".
     */
    @Override
    public String imprimirTipo() {
        return "Escalador";
    }

    // --- Métodos de Acceso (Getters y Setters) ---

    /** @return La aceleración promedio en subida. */
    public double getAceleracion() {
        return aceleracion;
    }

    /** @param aceleracion Establece la aceleración promedio en subida. */
    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    /** @return El grado de inclinación de la rampa. */
    public double getGradoRampa() {
        return gradoRampa;
    }

    /** @param gradoRampa Establece el grado de inclinación de la rampa. */
    public void setGradoRampa(double gradoRampa) {
        this.gradoRampa = gradoRampa;
    }

    /**
     * Muestra por consola la información completa del ciclista escalador,
     * incluyendo los atributos heredados de la clase base.
     */
    public void imprimir() {
        System.out.println("Tipo: " + imprimirTipo());
        System.out.println("Identificacion: " + getIdentificacion());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Aceleracion: " + getAceleracion());
        System.out.println("Grado Rampa: " + getGradoRampa());
        System.out.println("Tiempo Acumulado: " + getTiempoAcomulado());
    }
}