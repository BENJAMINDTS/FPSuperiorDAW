package lectura_de_json_con_gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Clase principal encargada de la ingesta, deserialización y cruce de datos
 * JSON. Consume endpoints públicos (JSONPlaceholder) para obtener publicaciones
 * y usuarios, procesándolos mediante la librería Gson de Google.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class App {

    /**
     * Excepción personalizada para gestionar errores durante la recuperación de
     * datos JSON a través de peticiones HTTP.
     *
     * * @author BenjaminDTS
     */
    public static class JsonRetrievalException extends Exception {

        /**
         * Construye una nueva excepción con un mensaje detallado y la causa
         * raíz del error.
         *
         * * @param message El mensaje descriptivo del error.
         * @param cause La causa subyacente (excepción original) que desencadenó
         * este error.
         */
        public JsonRetrievalException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Punto de entrada de la aplicación. Orquesta la ejecución de peticiones
     * HTTP, la deserialización de listas de objetos y el cruce relacional en
     * memoria (mapeo) entre Publicaciones (Posts) y Autores (Usuarios).
     *
     * * @param args Argumentos de la línea de comandos (no utilizados en esta
     * implementación).
     */
    public static void main(String[] args) {
        try {
            // 1. Obtener JSON de Posts
            String postsJson = getJsonFromUrl("https://jsonplaceholder.typicode.com/posts");
            Type postListType = new TypeToken<List<Post>>() {
            }.getType();
            List<Post> posts = new Gson().fromJson(postsJson, postListType);

            // 2. Obtener JSON de Usuarios
            String usuariosJson = getJsonFromUrl("https://jsonplaceholder.typicode.com/users");
            Type userListType = new TypeToken<List<Usuario>>() {
            }.getType();
            List<Usuario> usuarios = new Gson().fromJson(usuariosJson, userListType);

            // 3. Mapeamos userId → Usuario
            Map<Integer, Usuario> usuarioMap = new HashMap<>();
            for (Usuario u : usuarios) {
                usuarioMap.put(u.getId(), u);
            }

            // 4. Imprimir publicaciones cruzadas con sus autores
            for (Post post : posts) {
                Usuario autor = usuarioMap.get(post.getUserId());
                System.out.println("Post #" + post.getId());
                System.out.println("Título: " + post.getTitle());
                System.out.println("Cuerpo: " + post.getBody());
                if (autor != null) {
                    System.out.println("Autor: " + autor.getName() + " (" + autor.getEmail() + ")");
                }
                System.out.println("-----");
            }

        } catch (JsonRetrievalException e) {
            System.err.println("Error al obtener JSON: " + e.getMessage());
        }
    }

    /**
     * Realiza una petición HTTP GET a una URL específica y devuelve la
     * respuesta en texto plano.
     *
     * * @param urlStr La dirección URL del endpoint que provee el JSON.
     * @return Un String que contiene la estructura JSON en crudo obtenida del
     * servidor.
     * @throws JsonRetrievalException Si ocurre un error de conexión, lectura o
     * formato de URL.
     */
    private static String getJsonFromUrl(String urlStr) throws JsonRetrievalException {
        try {
            URL url = URI.create(urlStr).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder json = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    json.append(line);
                }
                return json.toString();
            }
        } catch (Exception e) {
            throw new JsonRetrievalException("Failed to retrieve JSON from URL: " + urlStr, e);
        }
    }
}
