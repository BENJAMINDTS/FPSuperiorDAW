package composiciónmultiple;

public class Llanta {
    private String marca;
    private int diametroRin;
    private int ancho;
    private int altura;

    public Llanta(String marca, int diametroRin, int ancho, int altura) {
        this.marca = marca;
        this.diametroRin = diametroRin;
        this.ancho = ancho;
        this.altura = altura;
    }

    public String getMarca() {
        return marca;
    }

    public int getDiametroRin() {
        return diametroRin;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAltura() {
        return altura;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setDiametroRin(int diametroRin) {
        this.diametroRin = diametroRin;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

}
