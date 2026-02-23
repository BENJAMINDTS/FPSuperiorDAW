package modeloBancario;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa un movimiento financiero individual dentro del sistema bancario.
 * Gestiona de forma automática la identificación única y el registro temporal
 * de cada operación realizada.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Transaccion {

    /** Contador global para generar identificadores únicos incrementales */
    protected static int contadorTransacciones = 0;

    /** Identificador numérico único de la transacción */
    protected int idTransaccion;

    /** Marca de tiempo de la operación en formato legible (dd/MM/yyyy HH:mm:ss) */
    protected String fecha;

    /** Valor económico de la operación */
    protected double monto;

    /** Clasificación de la transacción (DEPOSITO, RETIRO, etc.) */
    protected TipoTransaccion tipo;

    /**
     * Constructor de la clase Transaccion.
     * Automatiza la asignación del ID y captura la fecha/hora exacta del sistema.
     * * @param monto Cantidad de dinero involucrada en la operación.
     * 
     * @param tipo Categoría de la transacción basada en el enumerado
     *             TipoTransaccion.
     */
    public Transaccion(double monto, TipoTransaccion tipo) {
        // Incrementa el contador global y asigna el valor al ID de esta instancia
        this.idTransaccion = ++contadorTransacciones;

        // Configuración del formato de fecha y captura del tiempo actual
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        this.fecha = formatter.format(date);

        this.monto = monto;
        this.tipo = tipo;
    }

    // --- Métodos de Acceso (Getters) ---

    /** @return El identificador único de la transacción */
    public int getIdTransaccion() {
        return idTransaccion;
    }

    /** @return La fecha y hora en que se registró la operación */
    public String getFecha() {
        return fecha;
    }

    /** @return El monto económico de la transacción */
    public double getMonto() {
        return monto;
    }

    /** @return El tipo de movimiento realizado */
    public TipoTransaccion getTipo() {
        return tipo;
    }

    /**
     * Genera una representación textual de la transacción para su visualización o
     * depuración.
     * * @return Cadena con el estado detallado del objeto.
     */
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