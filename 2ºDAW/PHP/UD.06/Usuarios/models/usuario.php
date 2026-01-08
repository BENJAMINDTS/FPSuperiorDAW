<?php
class Usuario
{
  private $pdo;

  public function __construct($pdo)
  {
    $this->pdo = $pdo;
  }

  // Método para REGISTRAR
  public function registrar($nombre, $email, $password)
  {
    try {
      $passHash = password_hash($password, PASSWORD_DEFAULT);

      // ACTUALIZADO: Tabla 'usuarios2'
      $sql = "INSERT INTO usuarios2 (nombre, email, password) VALUES (:nombre, :email, :password)";

      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([
        ':nombre' => $nombre,
        ':email' => $email,
        ':password' => $passHash
      ]);
    } catch (PDOException $e) {
      return false;
    }
  }

  // NUEVO: Método para LISTAR
  public function obtenerTodos()
  {
    try {
      // Seleccionamos id, nombre y email (¡Nunca la contraseña!)
      // ACTUALIZADO: Tabla 'usuarios2'
      $sql = "SELECT * FROM usuarios2";

      $stmt = $this->pdo->query($sql);

      // Devuelve un array asociativo con todas las filas
      return $stmt->fetchAll(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
      return []; // Si falla, devuelve array vacío
    }
  }
}
