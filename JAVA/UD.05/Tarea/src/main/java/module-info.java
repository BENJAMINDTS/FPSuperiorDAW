module tarea {
    requires javafx.controls;
    requires javafx.fxml;

    opens tarea to javafx.fxml;
    exports tarea;
}
