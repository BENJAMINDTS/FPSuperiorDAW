package clasesabstractas;

/**
 * Clase concreta que representa a un Perro doméstico, subclase de Canido.
 * Esta clase finaliza la rama de los cánidos implementando las características
 * específicas del perro común, diferenciándolo de su pariente silvestre, el
 * lobo.
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Perro extends Canido {

    /**
     * Define la vocalización típica del perro doméstico.
     * @return Cadena "Ladrido".
     */
    @Override
    public String getSonido() {
        return "Ladrido";
    }

    /**
     * Describe la dieta base del perro.
     * @return Cadena "Carnivoro".
     */
    @Override
    public String getAlimentos() {
        return "Carnivoro";
    }

    /**
     * Especifica el entorno de convivencia habitual de esta subespecie.
     * @return Cadena "Domestico".
     */
    @Override
    public String getHabitat() {
        return "Domestico";
    }

    /**
     * Proporciona la nomenclatura científica completa para el perro doméstico.
     * @return Cadena "Canis lupus familiaris".
     */
    @Override
    public String getNombreCientifico() {
        return "Canis lupus familiaris";
    }

}