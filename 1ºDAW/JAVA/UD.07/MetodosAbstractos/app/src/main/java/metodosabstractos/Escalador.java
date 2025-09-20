package metodosabstractos;

public class Escalador extends Ciclista {
    private double aceleracion;
    private double gradoRampa;

    Escalador(int identificacion, String nombre, double aceleracion, double gradoRampa) {
        super(identificacion, nombre);
        this.aceleracion = aceleracion;
        this.gradoRampa = gradoRampa;
    }

    @Override
    public String imprimirTipo() {
        return "Escalador";
    }

    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public double getGradoRampa() {
        return gradoRampa;
    }

    public void setGradoRampa(double gradoRampa) {
        this.gradoRampa = gradoRampa;
    }

    public void imprimir(){
        System.out.println(imprimirTipo());
        System.out.println("Identificacion: " + getIdentificacion());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Aceleracion: " + getAceleracion());
        System.out.println("Grado Rampa: " + getGradoRampa());
        System.out.println("Tiempo Acumulado: " + getTiempoAcomulado());
        
    }
    
}
