package modeloBancario.exepciones;

public class SaldoInsuficienteException extends Exception {
    // Constructor con mensaje personalizado
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}