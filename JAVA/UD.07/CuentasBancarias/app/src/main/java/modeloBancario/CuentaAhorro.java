package modeloBancario;

import java.util.ArrayList;
import java.util.List;

public class CuentaAhorro extends Cuenta implements OperacionesBancarias {
    // Declaración de variables
    protected double interesVariable;
    protected double saldoMinimo;
    private List<Transaccion> historialTransacciones; // Historial de transacciones

    // Constructor con parámetros
    public CuentaAhorro(double saldo, Cliente titular, double interesVariable, double saldoMinimo) {
        super(saldo, titular);
        this.interesVariable = interesVariable;
        this.saldoMinimo = saldoMinimo;
        this.historialTransacciones = new ArrayList<>(); // Inicializar el historial
    }

    // Constructor sin parámetros
    public CuentaAhorro() {
        super();
        this.interesVariable = 0;
        this.saldoMinimo = 0;
        this.historialTransacciones = new ArrayList<>();
    }

    // Implementación de los métodos abstractos de Cuenta
    @Override
    public void retirar(double monto) {
        if (monto > 0 && monto <= getSaldo() && getSaldo() >= saldoMinimo) {
            setSaldo(getSaldo() - monto);
            historialTransacciones.add(new Transaccion(monto, TipoTransaccion.RETIRO)); // Registrar transacción
        }
    }

    @Override
    public void actualizarSaldo() {
        double interes = getSaldo() * interesVariable;
        setSaldo(getSaldo() + interes);
        historialTransacciones.add(new Transaccion(interes, TipoTransaccion.ACTUALIZACION)); // Registrar transacción
    }

    // Implementación de los métodos abstractos de OperacionesBancarias
    @Override
    public void transferir(double monto, Cuenta cuentaDestino) {
        if (monto > 0 && monto <= getSaldo() && getSaldo() >= saldoMinimo) {
            setSaldo(getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
            historialTransacciones.add(new Transaccion(monto, TipoTransaccion.TRANSFERENCIA)); // Registrar transacción
        }
    }

    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            setSaldo(getSaldo() + monto);
            historialTransacciones.add(new Transaccion(monto, TipoTransaccion.DEPOSITO)); // Registrar transacción
        }
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
            historialTransacciones.add(new Transaccion(ultimaTransaccion.getMonto(), TipoTransaccion.CANCELACION));
        }
    }

    // Método para obtener el historial de transacciones
    public List<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }
}