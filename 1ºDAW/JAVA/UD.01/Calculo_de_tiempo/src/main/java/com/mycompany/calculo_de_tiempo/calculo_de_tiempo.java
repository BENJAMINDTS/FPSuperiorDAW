package com.mycompany.calculo_de_tiempo;

public class calculo_de_tiempo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //inicio de variables
        double velocidad = 8;
        double tiempo;
        double distancia = 12.4;
        //Calculo de tiempo
        tiempo = distancia / velocidad;
        System.out.println("La tiempo del corredor es " + tiempo);
    }
}
