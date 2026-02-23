package composiciónmultiple;

/**
 * Clase que representa el motor de un vehículo, con un volumen específico.
 * Esta clase forma parte de la composición múltiple en la que un vehículo puede
 * tener varios componentes, como motor, ruedas y chasis, cada uno con sus
 * propias
 * características y funcionalidades.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Motor {
    private int volumen;

    /**
     * Constructor que inicializa el volumen del motor.
     * 
     * @param volumen El volumen del motor en centímetros cúbicos (cc).
     */
    public Motor(int volumen) {
        this.volumen = volumen;
    }

    /**
     * Obtiene el volumen del motor.
     * 
     * @return El volumen del motor en centímetros cúbicos (cc).
     */
    public int getVolumen() {
        return volumen;
    }

    /**
     * Establece el volumen del motor.
     * 
     * @param volumen El volumen del motor a asignar en centímetros cúbicos (cc).
     */
    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

}
