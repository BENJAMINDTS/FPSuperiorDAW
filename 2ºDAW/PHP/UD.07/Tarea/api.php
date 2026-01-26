<?php

/**
 * API REST para la gestión de Autores y Libros (DWES)
 * @author Benjamin
 * @version 1.0
 */

// Configuración de la base de datos
define('DB_HOST', 'localhost');
define('DB_NAME', 'daw');
define('DB_USER', 'root');
define('DB_PASS', '');

/**
 * Establece la conexión con la base de datos usando PDO.
 * @return PDO Objeto de conexión a la base de datos.
 */
function getDB()
{
  try {
    $pdo = new PDO("mysql:host=" . DB_HOST . ";dbname=" . DB_NAME . ";charset=utf8", DB_USER, DB_PASS);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    return $pdo;
  } catch (PDOException $e) {
    exit(json_encode(["error" => "Error de conexión: " . $e->getMessage()]));
  }
}

/**
 * Retorna una lista con todos los autores de la base de datos.
 * @return array Array de objetos con los datos de los autores.
 */
function get_listado_autores()
{
  $db = getDB();
  $stmt = $db->query("SELECT id, nombre, apellidos FROM Autor");
  return $stmt->fetchAll(PDO::FETCH_OBJ);
}

/**
 * Retorna la información de un autor y la lista de sus libros.
 * @param int $id Identificador del autor.
 * @return object Objeto con los datos del autor y un array de sus libros.
 */
function get_datos_autor($id)
{
  $db = getDB();

  // 1. Obtener datos del autor
  $stmtAutor = $db->prepare("SELECT * FROM Autor WHERE id = ?");
  $stmtAutor->execute([$id]);
  $autor = $stmtAutor->fetch(PDO::FETCH_OBJ);

  // 2. Obtener los libros de ese autor
  $stmtLibros = $db->prepare("SELECT id, titulo FROM Libro WHERE id_autor = ?");
  $stmtLibros->execute([$id]);
  $libros = $stmtLibros->fetchAll(PDO::FETCH_OBJ);

  $info_autor = new stdClass();
  if ($autor) {
    $info_autor->datos = $autor;
    $info_autor->libros = $libros;
  } else {
    $info_autor->error = "Autor no encontrado";
  }

  return $info_autor;
}

/**
 * Retorna una lista con todos los libros de la base de datos.
 * @return array Array de objetos con id y título de los libros.
 */
function get_listado_libros()
{
  $db = getDB();
  $stmt = $db->query("SELECT id, titulo FROM Libro");
  return $stmt->fetchAll(PDO::FETCH_OBJ);
}

/**
 * Retorna la información de un libro y los datos de su autor.
 * @param int $id Identificador del libro.
 * @return object Objeto con los datos del libro y su autor asociado.
 */
function get_datos_libro($id)
{
  $db = getDB();
  // Usamos JOIN para obtener los datos del libro y del autor en una sola consulta
  $sql = "SELECT l.id, l.titulo, l.f_publicacion, l.id_autor, a.nombre, a.apellidos 
            FROM Libro l
            JOIN Autor a ON l.id_autor = a.id
            WHERE l.id = ?";
  $stmt = $db->prepare($sql);
  $stmt->execute([$id]);

  return $stmt->fetch(PDO::FETCH_OBJ);
}

// Array con las URLs permitidas
$posibles_URL = array("get_listado_autores", "get_datos_autor", "get_listado_libros", "get_datos_libro");

$valor = "Ha ocurrido un error";

if (isset($_GET["action"]) && in_array($_GET["action"], $posibles_URL)) {
  switch ($_GET["action"]) {
    case "get_listado_autores":
      $valor = get_listado_autores();
      break;
    case "get_datos_autor":
      if (isset($_GET["id"]))
        $valor = get_datos_autor($_GET["id"]);
      else
        $valor = "Argumento no encontrado";
      break;
    case "get_listado_libros":
      $valor = get_listado_libros();
      break;
    case "get_datos_libro":
      if (isset($_GET["id"]))
        $valor = get_datos_libro($_GET["id"]);
      else
        $valor = "Argumento no encontrado";
      break;
  }
}

// Configurar la cabecera para que el cliente sepa que es JSON
header('Content-Type: application/json; charset=utf-8');
// Devolvemos los datos serializados en JSON
exit(json_encode($valor));
