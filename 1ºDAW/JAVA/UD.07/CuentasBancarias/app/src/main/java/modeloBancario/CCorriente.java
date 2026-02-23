package modeloBancario;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una Cuenta Corriente bancaria.
 * A diferencia de la cuenta de ahorro, esta aplica un interés fijo y
 * permite una gestión de transacciones sin restricciones de saldo mínimo,
 * implementando las operaciones bancarias estándar.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class CCorriente extends Cuenta implements OperacionesBancarias {

    /** Tasa de interés fija aplicada a la cuenta corriente (15%) */
    protected final double INTERESFIJO = 0.15;

    /** Lista dinámica que almacena el registro histórico de movimientos */
    private List<Transaccion> historialTransacciones;

    /**
     * Constructor para inicializar una cuenta corriente con saldo y titular.
     * * @param saldo Monto inicial de la cuenta.
     * 
     * @param titular Objeto Cliente que posee la cuenta.
     */
    public CCorriente(double saldo, Cliente titular) {
        super(saldo, titular);
        this.historialTransacciones = new ArrayList<>();
    }

    // --- Implementación de métodos abstractos de Cuenta ---

    /**
     * Realiza un retiro de efectivo de la cuenta.
     * 
     * @param monto Cantidad a retirar.
     */
    @Override
    public void retirar(double monto) {
        if (monto > 0 && monto <= getSaldo()) {
            setSaldo(getSaldo() - monto);
            agregarTransaccion(monto, TipoTransaccion.RETIRO);
        }
    }

    /**
     * Aplica el interés fijo al saldo actual de la cuenta corriente.
     */
    @Override
    public void actualizarSaldo() {
        double interes = getSaldo() * INTERESFIJO;
        setSaldo(getSaldo() + interes);
        agregarTransaccion(interes, TipoTransaccion.ACTUALIZACION);
    }

    // --- Implementación de métodos de OperacionesBancarias ---

    /**
     * Transfiere un monto determinado a otra cuenta de destino.
     * 
     * @param monto         Valor a transferir.
     * @param cuentaDestino Cuenta que recibirá el dinero.
     */
    @Override
    public void transferir(double monto, Cuenta cuentaDestino) {
        if (monto > 0 && monto <= getSaldo()) {
            setSaldo(getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
            agregarTransaccion(monto, TipoTransaccion.TRANSFERENCIA);
        }
    }

    /**
     * Realiza un depósito de fondos en la cuenta.
     * 
     * @param monto Cantidad a ingresar.
     */
    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            setSaldo(getSaldo() + monto);
            agregarTransaccion(monto, TipoTransaccion.DEPOSITO);
        }
    }

    /**
     * Imprime el saldo actual disponible en la consola.
     */
    @Override
    public void consultarSaldo() {
        System.out.println("Saldo actual (Cuenta Corriente): " + getSaldo());
    }

    /**
     * Revierte la última transacción realizada en la cuenta.
     * Ajusta el saldo en función del tipo de operación (Débito/Crédito)
     * y registra la cancelación en el historial.
     */
    @Override
    public void cancelarTransaccion() {
        if (!historialTransacciones.isEmpty()) {
            Transaccion ultimaTransaccion = historialTransacciones.get(historialTransacciones.size() - 1);

            // Reversión lógica del saldo
            switch (ultimaTransaccion.getTipo()) {
                case DEPOSITO -> setSaldo(getSaldo() - ultimaTransaccion.getMonto());
                case RETIRO -> setSaldo(getSaldo() + ultimaTransaccion.getMonto());
                case TRANSFERENCIA -> setSaldo(getSaldo() + ultimaTransaccion.getMonto());
                default -> {
                }
            }

            historialTransacciones.remove(ultimaTransaccion);
            agregarTransaccion(ultimaTransaccion.getMonto(), TipoTransaccion.CANCELACION);
        }
    }

    /**
     * Método auxiliar privado para encapsular la creación y adición de
     * transacciones.
     * 
     * @param monto Valor del movimiento.
     * @param tipo  Categoría del movimiento (RETIRO, DEPOSITO, etc.).
     */
    private void agregarTransaccion(double monto, TipoTransaccion tipo) {
        historialTransacciones.add(new Transaccion(monto, tipo));
    }

    /**
     * Obtiene la lista completa de transacciones de la cuenta.
     * 
     * @return List con los objetos Transaccion registrados.
     */
    public List<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }
}