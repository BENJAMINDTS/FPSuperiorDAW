package seguridad_básica_en_apis_extra;

import java.util.Scanner;

/**
 * Clase principal que actúa como interfaz interactiva por línea de comandos
 * (CLI). Permite al usuario introducir manualmente los parámetros de conexión y
 * las credenciales para probar diferentes endpoints y métodos de autenticación
 * de forma dinámica.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class App {

    /**
     * Punto de entrada de la aplicación. Despliega un menú interactivo mediante
     * Scanner para capturar la URL, el endpoint, el formato de autenticación
     * (Bearer vs API Key) y el token. Finalmente, delega la ejecución al
     * ApiClient.
     *
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce la URL base de la API (ej. https://api.coingecko.com/api/v3): ");
        String baseUrl = scanner.nextLine();

        System.out.print("Introduce el endpoint (ej. /ping): ");
        String endpoint = scanner.nextLine();

        System.out.print("Tipo de autenticación (1 = Bearer, 2 = API Key): ");
        int authType = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer de nueva línea residual

        // Determinación dinámica del nombre de la cabecera HTTP según la elección del usuario
        String headerName = (authType == 1) ? "Authorization" : "X-API-Key";

        System.out.print("Introduce tu token o clave API: ");
        String token = scanner.nextLine();

        // Si es tipo Bearer, se formatea el token añadiendo el prefijo estándar requerido
        if (authType == 1) {
            token = "Bearer " + token;
        }

        // Instanciación del cliente y ejecución de la solicitud
        ApiClient client = new ApiClient(baseUrl, endpoint, headerName, token);
        client.makeGetRequest();

        scanner.close(); // Buena práctica: cerrar el recurso Scanner al finalizar
    }
}
