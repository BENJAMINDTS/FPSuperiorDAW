package modeloBancario;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una cuenta bancaria de ahorros con intereses y restricciones de
 * saldo.
 * Esta clase especializa a la superclase Cuenta e implementa la interfaz
 * OperacionesBancarias,
 * gestionando su propio historial de movimientos y reglas de capitalización.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class CuentaAhorro extends Cuenta implements OperacionesBancarias {

    /** Tasa de interés aplicada al saldo para la actualización de fondos */
    protected double interesVariable;

    /** Saldo mínimo requerido para permitir operaciones de débito o retiro */
    protected double saldoMinimo;

    /** Colección dinámica que almacena los registros de movimientos de la cuenta */
    private List<Transaccion> historialTransacciones;

    /**
     * Constructor con parámetros para inicializar una cuenta con fondos y titular.
     * * @param saldo Cantidad inicial de dinero.
     * 
     * @param titular         Objeto Cliente dueño de la cuenta.
     * @param interesVariable Porcentaje de interés (ej: 0.05 para 5%).
     * @param saldoMinimo     Límite inferior permitido para operar.
     */
    public CuentaAhorro(double saldo, Cliente titular, double interesVariable, double saldoMinimo) {
        super(saldo, titular);
        this.interesVariable = interesVariable;
        this.saldoMinimo = saldoMinimo;
        this.historialTransacciones = new ArrayList<>();
    }

    /**
     * Constructor por defecto. Inicializa valores en cero y crea una lista de
     * historial vacía.
     */
    public CuentaAhorro() {
        super();
        this.interesVariable = 0;
        this.saldoMinimo = 0;
        this.historialTransacciones = new ArrayList<>();
    }

    // --- Implementación de métodos abstractos de la clase Cuenta ---

    /**
     * Ejecuta un retiro de fondos validando disponibilidad y saldo mínimo.
     * 
     * @param monto Cantidad a retirar.
     */
    @Override
    public void retirar(double monto) {
        if (monto > 0 && monto <= getSaldo() && getSaldo() >= saldoMinimo) {
            setSaldo(getSaldo() - monto);
            historialTransacciones.add(new Transaccion(monto, TipoTransaccion.RETIRO));
        }
    }

    /**
     * Calcula e inyecta los intereses al saldo actual de la cuenta.
     */
    @Override
    public void actualizarSaldo() {
        double interes = getSaldo() * interesVariable;
        setSaldo(getSaldo() + interes);
        historialTransacciones.add(new Transaccion(interes, TipoTransaccion.ACTUALIZACION));
    }

    // --- Implementación de métodos de la interfaz OperacionesBancarias ---

    /**
     * Transfiere fondos a otra cuenta, registrando el movimiento como
     * transferencia.
     * 
     * @param monto         Valor a transferir.
     * @param cuentaDestino Cuenta receptora de los fondos.
     */
    @Override
    public void transferir(double monto, Cuenta cuentaDestino) {
        if (monto > 0 && monto <= getSaldo() && getSaldo() >= saldoMinimo) {
            setSaldo(getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
            historialTransacciones.add(new Transaccion(monto, TipoTransaccion.TRANSFERENCIA));
        }
    }

    /**
     * Realiza un abono de capital a la cuenta.
     * 
     * @param monto Valor a depositar.
     */
    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            setSaldo(getSaldo() + monto);
            historialTransacciones.add(new Transaccion(monto, TipoTransaccion.DEPOSITO));
        }
    }

    /**
     * Muestra por consola el estado actual de los fondos.
     */
    @Override
    public void consultarSaldo() {
        System.out.println("Saldo actual de la cuenta: " + getSaldo());
    }

    /**
     * Revierte la última operación realizada, ajustando el saldo según el tipo
     * de transacción y registrando la anulación en el historial.
     */
    @Override
    public void cancelarTransaccion() {
        if (!historialTransacciones.isEmpty()) {
            Transaccion ultimaTransaccion = historialTransacciones.get(historialTransacciones.size() - 1);

            // Lógica de reversión según tipo de movimiento
            switch (ultimaTransaccion.getTipo()) {
                case DEPOSITO -> setSaldo(getSaldo() - ultimaTransaccion.getMonto());
                case RETIRO -> setSaldo(getSaldo() + ultimaTransaccion.getMonto());
                case TRANSFERENCIA -> setSaldo(getSaldo() + ultimaTransaccion.getMonto());
                default -> {
                }
            }

            // Eliminamos la transacción original y registramos la cancelación
            historialTransacciones.remove(ultimaTransaccion);
            historialTransacciones.add(new Transaccion(ultimaTransaccion.getMonto(), TipoTransaccion.CANCELACION));
        }
    }

    /**
     * Recupera el historial completo de movimientos de la cuenta.
     * 
     * @return List con todos los objetos Transaccion registrados.
     */
    public List<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }
}