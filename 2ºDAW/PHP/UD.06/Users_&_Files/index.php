<?php
// 1. Iniciamos sesión antes de cualquier otra cosa
session_start();

require_once 'controllers/userController.php';
require_once 'controllers/fileController.php'; // Cargamos el controlador de archivos

$controller = new UsuarioController();
$fileController = new FileController();

$accion = $_GET['accion'] ?? 'login';

switch ($accion) {
  case 'registro':
    $controller->index();
    break;
  case 'login':
    $controller->login();
    break;
  case 'dashboard':
    if (isset($_SESSION['usuario'])) {
      require_once 'views/user/dashboard.php';
    } else {
      header("Location: index.php?accion=login");
    }
    break;
  case 'listar':
    $controller->listar();
    break;
  case 'editar':
    $controller->editar();
    break;
  case 'actualizar':
    $controller->actualizar();
    break;
  case 'eliminar':
    $controller->eliminar();
    break;

  // RUTAS DE ARCHIVOS
  case 'subirArchivo':
    $fileController->subirArchivo();
    break;
  case 'verArchivos':
    $fileController->verArchivos();
    break;
  case 'eliminarArchivo':
    $fileController->eliminarArchivo();
    break;

  case 'logout':
    session_destroy();
    header("Location: index.php?accion=login");
    exit();
  default:
    header("Location: index.php?accion=login");
    break;
}
