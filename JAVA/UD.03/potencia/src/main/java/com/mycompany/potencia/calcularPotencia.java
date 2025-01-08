package com.mycompany.potencia;

public class calcularPotencia {
    public static void main(String[] args) {
        int base = 11;
        int exponente = 5;
        int resultado = 1;
        
        try {
            if (base < 0 || exponente < 0) {
                throw new IllegalArgumentException("La base y el exponente deben ser mayores o iguales a 0");
            }
            
            assert base >= 0 && base <= 10 : "La base debe estar entre 0 y 10";
            assert exponente >= 0 && exponente <= 20 : "El exponente debe estar entre 0 y 20";
            
            if (base == 1 || exponente == 0) {
                resultado = 1;
            } else {
                for (int i = 0; i < exponente; i++) {
                    resultado *= base;

                    if (resultado > 1000) {
                        System.out.println("El resultado es mayor que 1000");
                        return;
                    }
                }
            }
            if (resultado >1000) {
                System.out.println("El resultado de " + base + " elevado a " + exponente + " es: " + resultado);
            }
            

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Se produjo un error inesperado: " + e.getMessage());
        } 
    }
}
