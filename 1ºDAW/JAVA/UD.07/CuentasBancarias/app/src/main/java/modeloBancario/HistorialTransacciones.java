package modeloBancario;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona el registro acumulativo de todos los movimientos financieros.
 * Esta clase actúa como un libro contable digital, almacenando cronológicamente
 * cada objeto de tipo Transaccion generado por el sistema.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class HistorialTransacciones {

    /**
     * * Colección dinámica que almacena las transacciones realizadas.
     * Se utiliza la interfaz List para permitir flexibilidad en la implementación.
     */
    private List<Transaccion> transacciones;

    /**
     * Constructor por defecto.
     * Inicializa el contenedor de transacciones como una lista vacía (ArrayList).
     */
    public HistorialTransacciones() {
        this.transacciones = new ArrayList<>();
    }

    /**
     * Registra un nuevo movimiento en el historial.
     * * @param transaccion El objeto Transaccion validado que se desea persistir en
     * el historial.
     */
    public void agregarTransaccion(Transaccion transaccion) {
        if (transaccion != null) {
            transacciones.add(transaccion);
        }
    }

    /**
     * Recupera la colección completa de movimientos registrados.
     * * @return List de objetos Transaccion contenidos en el historial.
     */
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
}