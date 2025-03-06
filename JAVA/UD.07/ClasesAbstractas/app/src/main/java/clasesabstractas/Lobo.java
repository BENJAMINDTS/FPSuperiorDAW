package clasesabstractas;

public class Lobo extends Canido {


    @Override
    public String getSonido() {
        return "Aullido";
    }

    @Override
    public String getAlimentos() {
        return "Carnivoro";
    }

    @Override
    public String getHabitat() {
        return "Bosques";
    }

    @Override
    public String getNombreCientifico() {
        return "Canis lupus";
    }
    
}
