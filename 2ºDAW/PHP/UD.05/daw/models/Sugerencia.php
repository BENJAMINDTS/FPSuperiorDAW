<?php

/**
 * Verifica el acceso controlado (RA5_e)
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

require_once 'BaseModel.php';

/**
 * Clase Sugerencia - Modelo para gestionar sugerencias de usuarios

 * @package Models
 * @extends BaseModel
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 */
class Sugerencia extends BaseModel
{

  /**
   * Nombre de la tabla en la base de datos

   * @var string
   */
  protected $table = 'sugerencias';

  /**
   * Constructor de la clase Sugerencia

   * @return void
   */
  public function __construct()
  {
    parent::__construct();
    $this->createTableIfNotExists();
  }

  /**
   * Crea la tabla de sugerencias si no existe
   * @return void
   */
  private function createTableIfNotExists()
  {
    $sql = "CREATE TABLE IF NOT EXISTS sugerencias (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
            email VARCHAR(100) NOT NULL,
            mensaje TEXT NOT NULL,
            fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            leida BOOLEAN DEFAULT FALSE
        )";
    $this->db->exec($sql);
  }

  /**
   * Crea una nueva sugerencia
   * @param string $nombre Nombre del usuario
   * @param string $email Email del usuario
   * @param string $mensaje Contenido de la sugerencia
   * @return bool True si se creó correctamente
   */
  public function crear($nombre, $email, $mensaje)
  {
    $sql = "INSERT INTO sugerencias (nombre, email, mensaje) VALUES (?, ?, ?)";
    $stmt = $this->db->prepare($sql);
    return $stmt->execute([$nombre, $email, $mensaje]);
  }
}
