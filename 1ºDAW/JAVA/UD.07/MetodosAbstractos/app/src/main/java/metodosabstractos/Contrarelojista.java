package metodosabstractos;

/**
 * Representa a un ciclista especialista en pruebas contra el reloj
 * (Contrarelojista).
 * Esta clase hereda de Ciclista y destaca por su capacidad de mantener una
 * velocidad máxima elevada en terrenos llanos.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Contrarelojista extends Ciclista {

    /** Velocidad máxima alcanzable por el ciclista en condiciones óptimas (km/h) */
    private double velocidadMaxima;

    /**
     * Constructor para inicializar un Contrarelojista con su identificación, nombre
     * y velocidad tope.
     * * @param identificacion ID única del ciclista.
     * 
     * @param nombre          Nombre completo del deportista.
     * @param velocidadMaxima Valor de la velocidad punta para esta especialidad.
     */
    Contrarelojista(int identificacion, String nombre, double velocidadMaxima) {
        super(identificacion, nombre); // Inicializa los atributos de la clase base Ciclista
        this.velocidadMaxima = velocidadMaxima;
    }

    /**
     * Implementación del método abstracto de la clase madre.
     * Define la identidad deportiva del objeto.
     * * @return Una cadena de texto con el valor "Contrarelojista".
     */
    @Override
    public String imprimirTipo() {
        return "Contrarelojista";
    }

    // --- Métodos de Acceso (Getters y Setters) ---

    /**
     * Obtiene la velocidad máxima registrada para el ciclista.
     * 
     * @return Velocidad máxima en km/h.
     */
    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    /**
     * Establece el límite de velocidad máxima del ciclista.
     * 
     * @param velocidadMaxima Nuevo valor de velocidad máxima.
     */
    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    /**
     * Imprime por consola el desglose completo de la información del
     * contrarelojista,
     * incluyendo los datos de identidad y el tiempo acumulado en competición.
     */
    public void imprimir() {
        System.out.println("Especialidad: " + imprimirTipo());
        System.out.println("Identificacion: " + getIdentificacion());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Velocidad Maxima: " + getVelocidadMaxima());
        System.out.println("Tiempo Acumulado: " + getTiempoAcomulado());
    }
}