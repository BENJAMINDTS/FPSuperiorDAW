<?php

/**
 * Verifica el acceso controlado (RA5_e)
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

require_once 'BaseModel.php';

/**
 * Clase Producto - Modelo para gestionar productos
 * @package Models
 * @extends BaseModel
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 */
class Producto extends BaseModel
{

  /**
   * Nombre de la tabla en la base de datos
   * @var string
   */
  protected $table = 'productos';

  /**
   * Constructor de la clase Producto
   * @return void
   */
  public function __construct()
  {
    parent::__construct();
    $this->createTableIfNotExists();
  }

  /**
   * Crea la tabla de productos si no existe
   * @return void
   */
  private function createTableIfNotExists()
  {
    $sql = "CREATE TABLE IF NOT EXISTS productos (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
            descripcion TEXT,
            precio DECIMAL(10,2) NOT NULL,
            stock INT DEFAULT 0,
            categoria VARCHAR(50),
            fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )";
    $this->db->exec($sql);
  }

  /**
   * Crea un nuevo producto
   * @param string $nombre Nombre del producto
   * @param string $descripcion Descripción
   * @param float $precio Precio
   * @param int $stock Cantidad en stock
   * @param string $categoria Categoría
   * @return bool True si se creó correctamente
   */
  public function crear($nombre, $descripcion, $precio, $stock, $categoria)
  {
    $sql = "INSERT INTO productos (nombre, descripcion, precio, stock, categoria) VALUES (?, ?, ?, ?, ?)";
    $stmt = $this->db->prepare($sql);
    return $stmt->execute([$nombre, $descripcion, $precio, $stock, $categoria]);
  }
}
