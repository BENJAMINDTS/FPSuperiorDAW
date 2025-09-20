
package com.mycompany.coches;

public class coche { //Declaramos las variable necesarias
    String marca;
    String color;
    double velocidad;

    public coche(String marca, String color, double velocidad) { //Constructor
        this.marca = marca;
        this.color = color;
        this.velocidad = velocidad;
    }

    public void setmarca(String marca) { //Metodo set de la variable "marca"
        this.marca = marca;
    }

    public void setdicolor(String color) { //Metodo set de la variable "color"
        this.color = color;
    }

    public void setvelocidad(double velocidad) { //Metodo set de la variable "velocidad"
        this.velocidad = velocidad;
    }

    public String getmarca() { //Metodo get de la variable "marca"
        return marca;
    }

    public String getcolor() { //Metodo get de la variable "color"
        return color;
    }

    public double getvelocidad() { //Metodo get de la variable "velocidad"
        return velocidad;
    }
    public double aumentavelocidad(){ //Metodo para aumentar la velocidad
        return velocidad+10;
    }
    public double disminuyevelocidad(){ //Metodo para disminuir la velocidad
        return velocidad-5;
    }
} 

