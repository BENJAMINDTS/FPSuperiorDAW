package seguridad_básica_en_apis_extra;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce la URL base de la API (ej. https://api.coingecko.com/api/v3): ");
        String baseUrl = scanner.nextLine();

        System.out.print("Introduce el endpoint (ej. /ping): ");
        String endpoint = scanner.nextLine();

        System.out.print("Tipo de autenticación (1 = Bearer, 2 = API Key): ");
        int authType = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        String headerName = (authType == 1) ? "Authorization" : "X-API-Key";

        System.out.print("Introduce tu token o clave API: ");
        String token = scanner.nextLine();

        if (authType == 1) token = "Bearer " + token;

        
        ApiClient client = new ApiClient(baseUrl, endpoint, headerName, token);
        client.makeGetRequest();
    }
}
