package seguridad_básica_en_apis;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Cliente HTTP diseñado para interactuar con APIs RESTful de forma segura.
 * Utiliza la biblioteca OkHttp para construir y ejecutar peticiones web,
 * facilitando la inyección de tokens de seguridad (como Bearer tokens o API
 * Keys) en las cabeceras de cada solicitud.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class ApiClient {

    /**
     * * Instancia del cliente HTTP subyacente (OkHttpClient). Se recomienda
     * instanciarlo una sola vez para reutilizar el pool de conexiones.
     */
    private final OkHttpClient client = new OkHttpClient();

    /**
     * URL raíz o base de la API a la que se conectará el cliente.
     */
    private final String baseUrl;

    /**
     * Nombre de la cabecera HTTP utilizada para la autenticación (ej.
     * "Authorization" o "X-API-Key").
     */
    private final String authHeaderName;

    /**
     * Valor del token de seguridad o credencial que autoriza las transacciones.
     */
    private final String authToken;

    /**
     * Constructor principal que inicializa el cliente con las credenciales y la
     * ruta base.
     *
     * * @param baseUrl La URL raíz de la API (por ejemplo,
     * "https://api.dominio.com/v1").
     * @param authHeaderName El nombre de la cabecera donde se inyectará el
     * token de seguridad.
     * @param authToken El token de acceso, clave API o credencial Bearer.
     */
    public ApiClient(String baseUrl, String authHeaderName, String authToken) {
        this.baseUrl = baseUrl;
        this.authHeaderName = authHeaderName;
        this.authToken = authToken;
    }

    /**
     * Construye y ejecuta una petición HTTP GET hacia un endpoint específico,
     * inyectando automáticamente la cabecera de autorización configurada en el
     * constructor. Además, gestiona y captura los códigos de estado HTTP
     * relacionados con la seguridad (401 y 403).
     *
     * * @param endpoint La ruta final o recurso específico a consultar (ej.
     * "/usuarios/perfil").
     */
    public void makeGetRequest(String endpoint) {
        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .header(authHeaderName, authToken)
                .build();

        // El uso de try-with-resources garantiza que la respuesta se cierre automáticamente, evitando fugas de memoria.
        try (Response response = client.newCall(request).execute()) {
            int status = response.code();
            System.out.println("HTTP Status: " + status);

            if (status == 401) {
                System.out.println("Error: Unauthorized. Token is missing or invalid.");
            } else if (status == 403) {
                System.out.println("Error: Forbidden. You do not have permission.");
            } else {
                System.out.println("Headers: " + response.headers());
                // Se extrae el cuerpo de la respuesta en formato String de manera segura.
                if (response.body() != null) {
                    System.out.println("Response Body:\n" + response.body().string());
                }
            }
        } catch (IOException e) {
            System.out.println("Network Error: " + e.getMessage());
        }
    }

    /**
     * Punto de entrada principal para realizar una prueba funcional del
     * cliente. Ejecuta una simulación contra un endpoint público (CoinGecko)
     * enviando un token ficticio.
     *
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Simulación con CoinGecko (sin autenticación real, pero simulamos header)
        String baseUrl = "https://api.coingecko.com/api/v3";
        String endpoint = "/ping";

        // Simulación de Bearer token (puedes cambiar por "X-API-Key" y un valor)
        String headerName = "Authorization";
        String token = "Bearer FAKE_TOKEN";

        ApiClient apiClient = new ApiClient(baseUrl, headerName, token);
        apiClient.makeGetRequest(endpoint);
    }
}
