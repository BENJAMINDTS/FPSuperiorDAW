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

/**
 * Gestiona la persistencia de datos de la Tienda y sus Productos en formato
 * XML.
 * Utiliza el modelo de objetos de documento (DOM) para la lectura y escritura.
 * * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class FileHandler {
    private File file;

    /**
     * Constructor que inicializa la ubicación del archivo de persistencia.
     */
    public FileHandler() {
        this.file = new File("src/main/java/tarea/tienda.xml");
        System.out.println("Ruta del archivo configurada: " + file.getAbsolutePath());
    }

    /**
     * Transforma un objeto Tienda en un archivo estructurado XML.
     * Implementa validación de códigos de producto antes de la escritura.
     * * * @param tienda Objeto Tienda que contiene la información y lista de
     * productos.
     */
    public void guardarTiendaEnXML(Tienda tienda) {
        try {
            // Configuración del constructor de documentos XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Creación del nodo raíz <tienda>
            Element tiendaElement = doc.createElement("tienda");
            doc.appendChild(tiendaElement);

            // Inserción de metadatos de la tienda
            agregarElemento(doc, tiendaElement, "nombre", tienda.getNombre());
            agregarElemento(doc, tiendaElement, "descripción", tienda.getDescripcion());
            agregarElemento(doc, tiendaElement, "dirección", tienda.getDireccion());

            // Nodo contenedor de la colección de productos
            Element productosElement = doc.createElement("productos");
            productosElement.setAttribute("num", String.valueOf(tienda.getProductos().size()));
            tiendaElement.appendChild(productosElement);

            // Iteración y serialización de cada producto
            for (Producto producto : tienda.getProductos()) {
                // Validación de integridad del código según formato RegEx
                if (!validarFormatoCodigo(producto.getCodigo())) {
                    System.err.println("Error: El código '" + producto.getCodigo() + "' es inválido. Omitiendo...");
                    continue;
                }

                Element productoElement = doc.createElement("producto");
                productoElement.setAttribute("código", producto.getCodigo());
                productosElement.appendChild(productoElement);

                // Adición de nodos hijo con información detallada
                agregarElemento(doc, productoElement, "nombre", producto.getNombre());
                agregarElemento(doc, productoElement, "categoría", producto.getCategoria().name());
                agregarElemento(doc, productoElement, "cantidad", String.valueOf(producto.getCantidad()));
                agregarElemento(doc, productoElement, "precio", String.valueOf(producto.getPrecio()));
                agregarElemento(doc, productoElement, "descripción", producto.getDescripcion());
            }

            // Transformación del árbol DOM en un archivo físico .xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Tienda guardada correctamente en: " + file.getAbsolutePath());
        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println("Error crítico al guardar la tienda: " + e.getMessage());
        }
    }

    /**
     * Reconstruye el objeto Tienda a partir del análisis del archivo XML.
     * * * @return Tienda Objeto reconstruido o null si el archivo no existe/está
     * vacío.
     */
    public Tienda leerTiendaDesdeXML() {
        Tienda tienda = null;
        try {
            if (!file.exists() || file.length() == 0) {
                System.out.println("Archivo inexistente o sin contenido.");
                return null;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            Element tiendaElement = doc.getDocumentElement();

            // Extracción de datos básicos
            String nombre = obtenerTextoElemento(tiendaElement, "nombre");
            String descripcion = obtenerTextoElemento(tiendaElement, "descripción");
            String direccion = obtenerTextoElemento(tiendaElement, "dirección");

            tienda = new Tienda(nombre, descripcion, direccion, new ArrayList<>());

            // Procesamiento de la lista de productos
            NodeList productosNodeList = tiendaElement.getElementsByTagName("producto");
            for (int i = 0; i < productosNodeList.getLength(); i++) {
                Node productoNode = productosNodeList.item(i);
                if (productoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element productoElement = (Element) productoNode;
                    String codigo = productoElement.getAttribute("código");

                    if (!validarFormatoCodigo(codigo))
                        continue;

                    // Reconstrucción del objeto Producto
                    String nomProd = obtenerTextoElemento(productoElement, "nombre");
                    Categoria cat = obtenerCategoria(productoElement, "categoría");
                    int cant = obtenerCantidad(productoElement, "cantidad");
                    double precio = obtenerPrecio(productoElement, "precio");
                    String descProd = obtenerTextoElemento(productoElement, "descripción");

                    tienda.getProductos().add(new Producto(codigo, nomProd, cant, precio, descProd, cat));
                }
            }
            System.out.println("Tienda cargada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al leer XML: " + e.getMessage());
        }
        return tienda;
    }

    // --- Métodos Auxiliares de Procesamiento ---

    private void agregarElemento(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parent.appendChild(element);
    }

    private String obtenerTextoElemento(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        return (nodeList != null && nodeList.getLength() > 0) ? nodeList.item(0).getTextContent() : "";
    }

    private Categoria obtenerCategoria(Element parent, String tagName) {
        String catStr = obtenerTextoElemento(parent, tagName);
        try {
            return Categoria.valueOf(catStr);
        } catch (Exception e) {
            return Categoria.ALIMENTOS;
        }
    }

    private int obtenerCantidad(Element parent, String tagName) {
        try {
            return Integer.parseInt(obtenerTextoElemento(parent, tagName));
        } catch (Exception e) {
            return 0;
        }
    }

    private double obtenerPrecio(Element parent, String tagName) {
        try {
            return Double.parseDouble(obtenerTextoElemento(parent, tagName));
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * Valida el código mediante RegEx: 2-3 Mayúsculas seguidas de números.
     * * * @param codigo Cadena a validar.
     * 
     * @return boolean True si el formato es correcto.
     */
    private boolean validarFormatoCodigo(String codigo) {
        return codigo != null && codigo.matches("^[A-Z]{2,3}\\d+$");
    }
}