<?php
class userModel
{
  private $pdo;

  public function __construct($pdo)
  {
    $this->pdo = $pdo;
  }

  // LISTAR O BUSCAR (Si pasas $busqueda, filtra)
  public function obtenerTodos($busqueda = null, $limit = null, $offset = null)
  {
    try {
      if ($busqueda) {
        $sql = "SELECT id, nombre, email FROM usuarios3 WHERE nombre LIKE :busqueda";
        // La paginación en búsquedas suele ser opcional, pero aquí la añadimos:
        if ($limit !== null && $offset !== null) {
          $sql .= " LIMIT $limit OFFSET $offset";
        }
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([':busqueda' => "%$busqueda%"]);
      } else {
        $sql = "SELECT id, nombre, email FROM usuarios3";
        if ($limit !== null && $offset !== null) {
          $sql .= " LIMIT $limit OFFSET $offset";
        }
        $stmt = $this->pdo->query($sql);
      }
      return $stmt->fetchAll(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
      return [];
    }
  }

  // OBTENER UN SOLO USUARIO (Para rellenar el formulario de edición)
  public function obtenerPorId($id)
  {
    $sql = "SELECT id, nombre, email FROM usuarios3 WHERE id = :id LIMIT 1";
    $stmt = $this->pdo->prepare($sql);
    $stmt->execute([':id' => $id]);
    return $stmt->fetch(PDO::FETCH_ASSOC);
  }

  // REGISTRAR (Igual que antes)
  public function registrar($nombre, $email, $password)
  {
    // ... (código anterior) ...
    try {
      $passHash = password_hash($password, PASSWORD_DEFAULT);
      $sql = "INSERT INTO usuarios3 (nombre, email, password) VALUES (:nombre, :email, :password)";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([':nombre' => $nombre, ':email' => $email, ':password' => $passHash]);
    } catch (PDOException $e) {
      return false;
    }
  }

  // ELIMINAR (Usando el id como referencia)
  public function eliminar($id)
  {
    try {
      $sql = "DELETE FROM usuarios3 WHERE id = :id";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([':id' => $id]);
    } catch (PDOException $e) {
      return false;
    }
  }

  // ACTUALIZAR (Actualizamos nombre y email en funcion del id)
  public function actualizar($id, $nombre, $email)
  {
    try {
      $sql = "UPDATE usuarios3 SET nombre = :nombre, email = :email WHERE id = :id";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([
        ':nombre' => $nombre,
        ':email' => $email,
        ':id' => $id
      ]);
    } catch (PDOException $e) {
      return false;
    }
  }

  //Inicio de sesión
  public function iniciarSesion($email, $password)
  {
    try {
      $sql = "SELECT id, nombre, email, password FROM usuarios3 WHERE email = :email LIMIT 1";
      $stmt = $this->pdo->prepare($sql);
      $stmt->execute([':email' => $email]);
      $usuario = $stmt->fetch(PDO::FETCH_ASSOC);

      if ($usuario && password_verify($password, $usuario['password'])) {
        // Contraseña correcta
        return [
          'id' => $usuario['id'],
          'nombre' => $usuario['nombre'],
          'email' => $usuario['email']
        ];
      } else {
        // Usuario no encontrado o contraseña incorrecta
        return null;
      }
    } catch (PDOException $e) {
      return null;
    }
  }
}
