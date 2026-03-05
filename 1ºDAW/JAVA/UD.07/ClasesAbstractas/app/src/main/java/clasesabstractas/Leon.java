package clasesabstractas;

/**
 * Clase concreta que representa a un León, una subclase especializada de
 * Felino.
 * Implementa los métodos abstractos de la clase base Animal para definir las
 * características propias del "Rey de la selva".
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Leon extends Felino {

    /**
     * Define la vocalización potente característica de los grandes felinos del
     * género Panthera.
     * @return Cadena "Rugido".
     */
    @Override
    public String getSonido() {
        return "Rugido";
    }

    /**
     * Especifica el régimen alimenticio del león.
     * @return Cadena "Carnivoro".
     */
    @Override
    public String getAlimentos() {
        return "Carnivoro";
    }

    /**
     * Describe el ecosistema principal donde se desarrolla la vida de esta especie.
     * @return Cadena "Savanas".
     */
    @Override
    public String getHabitat() {
        return "Savanas";
    }

    /**
     * Proporciona la denominación biológica binominal del león.
     * @return Cadena "Panthera leo".
     */
    @Override
    public String getNombreCientifico() {
        return "Panthera leo";
    }

}