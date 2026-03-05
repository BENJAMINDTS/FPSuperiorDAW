package modeloBancario;

/**
 * Define el contrato de servicios financieros esenciales para el sistema.
 * Esta interfaz establece las operaciones obligatorias que cualquier tipo
 * de cuenta (Ahorros, Corriente, etc.) debe ser capaz de ejecutar.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public interface OperacionesBancarias {

    /**
     * Realiza el traslado de fondos desde la cuenta actual hacia una cuenta de
     * destino.
     * * @param monto Cantidad de dinero a enviar.
     * 
     * @param cuentaDestino Instancia de la cuenta que recibirá los fondos.
     */
    public void transferir(double monto, Cuenta cuentaDestino);

    /**
     * Incrementa el saldo disponible de la cuenta mediante un ingreso de capital.
     * * @param monto Cantidad de dinero a ingresar.
     */
    public void depositar(double monto);

    /**
     * Recupera y visualiza el estado actual de los fondos en la cuenta.
     */
    public void consultarSaldo();

    /**
     * Revierte o anula la última operación realizada, siempre que las
     * reglas de negocio lo permitan.
     */
    public void cancelarTransaccion();
}