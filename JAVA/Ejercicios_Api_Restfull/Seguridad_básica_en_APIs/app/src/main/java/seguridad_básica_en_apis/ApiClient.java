package seguridad_básica_en_apis;

import okhttp3.*;

import java.io.IOException;

public class ApiClient {
	private final OkHttpClient client = new OkHttpClient();
	private final String baseUrl;
	private final String authHeaderName;
	private final String authToken;

	public ApiClient(String baseUrl, String authHeaderName, String authToken) {
		this.baseUrl = baseUrl;
		this.authHeaderName = authHeaderName;
		this.authToken = authToken;
	}

	public void makeGetRequest(String endpoint) {
		Request request = new Request.Builder()
				.url(baseUrl + endpoint)
				.header(authHeaderName, authToken)
				.build();

		try (Response response = client.newCall(request).execute()) {
			int status = response.code();
			System.out.println("HTTP Status: " + status);

			if (status == 401) {
				System.out.println("Error: Unauthorized. Token is missing or invalid.");
			} else if (status == 403) {
				System.out.println("Error: Forbidden. You do not have permission.");
			} else {
				System.out.println("Headers: " + response.headers());
				System.out.println("Response Body:\n" + response.body().string());
			}
		} catch (IOException e) {
			System.out.println("Network Error: " + e.getMessage());
		}
	}

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
