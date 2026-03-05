package tarea;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tarea.Producto.Categoria;

/**
 * Controlador principal de la interfaz de usuario JavaFX. Intermedia entre los
 * eventos visuales generados por el usuario y la capa de acceso a datos (DAO).
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
    private TextField TFPrecio;
    @FXML
    private TextField TFCantidad;
    @FXML
    private TextField TFDescripcion;
    @FXML
    private TextArea TALista;
    @FXML
    private Button BBuscar, BMostrar, BInsertar, BBorrar, BSalir, BExportar, BModificar;
    @FXML
    private ChoiceBox<Categoria> choiceBoxCategoria;

    /**
     * Objeto DAO encargado de la persistencia
     */
    DAO dao = new DAO();

    /**
     * Producto actualmente seleccionado o gestionado en memoria
     */
    private Producto producto;

    /**
     * Lista caché de productos para mostrar en la interfaz
     */
    private List<Producto> productos = new ArrayList<>();

    /**
     * Formateador para mostrar el precio monetario de forma consistente
     */
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    /**
     * Método de inicialización automática llamado por JavaFX tras cargar el
     * FXML. Configura el selector de categorías y establece listeners reactivos
     * en los campos de texto.
     */
    @FXML
    public void initialize() {
        choiceBoxCategoria.getItems().setAll(Categoria.values());
        InsertarOpacity();

        TFCodigo.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFNombre.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFPrecio.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFCantidad.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFDescripcion.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
    }

    /**
     * Formatea y vuelca una lista de productos en el componente visual
     * TextArea.
     *
     * * @param productos Lista de productos a visualizar.
     */
    private void actualizarListaProductos(List<Producto> productos) {
        StringBuilder sb = new StringBuilder();
        for (Producto prod : productos) {
            sb.append("Código: ").append(prod.getCodigo()).append("\n");
            sb.append("Nombre: ").append(prod.getNombre()).append("\n");
            sb.append("Cantidad: ").append(prod.getCantidad()).append("\n");
            sb.append("Precio: ").append(decimalFormat.format(prod.getPrecio())).append("\n");
            sb.append("Descripción: ").append(prod.getDescripcion()).append("\n");
            sb.append("Categoría: ").append(prod.getCategoria().name()).append("\n\n");
        }
        TALista.setText(sb.toString());
    }

    /**
     * Habilita o deshabilita el botón de insertar y ajusta su opacidad
     * dependiendo de si los campos obligatorios del formulario están vacíos.
     */
    @FXML
    private void InsertarOpacity() {
        boolean isEmpty = TFCodigo.getText().trim().isEmpty() || TFNombre.getText().trim().isEmpty()
                || TFPrecio.getText().trim().isEmpty() || TFCantidad.getText().trim().isEmpty()
                || TFDescripcion.getText().trim().isEmpty();
        BInsertar.setDisable(isEmpty);
        BInsertar.setOpacity(isEmpty ? 0.5 : 1.0);
    }

    /**
     * Cierra completamente la aplicación de manera segura.
     */
    @FXML
    private void handleSalir() {
        System.exit(0);
    }

    /**
     * Captura los datos del formulario, valida el formato y persiste un nuevo
     * producto. Gestiona notificaciones visuales mediante alertas en caso de
     * error.
     */
    @FXML
    private void handleInsertar() {
        try {
            String codigoTexto = TFCodigo.getText().trim();
            if (!codigoTexto.matches("[A-Z]{2,3}\\d+")) {
                mostrarAlerta("Código Inválido", "Formato Incorrecto", "Debe tener 2 o 3 letras mayúsculas seguidas de un número.", Alert.AlertType.ERROR);
                return;
            }

            Categoria categoria = choiceBoxCategoria.getValue();
            if (categoria == null) {
                mostrarAlerta("Categoría requerida", "Selecciona una Categoría", "Seleccione una categoría válida.", Alert.AlertType.ERROR);
                return;
            }

            Producto nuevoProducto = new Producto(
                    codigoTexto, TFNombre.getText().trim(),
                    Integer.parseInt(TFCantidad.getText().trim()),
                    Double.parseDouble(TFPrecio.getText().trim()),
                    TFDescripcion.getText().trim(), categoria);

            dao.save(nuevoProducto);
            limpiarCampos();
            System.out.println("Producto insertado: " + nuevoProducto.getNombre());

        } catch (NumberFormatException e) {
            mostrarAlerta("Error numérico", "Datos Inválidos", "Revise cantidad y precio.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Obtiene y muestra todos los productos almacenados en la base de datos.
     */
    @FXML
    private void handleMostrar() {
        TALista.clear();
        dao.mostrarProductos().forEach(p -> TALista.appendText(p.toString() + "\n"));
    }

    /**
     * Busca un producto por código y rellena el formulario con sus datos si
     * existe.
     */
    @FXML
    private void handleBuscar() {
        String codigoTexto = TFCodigo.getText().trim();
        if (codigoTexto.isEmpty()) {
            mostrarAlerta("Código Vacío", "Ingrese un código", "No puede estar vacío.", Alert.AlertType.WARNING);
            return;
        }
        producto = dao.findById(codigoTexto);
        if (producto != null) {
            TFNombre.setText(producto.getNombre());
            TFCantidad.setText(String.valueOf(producto.getCantidad()));
            TFPrecio.setText(String.valueOf(producto.getPrecio()));
            TFDescripcion.setText(producto.getDescripcion());
            choiceBoxCategoria.setValue(producto.getCategoria());
        } else {
            mostrarAlerta("No encontrado", "Búsqueda fallida", "Verifica el código.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Elimina el producto asociado al código ingresado en el formulario.
     */
    @FXML
    private void handleBorrar() {
        String codigo = TFCodigo.getText().trim();
        dao.delete(codigo);
        productos = dao.mostrarProductos();
        actualizarListaProductos(productos);
        limpiarCampos();
        mostrarAlerta("Eliminado", "Correcto", "El producto ha sido eliminado.", Alert.AlertType.INFORMATION);
    }

    /**
     * Modifica el producto actualmente cargado en el formulario con los nuevos
     * valores ingresados.
     */
    @FXML
    private void handleModificar() {
        Categoria categoria = choiceBoxCategoria.getValue();
        if (categoria == null) {
            mostrarAlerta("Categoría", "Selecciona Categoría", "Requerido.", Alert.AlertType.ERROR);
            return;
        }
        Producto pMod = new Producto(
                producto.getCodigo(), TFNombre.getText().trim(),
                Integer.parseInt(TFCantidad.getText().trim()),
                Double.parseDouble(TFPrecio.getText().trim()),
                TFDescripcion.getText().trim(), categoria);

        dao.update(pMod);
        actualizarListaProductos(dao.mostrarProductos());
    }

    /**
     * Limpia visualmente todos los controles del formulario
     */
    private void limpiarCampos() {
        TFCodigo.clear();
        TFNombre.clear();
        TFCantidad.clear();
        TFPrecio.clear();
        TFDescripcion.clear();
        choiceBoxCategoria.setValue(null);
    }

    /**
     * Centraliza la creación y muestra de cuadros de diálogo emergentes
     * (Alerts).
     */
    private void mostrarAlerta(String titulo, String encabezado, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Handlers para filtrar por categoría
    @FXML
    private void handleMostrarCategoriaElectronica() {
        mostrarProductosPorCategoria(Categoria.ELECTRONICA);
    }

    @FXML
    private void handleMostrarCategoriaRopa() {
        mostrarProductosPorCategoria(Categoria.ROPA);
    }

    @FXML
    private void handleMostrarCategoriaAlimentos() {
        mostrarProductosPorCategoria(Categoria.ALIMENTOS);
    }

    @FXML
    private void handleMostrarCategoriaHogar() {
        mostrarProductosPorCategoria(Categoria.HOGAR);
    }

    /**
     * Filtra la lista en memoria y muestra únicamente los productos que
     * pertenezcan a la categoría dada.
     *
     * * @param categoria La enumeración Categoria utilizada como filtro.
     */
    private void mostrarProductosPorCategoria(Categoria categoria) {
        List<Producto> filtrados = dao.mostrarProductos().stream()
                .filter(p -> p.getCategoria() == categoria)
                .collect(Collectors.toList());

        if (filtrados.isEmpty()) {
            mostrarAlerta("Vacío", "Sin productos", "No hay elementos en " + categoria.name(), Alert.AlertType.INFORMATION);
        } else {
            actualizarListaProductos(filtrados);
        }
    }
}
