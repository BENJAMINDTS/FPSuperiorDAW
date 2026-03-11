<?php
require_once '../../config/database.php';

/**
 * Clase Estudiantes
 *
 * Gestiona las operaciones de acceso a datos (CRUD) para la entidad estudiante.
 *
 * @author BenjaminDTS
 */
class Estudiantes
{
  /**
   * @var PDO Instancia de la conexión a la base de datos.
   */
  private $connection;

  /**
   * Constructor de la clase Estudiantes.
   *
   * @param PDO $connection Objeto PDO con la conexión a la base de datos activa.
   */
  public function __construct($connection)
  {
    $this->connection = $connection;
  }

  /**
   * Obtiene un listado de todos los estudiantes registrados.
   *
   * @return array Un arreglo asociativo que contiene los registros de todos los estudiantes.
   */
  public function getAllEstudiantes()
  {
    $stmt = $this->connection->prepare("SELECT * FROM estudiante");
    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
  }

  /**
   * Registra un nuevo estudiante en la base de datos.
   *
   * @param string $dni    El Documento Nacional de Identidad del estudiante.
   * @param string $nombre El nombre completo del estudiante.
   * @param string $email  La dirección de correo electrónico del estudiante.
   * @return bool Devuelve true si la inserción fue exitosa, o false en caso de error.
   */
  public function addEstudiantes($dni, $nombre, $email)
  {
    $stmt = $this->connection->prepare("INSERT INTO estudiante (dni, nombre, email) VALUES (:dni, :nombre, :email)");
    $stmt->bindParam(':dni', $dni);
    $stmt->bindParam(':nombre', $nombre);
    $stmt->bindParam(':email', $email);
    return $stmt->execute();
  }

  /**
   * Elimina el registro de un estudiante utilizando su DNI.
   *
   * @param string $dni El Documento Nacional de Identidad del estudiante que se desea eliminar.
   * @return bool Devuelve true si la eliminación se realizó con éxito, o false en caso contrario.
   */
  public function deleteEstudiantes($dni)
  {
    $stmt = $this->connection->prepare("DELETE FROM estudiante WHERE dni = :dni");
    $stmt->bindParam(':dni', $dni);
    return $stmt->execute();
  }
}
