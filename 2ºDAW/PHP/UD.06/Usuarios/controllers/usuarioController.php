<?php
require_once 'models/Usuario.php';

class UsuarioController
{

  // Método para la página de REGISTRO
  public function index()
  {
    $mensaje = "";

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
      $nombre = $_POST['nombre'] ?? '';
      $email = $_POST['email'] ?? '';
      $password = $_POST['password'] ?? '';

      if (!empty($nombre) && !empty($email) && !empty($password)) {
        require 'config/database.php';
        $usuarioModel = new Usuario($connection);

        if ($usuarioModel->registrar($nombre, $email, $password)) {
          $mensaje = "¡Usuario registrado en 'usuarios2' con éxito!";
        } else {
          $mensaje = "Error: No se pudo registrar.";
        }
      } else {
        $mensaje = "Completa todos los campos.";
      }
    }
    require_once 'views/registro.php';
  }

  // NUEVO: Método para la página de LISTADO
  public function listar()
  {
    require 'config/database.php'; // Traemos la conexión $connection

    $usuarioModel = new Usuario($connection);

    // Pedimos los datos al modelo
    $lista_usuarios = $usuarioModel->obtenerTodos();

    // Cargamos la vista de la tabla
    require_once 'views/lista_usuarios.php';
  }
}
