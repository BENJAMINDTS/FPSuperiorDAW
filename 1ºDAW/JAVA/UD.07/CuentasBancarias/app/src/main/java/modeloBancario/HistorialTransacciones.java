package modeloBancario;

import java.util.ArrayList;
import java.util.List;

public class HistorialTransacciones {
    // Declaración de variables
    private List<Transaccion> transacciones;

    // Constructor
    public HistorialTransacciones() {
        this.transacciones = new ArrayList<>();
    }

    // Método para agregar una transacción
    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    // Método para obtener todas las transacciones
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
}