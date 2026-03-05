<?php

/**
 * Verifica el acceso controlado (RA5_e)
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

require_once 'BaseModel.php';

/**
 * Clase Usuario - Modelo para gestionar usuarios
 * @package Models
 * @extends BaseModel
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 */
class Usuario extends BaseModel
{

  /**
   * Nombre de la tabla en la base de datos
   * @var string
   */
  protected $table = 'usuarios';

  /**
   * Constructor de la clase Usuario
   * @return void
   */
  public function __construct()
  {
    parent::__construct();
    $this->createTableIfNotExists();
  }

  /**
   * Crea la tabla de usuarios si no existe

   * @return void
   */
  private function createTableIfNotExists()
  {
    $sql = "CREATE TABLE IF NOT EXISTS usuarios (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
            email VARCHAR(100) UNIQUE NOT NULL,
            telefono VARCHAR(20),
            direccion TEXT,
            fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )";
    $this->db->exec($sql);
  }

  /**
   * Registra un nuevo usuario
   * @param string $nombre Nombre completo
   * @param string $email Email único
   * @param string $telefono Teléfono
   * @param string $direccion Dirección
   * @return bool True si se registró correctamente
   */
  public function registrar($nombre, $email, $telefono = '', $direccion = '')
  {
    $sql = "INSERT INTO usuarios (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";
    $stmt = $this->db->prepare($sql);
    return $stmt->execute([$nombre, $email, $telefono, $direccion]);
  }
}
