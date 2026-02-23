package cuentasbancarias;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import modeloBancario.CCorriente;
import modeloBancario.Cliente;
import modeloBancario.Cuenta;
import modeloBancario.CuentaAhorro;
import modeloBancario.OperacionesBancarias;
import modeloBancario.Transaccion;
import modeloBancario.exepciones.MontoInvalidoException;
import modeloBancario.exepciones.SaldoInsuficienteException;

/**
 * Clase principal que actúa como interfaz de usuario (CLI) para el sistema
 * bancario.
 * Gestiona el flujo de registro, autenticación y operaciones financieras de los
 * clientes,
 * integrando todas las reglas de negocio del modelo bancario.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {
    /**
     * Lista estática que actúa como base de datos temporal de clientes registrados
     */
    private static final List<Cliente> clientes = new ArrayList<>();

    /** Escáner global para la captura de entradas de usuario por consola */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Punto de entrada de la aplicación. Gestiona el ciclo de vida del Menú
     * Principal.
     * * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1 -> registrarCliente();
                    case 2 -> iniciarSesion();
                    case 3 -> {
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                    }
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número entero.");
                scanner.nextLine(); // Limpiar entrada errónea
            }
        }
    }

    /**
     * Captura los datos de un nuevo cliente y lo añade al sistema.
     * Valida la unicidad del DNI y la robustez de la contraseña mediante el
     * constructor de Cliente.
     */
    private static void registrarCliente() {
        System.out.println("\n--- Registro de Cliente ---");
        System.out.print("Ingrese su DNI: ");
        String dni = scanner.nextLine();

        if (buscarClientePorDni(dni) != null) {
            System.out.println("El cliente con DNI " + dni + " ya está registrado.");
            return;
        }

        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese su teléfono: ");
        String telefono = scanner.nextLine();

        try {
            Cliente nuevoCliente = new Cliente(dni, contrasena, nombre, direccion, telefono);
            clientes.add(nuevoCliente);
            System.out.println("Cliente registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error de Seguridad: " + e.getMessage());
        }
    }

    /**
     * Valida las credenciales del usuario y otorga acceso al menú de operaciones.
     */
    private static void iniciarSesion() {
        System.out.println("\n--- Iniciar Sesión ---");
        System.out.print("Ingrese su DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Cliente cliente = buscarClientePorDni(dni);

        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + cliente.getNombre() + ".");
            menuCliente(cliente);
        } else {
            System.out.println("DNI o contraseña incorrectos.");
        }
    }

    /**
     * Busca un objeto Cliente en la lista maestra mediante su DNI.
     * * @param dni Documento de identidad a buscar.
     * 
     * @return El objeto Cliente si existe, null en caso contrario.
     */
    private static Cliente buscarClientePorDni(String dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Menú secundario para usuarios autenticados. Permite gestionar cuentas y
     * operar fondos.
     * * @param cliente El cliente que ha iniciado sesión.
     */
    private static void menuCliente(Cliente cliente) {
        boolean cerrarSesion = false;

        while (!cerrarSesion) {
            System.out.println("\n--- Menú del Cliente (" + cliente.getNombre() + ") ---");
            System.out.println("1. Crear cuenta de ahorro");
            System.out.println("2. Crear cuenta corriente");
            System.out.println("3. Ver cuentas");
            System.out.println("4. Realizar operaciones en una cuenta");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearCuentaAhorro(cliente);
                case 2 -> crearCCorriente(cliente);
                case 3 -> verCuentas(cliente);
                case 4 -> {
                    try {
                        realizarOperaciones(cliente);
                    } catch (MontoInvalidoException | SaldoInsuficienteException e) {
                        System.err.println("Error en la operación: " + e.getMessage());
                    }
                }
                case 5 -> {
                    cerrarSesion = true;
                    System.out.println("Cerrando sesión...");
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Orquesta el submenú de operaciones transaccionales para una cuenta
     * específica.
     * * @param cliente Cliente titular.
     * 
     * @throws MontoInvalidoException     Si el monto no es válido.
     * @throws SaldoInsuficienteException Si no hay fondos suficientes.
     */
    private static void realizarOperaciones(Cliente cliente) throws MontoInvalidoException, SaldoInsuficienteException {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        Cuenta cuenta = cliente.buscarCuenta(numeroCuenta);

        if (cuenta == null) {
            System.out.println("No se encontró la cuenta.");
            return;
        }

        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Operaciones: " + numeroCuenta + " ---");
            System.out.println(
                    "1. Depositar  2. Retirar  3. Transferir  4. Saldo  5. Historial  6. Cancelar Ult.  7. Actualizar (Ahorro)  8. Volver");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> depositarEnCuenta(cuenta);
                case 2 -> retirarDeCuenta(cuenta);
                case 3 -> transferirDesdeCuenta(cuenta, cliente);
                case 4 -> consultarSaldo(cuenta);
                case 5 -> verHistorialTransacciones(cuenta);
                case 6 -> cancelarUltimaTransaccion(cuenta);
                case 7 -> actualizarSaldo(cuenta);
                case 8 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // --- Métodos de apoyo para transacciones específicas ---

    private static void depositarEnCuenta(Cuenta cuenta) throws MontoInvalidoException {
        System.out.print("Monto a depositar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        if (cuenta instanceof OperacionesBancarias operacionesBancarias) {
            operacionesBancarias.depositar(monto);
            System.out.println("Depósito exitoso.");
        }
    }

    private static void retirarDeCuenta(Cuenta cuenta) throws SaldoInsuficienteException, MontoInvalidoException {
        System.out.print("Monto a retirar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        cuenta.retirar(monto);
        System.out.println("Retiro exitoso.");
    }

    private static void transferirDesdeCuenta(Cuenta cuenta, Cliente cliente)
            throws SaldoInsuficienteException, MontoInvalidoException {
        System.out.print("Número de cuenta destino: ");
        String destino = scanner.nextLine();
        Cuenta cuentaDestino = cliente.buscarCuenta(destino);

        if (cuentaDestino != null && cuenta instanceof OperacionesBancarias) {
            System.out.print("Monto a transferir: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();
            ((OperacionesBancarias) cuenta).transferir(monto, cuentaDestino);
            System.out.println("Transferencia exitosa.");
        } else {
            System.out.println("Error en la transferencia.");
        }
    }

    private static void consultarSaldo(Cuenta cuenta) {
        if (cuenta instanceof OperacionesBancarias operacionesBancarias) {
            operacionesBancarias.consultarSaldo();
        }
    }

    private static void verHistorialTransacciones(Cuenta cuenta) {
        List<Transaccion> historial = null;
        if (cuenta instanceof CuentaAhorro cuentaAhorro)
            historial = cuentaAhorro.getHistorialTransacciones();
        else if (cuenta instanceof CCorriente cuentaCorriente)
            historial = cuentaCorriente.getHistorialTransacciones();

        if (historial != null) {
            System.out.println("\n--- Historial ---");
            historial.forEach(System.out::println);
        }
    }

    private static void cancelarUltimaTransaccion(Cuenta cuenta) {
        if (cuenta instanceof OperacionesBancarias operacionesBancarias) {
            operacionesBancarias.cancelarTransaccion();
            System.out.println("Operación revertida.");
        }
    }

    private static void actualizarSaldo(Cuenta cuenta) {
        cuenta.actualizarSaldo();
        System.out.println("Intereses aplicados.");
    }

    private static void crearCuentaAhorro(Cliente cliente) {
        System.out.print("Saldo inicial: ");
        double s = scanner.nextDouble();
        System.out.print("Interés: ");
        double i = scanner.nextDouble();
        System.out.print("Mínimo: ");
        double m = scanner.nextDouble();
        cliente.agregarCuenta(new CuentaAhorro(s, cliente, i, m));
        System.out.println("Cuenta de Ahorro creada.");
    }

    private static void crearCCorriente(Cliente cliente) {
        System.out.print("Saldo inicial: ");
        double s = scanner.nextDouble();
        cliente.agregarCuenta(new CCorriente(s, cliente));
        System.out.println("Cuenta Corriente creada.");
    }

    private static void verCuentas(Cliente cliente) {
        cliente.getCuentas()
                .forEach(c -> System.out.println("Nº: " + c.getNumeroCuenta() + " | Saldo: " + c.getSaldo()));
    }
}