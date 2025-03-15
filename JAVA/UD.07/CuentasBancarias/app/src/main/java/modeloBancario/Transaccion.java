package modeloBancario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaccion {
    // Declaración de variables
    protected static int contadorTransacciones = 0;
    protected int idTransaccion;
    protected String fecha;
    protected double monto;
    protected TipoTransaccion tipo;

    // Constructor con parámetros
    public Transaccion(double monto, TipoTransaccion tipo) {
        this.idTransaccion = ++contadorTransacciones; // Incrementa el contador y asigna el id
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        this.fecha = formatter.format(date);
        this.monto = monto;
        this.tipo = tipo;
    }

    // Métodos get
    public int getIdTransaccion() {
        return idTransaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    // Método para imprimir
    @Override
    public String toString() {
        return "Transaccion{" +
                "idTransaccion=" + idTransaccion +
                ", fecha='" + fecha + '\'' +
                ", monto=" + monto +
                ", tipo=" + tipo +
                '}';
    }
}