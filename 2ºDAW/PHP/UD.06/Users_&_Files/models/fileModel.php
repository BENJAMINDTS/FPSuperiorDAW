<?php
class fileModel
{
  private $pdo;

  public function __construct($pdo)
  {
    $this->pdo = $pdo;
  }

  // Insertar el registro en la base de datos
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
