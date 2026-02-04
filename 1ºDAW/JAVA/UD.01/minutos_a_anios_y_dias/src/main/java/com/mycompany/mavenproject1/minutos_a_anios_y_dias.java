package com.mycompany.mavenproject1;

public class minutos_a_anios_y_dias {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //inicio de variables
        float minutos = 21212121, años, dias;
        //Calculo de años y dias
        años = minutos / 525600;
        System.out.println("Son " + años + " anios");
        dias = minutos / 1440;
        System.out.println("Son " + dias + "dias");
        //Calculo de años y dias con el resto de minutos
        años = (int) minutos / 525600;
        dias = (minutos % 525600) / 1440;
        System.out.println("Son " + años + " anios y " + dias + " dias");
    }
}
