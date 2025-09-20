package tarea;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tarea.Producto.Categoria;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class FileHandler {
    private File file;

    // Constructor: inicializa la ruta del archivo
    public FileHandler() {
        this.file = new File("src/main/java/tarea/tienda.xml");
        System.out.println("Ruta del archivo: " + file.getAbsolutePath());
    }

    // Guarda la información de la tienda en un archivo XML
    public void guardarTiendaEnXML(Tienda tienda) {
        try {
            // Crear el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Crear el elemento raíz <tienda>
            Element tiendaElement = doc.createElement("tienda");
            doc.appendChild(tiendaElement);

            // Añadir elementos <nombre>, <descripción> y <dirección>
            agregarElemento(doc, tiendaElement, "nombre", tienda.getNombre());
            agregarElemento(doc, tiendaElement, "descripción", tienda.getDescripcion());
            agregarElemento(doc, tiendaElement, "dirección", tienda.getDireccion());

            // Crear el elemento <productos> con el atributo num
            Element productosElement = doc.createElement("productos");
            productosElement.setAttribute("num", String.valueOf(tienda.getProductos().size()));
            tiendaElement.appendChild(productosElement);

            // Añadir cada producto como un elemento <producto>
            for (Producto producto : tienda.getProductos()) {
                // Validar el formato del código
                if (!validarFormatoCodigo(producto.getCodigo())) {
                    System.out.println("Error: El código '" + producto.getCodigo() + "' no cumple con el formato requerido (2 o 3 letras mayúsculas seguidas de un número).");
                    continue; // Omitir este producto
                }

                // Crear el elemento <producto>
                Element productoElement = doc.createElement("producto");
                productoElement.setAttribute("código", producto.getCodigo());
                productosElement.appendChild(productoElement);

                // Añadir los elementos hijos: <nombre>, <categoría>, <cantidad>, <precio>, <descripción>
                agregarElemento(doc, productoElement, "nombre", producto.getNombre());
                agregarElemento(doc, productoElement, "categoría", producto.getCategoria().name());
                agregarElemento(doc, productoElement, "cantidad", String.valueOf(producto.getCantidad()));
                agregarElemento(doc, productoElement, "precio", String.valueOf(producto.getPrecio())); // Guardar el precio
                agregarElemento(doc, productoElement, "descripción", producto.getDescripcion());
            }

            // Escribir el documento en el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Tienda guardada correctamente en: " + file.getAbsolutePath());
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Error al guardar la tienda en XML: " + e.getMessage());
        }
    }

    // Lee la información de la tienda desde un archivo XML
    public Tienda leerTiendaDesdeXML() {
        Tienda tienda = null;
        try {
            // Verifica si el archivo existe y no está vacío
            if (!file.exists() || file.length() == 0) {
                System.out.println("El archivo XML no existe o está vacío.");
                return null;
            }

            // Parsear el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            // Obtener el elemento raíz <tienda>
            Element tiendaElement = doc.getDocumentElement();

            // Leer los elementos <nombre>, <descripción> y <dirección>
            String nombre = obtenerTextoElemento(tiendaElement, "nombre");
            String descripcion = obtenerTextoElemento(tiendaElement, "descripción");
            String direccion = obtenerTextoElemento(tiendaElement, "dirección");

            // Crear la tienda
            tienda = new Tienda(nombre, descripcion, direccion, new ArrayList<>());

            // Leer los productos
            NodeList productosNodeList = tiendaElement.getElementsByTagName("producto");
            for (int i = 0; i < productosNodeList.getLength(); i++) {
                Node productoNode = productosNodeList.item(i);
                if (productoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element productoElement = (Element) productoNode;

                    // Obtener el atributo "código"
                    String codigo = productoElement.getAttribute("código");

                    // Validar el formato del código
                    if (!validarFormatoCodigo(codigo)) {
                        System.out.println("Advertencia: El código '" + codigo + "' no cumple con el formato requerido (2 o 3 letras mayúsculas seguidas de un número).");
                        continue; // Omitir este producto
                    }

                    // Obtener los elementos hijos del producto
                    String nombreProducto = obtenerTextoElemento(productoElement, "nombre");
                    Categoria categoria = obtenerCategoria(productoElement, "categoría");
                    int cantidad = obtenerCantidad(productoElement, "cantidad");
                    double precio = obtenerPrecio(productoElement, "precio"); // Leer el precio
                    String descripcionProducto = obtenerTextoElemento(productoElement, "descripción");

                    // Crear el producto y añadirlo a la tienda
                    Producto producto = new Producto(codigo, nombreProducto, cantidad, precio, descripcionProducto, categoria);
                    tienda.getProductos().add(producto);
                }
            }

            System.out.println("Tienda leída correctamente desde: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error al leer la tienda desde XML: " + e.getMessage());
        }

        return tienda;
    }

    // Método auxiliar para agregar un elemento al documento XML
    private void agregarElemento(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parent.appendChild(element);
    }

    // Método auxiliar para obtener el texto de un elemento
    private String obtenerTextoElemento(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            System.out.println("Advertencia: Elemento '" + tagName + "' no encontrado en el XML.");
            return ""; // Valor por defecto si el elemento no existe
        }
    }

    // Método auxiliar para obtener la categoría de un producto
    private Categoria obtenerCategoria(Element parent, String tagName) {
        String categoriaStr = obtenerTextoElemento(parent, tagName);
        try {
            return Categoria.valueOf(categoriaStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Advertencia: Categoría '" + categoriaStr + "' no válida. Usando valor por defecto.");
            return Categoria.ALIMENTOS; // Valor por defecto si la categoría no es válida
        }
    }

    // Método auxiliar para obtener la cantidad de un producto
    private int obtenerCantidad(Element parent, String tagName) {
        String cantidadStr = obtenerTextoElemento(parent, tagName);
        try {
            return Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            System.out.println("Advertencia: Cantidad '" + cantidadStr + "' no válida. Usando valor por defecto.");
            return 0; // Valor por defecto si la cantidad no es válida
        }
    }

    // Método auxiliar para obtener el precio de un producto
    private double obtenerPrecio(Element parent, String tagName) {
        String precioStr = obtenerTextoElemento(parent, tagName);
        try {
            return Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            System.out.println("Advertencia: Precio '" + precioStr + "' no válido. Usando valor por defecto.");
            return 0.0; // Valor por defecto si el precio no es válido
        }
    }

    // Método auxiliar para validar el formato del código
    private boolean validarFormatoCodigo(String codigo) {
        // Expresión regular para validar el formato: 2 o 3 letras mayúsculas seguidas de un número entero
        String regex = "^[A-Z]{2,3}\\d+$";
        return codigo.matches(regex);
    }
}