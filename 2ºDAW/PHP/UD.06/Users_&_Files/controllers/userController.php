<?php
require_once 'models/userModel.php';

class UsuarioController
{

  private function conectar()
  {
    require 'config/database.php'; // Esto genera $connection
    return new userModel($connection);
  }

  // REGISTRO
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

  // LISTAR + BUSCAR
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

  // ELIMINAR
  public function eliminar()
  {
    $id = $_GET['id'] ?? null;
    if ($id) {
      $model = $this->conectar();
      $model->eliminar($id);
    }
    // Redirigir a la lista
    header("Location: index.php?accion=listar");
  }

  // EDITAR (Mostrar formulario)
  public function editar()
  {
    $id = $_GET['id'] ?? null;
    if ($id) {
      $model = $this->conectar();
      $usuario = $model->obtenerPorId($id);
      require_once 'views/user/edit.php';
    } else {
      header("Location: index.php?accion=listar");
    }
  }

  // ACTUALIZAR (Procesar formulario de edición)
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
  }

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
