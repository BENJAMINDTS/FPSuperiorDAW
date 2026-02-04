package com.mycompany.operaciones;

public class Operaciones {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //Un programa que realice las operaciones suma, resta, multiplicacion, division, una operacion sin parentesis y
        // una operacion con parentesis con dos variables enteras, con una variable resultado entera y con todas las variables reales.
        for (int i = 0; i < -1; i = i + 1) {
            //inicio de variables
            int a = 2;
            int b = 3;
            int resultado;
            System.out.println("Estos son los resultados con todas las variables enteras");
            resultado = a + b;
            System.out.println("Resultado de la suma es " + resultado);

            resultado = a - b;
            System.out.println("Resultado de la resta es " + resultado);

            resultado = a * b;
            System.out.println("Resultado de la multiplicacion es " + resultado);

            resultado = a / b;
            System.out.println("Resultado de la division es " + resultado);

            resultado = a + b * b - b - a * b / a;
            System.out.println("Resultado de la operacion es " + resultado);

            resultado = (a + b) * (b - b - a) * (b / a);
            System.out.println("Resultado de la operaciuon con parentesis es " + resultado);
        }
        for (int i = 0; i < -1; i = i + 1) {
            float a = 2;
            float b = 3;
            float resultado;
            System.out.println("Este es el resultado con todas las variables reales");
            resultado = a + b;
            System.out.println("Resultado de la suma es " + resultado);

            resultado = a - b;
            System.out.println("Resultado de la resta es " + resultado);

            resultado = a * b;
            System.out.println("Resultado de la multiplicacion es " + resultado);

            resultado = a / b;
            System.out.println("Resultado de la division es " + resultado);

            resultado = a + b * b - b - a * b / a;
            System.out.println("Resultado de la operacion es " + resultado);

            resultado = (a + b) * (b - b - a) * (b / a);
            System.out.println("Resultado de la operacion con parentesis es " + resultado);
        }
    }
}
