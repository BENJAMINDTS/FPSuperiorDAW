<?php
class Usuario
{
  private $pdo;

  public function __construct($pdo)
  {
    $this->pdo = $pdo;
  }

  // LISTAR O BUSCAR (Si pasas $busqueda, filtra)
  public function obtenerTodos($busqueda = null)
  {
    try {
      if ($busqueda) {
        // Búsqueda por nombre (parcial)
        $sql = "SELECT nombre, email FROM usuarios2 WHERE nombre LIKE :busqueda";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([':busqueda' => "%$busqueda%"]);
      } else {
        // Listar todos
        $sql = "SELECT nombre, email FROM usuarios2";
        $stmt = $this->pdo->query($sql);
      }
      return $stmt->fetchAll(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
      return [];
    }
  }

  // OBTENER UN SOLO USUARIO (Para rellenar el formulario de edición)
  public function obtenerPorEmail($email)
  {
    $sql = "SELECT nombre, email FROM usuarios2 WHERE email = :email LIMIT 1";
    $stmt = $this->pdo->prepare($sql);
    $stmt->execute([':email' => $email]);
    return $stmt->fetch(PDO::FETCH_ASSOC);
  }

  // REGISTRAR (Igual que antes)
  public function registrar($nombre, $email, $password)
  {
    // ... (código anterior) ...
    try {
      $passHash = password_hash($password, PASSWORD_DEFAULT);
      $sql = "INSERT INTO usuarios2 (nombre, email, password) VALUES (:nombre, :email, :password)";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([':nombre' => $nombre, ':email' => $email, ':password' => $passHash]);
    } catch (PDOException $e) {
      return false;
    }
  }

  // ELIMINAR (Usando el email como referencia)
  public function eliminar($email)
  {
    try {
      $sql = "DELETE FROM usuarios2 WHERE email = :email";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([':email' => $email]);
    } catch (PDOException $e) {
      return false;
    }
  }

  // ACTUALIZAR (Necesitamos el email original para saber a quién cambiar)
  public function actualizar($emailOriginal, $nombre, $nuevoEmail)
  {
    try {
      $sql = "UPDATE usuarios2 SET nombre = :nombre, email = :nuevoEmail WHERE email = :emailOriginal";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([
        ':nombre' => $nombre,
        ':nuevoEmail' => $nuevoEmail,
        ':emailOriginal' => $emailOriginal
      ]);
    } catch (PDOException $e) {
      return false;
    }
  }
}
