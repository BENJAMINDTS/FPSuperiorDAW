package modeloBancario;

public interface OperacionesBancarias {
    //Metodos abstractos
    public void transferir(double monto, Cuenta cuentaDestino);
    public void depositar(double monto); 
    public void consultarSaldo();
    public void cancelarTransaccion();
}
