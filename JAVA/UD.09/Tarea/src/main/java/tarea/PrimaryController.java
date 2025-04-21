package tarea;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrimaryController {

    @FXML
    private TextField TFCodigo;
    @FXML
    private TextField TFNombre;
    @FXML
    private TextField TFManager;
    @FXML
    private TextField TFLocalizacion;

    @FXML
    private TextArea TALista;
    @FXML
    private Button BBuscar;
    @FXML
    private Button BMostrar;
    @FXML
    private Button BInsertar;
    @FXML
    private Button BBorrar;
    @FXML
    private Button BSalir;
    @FXML
    private Button BExportar;
    @FXML
    private Button BModificar;


    DAO dao = new DAO();
    private Departamento Departamento;
    private List<Departamento> Departamentos = new ArrayList<>();

    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public void initialize() {



        InsertarOpacity();

        TFCodigo.textProperty().addListener((observable, oldValue, newValue) -> {

            InsertarOpacity();
        });

        TFNombre.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFManager.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFLocalizacion.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());

    }

    private void actualizarListaDepartamentos(List<Departamento> Departamentos) {
        StringBuilder sb = new StringBuilder();
        for (Departamento Departamento : Departamentos) {
            sb.append("Código: ").append(Departamento.getCodigo()).append("\n");
            sb.append("Nombre: ").append(Departamento.getNombre()).append("\n");
            sb.append("localizacion: ").append(Departamento.getIdLocalizacion()).append("\n");
            sb.append("manager: ").append(decimalFormat.format(Departamento.getIdManager())).append("\n");

        }
        TALista.setText(sb.toString());
    }

    @FXML
    private void InsertarOpacity() {
        boolean isEmpty = TFCodigo.getText().trim().isEmpty() || TFNombre.getText().trim().isEmpty()
                || TFManager.getText().trim().isEmpty() || TFLocalizacion.getText().trim().isEmpty();
        BInsertar.setDisable(isEmpty);
        BInsertar.setOpacity(isEmpty ? 0.5 : 1.0);
    }

    @FXML
    private void handleSalir() {
        System.exit(0);
    }

    @FXML
    private void handleInsertar() {
        try {
            String codigoTexto = TFCodigo.getText().trim();
            if (codigoTexto.isEmpty()) {
                mostrarAlerta("Código Vacío", "Por favor, ingresa un código.",
                        "El campo de código no puede estar vacío.", Alert.AlertType.WARNING);
                return;
            }


            int codigo = TFCodigo.getText().trim().length() > 0 ? Integer.parseInt(codigoTexto) : 0;
            if (codigo <= 0) {
                mostrarAlerta("Código Inválido", "Código Incorrecto",
                        "El código debe ser un número entero positivo.", Alert.AlertType.ERROR);
                return;
            }
            String nombre = TFNombre.getText().trim();
            int localizacion = Integer.parseInt(TFLocalizacion.getText().trim());
            int manager = Integer.parseInt(TFManager.getText().trim());




            Departamento Departamento = new Departamento(codigo, nombre, localizacion, manager);

            dao.save(Departamento); // Guardar el Departamento en la base de datos

            TFCodigo.clear();
            TFNombre.clear();
            TFLocalizacion.clear();
            TFManager.clear();


            System.out.println("Departamento insertado: " + Departamento.getNombre());
            imprimirDepartamento(Departamento);

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Datos Inválidos",
                    "Por favor, ingresa valores numéricos válidos para código, localizacion y manager.",
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleMostrar() {
        // Limpiar el TextArea antes de mostrar los Departamentos
        TALista.clear();
        dao.mostrarDepartamentos().forEach(Departamento -> TALista.appendText(Departamento.toString() + "\n"));

    }

@FXML
private void handleBuscar() {
    String codigoTexto = TFCodigo.getText().trim();
    String nombreTexto = TFNombre.getText().trim();
    
    if (codigoTexto.isEmpty() && nombreTexto.isEmpty()) {
        mostrarAlerta("Error", "Campos vacíos", 
                     "Debe ingresar un código o nombre para buscar", 
                     Alert.AlertType.WARNING);
        return;
    }

    try {
        if (!codigoTexto.isEmpty()) {
            buscarPorCodigo(codigoTexto);
        } else {
            buscarPorNombre(nombreTexto);
        }
    } catch (NumberFormatException e) {
        mostrarAlerta("Error", "Formato incorrecto", 
                     "El código debe ser un número válido", 
                     Alert.AlertType.ERROR);
    }
}

private void buscarPorCodigo(String codigoTexto) {
    int codigo = Integer.parseInt(codigoTexto);
    if (codigo <= 0) {
        mostrarAlerta("Error", "Código inválido", 
                     "El código debe ser mayor que cero", 
                     Alert.AlertType.ERROR);
        return;
    }
    
    Departamento departamento = dao.findById(codigo);
    if (departamento == null) {
        mostrarAlerta("No encontrado", "Departamento no existe", 
                     "No se encontró el departamento con código: " + codigo, 
                     Alert.AlertType.WARNING);
    } else {
        mostrarModalDepartamento(departamento);
    }
}

private void buscarPorNombre(String nombreTexto) {
    List<Departamento> resultados = dao.findByNombre(nombreTexto);
    
    if (resultados.isEmpty()) {
        mostrarAlerta("No encontrado", "Búsqueda sin resultados", 
                     "No hay departamentos con ese nombre", 
                     Alert.AlertType.WARNING);
    } else if (resultados.size() == 1) {
        mostrarModalDepartamento(resultados.get(0));
    } else {
        Optional<Departamento> seleccionado = mostrarDialogoSeleccion(resultados);
        seleccionado.ifPresent(this::mostrarModalDepartamento);
    }
}

private void mostrarModalDepartamento(Departamento departamento) {
    // Crear la ventana modal
    Stage modal = new Stage();
    modal.initModality(Modality.APPLICATION_MODAL);
    modal.setTitle("Detalles del Departamento");
    
    // Crear el contenido
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20));
    grid.setHgap(10);
    grid.setVgap(10);
    
    // Agregar los datos del departamento
    grid.add(new Label("Código:"), 0, 0);
    grid.add(new Label(String.valueOf(departamento.getCodigo())), 1, 0);
    
    grid.add(new Label("Nombre:"), 0, 1);
    grid.add(new Label(departamento.getNombre()), 1, 1);
    
    grid.add(new Label("Localización:"), 0, 2);
    grid.add(new Label(String.valueOf(departamento.getIdLocalizacion())), 1, 2);
    
    grid.add(new Label("Manager:"), 0, 3);
    grid.add(new Label(String.valueOf(departamento.getIdManager())), 1, 3);
    
    // Botón para cerrar
    Button cerrarBtn = new Button("Cerrar");
    cerrarBtn.setOnAction(e -> modal.close());
    grid.add(cerrarBtn, 1, 4);
    
    // Configurar y mostrar la escena
    Scene scene = new Scene(grid, 300, 200);
    modal.setScene(scene);
    modal.showAndWait();
}

private Optional<Departamento> mostrarDialogoSeleccion(List<Departamento> departamentos) {
    ChoiceDialog<Departamento> dialog = new ChoiceDialog<>(departamentos.get(0), departamentos);
    dialog.setTitle("Múltiples resultados");
    dialog.setHeaderText("Se encontraron varios departamentos");
    dialog.setContentText("Seleccione el departamento deseado:");
    return dialog.showAndWait();
}

@FXML
private void handleBorrar() {
    String codigoTexto = TFCodigo.getText().trim();
    
    if (codigoTexto.isEmpty()) {
        mostrarAlerta("Error", "Campo vacío", 
                     "Ingrese un código para eliminar", 
                     Alert.AlertType.WARNING);
        return;
    }

    try {
        int codigo = Integer.parseInt(codigoTexto);
        if (codigo <= 0) {
            mostrarAlerta("Error", "Código inválido", 
                         "El código debe ser positivo", 
                         Alert.AlertType.ERROR);
            return;
        }

        Optional<ButtonType> resultado = mostrarConfirmacion(
            "Confirmar eliminación", 
            "¿Está seguro de eliminar el departamento con código " + codigo + "?"
        );

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            boolean eliminado = dao.delete(codigo);
            if (eliminado) {
                limpiarCampos();
                mostrarAlerta("Éxito", "Departamento eliminado", 
                            "El departamento fue eliminado correctamente", 
                            Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo eliminar", 
                            "El departamento no existe o no pudo ser eliminado", 
                            Alert.AlertType.ERROR);
            }
        }
    } catch (NumberFormatException e) {
        mostrarAlerta("Error", "Formato inválido", 
                     "El código debe ser un número válido", 
                     Alert.AlertType.ERROR);
    }
}

private void limpiarCampos() {
    TFCodigo.clear();
    TFNombre.clear();
    TFLocalizacion.clear();
    TFManager.clear();
}

private Optional<ButtonType> mostrarConfirmacion(String titulo, String mensaje) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    return alert.showAndWait();
}



    @FXML
    private void handleModificar() {

        String nombre = TFNombre.getText().trim();
        int localizacion = Integer.parseInt(TFLocalizacion.getText().trim());
        int manager = Integer.parseInt(TFManager.getText().trim());

        Departamento DepartamentoAModificar = new Departamento(Departamento.getCodigo(), nombre, localizacion, manager);

        dao.update(DepartamentoAModificar); // Actualizar el Departamento en la base de datos

        System.out.println("Departamento modificado: " + DepartamentoAModificar.getNombre());
        imprimirDepartamento(DepartamentoAModificar);
        actualizarListaDepartamentos(Departamentos);

    }

    private void imprimirDepartamento(Departamento Departamento) {
        System.out.println("Código: " + Departamento.getCodigo());
        System.out.println("Nombre: " + Departamento.getNombre());
        System.out.println("localizacion: " + Departamento.getIdLocalizacion());
        System.out.println("manager: " + decimalFormat.format(Departamento.getIdManager()));
        System.out.println("--------------------------------------------------");
    }

    private void mostrarAlerta(String titulo, String encabezado, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    // Método para guardar la lista de Departamentos en un archivo XML

}