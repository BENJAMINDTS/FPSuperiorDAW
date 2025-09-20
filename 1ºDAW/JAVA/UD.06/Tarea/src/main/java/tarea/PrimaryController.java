package tarea;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tarea.Producto.Categoria;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
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

    private List<Producto> productos = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public void initialize() {
        try {
            productos = leerProductosDesdeXML("tienda.xml");
        } catch (Exception e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
        }

        // Cargar categorías en el ChoiceBox desde el enum
        choiceBoxCategoria.getItems().setAll(Categoria.values());

        ComprobarOpacity();
        InsertarOpacity();
        MostrarOpacity(); // Verificar si hay productos al inicio

        TFCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
            ComprobarOpacity();
            InsertarOpacity();
        });

        TFNombre.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFPrecio.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFCantidad.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
        TFDescripcion.textProperty().addListener((observable, oldValue, newValue) -> InsertarOpacity());
    }

    private List<Producto> leerProductosDesdeXML(String archivo) {
        List<Producto> productos = new ArrayList<>();
        File file = new File(archivo);

        if (!file.exists()) {
            System.out.println("El archivo " + archivo + " no existe. Se creará uno nuevo.");
            return productos;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("producto");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String codigo = element.getAttribute("codigo");
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    Categoria categoria = Categoria.valueOf(element.getElementsByTagName("categoria").item(0).getTextContent().toUpperCase());
                    int cantidad = Integer.parseInt(element.getElementsByTagName("cantidad").item(0).getTextContent());
                    double precio = Double.parseDouble(element.getElementsByTagName("precio").item(0).getTextContent());
                    String descripcion = element.getElementsByTagName("descripcion").item(0).getTextContent();

                    Producto producto = new Producto(codigo, nombre, cantidad, precio, descripcion, categoria);
                    productos.add(producto);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
        }

        return productos;
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
        boolean isProductosEmpty = productos.isEmpty();
        BMostrar.setDisable(isProductosEmpty);
        BMostrar.setOpacity(isProductosEmpty ? 0.5 : 1.0);
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
            productos.add(producto);

            TFCodigo.clear();
            TFNombre.clear();
            TFCantidad.clear();
            TFPrecio.clear();
            TFDescripcion.clear();
            choiceBoxCategoria.setValue(null);

            System.out.println("Producto insertado: " + producto.getNombre());
            imprimirProducto(producto);
            actualizarListaProductos(productos);
            ComprobarOpacity(); // Actualizar la opacidad del botón Borrar
            MostrarOpacity(); // Actualizar la opacidad del botón Mostrar
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Datos Inválidos", "Por favor, ingresa valores numéricos válidos para código, cantidad y precio.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleMostrar() {
        actualizarListaProductos(productos);
    }

    private void actualizarListaProductos(List<Producto> productos) {
        StringBuilder lista = new StringBuilder();
        for (Producto producto : productos) {
            lista.append("Código: ").append(producto.getCodigo()).append("\n")
                 .append("Nombre: ").append(producto.getNombre()).append("\n")
                 .append("Cantidad: ").append(producto.getCantidad()).append("\n")
                 .append("Precio: ").append(decimalFormat.format(producto.getPrecio())).append("\n")
                 .append("Descripción: ").append(producto.getDescripcion()).append("\n")
                 .append("Categoría: ").append(producto.getCategoria().name()).append("\n\n");
        }
        TALista.setText(lista.toString());
    }

    @FXML
    private void handleBuscar() {
        String codigoTexto = TFCodigo.getText().trim();
        String descripcionTexto = TFDescripcion.getText().trim();

        if (!codigoTexto.isEmpty()) {
            buscarPorCodigo(codigoTexto);
        } else if (!descripcionTexto.isEmpty()) {
            buscarPorDescripcion(descripcionTexto);
        } else {
            mostrarAlerta("Búsqueda Vacía", "No se ha ingresado ningún criterio de búsqueda.", "Por favor, ingresa un código o una descripción para buscar.", Alert.AlertType.WARNING);
        }
    }

    private void buscarPorCodigo(String codigoTexto) {
        try {
            String codigo = codigoTexto;
            for (Producto producto : productos) {
                if (producto.getCodigo().equals(codigo)) {
                    rellenarCamposProducto(producto);
                    return;
                }
            }
            mostrarAlerta("Producto No Encontrado", "No se encontró ningún producto con el código especificado.", "Por favor, verifica el código e inténtalo de nuevo.", Alert.AlertType.WARNING);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Código Inválido", "Por favor, ingresa un código numérico válido.", Alert.AlertType.ERROR);
        }
    }

    private void buscarPorDescripcion(String descripcionTexto) {
        for (Producto producto : productos) {
            if (producto.getDescripcion().equalsIgnoreCase(descripcionTexto)) {
                rellenarCamposProducto(producto);
                return;
            }
        }
        mostrarAlerta("Producto No Encontrado", "No se encontró ningún producto con la descripción especificada.", "Por favor, verifica la descripción e inténtalo de nuevo.", Alert.AlertType.WARNING);
    }

    private void rellenarCamposProducto(Producto producto) {
        TFCodigo.setText(String.valueOf(producto.getCodigo()));
        TFNombre.setText(producto.getNombre());
        TFCantidad.setText(String.valueOf(producto.getCantidad()));
        TFPrecio.setText(decimalFormat.format(producto.getPrecio()));
        TFDescripcion.setText(producto.getDescripcion());
    }

    @FXML
    private void handleBorrar() {
        try {
            String codigo = TFCodigo.getText().trim();
            Producto productoAEliminar = null;
            for (Producto producto : productos) {
                if (producto.getCodigo().equals(codigo)) {
                    productoAEliminar = producto;
                    break;
                }
            }

            if (productoAEliminar != null) {
                productos.remove(productoAEliminar);
                actualizarListaProductos(productos);
                TFCodigo.clear();
                TFNombre.clear();
                TFCantidad.clear();
                TFPrecio.clear();
                TFDescripcion.clear();
                choiceBoxCategoria.setValue(null);
                mostrarAlerta("Producto Eliminado", "Producto Eliminado Correctamente", "El producto ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);

                // Actualizar el archivo XML después de eliminar el producto
                guardarProductosEnXML("tienda.xml", productos);
                MostrarOpacity(); // Actualizar la opacidad del botón Mostrar
            } else {
                mostrarAlerta("Producto No Encontrado", "No se encontró ningún producto con el código especificado.", "Por favor, verifica el código e inténtalo de nuevo.", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Código Inválido", "Por favor, ingresa un código numérico válido para borrar un producto.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleExportar() {
        if (productos.isEmpty()) {
            mostrarAlerta("Lista Vacía", "No hay productos para exportar.", "Agrega productos antes de exportar.", Alert.AlertType.WARNING);
            return;
        }

        try {
            guardarProductosEnXML("tienda.xml", productos);
            mostrarAlerta("Exportación Exitosa", "Productos Exportados Correctamente", "Los productos se han exportado al archivo tienda.xml.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta("Error de Exportación", "No se pudo exportar los productos", "Ocurrió un error al exportar los productos al archivo tienda.xml.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleModificar() {
        try {
            String codigo = TFCodigo.getText().trim();
            Producto productoAModificar = null;
            for (Producto producto : productos) {
                if (producto.getCodigo().equals(codigo)) {
                    productoAModificar = producto;
                    break;
                }
            }

            if (productoAModificar == null) {
                mostrarAlerta("Producto No Encontrado", "No se encontró ningún producto con el código especificado.", "Por favor, verifica el código e inténtalo de nuevo.", Alert.AlertType.WARNING);
                return;
            }

            String nombre = TFNombre.getText().trim();
            int cantidad = Integer.parseInt(TFCantidad.getText().trim());
            double precio = Double.parseDouble(TFPrecio.getText().trim());
            String descripcion = TFDescripcion.getText().trim();
            Categoria categoria = choiceBoxCategoria.getValue();

            if (categoria == null) {
                mostrarAlerta("Categoría No Seleccionada", "Selecciona una Categoría", "Por favor, selecciona una categoría para el producto.", Alert.AlertType.ERROR);
                return;
            }

            productoAModificar.setNombre(nombre);
            productoAModificar.setCantidad(cantidad);
            productoAModificar.setPrecio(precio);
            productoAModificar.setDescripcion(descripcion);
            productoAModificar.setCategoria(categoria);

            System.out.println("Producto modificado: " + productoAModificar.getNombre());
            imprimirProducto(productoAModificar);
            actualizarListaProductos(productos);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Datos Inválidos", "Por favor, ingresa valores numéricos válidos para cantidad y precio.", Alert.AlertType.ERROR);
        }
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
    private void guardarProductosEnXML(String archivo, List<Producto> productos) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("tienda");
            doc.appendChild(rootElement);

            Element productosElement = doc.createElement("productos");
            productosElement.setAttribute("num", String.valueOf(productos.size()));
            rootElement.appendChild(productosElement);

            for (Producto producto : productos) {
                Element productoElement = doc.createElement("producto");
                productoElement.setAttribute("codigo", producto.getCodigo());
                productosElement.appendChild(productoElement);

                Element nombreElement = doc.createElement("nombre");
                nombreElement.appendChild(doc.createTextNode(producto.getNombre()));
                productoElement.appendChild(nombreElement);

                Element categoriaElement = doc.createElement("categoria");
                categoriaElement.appendChild(doc.createTextNode(producto.getCategoria().name()));
                productoElement.appendChild(categoriaElement);

                Element cantidadElement = doc.createElement("cantidad");
                cantidadElement.appendChild(doc.createTextNode(String.valueOf(producto.getCantidad())));
                productoElement.appendChild(cantidadElement);

                Element precioElement = doc.createElement("precio");
                precioElement.appendChild(doc.createTextNode(String.valueOf(producto.getPrecio())));
                productoElement.appendChild(precioElement);

                Element descripcionElement = doc.createElement("descripcion");
                descripcionElement.appendChild(doc.createTextNode(producto.getDescripcion()));
                productoElement.appendChild(descripcionElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(archivo));
            transformer.transform(source, result);

            System.out.println("Productos guardados en el archivo XML: " + archivo);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Error al guardar los productos en el archivo XML: " + e.getMessage());
        }
    }
}