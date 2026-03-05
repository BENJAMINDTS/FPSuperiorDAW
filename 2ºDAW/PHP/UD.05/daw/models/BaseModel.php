<?php

/**
 * Verifica que el acceso se realice a través del controlador (RA5_e)
 * Previene el acceso directo a los archivos del modelo
 * @throws Exception Si se intenta acceder directamente al archivo
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

/**
 * Clase BaseModel - Modelo base abstracto para operaciones CRUD
 * Proporciona métodos comunes para interactuar con la base de datos.
 * Implementa operaciones CRUD básicas y sigue el patrón ActiveRecord.
 * @package Models
 * @abstract
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 */
abstract class BaseModel
{

  /**
   * Nombre de la tabla en la base de datos
   * @var string $table
   * @protected
   */
  protected $table;

  /**
   * Instancia de conexión PDO a la base de datos
   * @var PDO $db
   * @protected
   */
  protected $db;

  /**
   * Constructor de la clase BaseModel
   * Inicializa la conexión a la base de datos

   * @throws Exception Si la propiedad $table no está definida
   * @return void
   */
  public function __construct()
  {
    global $connection;
    $this->db = $connection;

    if (empty($this->table)) {
      throw new Exception("La propiedad 'table' debe ser definida en la clase hija.");
    }
  }

  /**
   * Obtiene todos los registros de la tabla
   * @param array $order Campo y dirección para ordenar
   * @return array Lista de registros encontrados
   */
  public function getAll($order = ['campo' => 'id', 'direccion' => 'ASC'])
  {
    $sql = "SELECT * FROM {$this->table} ORDER BY {$order['campo']} {$order['direccion']}";
    $stmt = $this->db->prepare($sql);
    $stmt->execute();
    return $stmt->fetchAll();
  }

  /**
   * Busca un registro por su ID
   * @param int $id Identificador del registro
   * @return array|null Datos del registro o null si no existe
   */
  public function find($id)
  {
    $sql = "SELECT * FROM {$this->table} WHERE id = ? LIMIT 1";
    $stmt = $this->db->prepare($sql);
    $stmt->execute([$id]);
    return $stmt->fetch();
  }

  /**
   * Elimina un registro por su ID
   * @param int $id Identificador del registro a eliminar
   * @return bool True si se eliminó correctamente
   */
  public function delete($id)
  {
    $sql = "DELETE FROM {$this->table} WHERE id = ?";
    $stmt = $this->db->prepare($sql);
    return $stmt->execute([$id]);
  }
}
