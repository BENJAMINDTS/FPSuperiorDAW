package composiciónmultiple;

public class Carroceria {
    private String color;

   

    private TipoCarroceria tipo;

    public Carroceria(String color, TipoCarroceria tipo) {
        this.color = color;
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public TipoCarroceria getTipo() {
        return tipo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTipo(TipoCarroceria tipo) {
        this.tipo = tipo;
    }

}
enum TipoCarroceria {
    Independiente, Autoportante, Tubular
}