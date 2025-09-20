package modeloBancario;

import java.util.ArrayList;
import java.util.List;

public class CCorriente extends Cuenta implements OperacionesBancarias {

    // Declaración de variables
    protected final double INTERESFIJO = 0.15;
    private List<Transaccion> historialTransacciones; // Historial de transacciones

    // Constructor con parámetros
    public CCorriente(double saldo, Cliente titular) {
        super(saldo, titular);
        this.historialTransacciones = new ArrayList<>(); // Inicializar el historial
    }

    // Implementación de los métodos abstractos de Cuenta
    @Override
    public void retirar(double monto) {
        if (monto <= 0) {

        }
        if (monto > getSaldo()) {

        }
        setSaldo(getSaldo() - monto);
        agregarTransaccion(monto, TipoTransaccion.RETIRO); // Registrar transacción
    }

    @Override
    public void actualizarSaldo() {
        double interes = getSaldo() * INTERESFIJO;
        setSaldo(getSaldo() + interes);
        agregarTransaccion(interes, TipoTransaccion.ACTUALIZACION); // Registrar transacción
    }

    // Implementación de los métodos abstractos de OperacionesBancarias
    @Override
    public void transferir(double monto, Cuenta cuentaDestino) {
        if (monto <= 0) {

        }
        if (monto > getSaldo()) {

        }
        setSaldo(getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        agregarTransaccion(monto, TipoTransaccion.TRANSFERENCIA); // Registrar transacción
    }

    @Override
    public void depositar(double monto) {
        if (monto <= 0) {

        }
        setSaldo(getSaldo() + monto);
        agregarTransaccion(monto, TipoTransaccion.DEPOSITO); // Registrar transacción
    }

    @Override
    public void consultarSaldo() {
        System.out.println("Saldo: " + getSaldo());
    }

    @Override
    public void cancelarTransaccion() {
        if (!historialTransacciones.isEmpty()) {
            Transaccion ultimaTransaccion = historialTransacciones.get(historialTransacciones.size() - 1);
            if (ultimaTransaccion.getTipo().equals(TipoTransaccion.DEPOSITO)) {
                setSaldo(getSaldo() - ultimaTransaccion.getMonto());
            } else if (ultimaTransaccion.getTipo().equals(TipoTransaccion.RETIRO)) {
                setSaldo(getSaldo() + ultimaTransaccion.getMonto());
            } else if (ultimaTransaccion.getTipo().equals(TipoTransaccion.TRANSFERENCIA)) {
                setSaldo(getSaldo() + ultimaTransaccion.getMonto());
            }
            historialTransacciones.remove(ultimaTransaccion);
            agregarTransaccion(ultimaTransaccion.getMonto(), TipoTransaccion.CANCELACION); // Registrar cancelación
        }
    }

    // Método para agregar una transacción al historial
    private void agregarTransaccion(double monto, TipoTransaccion tipo) {
        historialTransacciones.add(new Transaccion(monto, tipo));
    }

    // Método para obtener el historial de transacciones
    public List<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }
}
