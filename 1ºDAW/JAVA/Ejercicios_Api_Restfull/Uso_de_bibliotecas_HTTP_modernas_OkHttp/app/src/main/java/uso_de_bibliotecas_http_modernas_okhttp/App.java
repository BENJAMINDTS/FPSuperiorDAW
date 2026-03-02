package uso_de_bibliotecas_http_modernas_okhttp;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase principal que demuestra el uso combinado de bibliotecas modernas en
 * Java. Utiliza OkHttp para realizar peticiones HTTP eficientes configurando
 * parámetros de consulta y cabeceras personalizadas, y emplea Gson para
 * deserializar la respuesta JSON en objetos Java nativos.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class App {

    /**
     * Método de entrada principal de la aplicación. Ejecuta el flujo completo:
     * construcción dinámica de la URL, creación de la solicitud, ejecución
     * sincrónica de la llamada HTTP, lectura de cabeceras y mapeo del cuerpo
     * JSON.
     *
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Instanciación del cliente HTTP
        OkHttpClient client = new OkHttpClient();

        // Construcción segura y dinámica de la URL con parámetros de consulta (Query Parameters)
        HttpUrl url = HttpUrl.parse("https://jsonplaceholder.typicode.com/posts")
                .newBuilder()
                .addQueryParameter("userId", "1")
                .build();

        // Creación de la solicitud HTTP (Request) inyectando cabeceras personalizadas
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Java OkHttp Client")
                .addHeader("X-Curso-Java", "ETL2025") // Ejemplo de cabecera customizada
                .build();

        // Ejecución de la solicitud dentro de un bloque try-with-resources para liberar memoria
        try (Response response = client.newCall(request).execute()) {
            System.out.println("Código de estado: " + response.code());

            // Extracción y volcado de todas las cabeceras devueltas por el servidor
            System.out.println("Encabezados de respuesta:");
            Headers headers = response.headers();
            for (String name : headers.names()) {
                System.out.println(name + ": " + headers.get(name));
            }

            // Lectura del cuerpo de la respuesta en formato de texto (JSON crudo)
            String jsonResponse = response.body().string();
            System.out.println("\nJSON crudo:\n" + jsonResponse);

            // Deserialización del JSON a una lista tipada utilizando Gson y TypeToken
            Gson gson = new Gson();
            Type postListType = new TypeToken<List<Post>>() {
            }.getType();
            List<Post> posts = gson.fromJson(jsonResponse, postListType);

            // Iteración e impresión de los objetos Java resultantes
            System.out.println("\nLista de objetos Post:");
            for (Post post : posts) {
                System.out.println(post);
            }

        } catch (IOException e) {
            System.err.println("Error en la petición: " + e.getMessage());
        }
    }
}
