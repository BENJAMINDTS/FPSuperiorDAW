package seguridad_básica_en_apis_extra;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Cliente HTTP dinámico para interactuar con APIs RESTful. A diferencia de
 * clientes estáticos, esta clase permite modificar sus parámetros (URL,
 * endpoint y credenciales) en tiempo de ejecución mediante sus métodos
 * mutadores (setters).
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class ApiClient {

    /**
     * Instancia del cliente HTTP de OkHttp, inicializada en el constructor.
     */
    private final OkHttpClient client;

    /**
     * URL raíz o base de la API destino.
     */
    private String baseUrl;

    /**
     * Ruta o recurso específico a consultar dentro de la API.
     */
    private String endpoint;

    /**
     * Nombre de la cabecera HTTP utilizada para enviar la credencial (ej.
     * "Authorization").
     */
    private String headerName;

    /**
     * Valor de la credencial o token de acceso.
     */
    private String token;

    /**
     * Constructor principal que inicializa el cliente HTTP y establece los
     * parámetros de conexión iniciales.
     *
     * * @param baseUrl La URL raíz de la API (por ejemplo,
     * "https://api.dominio.com/v3").
     * @param endpoint El recurso específico a solicitar (por ejemplo, "/ping").
     * @param headerName El nombre de la cabecera de autenticación.
     * @param token El valor del token o clave de acceso.
     */
    public ApiClient(String baseUrl, String endpoint, String headerName, String token) {
        this.client = new OkHttpClient();
        this.baseUrl = baseUrl;
        this.endpoint = endpoint;
        this.headerName = headerName;
        this.token = token;
    }

    /**
     * Actualiza el valor del token de autenticación.
     *
     * * @param newToken El nuevo token o clave de acceso a utilizar en futuras
     * peticiones.
     */
    public void setToken(String newToken) {
        this.token = newToken;
    }

    /**
     * Actualiza el recurso o ruta específica a consultar.
     *
     * * @param newEndpoint El nuevo endpoint de la API (debe comenzar con
     * "/").
     */
    public void setEndpoint(String newEndpoint) {
        this.endpoint = newEndpoint;
    }

    /**
     * Actualiza el nombre de la cabecera HTTP destinada a la autenticación.
     *
     * * @param newHeader El nuevo nombre de la cabecera (ej. "X-API-Key").
     */
    public void setHeaderName(String newHeader) {
        this.headerName = newHeader;
    }

    /**
     * Actualiza la URL base o raíz de la API.
     *
     * * @param newBaseUrl La nueva URL principal del servidor destino.
     */
    public void setBaseUrl(String newBaseUrl) {
        this.baseUrl = newBaseUrl;
    }

    /**
     * Construye y ejecuta una petición HTTP GET utilizando los parámetros
     * actuales del objeto. Procesa la respuesta del servidor mediante una
     * estructura switch para evaluar los códigos de estado HTTP más comunes
     * (200, 400, 401, 403) y emite el resultado por consola.
     */
    public void makeGetRequest() {
        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .header(headerName, token)
                .build();

        // Bloque try-with-resources para asegurar el cierre automático de la conexión HTTP
        try (Response response = client.newCall(request).execute()) {
            int status = response.code();
            System.out.println("HTTP Status: " + status);

            // Evaluación del código de estado utilizando switch expressions (Java 14+)
            switch (status) {
                case 200 -> {
                    System.out.println("✅ OK");
                    System.out.println("Headers:\n" + response.headers());
                    if (response.body() != null) {
                        System.out.println("Response Body:\n" + response.body().string());
                    }
                }
                case 401 ->
                    System.out.println("Unauthorized (401): Token incorrecto o ausente.");
                case 403 ->
                    System.out.println("Forbidden (403): No tienes permisos.");
                case 400 ->
                    System.out.println("Bad Request (400): Petición mal formada.");
                default ->
                    System.out.println("Código inesperado: " + status);
            }
        } catch (IOException e) {
            System.out.println("Error de red: " + e.getMessage());
        }
    }
}
