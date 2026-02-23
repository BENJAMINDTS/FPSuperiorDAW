package composiciónmultiple;

/**
 * Clase que representa una llanta de un vehículo, con características
 * específicas
 * como marca, diámetro del rin, ancho y altura.
 * Esta clase forma parte de la composición múltiple en la que un vehículo puede
 * tener varios componentes, como motor, ruedas y chasis, cada uno con sus
 * propias
 * características y funcionalidades.
 * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class Llanta {
    private String marca;
    private int diametroRin;
    private int ancho;
    private int altura;

    /**
     * Constructor que inicializa las características de la llanta.
     * 
     * @param marca       La marca de la llanta.
     * @param diametroRin El diámetro del rin en pulgadas.
     * @param ancho       El ancho de la llanta en milímetros.
     * @param altura      La altura de la llanta en porcentaje.
     */
    public Llanta(String marca, int diametroRin, int ancho, int altura) {
        this.marca = marca;
        this.diametroRin = diametroRin;
        this.ancho = ancho;
        this.altura = altura;
    }

    /**
     * Obtiene la marca de la llanta.
     * 
     * @return La marca de la llanta.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Obtiene el diámetro del rin de la llanta.
     * 
     * @return El diámetro del rin en pulgadas.
     */
    public int getDiametroRin() {
        return diametroRin;
    }

    /**
     * Obtiene el ancho de la llanta.
     * 
     * @return El ancho de la llanta en milímetros.
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Obtiene la altura de la llanta.
     * 
     * @return La altura de la llanta en porcentaje.
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Establece la marca de la llanta.
     * 
     * @param marca La nueva marca de la llanta.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Establece el diámetro del rin de la llanta.
     * 
     * @param diametroRin El nuevo diámetro del rin en pulgadas.
     */
    public void setDiametroRin(int diametroRin) {
        this.diametroRin = diametroRin;
    }

    /**
     * Establece el ancho de la llanta.
     * 
     * @param ancho El nuevo ancho de la llanta en milímetros.
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * Establece la altura de la llanta.
     * 
     * @param altura La nueva altura de la llanta en porcentaje.
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

}
