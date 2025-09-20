package composiciónmultiple;




public class Coche {
    
    private Carroceria carroceria;
    private Chasis chasis;
    private Motor motor;
    private Llanta[] llantas;

    public Coche(String color, TipoCarroceria tipoCarroceria, TipoChasis tipoChasis, int volumen, String marcaLlanta, int diametroRin, int ancho, int altura) {
        this.carroceria = new Carroceria(color, tipoCarroceria);
        this.chasis = new Chasis(tipoChasis);
        this.motor = new Motor(volumen);
        this.llantas = new Llanta[4];
        for (int i = 0; i < llantas.length; i++) {
            llantas[i] = new Llanta(marcaLlanta, diametroRin, ancho, altura);
        }
    }

    public Carroceria getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(Carroceria carroceria) {
        this.carroceria = carroceria;
    }

    public Chasis getChasis() {
        return chasis;
    }

    public void setChasis(Chasis chasis) {
        this.chasis = chasis;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Llanta[] getLlantas() {
        return llantas;
    }

    public void setLlantas(Llanta[] llantas) {
        this.llantas = llantas;
    }

    public static void ImprimirCoche(Coche coche) {
        System.out.println("Carroceria: " + coche.getCarroceria().getColor() + " " + coche.getCarroceria().getTipo());
        System.out.println("Chasis: " + coche.getChasis().getTipo());
        System.out.println("Motor: " + coche.getMotor().getVolumen());  
        
        System.out.println("Llanta " + ": " + coche.getLlantas()[0].getMarca() + " " + coche.getLlantas()[0].getDiametroRin() + " " + coche.getLlantas()[0].getAncho() + " " + coche.getLlantas()[0].getAltura());
    }       

}
