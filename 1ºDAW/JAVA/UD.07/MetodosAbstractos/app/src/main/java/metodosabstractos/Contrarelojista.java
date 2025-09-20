package metodosabstractos;

public class Contrarelojista extends Ciclista {
    private double velocidadMaxima;

    Contrarelojista(int identificacion, String nombre, double velocidadMaxima) {
        super(identificacion, nombre);
        this.velocidadMaxima = velocidadMaxima;
    }

    @Override
    public String imprimirTipo() {
        return "Contrarelojista";
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public void imprimir(){
        System.out.println(imprimirTipo());
        System.out.println("Identificacion: " + getIdentificacion());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Velocidad Maxima: " + getVelocidadMaxima());
        System.out.println("Tiempo Acumulado: " + getTiempoAcomulado());
        
    }

    
    
}
