package clasesabstractas;

/**
 * Clase abstracta que define la estructura base para cualquier tipo de animal.
 * Esta clase no puede ser instanciada directamente y obliga a sus subclases
 * a implementar métodos específicos de comportamiento y entorno.
 * * @author BenjaminDTS
 * @version 1.0
 */
public abstract class Animal {

    /** Representa la onomatopeya o descripción del sonido que emite el animal */
    protected String sonido;

    /** Describe el tipo de dieta o comida que consume el animal */
    protected String alimentos;

    /** Indica el entorno natural donde reside la especie */
    protected String habitat;

    /** Almacena la nomenclatura taxonómica oficial (Género y especie) */
    protected String nombreCientifico;

    /**
     * Obtiene el sonido característico del animal.
     * @return Una cadena de texto con el sonido.
     */
    public abstract String getSonido();

    /**
     * Obtiene el tipo de alimentación del animal.

     * @return Una cadena de texto con el tipo de alimentos.
     */
    public abstract String getAlimentos();

    /**
     * Obtiene el hábitat natural del animal.

     * @return Una cadena de texto con la descripción del hábitat.
     */
    public abstract String getHabitat();

    /**
     * Obtiene el nombre científico de la especie.
     * @return Una cadena de texto con el nombre científico.
     */
    public abstract String getNombreCientifico();

}