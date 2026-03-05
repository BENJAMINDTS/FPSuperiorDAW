package metodosabstractos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase principal que actúa como punto de entrada a la aplicación.
 * Gestiona la lógica de negocio mediante un menú interactivo por consola
 * que permite la administración de equipos y ciclistas.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App {

    /**
     * Punto de inicio del programa. Implementa un bucle infinito para el menú
     * y utiliza bloques try-catch para garantizar la robustez ante entradas
     * inválidas.
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        /** Lista que almacena todos los equipos creados en la sesión */
        List<Equipo> equipos = new ArrayList<>();

        // Uso de try-with-resources para asegurar el cierre automático del Scanner
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- SISTEMA DE GESTIÓN CICLISTA ---");
                System.out.println("1. Añadir equipo");
                System.out.println("2. Añadir ciclista");
                System.out.println("3. Buscar ciclista");
                System.out.println("4. Buscar equipo");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1 -> {
                        // Lógica para registrar un nuevo equipo
                        System.out.println("Introduce el nombre del equipo: ");
                        String nombreEquipo = sc.next();
                        System.out.println("Introduce el pais del equipo: ");
                        String paisEquipo = sc.next();
                        equipos.add(new Equipo(nombreEquipo, paisEquipo));
                    }

                    case 2 -> {
                        // Submenú para seleccionar el tipo de ciclista a instanciar
                        System.out
                                .println("Tipo de ciclista: 1.Escalador | 2.Velocista | 3.Contrarelojista | 4.Volver");
                        int tipoCiclista = sc.nextInt();
                        gestionarAltaCiclista(sc, equipos, tipoCiclista);
                    }

                    case 3 -> {
                        // Búsqueda de ciclistas dentro de un equipo específico
                        System.out.println("Nombre del equipo: ");
                        String nomEquipoBusq = sc.next();
                        for (Equipo e : equipos) {
                            if (e.getNombre().equals(nomEquipoBusq)) {
                                System.out.println("ID del ciclista: ");
                                int id = sc.nextInt();
                                e.buscarCiclista(id);
                            }
                        }
                    }

                    case 4 -> {
                        // Búsqueda global de equipo
                        System.out.println("Introduce el nombre del equipo: ");
                        String nomEq = sc.next();
                        Equipo.buscarEquipo(nomEq);
                    }

                    case 5 -> {
                        System.out.println("Finalizando programa...");
                        System.exit(0);
                    }

                    default -> System.out.println("Opción no válida.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Se esperaba un valor numérico válido.");
        } catch (NoSuchElementException e) {
            System.out.println("Error: No se encontró el elemento solicitado.");
        } catch (IllegalStateException e) {
            System.out.println("Error: El flujo de entrada está cerrado.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para centralizar la creación de diferentes tipos de
     * ciclistas.
     * Facilita la legibilidad del método main.
     */
    private static void gestionarAltaCiclista(Scanner sc, List<Equipo> equipos, int tipo) {
        if (tipo == 4)
            return;

        System.out.println("Nombre del equipo destino: ");
        String target = sc.next();

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equals(target)) {
                System.out.println("ID: ");
                int id = sc.nextInt();
                System.out.println("Nombre: ");
                String nombre = sc.next();

                switch (tipo) {
                    case 1 -> {
                        // Escalador
                        System.out.println("Aceleración: ");
                        double ac = sc.nextDouble();
                        System.out.println("Grado Rampa: ");
                        double gr = sc.nextDouble();
                        equipo.getCiclistas().add(new Escalador(id, nombre, ac, gr));
                    }
                    case 2 -> {
                        // Velocista
                        System.out.println("Potencia: ");
                        double pot = sc.nextDouble();
                        System.out.println("Velocidad: ");
                        double vel = sc.nextDouble();
                        equipo.getCiclistas().add(new Velocista(id, nombre, pot, vel));
                    }
                    case 3 -> {
                        // Contrarelojista
                        System.out.println("Velocidad Máxima: ");
                        double vm = sc.nextDouble();
                        equipo.getCiclistas().add(new Contrarelojista(id, nombre, vm));
                    }
                }
                return;
            }
        }
        System.out.println("Equipo no encontrado.");
    }
}