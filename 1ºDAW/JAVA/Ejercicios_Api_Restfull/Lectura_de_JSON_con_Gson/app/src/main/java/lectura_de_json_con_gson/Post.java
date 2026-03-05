package lectura_de_json_con_gson;

/**
 * Entidad modelo (POJO) que representa una publicación o artículo (Post). Sus
 * atributos están diseñados para coincidir exactamente con las claves del JSON
 * devuelto por la API JSONPlaceholder para facilitar su deserialización.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Post {

    /**
     * Identificador numérico del usuario (autor) que creó la publicación.
     */
    private int userId;

    /**
     * Identificador numérico único de la publicación.
     */
    private int id;

    /**
     * Título o encabezado de la publicación.
     */
    private String title;

    /**
     * Contenido o cuerpo de texto de la publicación.
     */
    private String body;

    /**
     * Obtiene el identificador del usuario creador de la publicación.
     *
     * * @return El ID numérico del usuario.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Establece el identificador del usuario creador de la publicación.
     *
     * * @param userId El ID numérico del usuario a asignar.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Obtiene el identificador único de la publicación.
     *
     * * @return El ID numérico de la publicación.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la publicación.
     *
     * * @param id El nuevo ID numérico a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el título de la publicación.
     *
     * * @return Una cadena de texto con el título.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título de la publicación.
     *
     * * @param title La cadena de texto que representa el nuevo título.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el contenido o cuerpo principal de la publicación.
     *
     * * @return Una cadena de texto con el contenido.
     */
    public String getBody() {
        return body;
    }

    /**
     * Establece el contenido o cuerpo principal de la publicación.
     *
     * * @param body La cadena de texto con el nuevo contenido.
     */
    public void setBody(String body) {
        this.body = body;
    }
}
