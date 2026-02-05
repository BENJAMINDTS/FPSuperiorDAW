package tarea;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controlador principal de la interfaz JavaFX.
 * Gestiona la lógica de botones, validación visual y persistencia de la lista
 * de productos.
 * * * @author BenjaminDTS
 * 
 * @version 1.0
 */
public class PrimaryController {

	@FXML
	private TextField TFCodigo, TFNombre, TFPrecio, TFCantidad, TFDescripcion;
	@FXML
	private TextArea TALista;
	@FXML
	private Button BBuscar, BMostrar, BInsertar, BBorrar, BSalir;

	/** Lista dinámica que mantiene los productos en memoria RAM */
	private List<Producto> productos = new ArrayList<>();
	/** Manejador de persistencia en disco */
	private FileHandler fileHandler = new FileHandler();
	/** Formateador para mostrar precios con dos decimales */
	private DecimalFormat decimalFormat = new DecimalFormat("#.00");

	/**
	 * Inicializa el controlador, carga los datos del archivo y configura los
	 * listeners de la UI.
	 */
	public void initialize() {
		try {
			productos = fileHandler.readProducts();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error inicial: " + e.getMessage());
		}

		configurarEstadoBotones();
		actualizarListaProductos();

		// Listeners para actualizar la opacidad dinámicamente según el texto ingresado
		TFCodigo.textProperty().addListener((obs, old, newValue) -> {
			ComprobarOpacity();
			InsertarOpacity();
		});
		TFNombre.textProperty().addListener((obs, old, n) -> InsertarOpacity());
		TFPrecio.textProperty().addListener((obs, old, n) -> InsertarOpacity());
		TFCantidad.textProperty().addListener((obs, old, n) -> InsertarOpacity());
		TFDescripcion.textProperty().addListener((obs, old, n) -> InsertarOpacity());
		TALista.textProperty().addListener((obs, old, n) -> MostrarOpacity());
	}

	private void configurarEstadoBotones() {
		ComprobarOpacity();
		InsertarOpacity();
		MostrarOpacity();
	}

	@FXML
	private void ComprobarOpacity() {
		boolean isCodigoEmpty = TFCodigo.getText().trim().isEmpty();
		BBuscar.setDisable(isCodigoEmpty);
		BBuscar.setOpacity(isCodigoEmpty ? 0.5 : 1.0);

		boolean isBorrarDisabled = productos.isEmpty() || isCodigoEmpty;
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

	/**
	 * Captura los datos de los campos de texto, crea un nuevo Producto y lo guarda.
	 */
	@FXML
	private void handleInsertar() {
		try {
			int codigo = Integer.parseInt(TFCodigo.getText().trim());
			String nombre = TFNombre.getText().trim();
			int cantidad = Integer.parseInt(TFCantidad.getText().trim());
			double precio = Double.parseDouble(TFPrecio.getText().trim());
			String desc = TFDescripcion.getText().trim();

			Producto p = new Producto(codigo, nombre, cantidad, precio, desc);
			productos.add(p);
			fileHandler.writeProducts(productos);

			limpiarCampos();
			actualizarListaProductos();
			ComprobarOpacity();
		} catch (NumberFormatException | IOException e) {
			System.err.println("Fallo al insertar: " + e.getMessage());
		}
	}

	private void limpiarCampos() {
		TFCodigo.clear();
		TFNombre.clear();
		TFCantidad.clear();
		TFPrecio.clear();
		TFDescripcion.clear();
	}

	@FXML
	private void handleMostrar() {
		actualizarListaProductos();
	}

	/**
	 * Refresca el TextArea con la lista formateada de todos los productos.
	 */
	private void actualizarListaProductos() {
		StringBuilder sb = new StringBuilder();
		for (Producto p : productos) {
			sb.append("Código: ").append(p.getCodigo()).append("\n")
					.append("Nombre: ").append(p.getNombre()).append("\n")
					.append("Precio: ").append(decimalFormat.format(p.getPrecio())).append("€\n\n");
		}
		TALista.setText(sb.toString());
	}

	/**
	 * Busca un producto por código y rellena los campos de texto si lo encuentra.
	 */
	@FXML
	private void handleBuscar() {
		try {
			int cod = Integer.parseInt(TFCodigo.getText().trim());
			for (Producto p : productos) {
				if (p.getCodigo() == cod) {
					TFNombre.setText(p.getNombre());
					TFCantidad.setText(String.valueOf(p.getCantidad()));
					TFPrecio.setText(String.valueOf(p.getPrecio()));
					TFDescripcion.setText(p.getDescripcion());
					return;
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Código inválido");
		}
	}

	/**
	 * Elimina un producto de la lista basándose en el código del TextField.
	 */
	@FXML
	private void handleBorrar() {
		try {
			int cod = Integer.parseInt(TFCodigo.getText().trim());
			productos.removeIf(p -> p.getCodigo() == cod);
			fileHandler.writeProducts(productos);
			actualizarListaProductos();
			ComprobarOpacity();
		} catch (NumberFormatException | IOException e) {
			System.err.println("Error al borrar");
		}
	}
}