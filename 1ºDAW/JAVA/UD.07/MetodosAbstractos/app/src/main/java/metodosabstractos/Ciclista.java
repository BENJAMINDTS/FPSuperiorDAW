package metodosabstractos;

public abstract class Ciclista {
    private int identificacion;
    private String nombre;
    private int tiempoAcomulado;

    public Ciclista(int identificacion, String nombre) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.tiempoAcomulado = 0;
    }
    public abstract String imprimirTipo();

    protected int getTiempoAcomulado() {
        return tiempoAcomulado;
    }

    protected void setTiempoAcomulado(int tiempoAcomulado) {
        this.tiempoAcomulado = tiempoAcomulado;
    }

    protected int getIdentificacion() {
        return identificacion;
    }

    protected void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    protected String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
