package com.mycompany.comprobacion_y_transformacion_de_horas;

import java.util.Scanner;

public class Comprobacion_y_Transformacion_de_Horas {

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        System.out.println("introduce una hora con el formato horario hh:mm");
        String hora= sc.nextLine();
        if (hora.length()!=5|| hora.charAt(2)!=':') {
            System.out.println("El formato es incorrecto");
        }
        int horas;
        int minutos;
        horas = Integer.parseInt(hora.substring(0, 1));
        minutos = Integer.parseInt(hora.substring(3, 5));
   
        boolean formato = (horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59);
        if (formato==true) {
            System.out.println("El formato introducido es correcto");
        }else{
            System.out.println("El formato introducido es incorrecto");
        }
        System.out.println("Quieres ver la hora que has introducido");
        String eleccion = sc.nextLine();
        if (eleccion.equalsIgnoreCase("no")) {
        System.exit(0);
        }else{
            System.out.println("Quieres verlo en formato 24h o en formato am/pm");
            eleccion = sc.nextLine();
            if (eleccion.equalsIgnoreCase("24h")) {
                System.out.println(hora);
            }else{
                String ampm;
                if (horas == 0) {
                    ampm = 12 + ":" + minutos + " am";
                } else if (horas == 12) {
                    ampm = 12 + ":" + minutos + " pm";
                } else if (horas > 12) {
                    ampm = (horas - 12) + ":" + minutos + " pm";
                } else {
                    ampm = horas + ":" + minutos + " am";
                }
                    System.out.println(ampm);
            }
        }
        
    }
}