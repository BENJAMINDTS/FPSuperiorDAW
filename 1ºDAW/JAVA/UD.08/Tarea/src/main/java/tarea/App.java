package tarea;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación que inicializa el entorno de JavaFX. Se
 * encarga de cargar la interfaz gráfica desde el archivo FXML y configurar el
 * escenario principal.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class App extends Application {

    /**
     * Contenedor principal de la escena gráfica
     */
    private static Scene scene;

    /**
     * Punto de entrada para la aplicación JavaFX. Carga el diseño FXML
     * principal y establece las dimensiones iniciales de la ventana.
     *
     * * @param stage El escenario principal proporcionado por la plataforma
     * JavaFX.
     * @throws IOException Si el archivo FXML especificado no puede ser
     * encontrado o cargado.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("tarea"), 1024, 720);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Carga un archivo FXML dinámicamente desde el directorio de recursos.
     *
     * * @param fxml El nombre del archivo FXML (sin la extensión .fxml).
     * @return El nodo raíz (Parent) cargado desde el archivo.
     * @throws IOException Si ocurre un error de lectura o el archivo no existe.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/tarea/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método principal estándar de Java que lanza la aplicación JavaFX.
     *
     * * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
