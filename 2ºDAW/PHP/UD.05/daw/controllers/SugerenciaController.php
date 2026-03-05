<?php

/**
 * Verifica acceso controlado (RA5_e)
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

require_once 'BaseController.php';
require_once(__DIR__ . '/../models/Sugerencia.php');

/**
 * Clase SugerenciaController - Controlador de sugerencias
 * @package Controllers
 * @extends BaseController
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 */
class SugerenciaController extends BaseController
{

  /**
   * Muestra la página principal de sugerencias

   * @return void
   */
  public function index()
  {
    $modelo = new Sugerencia();
    $sugerencias = $modelo->getAll(['campo' => 'fecha_creacion', 'direccion' => 'DESC']);

    $this->render('sugerencia/index', [
      'sugerencias' => $sugerencias,
      'titulo' => 'Sugerencias'
    ]);
  }

  /**
   * Muestra el formulario para crear una sugerencia
   * @return void
   */
  public function crear()
  {
    $this->render('sugerencia/crear', [
      'titulo' => 'Nueva Sugerencia'
    ]);
  }

  /**
   * Procesa el formulario de sugerencia
   * @return void
   */
  public function guardar()
  {
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
      $nombre = $_POST['nombre'] ?? '';
      $email = $_POST['email'] ?? '';
      $mensaje = $_POST['mensaje'] ?? '';

      $modelo = new Sugerencia();
      $resultado = $modelo->crear($nombre, $email, $mensaje);

      if ($resultado) {
        $this->redirect('/daw/index.php?controller=sugerencia&action=index');
      } else {
        echo "Error al guardar la sugerencia";
      }
    }
  }

  /**
   * Muestra el detalle de una sugerencia
   * @param int $id ID de la sugerencia
   * @return void
   */
  public function detalle($id)
  {
    $modelo = new Sugerencia();
    $sugerencia = $modelo->find($id);

    if ($sugerencia) {
      $this->render('sugerencia/detalle', [
        'sugerencia' => $sugerencia,
        'titulo' => 'Detalle Sugerencia'
      ]);
    } else {
      echo "Sugerencia no encontrada";
    }
  }
}
