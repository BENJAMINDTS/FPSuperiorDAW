package seguridad_básica_en_apis_extra;

import okhttp3.*;

import java.io.IOException;

public class ApiClient {
	private final OkHttpClient client;
	private String baseUrl;
	private String endpoint;
	private String headerName;
	private String token;

	public ApiClient(String baseUrl, String endpoint, String headerName, String token) {
		this.client = new OkHttpClient();
		this.baseUrl = baseUrl;
		this.endpoint = endpoint;
		this.headerName = headerName;
		this.token = token;
	}

	public void setToken(String newToken) {
		this.token = newToken;
	}

	public void setEndpoint(String newEndpoint) {
		this.endpoint = newEndpoint;
	}

	public void setHeaderName(String newHeader) {
		this.headerName = newHeader;
	}

	public void setBaseUrl(String newBaseUrl) {
		this.baseUrl = newBaseUrl;
	}

	public void makeGetRequest() {
		Request request = new Request.Builder()
				.url(baseUrl + endpoint)
				.header(headerName, token)
				.build();

		try (Response response = client.newCall(request).execute()) {
			int status = response.code();
			System.out.println("HTTP Status: " + status);

			switch (status) {
				case 200 -> {
					System.out.println("✅ OK");
					System.out.println("Headers:\n" + response.headers());
					System.out.println("Response Body:\n" + response.body().string());
				}
				case 401 -> System.out.println("Unauthorized (401): Token incorrecto o ausente.");
				case 403 -> System.out.println("Forbidden (403): No tienes permisos.");
				case 400 -> System.out.println("Bad Request (400): Petición mal formada.");
				default -> System.out.println("Código inesperado: " + status);
			}
		} catch (IOException e) {
			System.out.println("Error de red: " + e.getMessage());
		}
	}
}
