<?php
/**
 * Verifica acceso controlado (RA5_e)
 */
if (!defined('CON_CONTROLADOR')) {
    die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

require_once 'BaseController.php';

/**
 * Clase ProductoController - Controlador de productos
 * Maneja todas las operaciones relacionadas con productos
 * @package Controllers
 * @extends BaseController
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 */
class ProductoController extends BaseController {
    
    /**
     * Muestra la página principal de productos
     * @return void
     */
    public function index() {
        $productos = $this->model->getAll(['campo' => 'nombre', 'direccion' => 'ASC']);
        
        $this->render('producto/index', [
            'productos' => $productos,
            'titulo' => 'Productos',
            'icono' => 'box',
            'accion' => [
                'url' => '/daw/producto/crear',
                'icono' => 'plus',
                'texto' => 'Nuevo Producto'
            ]
        ]);
    }
    
    /**
     * Muestra el formulario para crear producto
     * @return void
     */
    public function crear() {
        $this->render('producto/crear', [
            'titulo' => 'Nuevo Producto',
            'icono' => 'box-open',
            'breadcrumb' => 'Crear Producto'
        ]);
    }
    
    /**
     * Procesa la creación de producto
     * @return void
     */
    public function guardar() {
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $nombre = $_POST['nombre'] ?? '';
            $descripcion = $_POST['descripcion'] ?? '';
            $precio = $_POST['precio'] ?? 0;
            $stock = $_POST['stock'] ?? 0;
            $categoria = $_POST['categoria'] ?? '';
            
            // Validar datos
            if (empty($nombre) || empty($descripcion) || empty($categoria) || $precio <= 0) {
                $this->setFlash('error', 'Complete todos los campos correctamente');
                $this->redirect('/daw/producto/crear');
                return;
            }
            
            // Crear producto
            $resultado = $this->model->crear($nombre, $descripcion, $precio, $stock, $categoria);
            
            if ($resultado) {
                $this->setFlash('success', 'Producto creado correctamente');
                $this->redirect('/daw/producto');
            } else {
                $this->setFlash('error', 'Error al crear el producto');
                $this->redirect('/daw/producto/crear');
            }
        } else {
            $this->redirect('/daw/producto/crear');
        }
    }
}
?>