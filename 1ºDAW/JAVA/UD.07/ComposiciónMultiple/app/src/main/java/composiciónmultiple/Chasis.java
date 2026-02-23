package composiciónmultiple;

/**
 * Clase que representa el chasis de un vehículo, con un tipo específico.
 * Esta clase forma parte de la composición múltiple en la que un vehículo puede
 * tener varios componentes, como motor, ruedas y chasis, cada uno con sus
 * propias características y funcionalidades.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Chasis {

    private TipoChasis tipo;

    /**
     * Constructor que inicializa el tipo de chasis.
     * @param tipo Tipo de chasis a asignar.
     */
    public Chasis(TipoChasis tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el tipo de chasis.
     * @return Tipo de chasis.
     */
    public TipoChasis getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de chasis.
     * @param tipo Tipo de chasis a asignar.
     */
    public void setTipo(TipoChasis tipo) {
        this.tipo = tipo;
    }

}

/**
 * Enum que define los tipos de chasis disponibles para un vehículo.
 * Puede ser extendido en el futuro para incluir más tipos si es necesario.
 */
enum TipoChasis {
    Independiente, Monocasco
}
