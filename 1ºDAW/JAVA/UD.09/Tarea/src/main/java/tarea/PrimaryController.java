package tarea;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

/**
 * Controlador principal de la interfaz de usuario JavaFX para la gestión de
 * Departamentos. Procesa los eventos de la vista y coordina las llamadas a la
 * capa de acceso a datos (DAO).
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
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
    private Button BBuscar, BMostrar, BInsertar, BBorrar, BSalir, BExportar, BModificar;

    /**
     * Instancia del DAO para ejecutar operaciones en la base de datos MariaDB.
     */
    DAO dao = new DAO();

    /**
     * Objeto Departamento en memoria usado temporalmente para modificaciones.
     */
    private Departamento Departamento;

    /**
     * Lista caché local de departamentos cargados desde la base de datos.
     */
    private List<Departamento> Departamentos = new ArrayList<>();

    /**
     * Formateador numérico utilizado para presentar valores enteros en la
     * interfaz.
     */
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    /**
     * Método de inicialización llamado automáticamente por JavaFX tras la carga
     * del FXML. Configura los listeners en los campos de texto para actualizar
     * dinámicamente el estado de los botones.
     */
    public void initialize() {
        InsertarOpacity();

        TFCodigo.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFNombre.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFManager.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFLocalizacion.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
    }

    /**
     * Vuelca la lista proporcionada de departamentos en el área de texto
     * (TextArea) de la interfaz.
     *
     * * @param Departamentos La colección de objetos Departamento a mostrar.
     */
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

    /**
     * Verifica el contenido de los campos del formulario para habilitar o
     * deshabilitar visual y lógicamente el botón de inserción.
     */
    @FXML
    private void InsertarOpacity() {
        boolean isEmpty = TFCodigo.getText().trim().isEmpty() || TFNombre.getText().trim().isEmpty()
                || TFManager.getText().trim().isEmpty() || TFLocalizacion.getText().trim().isEmpty();
        BInsertar.setDisable(isEmpty);
        BInsertar.setOpacity(isEmpty ? 0.5 : 1.0);
    }

    /**
     * Finaliza de forma abrupta la ejecución completa de la aplicación.
     */
    @FXML
    private void handleSalir() {
        System.exit(0);
    }

    /**
     * Captura la información del formulario, la valida y guarda un nuevo
     * Departamento mediante el DAO.
     */
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
            dao.save(Departamento);

            limpiarCampos();

            System.out.println("Departamento insertado: " + Departamento.getNombre());
            imprimirDepartamento(Departamento);

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Datos Inválidos",
                    "Por favor, ingresa valores numéricos válidos para código, localizacion y manager.",
                    Alert.AlertType.ERROR);
        }
    }

    /**
     * Recupera todos los departamentos desde la base de datos y los expone en
     * la vista principal.
     */
    @FXML
    private void handleMostrar() {
        TALista.clear();
        dao.mostrarDepartamentos().forEach(Departamento -> TALista.appendText(Departamento.toString() + "\n"));
    }

    /**
     * Decide si la búsqueda se realizará por código numérico exacto o por
     * coincidencia de nombre.
     */
    @FXML
    private void handleBuscar() {
        String codigoTexto = TFCodigo.getText().trim();
        String nombreTexto = TFNombre.getText().trim();

        if (codigoTexto.isEmpty() && nombreTexto.isEmpty()) {
            mostrarAlerta("Error", "Campos vacíos",
                    "Debe ingresar un código o nombre para buscar", Alert.AlertType.WARNING);
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
                    "El código debe ser un número válido", Alert.AlertType.ERROR);
        }
    }

    /**
     * Realiza una búsqueda exacta en base de datos utilizando la Primary Key.
     *
     * * @param codigoTexto El string numérico con el código a buscar.
     */
    private void buscarPorCodigo(String codigoTexto) {
        int codigo = Integer.parseInt(codigoTexto);
        if (codigo <= 0) {
            mostrarAlerta("Error", "Código inválido", "El código debe ser mayor que cero", Alert.AlertType.ERROR);
            return;
        }

        Departamento departamento = dao.findById(codigo);
        if (departamento == null) {
            mostrarAlerta("No encontrado", "Departamento no existe",
                    "No se encontró el departamento con código: " + codigo, Alert.AlertType.WARNING);
        } else {
            mostrarModalDepartamento(departamento);
        }
    }

    /**
     * Realiza una búsqueda flexible basada en coincidencias de texto sobre el
     * nombre del departamento. Si encuentra múltiples resultados, levanta un
     * diálogo de selección.
     *
     * * @param nombreTexto El texto a buscar dentro de los nombres
     * registrados.
     */
    private void buscarPorNombre(String nombreTexto) {
        List<Departamento> resultados = dao.findByNombre(nombreTexto);

        if (resultados.isEmpty()) {
            mostrarAlerta("No encontrado", "Búsqueda sin resultados",
                    "No hay departamentos con ese nombre", Alert.AlertType.WARNING);
        } else if (resultados.size() == 1) {
            mostrarModalDepartamento(resultados.get(0));
        } else {
            Optional<Departamento> seleccionado = mostrarDialogoSeleccion(resultados);
            seleccionado.ifPresent(this::mostrarModalDepartamento);
        }
    }

    /**
     * Genera y despliega una ventana modal (pop-up) que exhibe los detalles
     * concretos de un departamento.
     *
     * * @param departamento El objeto cuyas propiedades serán expuestas.
     */
    private void mostrarModalDepartamento(Departamento departamento) {
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.setTitle("Detalles del Departamento");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Código:"), 0, 0);
        grid.add(new Label(String.valueOf(departamento.getCodigo())), 1, 0);

        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(new Label(departamento.getNombre()), 1, 1);

        grid.add(new Label("Localización:"), 0, 2);
        grid.add(new Label(String.valueOf(departamento.getIdLocalizacion())), 1, 2);

        grid.add(new Label("Manager:"), 0, 3);
        grid.add(new Label(String.valueOf(departamento.getIdManager())), 1, 3);

        Button cerrarBtn = new Button("Cerrar");
        cerrarBtn.setOnAction(e -> modal.close());
        grid.add(cerrarBtn, 1, 4);

        Scene scene = new Scene(grid, 300, 200);
        modal.setScene(scene);
        modal.showAndWait();
    }

    /**
     * Despliega un cuadro de diálogo permitiendo al usuario elegir un
     * departamento de una lista de coincidencias.
     *
     * * @param departamentos Lista de opciones encontradas en la búsqueda.
     * @return Un objeto Optional que envuelve la selección final del usuario,
     * si la hubo.
     */
    private Optional<Departamento> mostrarDialogoSeleccion(List<Departamento> departamentos) {
        ChoiceDialog<Departamento> dialog = new ChoiceDialog<>(departamentos.get(0), departamentos);
        dialog.setTitle("Múltiples resultados");
        dialog.setHeaderText("Se encontraron varios departamentos");
        dialog.setContentText("Seleccione el departamento deseado:");
        return dialog.showAndWait();
    }

    /**
     * Verifica intenciones y ejecuta la orden de borrado de un departamento
     * basándose en el código ingresado.
     */
    @FXML
    private void handleBorrar() {
        String codigoTexto = TFCodigo.getText().trim();

        if (codigoTexto.isEmpty()) {
            mostrarAlerta("Error", "Campo vacío", "Ingrese un código para eliminar", Alert.AlertType.WARNING);
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoTexto);
            if (codigo <= 0) {
                mostrarAlerta("Error", "Código inválido", "El código debe ser positivo", Alert.AlertType.ERROR);
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
                            "El departamento fue eliminado correctamente", Alert.AlertType.INFORMATION);
                } else {
                    mostrarAlerta("Error", "No se pudo eliminar",
                            "El departamento no existe o no pudo ser eliminado", Alert.AlertType.ERROR);
                }
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Formato inválido", "El código debe ser un número válido", Alert.AlertType.ERROR);
        }
    }

    /**
     * Borra visualmente todo el contenido alojado en las cajas de texto de la
     * interfaz.
     */
    private void limpiarCampos() {
        TFCodigo.clear();
        TFNombre.clear();
        TFLocalizacion.clear();
        TFManager.clear();
    }

    /**
     * Lanza una ventana de confirmación genérica exigiendo validación manual
     * del usuario.
     *
     * * @param titulo Texto de encabezado de la ventana.
     * @param mensaje Pregunta o afirmación a validar.
     * @return El botón que el usuario cliqueó encapsulado en un Optional.
     */
    private Optional<ButtonType> mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        return alert.showAndWait();
    }

    /**
     * Procesa los datos alojados en los textfields y emite una orden de
     * actualización a base de datos del departamento previamente seleccionado
     * en memoria.
     */
    @FXML
    private void handleModificar() {
        String nombre = TFNombre.getText().trim();
        int localizacion = Integer.parseInt(TFLocalizacion.getText().trim());
        int manager = Integer.parseInt(TFManager.getText().trim());

        // Asegúrate de que la variable "Departamento" haya sido cargada previamente (ej. por handleBuscar)
        Departamento DepartamentoAModificar = new Departamento(Departamento.getCodigo(), nombre, localizacion, manager);
        dao.update(DepartamentoAModificar);

        System.out.println("Departamento modificado: " + DepartamentoAModificar.getNombre());
        imprimirDepartamento(DepartamentoAModificar);
        actualizarListaDepartamentos(Departamentos);
    }

    /**
     * Imprime un volcado estructurado por la terminal de un departamento
     * específico.
     *
     * * @param Departamento El objeto a imprimir en consola.
     */
    private void imprimirDepartamento(Departamento Departamento) {
        System.out.println("Código: " + Departamento.getCodigo());
        System.out.println("Nombre: " + Departamento.getNombre());
        System.out.println("localizacion: " + Departamento.getIdLocalizacion());
        System.out.println("manager: " + decimalFormat.format(Departamento.getIdManager()));
        System.out.println("--------------------------------------------------");
    }

    /**
     * Generador genérico de alertas y notificaciones visuales emergentes.
     *
     * * @param titulo Título de la ventana emergente.
     * @param encabezado Cabecera resaltada del mensaje.
     * @param mensaje Texto descriptivo de la acción o error.
     * @param tipo El icono y modalidad (WARNING, ERROR, INFORMATION, etc.).
     */
    private void mostrarAlerta(String titulo, String encabezado, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
