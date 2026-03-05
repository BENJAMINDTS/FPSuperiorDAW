<?php
require_once 'controllers/UsuarioController.php';

$controller = new UsuarioController();

$accion = $_GET['accion'] ?? 'registro';

if ($accion == 'listar') {
  $controller->listar();
} elseif ($accion == 'eliminar') {
  $controller->eliminar();
} elseif ($accion == 'editar') {
  $controller->editar();
} elseif ($accion == 'actualizar') {
  $controller->actualizar();
} else {
  // Por defecto, ir al registro
  $controller->index();
}
