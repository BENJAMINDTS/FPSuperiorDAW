package com.mycompany.automoviles;

import java.util.Scanner;

/**
 * Clase principal que gestiona la entrada de datos de un automóvil.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Automoviles {

    /**
     * Punto de entrada del programa. Solicita los atributos del vehículo por
     * consola.
     *
     * * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {

        // Declaración de variables de instancia para el automóvil
        String marca;
        int modelo;
        int anioFab;
        int motor;
        String combustible;
        String tipo;
        int puertas;
        int asientos;
        double max_v;
        String color;
        double actual_v = 0; // Inicializada por defecto

        Scanner leer = new Scanner(System.in);
        metodos utilidades = new metodos();

        // Muestra la información inicial desde la clase métodos
        System.out.println(utilidades.info());

        // --- Recopilación de datos ---
        System.out.println("Dime su marca:");
        marca = leer.nextLine();

        System.out.println("Dime el año del modelo:");
        modelo = leer.nextInt();

        System.out.println("Dime su año de fabricación:");
        anioFab = leer.nextInt();

        System.out.println("Dime la cilindrada del motor (cm3):");
        motor = leer.nextInt();
        leer.nextLine(); // Limpiar el buffer tras leer un entero

        System.out.println("Dime el tipo de combustible:");
        combustible = leer.nextLine();

        System.out.println("Dime el tipo de automóvil (Sedán, SUV, etc.):");
        tipo = leer.nextLine();

        System.out.println("Dime el número de puertas:");
        puertas = leer.nextInt();

        System.out.println("Dime el número de asientos:");
        asientos = leer.nextInt();

        System.out.println("Dime la velocidad máxima:");
        max_v = leer.nextDouble();
        leer.nextLine(); // Limpiar el buffer

        System.out.println("Dime el color del vehículo:");
        color = leer.nextLine();

        // Ejemplo de salida de datos para verificar la captura
        System.out.println("\n--- Resumen del Automóvil ---");
        System.out.println("Marca: " + marca + " | Modelo: " + modelo + " | Color: " + color);
        System.out.println("Año de fabricación: " + anioFab + " | Tipo: " + tipo);
        System.out.println("Puertas: " + puertas + " | Asientos: " + asientos);
        System.out.println("Velocidad máxima: " + max_v + " km/h");
        System.out.println("Combustible: " + combustible + " | Motor: " + motor + " cm3");

        leer.close();
    }
}
