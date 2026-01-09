<?php
require_once 'models/Usuario.php';

class UsuarioController
{

  private function conectar()
  {
    require 'config/database.php'; // Esto genera $connection
    return new Usuario($connection);
  }

  // REGISTRO
  public function index()
  {
    // ... (Tu código de registro anterior) ...
    // Simplemente copialo o mantenlo como estaba.
    // Para ahorrar espacio aquí me centro en lo nuevo:

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
    require_once 'views/registro.php';
  }

  // LISTAR + BUSCAR
  public function listar()
  {
    $usuarioModel = $this->conectar();

    // Ver si hay búsqueda
    $busqueda = $_POST['busqueda'] ?? null;

    $lista_usuarios = $usuarioModel->obtenerTodos($busqueda);
    require_once 'views/lista_usuarios.php';
  }

  // ELIMINAR
  public function eliminar()
  {
    $email = $_GET['email'] ?? null;
    if ($email) {
      $model = $this->conectar();
      $model->eliminar($email);
    }
    // Redirigir a la lista
    header("Location: index.php?accion=listar");
  }

  // EDITAR (Mostrar formulario)
  public function editar()
  {
    $email = $_GET['email'] ?? null;
    if ($email) {
      $model = $this->conectar();
      $usuario = $model->obtenerPorEmail($email);
      require_once 'views/editar.php';
    } else {
      header("Location: index.php?accion=listar");
    }
  }

  // ACTUALIZAR (Procesar formulario de edición)
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
  }
}
