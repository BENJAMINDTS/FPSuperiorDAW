package com.mycompany.clasesbenjaminsantiagogonzalez;

/**
 * Define una entidad que representa a una persona con nombre y edad. Incluye
 * validaciones para asegurar que la edad se mantenga en un rango lógico.
 *
 * * @author BenjaminDTS
 */
public class NombreApellidos {

    /**
     * @var int edad Almacena la edad de la persona
     */
    private int edad;

    /**
     * @var String nombre Almacena el nombre completo de la persona
     */
    private String nombre;

    /**
     * Constructor por defecto. Inicializa el objeto con los datos del autor
     * BenjaminDTS.
     */
    public NombreApellidos() {
        this.nombre = "Benjamin Santiago Gonzalez";
        this.edad = 22;
        validarEstado();
    }

    /**
     * Constructor con parámetros.
     *
     * * @param nombre Nombre de la persona.
     * @param edad Edad de la persona (Debe estar entre 0 y 100).
     */
    public NombreApellidos(String nombre, int edad) {
        this.edad = edad;
        this.nombre = nombre;
        validarEstado();
    }

    /**
     * Método privado de apoyo para centralizar la validación de la edad. Si la
     * edad es inválida, se marcan los campos como PERSONA ERRONEA.
     */
    private void validarEstado() {
        if (this.edad > 100 || this.edad < 0) {
            this.edad = 0;
            this.nombre = "PERSONA ERRONEA";
        }
    }

    /**
     * Establece la edad de la persona y valida el rango.
     *
     * * @param edad Valor entero para la edad.
     */
    public void setedad(int edad) {
        this.edad = edad;
        validarEstado();
    }

    /**
     * Establece el nombre de la persona.
     *
     * * @param nombre Cadena de texto con el nombre.
     */
    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad de la persona.
     *
     * * @return int La edad actual.
     */
    public int getedad() {
        return edad;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * * @return String El nombre actual.
     */
    public String getnombre() {
        return nombre;
    }

    /**
     * Método estático auxiliar que describe la funcionalidad de la clase.
     */
    public static void ayuda() {
        System.out.println("Esta clase permite crear personas con un nombre y una edad.");
    }
}
