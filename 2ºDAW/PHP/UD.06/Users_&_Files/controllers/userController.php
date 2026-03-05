<?php
require_once 'models/userModel.php';

/**
 * Clase UsuarioController
 *
 * Controlador encargado de gestionar el flujo completo de los usuarios,
 * incluyendo registro, autenticación (login), listado con paginación, 
 * edición y eliminación de registros.
 *
 * @package Controllers
 * @author BenjaminDTS
 */
class UsuarioController
{
  /**
   * Inicializa la conexión a la base de datos y el modelo de usuarios.
   *
   * @return userModel Instancia del modelo de usuarios con la conexión inyectada.
   */
  private function conectar()
  {
    require 'config/database.php'; // Esto genera $connection
    return new userModel($connection);
  }

  /**
   * Carga la vista de registro y procesa el formulario de creación de usuarios.
   *
   * Si la petición es POST y los campos requeridos no están vacíos, 
   * intenta registrar al nuevo usuario en la base de datos.
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
        if ($model->registrar($nombre, $email, $password)) $mensaje = "Registrado OK";
        else $mensaje = "Error al registrar";
      }
    }
    require_once 'views/user/register.php';
  }

  /**
   * Lista los usuarios registrados, aplicando filtros de búsqueda y paginación.
   *
   * @return void
   */
  public function listar()
  {
    $usuarioModel = $this->conectar();
    $busqueda = $_POST['busqueda'] ?? null;

    // Lógica de paginación
    $pagina_actual = isset($_GET['pagina']) ? (int)$_GET['pagina'] : 1;
    $registros_per_page = 5;
    $offset = ($pagina_actual - 1) * $registros_per_page;

    // Pasamos el límite y el offset al modelo
    $lista_usuarios = $usuarioModel->obtenerTodos($busqueda, $registros_per_page, $offset);

    require_once 'views/user/list.php';
  }

  /**
   * Elimina un usuario del sistema utilizando su identificador.
   *
   * Tras procesar la eliminación, redirige al listado de usuarios.
   *
   * @return void
   */
  public function eliminar()
  {
    $id = $_GET['id'] ?? null;
    if ($id) {
      $model = $this->conectar();
      $model->eliminar($id);
    }
    // Redirigir a la lista
    header("Location: index.php?accion=listar");
    exit(); // Es buena práctica añadir exit() después de un header de redirección
  }

  /**
   * Muestra el formulario de edición para un usuario específico.
   *
   * Obtiene los datos del usuario por su ID y carga la vista correspondiente.
   * Si no se proporciona un ID, redirige al listado.
   *
   * @return void
   */
  public function editar()
  {
    $id = $_GET['id'] ?? null;
    if ($id) {
      $model = $this->conectar();
      $usuario = $model->obtenerPorId($id);
      require_once 'views/user/edit.php';
    } else {
      header("Location: index.php?accion=listar");
      exit();
    }
  }

  /**
   * Procesa la actualización de los datos de un usuario.
   *
   * Recibe los datos del formulario de edición vía POST y los envía al modelo.
   * Posteriormente redirige al listado de usuarios.
   *
   * @return void
   */
  public function actualizar()
  {
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
      $id = $_POST['id']; // NECESARIO para identificar
      $nombre = $_POST['nombre'];
      $nuevoEmail = $_POST['email'];

      $model = $this->conectar();
      $model->actualizar($id, $nombre, $nuevoEmail);
    }
    header("Location: index.php?accion=listar");
    exit();
  }

  /**
   * Gestiona el inicio de sesión de los usuarios.
   *
   * Valida las credenciales contra la base de datos. Si son correctas, 
   * inicializa la sesión y redirige al listado. Si fallan, muestra un error.
   *
   * @return void
   */
  public function login()
  {
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
      $email = $_POST['email'];
      $password = $_POST['password'];

      $model = $this->conectar();
      $usuario = $model->iniciarSesion($email, $password);

      if ($usuario) {
        // Inicio de sesión exitoso
        session_start();
        $_SESSION['usuario'] = $usuario;
        header("Location: index.php?accion=listar");
        exit();
      } else {
        $error = "Email o contraseña incorrectos.";
      }
    }

    require_once 'views/user/login.php';
  }
}
