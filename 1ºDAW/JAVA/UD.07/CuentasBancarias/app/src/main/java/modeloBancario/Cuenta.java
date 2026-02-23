package modeloBancario;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Clase abstracta que define la estructura base de una cuenta bancaria.
 * Proporciona mecanismos automáticos para la generación de números de cuenta
 * únicos (IBAN)
 * y establece el contrato para las operaciones de retiro y actualización de
 * saldo.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public abstract class Cuenta {

    /**
     * Almacén estático para garantizar que no se repitan números de cuenta en el
     * sistema
     */
    private static Set<String> numerosDeCuenta = new HashSet<>();

    /** Identificador alfanumérico único de la cuenta (formato IBAN) */
    private String numeroCuenta;

    /** Balance económico actual de la cuenta */
    private double saldo;

    /** Objeto que representa al dueño legal de la cuenta */
    private Cliente titular;

    /**
     * Constructor con parámetros. Inicializa la cuenta con un saldo base y un
     * titular,
     * generando automáticamente un número de cuenta único.
     * * @param saldo Cantidad inicial de dinero.
     * 
     * @param titular Objeto Cliente que será el dueño de la cuenta.
     */
    public Cuenta(double saldo, Cliente titular) {
        this.numeroCuenta = generarNumeroCuentaUnico();
        this.saldo = saldo;
        this.titular = titular;
    }

    /**
     * Gestiona la generación recursiva de números de cuenta hasta encontrar uno
     * que no exista en el registro del sistema.
     * * @return Un número de cuenta único validado.
     */
    private static String generarNumeroCuentaUnico() {
        String nuevoNumeroCuenta;
        do {
            nuevoNumeroCuenta = generarNumeroCuenta();
        } while (numerosDeCuenta.contains(nuevoNumeroCuenta));
        numerosDeCuenta.add(nuevoNumeroCuenta);
        return nuevoNumeroCuenta;
    }

    /**
     * Genera de forma aleatoria los segmentos de un número de cuenta español.
     * Incluye código de país, dígitos de control, entidad, oficina y número de
     * cuenta.
     * * @return Cadena formateada representando un IBAN.
     */
    private static String generarNumeroCuenta() {
        final String CODIGO_PAIS = "ES";
        final int DIGITO_CONTROL_IBAN = 12;
        Random random = new Random();

        // Formateo de segmentos con ceros a la izquierda
        String entidad = String.format("%04d", random.nextInt(10000));
        String oficina = String.format("%04d", random.nextInt(10000));
        String digitosControl = String.format("%02d", random.nextInt(100));
        String numeroCuenta = String.format("%010d", random.nextInt(1000000000));

        return String.format("%s%d %s %s %s %s", CODIGO_PAIS, DIGITO_CONTROL_IBAN, entidad, oficina, digitosControl,
                numeroCuenta);
    }

    /**
     * Constructor sin parámetros.
     * Crea una instancia vacía con saldo cero y un nuevo cliente por defecto.
     */
    public Cuenta() {
        this.numeroCuenta = null;
        this.saldo = 0;
        this.titular = new Cliente();
    }

    // --- Métodos de Acceso (Getters y Setters) ---

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    /**
     * Asigna un nuevo saldo a la cuenta, validando que no sea negativo.
     * 
     * @param saldo Nuevo monto a establecer.
     */
    public void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    // --- Métodos de Operación ---

    /**
     * Permite realizar un ingreso de capital a la cuenta.
     * 
     * @param monto Cantidad de dinero a sumar al saldo.
     */
    public void ingresar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    // --- Métodos Abstractos ---

    /**
     * Define la lógica de retiro que debe implementar cada tipo de cuenta
     * específico.
     * 
     * @param monto Cantidad a sustraer.
     */
    public abstract void retirar(double monto);

    /**
     * Obliga a las subclases a definir cómo se capitalizan los intereses o se
     * aplican comisiones.
     */
    public abstract void actualizarSaldo();
}