package tarea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo tarea.fxml
        scene = new Scene(loadFXML("tarea"), 1280, 960);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // Asegúrate de que la ruta sea correcta
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/tarea/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
