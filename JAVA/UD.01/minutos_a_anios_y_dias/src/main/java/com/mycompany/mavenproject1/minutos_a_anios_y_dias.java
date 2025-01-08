package com.mycompany.mavenproject1;

public class minutos_a_anios_y_dias {

    public static void main(String[] args) {
        float minutos=21212121,años, dias;

        años=minutos/525600;
        System.out.println("Son " + años + " anios");
        dias=minutos/1440;
        System.out.println("Son " + dias+ "dias");
        años= (int)minutos/525600;
        dias= (minutos%525600)/1440;
        System.out.println("Son " + años + " anios y " + dias + " dias");
    }
}
