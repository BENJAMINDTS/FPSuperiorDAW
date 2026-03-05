package com.mycompany.calculo_de_areas;

public class Calculo_de_areas {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //inicio de variables
        double b = 2.8;
        double a = 1.4;
        int r = 5;
        double l = 2.4;
        double resultado;
        final double PI = 3.14159;
        //Cuadrado
        resultado = l * l;
        System.out.println("El area del cuadrado" + resultado);

        //Rectangulo
        resultado = b * a;
        System.out.println("El area del rectangulo es " + resultado);

        //Triangulo
        resultado = (b * a) / 2;
        System.out.println("El area del triangulo es " + resultado);

        //Circulo
        resultado = (r + r) * PI;
        System.out.println("El area del circulo es " + resultado);
    }
}
