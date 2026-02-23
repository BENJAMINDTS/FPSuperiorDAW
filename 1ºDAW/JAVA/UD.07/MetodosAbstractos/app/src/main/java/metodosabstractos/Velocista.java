package metodosabstractos;

/**
 * Representa a un ciclista con la especialidad de Velocista.
 * Esta clase hereda de Ciclista y añade atributos específicos para medir
 * el rendimiento en sprints, como la potencia y la velocidad promedio.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Velocista extends Ciclista {

    /** Potencia promedio generada por el ciclista expresada en vatios (W) */
    private double potenciaPromedio;

    /** Velocidad promedio mantenida por el ciclista en km/h */
    private double velocidadPromedio;

    /**
     * Constructor para inicializar un Velocista con sus datos básicos y métricas de
     * rendimiento.
     * * @param identificacion ID única del ciclista.
     * 
     * @param nombre            Nombre completo del deportista.
     * @param potenciaPromedio  Vatios promedio generados.
     * @param velocidadPromedio Velocidad media en carrera.
     */
    Velocista(int identificacion, String nombre, double potenciaPromedio, double velocidadPromedio) {
        super(identificacion, nombre); // Invocación al constructor de la clase base Ciclista
        this.potenciaPromedio = potenciaPromedio;
        this.velocidadPromedio = velocidadPromedio;
    }

    /**
     * Implementación del método abstracto de la clase Ciclista.
     * Indica la especialidad del ciclista.
     * * @return Una cadena de texto con el valor "Velocista".
     */
    @Override
    public String imprimirTipo() {
        return "Velocista";
    }

    // --- Métodos de Acceso (Getters y Setters) ---

    /** @return La potencia promedio del velocista. */
    public double getPotenciaPromedio() {
        return potenciaPromedio;
    }

    /** @param potenciaPromedio Establece la potencia promedio en vatios. */
    public void setPotenciaPromedio(double potenciaPromedio) {
        this.potenciaPromedio = potenciaPromedio;
    }

    /** @return La velocidad promedio del velocista en km/h. */
    public double getVelocidadPromedio() {
        return velocidadPromedio;
    }

    /** @param velocidadPromedio Establece la velocidad promedio en km/h. */
    public void setVelocidadPromedio(double velocidadPromedio) {
        this.velocidadPromedio = velocidadPromedio;
    }

    /**
     * Muestra por consola toda la información detallada del velocista,
     * incluyendo los datos heredados de la clase Ciclista y sus métricas propias.
     */
    public void imprimir() {
        System.out.println("Tipo: " + imprimirTipo());
        System.out.println("Identificacion: " + getIdentificacion());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Potencia Promedio: " + getPotenciaPromedio());
        System.out.println("Velocidad Promedio: " + getVelocidadPromedio());
        System.out.println("Tiempo Acumulado: " + getTiempoAcomulado());
    }
}