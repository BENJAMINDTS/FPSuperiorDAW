package composiciónmultiple;
/**
 * Clase que representa un coche, compuesto por una carrocería, un chasis, un
 * motor y cuatro llantas.
 * Esta clase utiliza la composición múltiple para integrar diferentes componentes
 * que conforman un vehículo completo, cada uno con sus propias características
 * y funcionalidades.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Coche {
    
    private Carroceria carroceria;
    private Chasis chasis;
    private Motor motor;
    private Llanta[] llantas;

    /**
     * Constructor que inicializa un coche con sus componentes principales.
     * @param color El color de la carrocería del coche.
     * @param tipoCarroceria El tipo de carrocería del coche.
     * @param tipoChasis El tipo de chasis del coche.
     * @param volumen El volumen del motor del coche en centímetros cúbicos (cc).
     * @param marcaLlanta La marca de las llantas del coche.
     * @param diametroRin El diámetro del rin de las llantas en pulgadas.
     * @param ancho El ancho de las llantas en milímetros.
     * @param altura La altura de las llantas en porcentaje.
     */
    public Coche(String color, TipoCarroceria tipoCarroceria, TipoChasis tipoChasis, int volumen, String marcaLlanta,
            int diametroRin, int ancho, int altura) {
        this.carroceria = new Carroceria(color, tipoCarroceria);
        this.chasis = new Chasis(tipoChasis);
        this.motor = new Motor(volumen);
        this.llantas = new Llanta[4];
        for (int i = 0; i < llantas.length; i++) {
            llantas[i] = new Llanta(marcaLlanta, diametroRin, ancho, altura);
        }
    }

    /**
     * Obtiene la carrocería del coche.
     * @return La carrocería del coche.
     */
    public Carroceria getCarroceria() {
        return carroceria;
    }

    /**
     * Establece la carrocería del coche.
     * @param carroceria La carrocería a asignar al coche.
     */
    public void setCarroceria(Carroceria carroceria) {
        this.carroceria = carroceria;
    }

    /**
     * Obtiene el chasis del coche.
     * @return El chasis del coche.
     */
    public Chasis getChasis() {
        return chasis;
    }

    /**
     * Establece el chasis del coche.
     * @param chasis El chasis a asignar al coche.
     */
    public void setChasis(Chasis chasis) {
        this.chasis = chasis;
    }

    /**
     * Obtiene el motor del coche.
     * @return El motor del coche.
     */
    public Motor getMotor() {
        return motor;
    }

    /**
     * Establece el motor del coche.
     * @param motor El motor a asignar al coche.
     */
    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    /**
    * Obtiene las llantas del coche.
    * @return Un arreglo de llantas del coche.
    */
    public Llanta[] getLlantas() {
        return llantas;
    }
    /**
     * Establece las llantas del coche.
     * @param llantas Un arreglo de llantas a asignar al coche.
     */
    public void setLlantas(Llanta[] llantas) {
        this.llantas = llantas;
    }

    /**
     * Método estático para imprimir las características del coche por consola.
     * @param coche El coche cuyas características se desean imprimir.
     */
    public static void imprimirCoche(Coche coche) {
        System.out.println("Carroceria: " + coche.getCarroceria().getColor() + " " + coche.getCarroceria().getTipo());
        System.out.println("Chasis: " + coche.getChasis().getTipo());
        System.out.println("Motor: " + coche.getMotor().getVolumen());
        
        System.out.println("Llanta " + ": " + coche.getLlantas()[0].getMarca() + " " + coche.getLlantas()[0].getDiametroRin() + " " + coche.getLlantas()[0].getAncho() + " " + coche.getLlantas()[0].getAltura());
    }

}
