package composiciónmultiple;

/**
 * Clase que representa la carrocería de un vehículo, con un color y un tipo
 * específico.
 * Esta clase forma parte de la composición múltiple en la que un vehículo puede
 * tener
 * varios componentes, como motor, ruedas y chasis, cada uno con sus propias
 * características
 * y funcionalidades.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Carroceria {
    private String color;

    private TipoCarroceria tipo;

    /**
     * Constructor que inicializa el color y el tipo de carrocería.
     * 
     * @param color El color de la carrocería del vehículo.
     * @param tipo  El tipo de carrocería, definido por el enum TipoCarroceria.
     */
    public Carroceria(String color, TipoCarroceria tipo) {
        this.color = color;
        this.tipo = tipo;
    }

    /**
     * Obtiene el color de la carrocería.
     * 
     * @return El color de la carrocería.
     */
    public String getColor() {
        return color;
    }

    /**
     * Obtiene el tipo de carrocería.
     * 
     * @return El tipo de carrocería.
     */
    public TipoCarroceria getTipo() {
        return tipo;
    }

    /**
     * Establece el color de la carrocería.
     * 
     * @param color El color a asignar a la carrocería.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Establece el tipo de carrocería.
     * 
     * @param tipo El tipo de carrocería a asignar.
     */
    public void setTipo(TipoCarroceria tipo) {
        this.tipo = tipo;
    }

}

/**
 * Enum que define los tipos de carrocería disponibles para un vehículo.
 * Puede ser extendido en el futuro para incluir más tipos si es necesario.
 */
enum TipoCarroceria {
    Independiente, Autoportante, Tubular
}