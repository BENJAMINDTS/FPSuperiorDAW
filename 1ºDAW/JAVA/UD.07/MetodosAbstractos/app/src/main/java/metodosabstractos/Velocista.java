package metodosabstractos;

public class Velocista extends Ciclista {
    private double potenciaPromedio;
    private double velocidadPromedio;

    Velocista(int identificacion, String nombre, double potenciaPromedio, double velocidadPromedio) {
        super(identificacion, nombre);
        this.potenciaPromedio = potenciaPromedio;
        this.velocidadPromedio = velocidadPromedio;
    }

    @Override
    public String imprimirTipo() {
        return "Velocista";
    }

    public double getPotenciaPromedio() {
        return potenciaPromedio;
    }

    public void setPotenciaPromedio(double potenciaPromedio) {
        this.potenciaPromedio = potenciaPromedio;
    }

    public double getVelocidadPromedio() {
        return velocidadPromedio;
    }

    public void setVelocidadPromedio(double velocidadPromedio) {
        this.velocidadPromedio = velocidadPromedio;
    }

    public void imprimir(){
        System.out.println(imprimirTipo());
        System.out.println("Identificacion: " + getIdentificacion());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Potencia Promedio: " + getPotenciaPromedio());
        System.out.println("Velocidad Promedio: " + getVelocidadPromedio());
        System.out.println("Tiempo Acumulado: " + getTiempoAcomulado());
        
    }    
}
