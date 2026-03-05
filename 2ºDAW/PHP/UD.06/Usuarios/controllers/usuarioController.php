<?php
require_once 'models/Usuario.php';

/**
 * Clase UsuarioController
 *
 * Controlador encargado de gestionar las operaciones CRUD de los usuarios,
 * utilizando el correo electrónico como identificador principal.
 *
 * @package Controllers
 * @author BenjaminDTS
 */
class UsuarioController
{
  /**
   * Inicializa la conexión a la base de datos y el modelo de usuario.
   *
   * @return Usuario Instancia del modelo Usuario con la conexión inyectada.
   */
  private function conectar()
  {
    require 'config/database.php'; // Esto genera $connection
    return new Usuario($connection);
  }

  /**
   * Carga la vista principal y procesa el registro de un nuevo usuario.
   *
   * Si la petición es POST, valida los campos recibidos e intenta crear
   * el registro en la base de datos a través del modelo.
   *
   * @return void
   */
  public function index()
  {
    $mensaje = "";
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
      // ... lógica de registro ...
      $nombre = $_POST['nombre'] ?? '';
      $email = $_POST['email'] ?? '';
      $password = $_POST['password'] ?? '';
      if (!empty($nombre) && !empty($email) && !empty($password)) {
        $model = $this->conectar();
        if ($model->registrar($nombre, $email, $password)) {
          $mensaje = "Registrado OK";
        } else {
          $mensaje = "Error al registrar";
        }
      }
    }
    require_once 'views/registro.php';
  }

  /**
   * Obtiene la lista de usuarios y procesa búsquedas si se envían por POST.
   *
   * Carga la vista con el listado de usuarios obtenidos del modelo.
   *
   * @return void
   */
  public function listar()
  {
    $usuarioModel = $this->conectar();

    // Ver si hay búsqueda
    $busqueda = $_POST['busqueda'] ?? null;

    $lista_usuarios = $usuarioModel->obtenerTodos($busqueda);
    require_once 'views/lista_usuarios.php';
  }

  /**
   * Elimina un usuario de la base de datos utilizando su correo electrónico.
   *
   * Obtiene el email mediante GET y, tras realizar la eliminación, 
   * redirige de vuelta a la lista de usuarios.
   *
   * @return void
   */
  public function eliminar()
  {
    $email = $_GET['email'] ?? null;
    if ($email) {
      $model = $this->conectar();
      $model->eliminar($email);
    }
    // Redirigir a la lista
    header("Location: index.php?accion=listar");
    exit(); // Añadido por seguridad tras la redirección
  }

  /**
   * Muestra el formulario de edición de un usuario específico.
   *
   * Obtiene los datos del usuario basándose en el email proporcionado por GET.
   * Si no se proporciona un email válido, redirige al listado.
   *
   * @return void
   */
  public function editar()
  {
    $email = $_GET['email'] ?? null;
    if ($email) {
      $model = $this->conectar();
      $usuario = $model->obtenerPorEmail($email);
      require_once 'views/editar.php';
    } else {
      header("Location: index.php?accion=listar");
      exit(); // Añadido por seguridad tras la redirección
    }
  }

  /**
   * Procesa la actualización de los datos de un usuario desde el formulario.
   *
   * Utiliza un campo oculto (email_original) para localizar el registro,
   * permitiendo así la actualización del propio correo electrónico.
   * Tras procesar los datos, redirige al listado.
   *
   * @return void
   */
  public function actualizar()
  {
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
      $emailOriginal = $_POST['email_original']; // NECESARIO para identificar
      $nombre = $_POST['nombre'];
      $nuevoEmail = $_POST['email'];

      $model = $this->conectar();
      $model->actualizar($emailOriginal, $nombre, $nuevoEmail);
    }
    header("Location: index.php?accion=listar");
    exit(); // Añadido por seguridad tras la redirección
  }
}
