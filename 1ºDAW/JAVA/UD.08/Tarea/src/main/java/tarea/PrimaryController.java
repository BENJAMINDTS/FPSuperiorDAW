package tarea;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tarea.Producto.Categoria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @FXML
    private ChoiceBox<Categoria> choiceBoxCategoria;

    DAO dao = new DAO();
    private Producto producto;
    private List<Producto> productos = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public void initialize() {
        

        // Cargar categorías en el ChoiceBox desde el enum
        choiceBoxCategoria.getItems().setAll(Categoria.values());

        
        InsertarOpacity();


        TFCodigo.textProperty().addListener((observable, oldValue, newValue) -> {

            InsertarOpacity();
        });

        TFNombre.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFPrecio.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFCantidad.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFDescripcion.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
    }

    private void actualizarListaProductos(List<Producto> productos) {
        StringBuilder sb = new StringBuilder();
        for (Producto producto : productos) {
            sb.append("Código: ").append(producto.getCodigo()).append("\n");
            sb.append("Nombre: ").append(producto.getNombre()).append("\n");
            sb.append("Cantidad: ").append(producto.getCantidad()).append("\n");
            sb.append("Precio: ").append(decimalFormat.format(producto.getPrecio())).append("\n");
            sb.append("Descripción: ").append(producto.getDescripcion()).append("\n");
            sb.append("Categoría: ").append(producto.getCategoria().name()).append("\n\n");
        }
        TALista.setText(sb.toString());
    }


    


    @FXML
    private void InsertarOpacity() {
        boolean isEmpty = TFCodigo.getText().trim().isEmpty() || TFNombre.getText().trim().isEmpty()
                || TFPrecio.getText().trim().isEmpty() || TFCantidad.getText().trim().isEmpty()
                || TFDescripcion.getText().trim().isEmpty();
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
            if (!codigoTexto.matches("[A-Z]{2,3}\\d+")) {
                mostrarAlerta("Código Inválido", "Formato de Código Incorrecto", "El código debe tener dos o tres letras mayúsculas seguido de un número entero.", Alert.AlertType.ERROR);
                return;
            }

            String codigo = TFCodigo.getText().trim();
            String nombre = TFNombre.getText().trim();
            int cantidad = Integer.parseInt(TFCantidad.getText().trim());
            double precio = Double.parseDouble(TFPrecio.getText().trim());
            String descripcion = TFDescripcion.getText().trim();
            Categoria categoria = choiceBoxCategoria.getValue();

            if (categoria == null) {
                mostrarAlerta("Categoría No Seleccionada", "Selecciona una Categoría", "Por favor, selecciona una categoría para el producto.", Alert.AlertType.ERROR);
                return;
            }

            Producto producto = new Producto(codigo, nombre, cantidad, precio, descripcion, categoria);
            
            dao.save(producto); // Guardar el producto en la base de datos

            TFCodigo.clear();
            TFNombre.clear();
            TFCantidad.clear();
            TFPrecio.clear();
            TFDescripcion.clear();
            choiceBoxCategoria.setValue(null);

            System.out.println("Producto insertado: " + producto.getNombre());
            imprimirProducto(producto);


        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Datos Inválidos", "Por favor, ingresa valores numéricos válidos para código, cantidad y precio.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleMostrar() {
        // Limpiar el TextArea antes de mostrar los productos
        TALista.clear();
        dao.mostrarProductos().forEach(producto-> TALista.appendText(producto.toString()+ "\n"));

    }

   

    @FXML
    private void handleBuscar() {
        String codigoTexto = TFCodigo.getText().trim(); 
        if (codigoTexto.isEmpty()) {
            mostrarAlerta("Código Vacío", "Por favor, ingresa un código para buscar.", "El campo de código no puede estar vacío.", Alert.AlertType.WARNING);
        } else {
            try {
                producto=dao.findById(codigoTexto);
                if (producto != null) {
                    TFNombre.setText(producto.getNombre());
                    TFCantidad.setText(String.valueOf(producto.getCantidad()));
                    TFPrecio.setText(String.valueOf(producto.getPrecio()));
                    TFDescripcion.setText(producto.getDescripcion());
                    choiceBoxCategoria.setValue(producto.getCategoria());
                } else {
                    mostrarAlerta("Producto No Encontrado", "No se encontró ningún producto con el código especificado.", "Por favor, verifica el código e inténtalo de nuevo.", Alert.AlertType.WARNING);
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Error de Formato", "Código Inválido", "Por favor, ingresa un código numérico válido.", Alert.AlertType.ERROR);
            }
            
        }
    }



    @FXML
    private void handleBorrar() {
        try {
            String codigo = TFCodigo.getText().trim();

            dao.delete(codigo); // Eliminar el producto de la base de datos
            productos = dao.mostrarProductos(); // Actualizar la lista de productos desde la base de datos
            actualizarListaProductos(productos);
            TFCodigo.clear();
            TFNombre.clear();
            TFCantidad.clear();
            TFPrecio.clear();
            TFDescripcion.clear();
            choiceBoxCategoria.setValue(null);
            mostrarAlerta("Producto Eliminado", "Producto Eliminado Correctamente", "El producto ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Código Inválido", "Por favor, ingresa un código numérico válido para borrar un producto.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleModificar() {

            String nombre = TFNombre.getText().trim();
            int cantidad = Integer.parseInt(TFCantidad.getText().trim());
            double precio = Double.parseDouble(TFPrecio.getText().trim());
            String descripcion = TFDescripcion.getText().trim();
            Categoria categoria = choiceBoxCategoria.getValue();

            if (categoria == null) {
                mostrarAlerta("Categoría No Seleccionada", "Selecciona una Categoría", "Por favor, selecciona una categoría para el producto.", Alert.AlertType.ERROR);
                return;
            }

            Producto productoAModificar = new Producto(producto.getCodigo(), nombre, cantidad, precio, descripcion, categoria);

            dao.update(productoAModificar); // Actualizar el producto en la base de datos

            System.out.println("Producto modificado: " + productoAModificar.getNombre());
            imprimirProducto(productoAModificar);
            actualizarListaProductos(productos);
        
    }

    private void imprimirProducto(Producto producto) {
        System.out.println("Código: " + producto.getCodigo());
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Cantidad: " + producto.getCantidad());
        System.out.println("Precio: " + decimalFormat.format(producto.getPrecio()));
        System.out.println("Descripción: " + producto.getDescripcion());
        System.out.println("Categoría: " + producto.getCategoria().name());
    }

    private void mostrarAlerta(String titulo, String encabezado, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Métodos para manejar eventos de categorías
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

    // Método auxiliar para mostrar productos de una categoría específica
    private void mostrarProductosPorCategoria(Categoria categoria) {
        List<Producto> productosFiltrados = productos.stream()
                .filter(producto -> producto.getCategoria() == categoria)
                .collect(Collectors.toList());

        if (productosFiltrados.isEmpty()) {
            mostrarAlerta("Categoría Vacía", "No hay productos en la categoría " + categoria.name(),
                    "No se encontraron productos en esta categoría.", Alert.AlertType.INFORMATION);
        } else {
            actualizarListaProductos(productosFiltrados);
        }
    }

    // Método para guardar la lista de productos en un archivo XML
    
    
}