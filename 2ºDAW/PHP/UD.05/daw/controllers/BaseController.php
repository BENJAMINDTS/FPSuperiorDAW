<?php

/**
 * Verifica acceso controlado (RA5_e)
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

/**
 * Clase BaseController - Controlador base
 * Proporciona funcionalidad común para todos los controladores
 * @package Controllers
 * @abstract
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 */
abstract class BaseController
{

  /**
   * Modelo asociado al controlador
   * @var mixed
   * @protected
   */
  protected $model;

  /**
   * Constructor del controlador base
   * @param mixed $model Modelo asociado al controlador
   */
  public function __construct($model)
  {
    $this->model = $model;
  }

  /**
   * Carga una vista con datos
   * @param string $vista Nombre de la vista
   * @param array $datos Datos para pasar a la vista
   * @return void
   */
  protected function render($vista, $datos = [])
  {
    extract($datos);

    // Cargar header
    if (file_exists('views/layouts/header.php')) {
      require_once 'views/layouts/header.php';
    }

    // Cargar vista específica
    $ruta_vista = "views/{$vista}.php";
    if (file_exists($ruta_vista)) {
      require_once $ruta_vista;
    } else {
      echo "<div class='alert alert-danger'>Vista no encontrada: {$vista}</div>";
    }

    // Cargar footer
    if (file_exists('views/layouts/footer.php')) {
      require_once 'views/layouts/footer.php';
    }
  }

  /**
   * Redirige a una URL
   * @param string $url URL a la que redirigir
   * @return void
   */
  protected function redirect($url)
  {
    header("Location: $url");
    exit();
  }

  /**
   * Establece un mensaje flash en sesión
   * @param string $tipo Tipo de mensaje (success, error, warning, info)
   * @param string $mensaje Contenido del mensaje
   * @return void
   */
  protected function setFlash($tipo, $mensaje)
  {
    $_SESSION['flash'] = [
      'tipo' => $tipo,
      'mensaje' => $mensaje
    ];
  }

  /**
   * Obtiene y elimina el mensaje flash de sesión
   * @return array|null Datos del mensaje flash o null
   */
  protected function getFlash()
  {
    if (isset($_SESSION['flash'])) {
      $flash = $_SESSION['flash'];
      unset($_SESSION['flash']);
      return $flash;
    }
    return null;
  }
}
