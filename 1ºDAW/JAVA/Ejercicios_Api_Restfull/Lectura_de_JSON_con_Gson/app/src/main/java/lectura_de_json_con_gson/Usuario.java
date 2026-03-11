package lectura_de_json_con_gson;

/**
 * Entidad modelo (POJO) que representa a un autor o Usuario del sistema. Sus
 * atributos se mapean directamente desde el JSON proveniente de la API externa.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Usuario {

    /**
     * Identificador numérico único del usuario en el sistema.
     */
    private int id;

    /**
     * Nombre completo o seudónimo del usuario.
     */
    private String name;

    /**
     * Dirección de correo electrónico de contacto del usuario.
     */
    private String email;

    /**
     * Obtiene el identificador único del usuario.
     *
     * * @return El ID numérico del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * * @param id El nuevo ID numérico a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * * @return Una cadena de texto con el nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre completo del usuario.
     *
     * * @param name La cadena de texto con el nuevo nombre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la dirección de correo electrónico del usuario.
     *
     * * @return Una cadena de texto con el email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la dirección de correo electrónico del usuario.
     *
     * * @param email La cadena de texto con el nuevo email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
