package tarea;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

    private List<Producto> productos = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public void initialize() {
        try {
            productos = fileHandler.readProducts();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        ComprobarOpacity();
        InsertarOpacity();
        MostrarOpacity();
        actualizarListaProductos(); // Mostrar productos al iniciar

        TFCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
            ComprobarOpacity();
            InsertarOpacity();
        });

        TFNombre.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFPrecio.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFCantidad.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFDescripcion.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TALista.textProperty().addListener((observable, oldValue, newValue) -> MostrarOpacity());
    }

    @FXML
    private void ComprobarOpacity() {
        boolean isCodigoEmpty = TFCodigo.getText().trim().isEmpty();
        BBuscar.setDisable(isCodigoEmpty);
        BBuscar.setOpacity(isCodigoEmpty ? 0.5 : 1.0);

        boolean isBorrarDisabled = productos.isEmpty();
        BBorrar.setDisable(isBorrarDisabled);
        BBorrar.setOpacity(isBorrarDisabled ? 0.5 : 1.0);
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
    private void MostrarOpacity() {
        boolean isEmpty = TALista.getText().trim().isEmpty();
        BMostrar.setDisable(isEmpty);
        BMostrar.setOpacity(isEmpty ? 0.5 : 1.0);
    }

    @FXML
    private void BSalir() {
        System.exit(0);
    }

    @FXML
    private void handleInsertar() {
        try {
            int codigo = Integer.parseInt(TFCodigo.getText().trim());
            String nombre = TFNombre.getText().trim();
            int cantidad = Integer.parseInt(TFCantidad.getText().trim());
            double precio = Double.parseDouble(TFPrecio.getText().trim());
            String descripcion = TFDescripcion.getText().trim();

            Producto producto = new Producto(codigo, nombre, cantidad, precio, descripcion);
            productos.add(producto);
            fileHandler.writeProducts(productos);

            TFCodigo.clear();
            TFNombre.clear();
            TFCantidad.clear();
            TFPrecio.clear();
            TFDescripcion.clear();

            System.out.println("Producto insertado: " + producto.getNombre());
            imprimirProducto(producto);
            actualizarListaProductos();
            ComprobarOpacity(); // Actualizar la opacidad del botón Borrar
        } catch (NumberFormatException | IOException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
        }
    }

    @FXML
    private void handleMostrar() {
        actualizarListaProductos();
    }

    private void actualizarListaProductos() {
        StringBuilder lista = new StringBuilder();
        for (Producto producto : productos) {
            lista.append("Código: ").append(producto.getCodigo()).append("\n")
                 .append("Nombre: ").append(producto.getNombre()).append("\n")
                 .append("Cantidad: ").append(producto.getCantidad()).append("\n")
                 .append("Precio: ").append(decimalFormat.format(producto.getPrecio())).append("\n")
                 .append("Descripción: ").append(producto.getDescripcion()).append("\n\n");
        }
        TALista.setText(lista.toString());
    }

    @FXML
    private void handleBuscar() {
        try {
            int codigo = Integer.parseInt(TFCodigo.getText().trim());
            for (Producto producto : productos) {
                if (producto.getCodigo() == codigo) {
                    TFNombre.setText(producto.getNombre());
                    TFCantidad.setText(String.valueOf(producto.getCantidad()));
                    TFPrecio.setText(decimalFormat.format(producto.getPrecio()));
                    TFDescripcion.setText(producto.getDescripcion());
                    return;
                }
            }
            System.out.println("Producto no encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
        }
    }

    @FXML
    private void handleBorrar() {
        try {
            int codigo = Integer.parseInt(TFCodigo.getText().trim());
            Producto productoAEliminar = null;
            for (Producto producto : productos) {
                if (producto.getCodigo() == codigo) {
                    productoAEliminar = producto;
                    break;
                }
            }
            if (productoAEliminar != null) {
                productos.remove(productoAEliminar);
                fileHandler.writeProducts(productos);
                actualizarListaProductos();
                System.out.println("Producto borrado: " + productoAEliminar.getNombre());
                ComprobarOpacity(); // Actualizar la opacidad del botón Borrar
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Error al borrar el producto: " + e.getMessage());
        }
    }

    private void imprimirProducto(Producto producto) {
        System.out.println("Detalles del Producto:");
        System.out.println("Código: " + producto.getCodigo());
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Cantidad: " + producto.getCantidad());
        System.out.println("Precio: " + decimalFormat.format(producto.getPrecio()));
        System.out.println("Descripción: " + producto.getDescripcion());
    }
}