package composiciónmultiple;

public class Chasis {
   
    private TipoChasis tipo;

    public Chasis(TipoChasis tipo) {
        this.tipo = tipo;
    }

    public TipoChasis getTipo() {
        return tipo;
    }

    public void setTipo(TipoChasis tipo) {
        this.tipo = tipo;
    }

}
enum TipoChasis {
    Independiente, Monocasco
}
