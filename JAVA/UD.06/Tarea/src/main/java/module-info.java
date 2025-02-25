module tarea {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens tarea to javafx.fxml;
    exports tarea;
}