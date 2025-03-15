
package cuentasbancarias;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import modeloBancario.*;
import modeloBancario.exepciones.MontoInvalidoException;
import modeloBancario.exepciones.SaldoInsuficienteException;

public class App {
    private static List<Cliente> clientes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Método para registrar un nuevo cliente
    private static void registrarCliente() {
        System.out.println("\n--- Registro de Cliente ---");
        System.out.print("Ingrese su DNI: ");
        String dni = scanner.nextLine();

        // Verificar si el cliente ya está registrado
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
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para iniciar sesión
    private static void iniciarSesion() {
        System.out.println("\n--- Iniciar Sesión ---");
        System.out.print("Ingrese su DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Cliente cliente = buscarClientePorDni(dni);

        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + cliente.getNombre() + ".");
            menuCliente(cliente); // Mostrar menú del cliente
        } else {
            System.out.println("DNI o contraseña incorrectos.");
        }
    }

    // Método para buscar un cliente por su DNI
    private static Cliente buscarClientePorDni(String dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }

    // Menú del cliente después de iniciar sesión
    private static void menuCliente(Cliente cliente) {
        boolean cerrarSesion = false;

        while (!cerrarSesion) {
            System.out.println("\n--- Menú del Cliente ---");
            System.out.println("1. Crear cuenta de ahorro");
            System.out.println("2. Crear cuenta corriente");
            System.out.println("3. Ver cuentas");
            System.out.println("4. Realizar operaciones en una cuenta");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    crearCuentaAhorro(cliente);
                    break;
                case 2:
                    crearCCorriente(cliente);
                    break;
                case 3:
                    verCuentas(cliente);
                    break;
                case 4:
                    try {
                        realizarOperaciones(cliente);
                    } catch (MontoInvalidoException e) {
                        System.out.println("Monto invalido");
                        e.printStackTrace();
                    } catch (SaldoInsuficienteException e) {
                        System.out.println("Saldo insuficiente");
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    cerrarSesion = true;
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Método para crear una cuenta de ahorro
    private static void crearCuentaAhorro(Cliente cliente) {
        try {
            System.out.print("Ingrese el saldo inicial: ");
        double saldo = scanner.nextDouble();
        System.out.print("Ingrese el interés variable: ");
        double interesVariable = scanner.nextDouble();
        System.out.print("Ingrese el saldo mínimo: ");
        double saldoMinimo = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner

        CuentaAhorro cuentaAhorro = new CuentaAhorro(saldo, cliente, interesVariable, saldoMinimo);
        cliente.agregarCuenta(cuentaAhorro);
        System.out.println("Cuenta de ahorro creada exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
            scanner.nextLine();
        }
    }

    // Método para crear una cuenta corriente
    private static void crearCCorriente(Cliente cliente) {
       try {
        System.out.print("Ingrese el saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner

        CCorriente cCorriente = new CCorriente(saldo, cliente);
        cliente.agregarCuenta(cCorriente);
        System.out.println("Cuenta corriente creada exitosamente.");
       } catch (InputMismatchException e) {
              System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
              scanner.nextLine();
       }
    }

    // Método para ver las cuentas del cliente
    private static void verCuentas(Cliente cliente) {
        List<Cuenta> cuentas = cliente.getCuentas();

        if (cuentas.isEmpty()) {
            System.out.println("No tiene cuentas registradas.");
        } else {
            System.out.println("\n--- Cuentas ---");
            for (Cuenta cuenta : cuentas) {
                System.out.println("Número de cuenta: " + cuenta.getNumeroCuenta());
                System.out.println("Saldo: " + cuenta.getSaldo());
                System.out.println("Tipo de cuenta: " + (cuenta instanceof CuentaAhorro ? "Ahorro" : "Corriente"));
                System.out.println("-----------------------------");
            }
        }
    }

    // Método para realizar operaciones en una cuenta
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
            System.out.println("\n--- Operaciones en la Cuenta " + numeroCuenta + " ---");
            System.out.println("1. Depositar");
            System.out.println("2. Retirar");
            System.out.println("3. Transferir");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Ver historial de transacciones");
            System.out.println("6. Cancelar última transacción");
            System.out.println("7. Actualizar saldo (solo cuentas de ahorro)");
            System.out.println("8. Volver al menú anterior");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    depositarEnCuenta(cuenta);
                    break;
                case 2:
                    retirarDeCuenta(cuenta);
                    break;
                case 3:
                        transferirDesdeCuenta(cuenta, cliente);
                    break;
                case 4:
                    consultarSaldo(cuenta);
                    break;
                case 5:
                    verHistorialTransacciones(cuenta);
                    break;
                case 6:
                    cancelarUltimaTransaccion(cuenta);
                    break;
                case 7:
                    actualizarSaldo(cuenta);
                    break;
                case 8:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Método para depositar en una cuenta
    private static void depositarEnCuenta(Cuenta cuenta) throws MontoInvalidoException {
        System.out.print("Ingrese el monto a depositar: ");
        double montoDeposito = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner
        if (cuenta instanceof CuentaAhorro) {
            ((CuentaAhorro) cuenta).depositar(montoDeposito);
        } else if (cuenta instanceof CCorriente) {
            ((CCorriente) cuenta).depositar(montoDeposito);
            
        }
    }

    // Método para retirar de una cuenta
    private static void retirarDeCuenta(Cuenta cuenta) throws SaldoInsuficienteException, MontoInvalidoException {
        System.out.print("Ingrese el monto a retirar: ");
        double montoRetiro = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner
        if (cuenta instanceof CuentaAhorro) {
            ((CuentaAhorro) cuenta).retirar(montoRetiro);
        } else if (cuenta instanceof CCorriente) {
            ((CCorriente) cuenta).retirar(montoRetiro);
        }
        System.out.println("Retiro realizado exitosamente.");
    }

    // Método para transferir desde una cuenta
    private static void transferirDesdeCuenta(Cuenta cuenta, Cliente cliente) throws SaldoInsuficienteException, MontoInvalidoException {
        System.out.print("Ingrese el número de cuenta destino: ");
        String numeroCuentaDestino = scanner.nextLine();
        Cuenta cuentaDestino = cliente.buscarCuenta(numeroCuentaDestino);

        if (cuentaDestino == null) {
            System.out.println("No se encontró la cuenta destino.");
            return;
        }

        System.out.print("Ingrese el monto a transferir: ");
        double montoTransferencia = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner

        if (cuenta instanceof CuentaAhorro) {
            ((CuentaAhorro) cuenta).transferir(montoTransferencia, cuentaDestino);
        } else if (cuenta instanceof CCorriente) {
            ((CCorriente) cuenta).transferir(montoTransferencia, cuentaDestino);
        }
        System.out.println("Transferencia realizada exitosamente.");
    }

    // Método para consultar el saldo de una cuenta
    private static void consultarSaldo(Cuenta cuenta) {
        if (cuenta instanceof CuentaAhorro) {
            ((CuentaAhorro) cuenta).consultarSaldo();
        } else if (cuenta instanceof CCorriente) {
            ((CCorriente) cuenta).consultarSaldo();
            
        }
    }

    // Método para ver el historial de transacciones de una cuenta
    private static void verHistorialTransacciones(Cuenta cuenta) {
        if (cuenta instanceof CuentaAhorro) {
            List<Transaccion> historial = ((CuentaAhorro) cuenta).getHistorialTransacciones();
            System.out.println("\n--- Historial de Transacciones ---");
            for (Transaccion transaccion : historial) {
                System.out.println(transaccion);
            }
        } else if (cuenta instanceof CCorriente) {
            List<Transaccion> historial = ((CCorriente) cuenta).getHistorialTransacciones();
            System.out.println("\n--- Historial de Transacciones ---");
            for (Transaccion transaccion : historial) {
                System.out.println(transaccion);
            }
        } else {
            System.out.println("No se puede mostrar el historial para este tipo de cuenta.");
        }
    }

    // Método para cancelar la última transacción de una cuenta
    private static void cancelarUltimaTransaccion(Cuenta cuenta) {
        if (cuenta instanceof CuentaAhorro) {
            ((CuentaAhorro) cuenta).cancelarTransaccion();
            System.out.println("Última transacción cancelada exitosamente.");
        } else if (cuenta instanceof CCorriente) {
            ((CCorriente) cuenta).cancelarTransaccion();
            System.out.println("Última transacción cancelada exitosamente.");
        } else {
            System.out.println("No se puede cancelar la última transacción para este tipo de cuenta.");
        }
    }

    // Método para actualizar el saldo de una cuenta de ahorro
    private static void actualizarSaldo(Cuenta cuenta) {
        if (cuenta instanceof CuentaAhorro) {
            ((CuentaAhorro) cuenta).actualizarSaldo();
            System.out.println("Saldo actualizado exitosamente.");
        } else {
            System.out.println("Esta operación solo está disponible para cuentas de ahorro.");
        }
    }
}