import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpClientBasico {

  private static final Logger LOGGER = Logger.getLogger(HttpClientBasico.class.getName());

  public static void main(String[] args) {
    // Cambia esta URL para probar otros casos
    String urlString = "https://jsonplaceholder.typicode.com/posts";

    try {
      URI uri = URI.create(urlString);
      URL url = uri.toURL();

      HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
      conexion.setRequestMethod("GET");

      int status = conexion.getResponseCode();
      System.out.println("Código de estado HTTP: " + status + " " + conexion.getResponseMessage());

      System.out.println("\n=== Cabeceras de respuesta ===");
      Map<String, List<String>> headers = conexion.getHeaderFields();
      for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
        String clave = (entry.getKey() != null) ? entry.getKey() : "Status";
        System.out.println(clave + ": " + entry.getValue());
      }

      BufferedReader reader;
      if (status >= 200 && status < 400) {
        reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
      } else {
        reader = new BufferedReader(new InputStreamReader(conexion.getErrorStream()));
      }

      System.out.println("\n=== Cuerpo de la respuesta ===");
      String linea;
      StringBuilder contenido = new StringBuilder();
      while ((linea = reader.readLine()) != null) {
        contenido.append(linea).append("\n");
      }
      reader.close();

      System.out.println(contenido.toString());

      // Reacción según código de estado
      if (status >= 400 && status < 500) {
        System.out.println("Error del cliente (4xx). Verifica la URL o permisos.");
      } else if (status >= 500) {
        System.out.println("Error del servidor (5xx). El servidor encontró un problema.");
      }

      // Cerrar conexión
    } catch (IllegalArgumentException e) {
      LOGGER.log(Level.SEVERE, "La URL no tiene un formato válido: " + e.getMessage(), e);
    } catch (MalformedURLException e) {
      LOGGER.log(Level.SEVERE, "URL mal formada: " + e.getMessage(), e);
    } catch (UnknownHostException e) {
      LOGGER.log(Level.SEVERE, "No se pudo resolver el nombre del host: " + e.getMessage(), e);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error de E/S al realizar la petición: " + e.getMessage(), e);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Ocurrió un error inesperado: " + e.getMessage(), e);
    }
  }
}
