package modeloBancario.exepciones;

/**
 * Excepción personalizada para validar la integridad de los valores monetarios.
 * Se lanza cuando se intenta procesar una operación con un monto que no cumple
 * los requisitos básicos (por ejemplo, montos negativos o iguales a cero).
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class MontoInvalidoException extends Exception {

    /**
     * Construye una nueva excepción con un mensaje que describe la invalidez del
     * monto.
     * * @param mensaje Explicación detallada del error (ej. "El monto debe ser
     * mayor a cero").
     */
    public MontoInvalidoException(String mensaje) {
        super(mensaje);
    }

}