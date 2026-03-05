package com.mycompany.pruebadeexepciones;

import java.util.Scanner;

/**
 * Clase principal que ejecuta la lógica de captura de datos y manejo de
 * excepciones.
 * Implementa una estructura try-catch-finally para gestionar el flujo del
 * programa.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class PruebaDeExepciones {

    /**
     * Método de entrada que interactúa con el usuario y procesa las excepciones de
     * Vendedor.
     * * @param args Argumentos de sistema.
     */
    public static void main(String[] args) {
        String nombre;
        String apellido;
        int edad;
        Scanner sc = new Scanner(System.in);

        // Recolección de datos
        System.out.println("Introduce el nombre del vendedor:");
        nombre = sc.nextLine();
        System.out.println("Introduce el apellido del vendedor:");
        apellido = sc.nextLine();
        System.out.println("Introduce la edad del vendedor:");
        edad = sc.nextInt();

        Vendedor v1 = new Vendedor(nombre, apellido, edad);

        System.out.println("Muy bien, ahora vamos a verificar su edad...");

        /**
         * Bloque de control de excepciones.
         * try: Intenta ejecutar la validación.
         * catch: Se activa si la edad es menor a 18 o mayor a 120.
         * finally: Bloque de cierre que se ejecuta siempre.
         */
        try {
            // Si verificarEdad lanza excepción, saltamos directamente al catch
            String resultado = v1.verificarEdad();
            System.out.println(resultado);
        } catch (Exception ex) {
            System.out.println("ERROR: La edad es incorrecta para este puesto.");
        } finally {
            // Este mensaje aparecerá sin importar si la edad fue válida o no
            System.out.println("Proceso de verificación finalizado.");
            sc.close();
        }
    }
}