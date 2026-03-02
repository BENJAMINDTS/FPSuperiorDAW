package uso_de_bibliotecas_http_modernas_okhttp;

/**
 * Entidad de dominio (POJO) que mapea la estructura de una publicación (Post).
 * Los nombres de los atributos de esta clase coinciden exactamente con las
 * claves del JSON consumido para permitir que la biblioteca Gson realice la
 * conversión de manera automática por reflexión.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class Post {

    /**
     * Identificador numérico del usuario o autor de la publicación.
     */
    private int userId;

    /**
     * Identificador numérico único de la publicación generada por el servidor.
     */
    private int id;

    /**
     * Título principal de la publicación.
     */
    private String title;

    /**
     * Contenido textual o cuerpo del mensaje de la publicación.
     */
    private String body;

    /**
     * Genera una representación en formato de cadena de texto (String) con el
     * estado actual de todos los atributos del objeto Post.
     *
     * * @return Cadena estructurada con los valores de userId, id, title y
     * body.
     */
    @Override
    public String toString() {
        return "Post{"
                + "userId=" + userId
                + ", id=" + id
                + ", title='" + title + '\''
                + ", body='" + body + '\''
                + '}';
    }
}
