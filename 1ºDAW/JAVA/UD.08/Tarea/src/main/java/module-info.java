module tarea {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


 

    
    opens tarea to javafx.fxml, org.hibernate.orm.core;
    exports tarea;
}
