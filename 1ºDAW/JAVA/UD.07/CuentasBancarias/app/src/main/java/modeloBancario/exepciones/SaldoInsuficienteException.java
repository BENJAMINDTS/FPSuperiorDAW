package modeloBancario.exepciones;

/**
 * Excepción personalizada para gestionar errores de fondos en el sistema.
 * Se lanza cuando un cliente intenta realizar una operación de débito
 * (retiro o transferencia) que supera el saldo disponible o infringe
 * las reglas de saldo mínimo.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class SaldoInsuficienteException extends Exception {

    /**
     * Construye una nueva excepción con un mensaje detallado sobre el error.
     * * @param mensaje Descripción específica del motivo del fallo (ej. "Fondos
     * insuficientes").
     */
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}