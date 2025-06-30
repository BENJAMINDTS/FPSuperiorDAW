
package uso_de_bibliotecas_http_modernas_okhttp;

import okhttp3.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class App {

	public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient();

		HttpUrl url = HttpUrl.parse("https://jsonplaceholder.typicode.com/posts")
				.newBuilder()
				.addQueryParameter("userId", "1")
				.build();

		Request request = new Request.Builder()
				.url(url)
				.addHeader("User-Agent", "Java OkHttp Client")
				.addHeader("X-Curso-Java", "ETL2025")
				.build();

		try (Response response = client.newCall(request).execute()) {
			System.out.println("Código de estado: " + response.code());

			System.out.println("Encabezados de respuesta:");
			Headers headers = response.headers();
			for (String name : headers.names()) {
				System.out.println(name + ": " + headers.get(name));
			}

			String jsonResponse = response.body().string();
			System.out.println("\nJSON crudo:\n" + jsonResponse);

			Gson gson = new Gson();
			Type postListType = new TypeToken<List<Post>>() {
			}.getType();
			List<Post> posts = gson.fromJson(jsonResponse, postListType);

			System.out.println("\nLista de objetos Post:");
			for (Post post : posts) {
				System.out.println(post);
			}

		} catch (IOException e) {
			System.err.println("Error en la petición: " + e.getMessage());
		}
	}
}
