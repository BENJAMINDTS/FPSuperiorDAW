package tarea;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal encargada de iniciar el entorno gráfico de la aplicación.
 * Extiende de Application para heredar las capacidades del framework JavaFX.
 * * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class App extends Application {

	/** Contenedor principal de la interfaz visual (escena) */
	private static Scene scene;

	/**
	 * Punto de entrada principal para todas las aplicaciones JavaFX.
	 * Se ejecuta después de que el sistema ha sido preparado por el método
	 * launch().
	 * * * @param stage El escenario principal (ventana) sobre el que se colocará la
	 * escena.
	 * 
	 * @throws IOException Si ocurre un error al intentar cargar el archivo FXML.
	 */
	@Override
	public void start(Stage stage) throws IOException {
		/**
		 * * Inicialización de la escena:
		 * Se carga el diseño desde el archivo 'tarea.fxml' y se definen las
		 * dimensiones predeterminadas de la ventana (1280x960 píxeles).
		 */
		scene = new Scene(loadFXML("tarea"), 1280, 960);

		// Asignación de la escena a la ventana y visualización
		stage.setScene(scene);
		stage.setTitle("Sistema de Gestión - BenjaminDTS");
		stage.show();
	}

	/**
	 * Localiza y carga un archivo FXML para convertirlo en un objeto de la
	 * jerarquía de nodos.
	 * * * @param fxml Nombre del archivo FXML (sin la extensión .fxml).
	 * 
	 * @return Parent El nodo raíz del diseño cargado.
	 * @throws IOException Si el recurso no se encuentra en la ruta especificada.
	 */
	private static Parent loadFXML(String fxml) throws IOException {
		/**
		 * FXMLLoader se encarga de instanciar los componentes definidos en el XML
		 * y de vincularlos con su clase controladora correspondiente.
		 */
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/tarea/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	/**
	 * Método de lanzamiento estándar de Java.
	 * Delega la ejecución al método launch() de la clase Application.
	 * * * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		launch();
	}
}