<?php
require_once 'controllers/UsuarioController.php';

$controller = new UsuarioController();

// Verificamos si hay una acción en la URL (ej: index.php?accion=listar)
$accion = $_GET['accion'] ?? 'registro'; // Por defecto va a registro

if ($accion == 'listar') {
  $controller->listar();
} else {
  $controller->index();
}
