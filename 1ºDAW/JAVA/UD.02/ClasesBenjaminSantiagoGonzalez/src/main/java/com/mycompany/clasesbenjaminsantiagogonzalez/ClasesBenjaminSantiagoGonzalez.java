package com.mycompany.clasesbenjaminsantiagogonzalez;

/**
 * Clase principal que ejecuta demostraciones de manipulación de Strings y
 * gestión de objetos de tipo NombreApellidos.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class ClasesBenjaminSantiagoGonzalez {

    /**
     * Punto de entrada principal de la aplicación.
     *
     * * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        // Declaramos las variables locales para el manejo de cadenas
        String yo = "Benjamin Santiago Gonzalez";
        int tamaño;
        char primer_caracter;
        char ultimo_caracter;
        String hipocoristico;
        String change;

        /*
         * Asignamos valor a las variables utilizando los métodos 
         * nativos de la clase String para análisis de texto.
         */
        tamaño = yo.length();
        primer_caracter = yo.charAt(0);
        ultimo_caracter = yo.charAt(tamaño - 1);
        hipocoristico = yo.substring(0, 5);
        change = yo.replace("a", "_");

        // Impresión de resultados de los métodos de String
        System.out.println("El tamanio de la cadena es " + (tamaño));
        System.out.println("El primer caracter de la cadena es " + primer_caracter);
        System.out.println("El ultimo caracter de la cadena es " + ultimo_caracter);
        System.out.println("Mi hipocoristico es " + hipocoristico);
        System.out.println("Al cambiar las `a` por `_` la cadena queda así: " + change);

        // --- Gestión de Objetos ---
        // Creación de un objeto sin parámetros (Constructor por defecto)
        NombreApellidos me;
        me = new NombreApellidos();
        System.out.println("Mi edad es " + me.getedad());
        System.out.println("Mi nombre es " + me.getnombre());

        // Creación de un objeto con parámetros específicos
        NombreApellidos Juan;
        Juan = new NombreApellidos("Juan Pablo", 37);
        System.out.println("La edad de Juan es " + Juan.getedad());
        System.out.println("El nombre de Juan es " + Juan.getnombre());

        /*
         * Caso de prueba: Intento de asignación de parámetros fuera de rango
         * para verificar la validación interna de la clase.
         */
        NombreApellidos intentodeerror;
        intentodeerror = new NombreApellidos("Pepe", 134);
        System.out.println("Resultado error (Edad): " + intentodeerror.getedad());
        System.out.println("Resultado error (Nombre): " + intentodeerror.getnombre());

        // Llamada al método estático de ayuda
        NombreApellidos.ayuda();
    }
}
