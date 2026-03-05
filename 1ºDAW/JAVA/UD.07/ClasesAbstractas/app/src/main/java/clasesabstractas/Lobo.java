package clasesabstractas;

/**
 * Clase concreta que representa a un Lobo, una subclase de Canido.
 * Esta clase proporciona la implementación específica para los métodos
 * abstractos definidos en la clase base Animal, caracterizando al lobo
 * silvestre.
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Lobo extends Canido {

    /**
     * Define la forma de comunicación vocal característica del lobo.
     * @return Cadena "Aullido".
     */
    @Override
    public String getSonido() {
        return "Aullido";
    }

    /**
     * Describe el régimen alimenticio principal de la especie en libertad.
     * @return Cadena "Carnivoro".
     */
    @Override
    public String getAlimentos() {
        return "Carnivoro";
    }

    /**
     * Especifica el ecosistema natural donde habita esta especie.
     * @return Cadena "Bosques".
     */
    @Override
    public String getHabitat() {
        return "Bosques";
    }

    /**
     * Proporciona la nomenclatura científica oficial para el lobo.
     * @return Cadena "Canis lupus".
     */
    @Override
    public String getNombreCientifico() {
        return "Canis lupus";
    }

}