<?php

/**
 * Verifica acceso controlado (RA5_e)
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

require_once 'BaseController.php';

/**
 * Clase UsuarioController - Controlador de usuarios
 * Maneja todas las operaciones relacionadas con usuarios
 * @package Controllers
 * @extends BaseController
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 */
class UsuarioController extends BaseController
{

  /**
   * Muestra la página principal de usuarios
   * @return void
   */
  public function index()
  {
    $usuarios = $this->model->getAll(['campo' => 'fecha_registro', 'direccion' => 'DESC']);

    $this->render('usuario/index', [
      'usuarios' => $usuarios,
      'titulo' => 'Usuarios',
      'icono' => 'users',
      'accion' => [
        'url' => '/daw/usuario/crear',
        'icono' => 'user-plus',
        'texto' => 'Nuevo Usuario'
      ]
    ]);
  }

  /**
   * Muestra el formulario de registro (crear)
   * @return void
   */
  public function crear()  // ← NUEVO MÉTODO (antes era registrar)
  {
    $this->render('usuario/crear', [
      'titulo' => 'Registro de Usuario',
      'icono' => 'user-plus',
      'breadcrumb' => 'Registrar Usuario'
    ]);
  }

  /**
   * Procesa el registro de usuario
   * @return void
   */
  public function guardar()
  {
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
      $nombre = $_POST['nombre'] ?? '';
      $email = $_POST['email'] ?? '';
      $telefono = $_POST['telefono'] ?? '';
      $direccion = $_POST['direccion'] ?? '';

      // Validar datos
      if (empty($nombre) || empty($email)) {
        $this->setFlash('error', 'Nombre y email son obligatorios');
        $this->redirect('/daw/usuario/crear');
        return;
      }

      // Registrar usuario
      $resultado = $this->model->registrar($nombre, $email, $telefono, $direccion);

      if ($resultado) {
        $this->setFlash('success', 'Usuario registrado correctamente');
        $this->redirect('/daw/usuario');
      } else {
        $this->setFlash('error', 'Error al registrar el usuario');
        $this->redirect('/daw/usuario/crear');
      }
    } else {
      $this->redirect('/daw/usuario/crear');
    }
  }
}
