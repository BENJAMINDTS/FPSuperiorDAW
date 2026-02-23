package modeloBancario;

/**
 * Enumeración que define los tipos de movimientos financieros permitidos.
 * Garantiza la integridad de los datos al restringir las categorías de
 * transacciones a un conjunto predefinido de valores constantes.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public enum TipoTransaccion {
    /** Representa la entrada de fondos a una cuenta */
    DEPOSITO,

    /** Representa la salida de fondos por parte del titular */
    RETIRO,

    /** Representa el envío de fondos entre diferentes cuentas */
    TRANSFERENCIA,

    /** Indica una modificación en los datos o estado de la cuenta */
    ACTUALIZACION,

    /** Representa la anulación de una operación o el cierre de una cuenta */
    CANCELACION;
}