package modeloBancario;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Cuenta {
    //Declaracion de variables
    private static Set<String> numerosDeCuenta = new HashSet<>();
    private String numeroCuenta;
    private double saldo;
    private Cliente titular;

    //Constructor con parametros
    public Cuenta(double saldo, Cliente titular) {
        this.numeroCuenta = generarNumeroCuentaUnico();
        this.saldo = saldo;
        this.titular = titular;
    }

    //Metodo para generar un numero de cuenta unico
    private static String generarNumeroCuentaUnico() {
        String nuevoNumeroCuenta;
        do {
            nuevoNumeroCuenta = generarNumeroCuenta();
        } while (numerosDeCuenta.contains(nuevoNumeroCuenta));
        numerosDeCuenta.add(nuevoNumeroCuenta);
        return nuevoNumeroCuenta;
    }

    //Metodo para generar un numero de cuenta
    private static String generarNumeroCuenta() {
        final String CODIGO_PAIS = "ES";
        final int DIGITO_CONTROL_IBAN = 12;
        Random random = new Random();
        String entidad = String.format("%04d", random.nextInt(10000));
        String oficina = String.format("%04d", random.nextInt(10000));
        String digitosControl = String.format("%02d", random.nextInt(100));
        String numeroCuenta = String.format("%010d", random.nextInt(1000000000));

        return String.format("%s%d %s %s %s %s", CODIGO_PAIS, DIGITO_CONTROL_IBAN, entidad, oficina, digitosControl, numeroCuenta);
    }

    //Constructor sin parametros
    public Cuenta() {
        this.numeroCuenta = null;
        this.saldo = 0;
        this.titular = new Cliente();
    }

    //Metodos get y set
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular
    ) {
        this.titular = titular;
    }

    //Metodos 
    public void ingresar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    //Metodos abstractos
    public abstract void retirar(double monto);
    public abstract void actualizarSaldo();

}
