package clasesabstractas;

/**
 * Clase concreta que representa a un gato doméstico, extendiendo la familia de
 * los Felinos.
 * Esta clase cierra la cadena de herencia implementando el comportamiento final
 * y las características biológicas de la especie.
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Gato extends Felino {

    /**
     * Define el sonido característico del gato doméstico.
     * @return Cadena "Maullido".
     */
    @Override
    public String getSonido() {
        return "Maullido";
    }

    /**
     * Describe la dieta principal del gato en un entorno natural o instintivo.
     * @return Cadena "Ratones".
     */
    @Override
    public String getAlimentos() {
        return "Ratones";
    }

    /**
     * Especifica el entorno donde reside habitualmente esta especie.
     * @return Cadena "Domestico".
     */
    @Override
    public String getHabitat() {
        return "Domestico";
    }

    /**
     * Proporciona la nomenclatura taxonómica oficial para el gato.
     * @return Cadena "Felis silvestris catus".
     */
    @Override
    public String getNombreCientifico() {
        return "Felis silvestris catus";
    }

}