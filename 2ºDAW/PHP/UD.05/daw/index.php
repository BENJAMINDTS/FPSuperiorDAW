<?php
/**
 * Front Controller - Sistema DAW
 * Punto de entrada único de la aplicación que maneja todas las solicitudes.
 * Implementa el patrón Front Controller para enrutamiento MVC.
 * @package App
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 * @license MIT
 */

// Iniciar sesión
session_start();

// Definir constante para control de acceso (RA5_e)
define('CON_CONTROLADOR', true);
define('BASE_PATH', '/daw/');

// Incluir configuración de base de datos
require_once 'config/database.php';

// Función para cargar clases automáticamente
spl_autoload_register(function($class) {
    $directories = ['controllers/', 'models/'];
    
    foreach ($directories as $directory) {
        $file = $directory . $class . '.php';
        if (file_exists($file)) {
            require_once $file;
            return;
        }
    }
});

/**
 * Extrae la ruta limpia de la solicitud
 * @return string Ruta limpia sin parámetros GET
 */
function getCleanPath() {
    $request_uri = $_SERVER['REQUEST_URI'];
    $script_name = $_SERVER['SCRIPT_NAME'];
    
    // Eliminar el directorio base
    $base_path = dirname($script_name);
    if ($base_path == '/') $base_path = '';
    
    $path = str_replace($base_path, '', $request_uri);
    
    // Eliminar parámetros GET
    if (($pos = strpos($path, '?')) !== false) {
        $path = substr($path, 0, $pos);
    }
    
    return trim($path, '/');
}

/**
 * Maneja errores HTTP
 * @param int $code Código de error HTTP
 * @param string $message Mensaje de error
 */
function handleError($code, $message) {
    http_response_code($code);
    
    $titulo = "Error $code";
    require_once 'views/layouts/header.php';
    ?>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card border-danger">
                    <div class="card-header bg-danger text-white">
                        <h5 class="card-title mb-0">
                            <i class="fas fa-exclamation-triangle me-2"></i>Error <?php echo $code; ?>
                        </h5>
                    </div>
                    <div class="card-body text-center">
                        <i class="fas fa-times-circle fa-5x text-danger mb-4"></i>
                        <h4><?php echo $message; ?></h4>
                        <p class="text-muted">Lo sentimos, ha ocurrido un error.</p>
                        <a href="<?php echo BASE_PATH; ?>" class="btn btn-primary mt-3">
                            <i class="fas fa-home me-1"></i> Volver al Inicio
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <?php
    require_once 'views/layouts/footer.php';
    exit();
}

// Obtener la ruta solicitada
$path = getCleanPath();

// Cargar modelos necesarios
require_once 'models/Sugerencia.php';
require_once 'models/Usuario.php';
require_once 'models/Producto.php';

// Crear instancias de los modelos
$sugerenciaModel = new Sugerencia();
$usuarioModel = new Usuario();
$productoModel = new Producto();

// Enrutamiento básico
try {
    switch ($path) {
        case '':
        case 'index.php':
            // Página de inicio
            $titulo = 'Inicio - Sistema DAW';
            $icono = 'home';
            require_once 'views/layouts/header.php';
            ?>
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="jumbotron bg-light p-5 rounded">
                            <h1 class="display-4">Sistema DAW</h1>
                            <p class="lead">Aplicación web desarrollada con arquitectura MVC para el módulo de Desarrollo de Aplicaciones Web.</p>
                            <hr class="my-4">
                            <p>Seleccione una opción del menú para comenzar.</p>
                        </div>
                    </div>
                </div>
                
                <div class="row mt-4">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 text-center">
                            <div class="card-body">
                                <i class="fas fa-comment-dots fa-3x text-primary mb-3"></i>
                                <h5 class="card-title">Sugerencias</h5>
                                <p class="card-text">Envía y gestiona sugerencias de usuarios.</p>
                                <a href="<?php echo BASE_PATH; ?>sugerencia" class="btn btn-primary">
                                    <i class="fas fa-arrow-right me-1"></i> Acceder
                                </a>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 text-center">
                            <div class="card-body">
                                <i class="fas fa-users fa-3x text-success mb-3"></i>
                                <h5 class="card-title">Usuarios</h5>
                                <p class="card-text">Registro y gestión de usuarios del sistema.</p>
                                <a href="<?php echo BASE_PATH; ?>usuario" class="btn btn-success">
                                    <i class="fas fa-arrow-right me-1"></i> Acceder
                                </a>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 text-center">
                            <div class="card-body">
                                <i class="fas fa-box fa-3x text-warning mb-3"></i>
                                <h5 class="card-title">Productos</h5>
                                <p class="card-text">Catálogo y gestión de productos.</p>
                                <a href="<?php echo BASE_PATH; ?>producto" class="btn btn-warning">
                                    <i class="fas fa-arrow-right me-1"></i> Acceder
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <?php
            require_once 'views/layouts/footer.php';
            break;
            
        // Enrutamiento de sugerencias
        case 'sugerencias':
        case 'sugerencia':
            require_once 'controllers/SugerenciaController.php';
            $controller = new SugerenciaController($sugerenciaModel);
            $controller->index();
            break;
            
        case 'sugerencia/crear':
            require_once 'controllers/SugerenciaController.php';
            $controller = new SugerenciaController($sugerenciaModel);
            $controller->crear();
            break;
            
        case 'sugerencia/guardar':
            require_once 'controllers/SugerenciaController.php';
            $controller = new SugerenciaController($sugerenciaModel);
            $controller->guardar();
            break;
            
        // Enrutamiento de usuarios
        case 'usuarios':
        case 'usuario':
            require_once 'controllers/UsuarioController.php';
            $controller = new UsuarioController($usuarioModel);
            $controller->index();
            break;
            
        case 'usuario/crear':
            require_once 'controllers/UsuarioController.php';
            $controller = new UsuarioController($usuarioModel);
            $controller->crear();
            break;
            
        case 'usuario/guardar':
            require_once 'controllers/UsuarioController.php';
            $controller = new UsuarioController($usuarioModel);
            $controller->guardar();
            break;
            
        // Enrutamiento de productos
        case 'productos':
        case 'producto':
            require_once 'controllers/ProductoController.php';
            $controller = new ProductoController($productoModel);
            $controller->index();
            break;
            
        case 'producto/crear':
            require_once 'controllers/ProductoController.php';
            $controller = new ProductoController($productoModel);
            $controller->crear();
            break;
            
        case 'producto/guardar':
            require_once 'controllers/ProductoController.php';
            $controller = new ProductoController($productoModel);
            $controller->guardar();
            break;
            
        default:
            // Página no encontrada
            handleError(404, 'Página no encontrada');
            break;
    }
    
} catch (Exception $e) {
    // Error interno del servidor
    error_log("Error en la aplicación: " . $e->getMessage());
    handleError(500, 'Error interno del servidor');
}
?>