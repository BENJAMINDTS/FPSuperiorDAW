<?php

/**
 * Clase fileModel
 *
 * Gestiona las operaciones de base de datos relacionadas con los archivos
 * subidos por los usuarios (inserción, consulta y eliminación).
 *
 * @package Models
 * @author BenjaminDTS
 */
class fileModel
{
  /**
   * @var PDO Instancia de la conexión a la base de datos.
   */
  private $pdo;

  /**
   * Constructor de la clase fileModel.
   *
   * @param PDO $pdo Objeto PDO con la conexión a la base de datos activa.
   */
  public function __construct($pdo)
  {
    $this->pdo = $pdo;
  }

  /**
   * Inserta un nuevo registro de archivo en la base de datos.
   *
   * @param string $nombre     El nombre original del archivo subido.
   * @param string $ruta       La ruta física donde se almacenó el archivo en el servidor.
   * @param int    $usuario_id El identificador del usuario propietario del archivo.
   * @return bool Devuelve true si la inserción fue exitosa, o false si ocurrió una excepción.
   */
  public function agregarArchivo($nombre, $ruta, $usuario_id)
  {
    try {
      $sql = "INSERT INTO archivos (nombre, ruta, usuario_id) VALUES (:nombre, :ruta, :usuario_id)";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([
        ':nombre'     => $nombre,
        ':ruta'       => $ruta,
        ':usuario_id' => $usuario_id
      ]);
    } catch (PDOException $e) {
      return false;
    }
  }

  /**
   * Obtiene los datos detallados de un archivo específico mediante su identificador.
   *
   * @param int $id El identificador único del archivo en la base de datos.
   * @return array|false Devuelve un arreglo asociativo con los datos del archivo, o false si no existe o hay un error.
   */
  public function obtenerArchivoPorId($id)
  {
    try {
      $sql = "SELECT id, nombre, ruta, usuario_id FROM archivos WHERE id = :id";
      $stmt = $this->pdo->prepare($sql);
      $stmt->execute([':id' => $id]);
      return $stmt->fetch(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
      return false;
    }
  }

  /**
   * Obtiene la lista de todos los archivos asociados a un usuario específico.
   *
   * @param int $usuario_id El identificador del usuario.
   * @return array Devuelve un arreglo con los archivos del usuario, o un arreglo vacío si ocurre un error.
   */
  public function obtenerArchivosPorUsuario($usuario_id)
  {
    try {
      // Consulta limpia sin la columna fecha_subida que no existe en tu tabla
      $sql = "SELECT id, nombre, ruta FROM archivos WHERE usuario_id = :usuario_id";
      $stmt = $this->pdo->prepare($sql);
      $stmt->execute([':usuario_id' => $usuario_id]);
      return $stmt->fetchAll(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
      return [];
    }
  }

  /**
   * Elimina el registro de un archivo de la base de datos utilizando su identificador.
   *
   * @param int $id El identificador único del archivo a eliminar.
   * @return bool Devuelve true si la eliminación se realizó correctamente, o false en caso de excepción.
   */
  public function eliminarArchivo($id)
  {
    try {
      $sql = "DELETE FROM archivos WHERE id = :id";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([':id' => $id]);
    } catch (PDOException $e) {
      return false;
    }
  }
}
