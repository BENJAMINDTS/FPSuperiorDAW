<?php
require_once '../../config/database.php';
class Estudiantes
{
  private $connection;
  public function __construct($connection)
  {
    $this->connection = $connection;
  }
  public function getAllEstudiantes()
  {
    $stmt = $this->connection->prepare("SELECT * FROM estudiante");
    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
  }
  public function addEstudiantes($dni, $nombre, $email)
  {
    $stmt = $this->connection->prepare("INSERT INTO estudiante (dni, nombre, email) VALUES (:dni, :nombre, :email)");
    $stmt->bindParam(':dni', $dni);
    $stmt->bindParam(':nombre', $nombre);
    $stmt->bindParam(':email', $email);
    return $stmt->execute();
  }

  public function deleteEstudiantes($dni)
  {
    $stmt = $this->connection->prepare("DELETE FROM estudiante WHERE dni = :dni");
    $stmt->bindParam(':dni', $dni);
    return $stmt->execute();
  }
}
