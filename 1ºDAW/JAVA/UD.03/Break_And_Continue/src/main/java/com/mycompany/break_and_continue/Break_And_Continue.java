package com.mycompany.break_and_continue;

/**
 * Clase diseñada para demostrar el uso de las sentencias de control de flujo
 * 'break' y 'continue' dentro de una estructura iterativa.
 *
 * * @author BenjaminDTS
 *
 * @version 1.0
 */
public class Break_And_Continue {

    /**
     * Método principal que ejecuta un bucle del 1 al 50 con saltos
     * condicionales.
     *
     * * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        // Variable de control para la iteración
        int i;

        /**
         * El bucle recorre un rango de 1 a 50. Aplica lógica de filtrado
         * mediante el operador módulo (%).
         */
        for (i = 1; i <= 50; i++) {

            /*
			 * * Sentencia continue:
			 * Si el número es múltiplo de 3 (i % 3 == 0), se omite el resto del código
			 * de esta iteración y se pasa inmediatamente al siguiente número.
             */
            if (i % 3 == 0) {
                continue;
            } else {
                // Si no es múltiplo de 3, se imprime el valor en consola
                System.out.println(i);
            }

            /*
			 * * Sentencia break:
			 * Cuando la variable alcanza o supera el valor 40, el bucle finaliza
			 * de forma abrupta, sin completar las iteraciones restantes hasta 50.
             */
            if (i >= 40) {
                System.out.println("Se uso un break");
                break;
            }
        }
    }
}
