package lectura_de_json_con_gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class App {

	// Custom exception for JSON retrieval errors
	public static class JsonRetrievalException extends Exception {
		public JsonRetrievalException(String message, Throwable cause) {
			super(message, cause);
		}
	}

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

			// 4. Imprimir publicaciones
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
