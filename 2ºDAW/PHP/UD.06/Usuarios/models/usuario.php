<?php

/**
 * Clase Usuario
 *
 * Gestiona el acceso a datos para la tabla de usuarios (usuarios2),
 * permitiendo realizar operaciones CRUD utilizando el correo electrónico
 * como identificador principal en lugar de un ID numérico.
 *
 * @package Models
 * @author BenjaminDTS
 */
class Usuario
{
  /**
   * @var PDO Instancia de la conexión a la base de datos.
   */
  private $pdo;

  /**
   * Constructor de la clase Usuario.
   *
   * @param PDO $pdo Objeto PDO con la conexión activa a la base de datos.
   */
  public function __construct($pdo)
  {
    $this->pdo = $pdo;
  }

  /**
   * Obtiene la lista de usuarios, permitiendo filtrar opcionalmente por nombre.
   *
   * @param string|null $busqueda Término de búsqueda para filtrar coincidencias parciales en el nombre.
   * @return array Devuelve un arreglo asociativo con los usuarios encontrados, o un arreglo vacío en caso de excepción.
   */
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

  /**
   * Obtiene los datos de un único usuario utilizando su correo electrónico.
   *
   * @param string $email El correo electrónico exacto del usuario a buscar.
   * @return array|false Devuelve un arreglo con los datos del usuario, o false si no existe.
   */
  public function obtenerPorEmail($email)
  {
    $sql = "SELECT nombre, email FROM usuarios2 WHERE email = :email LIMIT 1";
    $stmt = $this->pdo->prepare($sql);
    $stmt->execute([':email' => $email]);
    return $stmt->fetch(PDO::FETCH_ASSOC);
  }

  /**
   * Registra un nuevo usuario en la base de datos.
   *
   * Genera automáticamente el hash de la contraseña antes de guardarla.
   *
   * @param string $nombre   El nombre completo o de usuario.
   * @param string $email    El correo electrónico (usado como identificador).
   * @param string $password La contraseña en texto plano.
   * @return bool Devuelve true si el registro fue exitoso, o false si ocurrió un error (ej. email duplicado).
   */
  public function registrar($nombre, $email, $password)
  {
    try {
      $passHash = password_hash($password, PASSWORD_DEFAULT);
      $sql = "INSERT INTO usuarios2 (nombre, email, password) VALUES (:nombre, :email, :password)";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([':nombre' => $nombre, ':email' => $email, ':password' => $passHash]);
    } catch (PDOException $e) {
      return false;
    }
  }

  /**
   * Elimina un usuario de la base de datos utilizando su correo electrónico.
   *
   * @param string $email El correo electrónico del usuario que se desea eliminar.
   * @return bool Devuelve true si la eliminación se realizó con éxito, o false en caso de excepción.
   */
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

  /**
   * Actualiza el nombre y el correo electrónico de un usuario.
   *
   * Requiere el correo original para localizar el registro exacto antes de aplicar los cambios.
   *
   * @param string $emailOriginal El correo electrónico actual del usuario (para la cláusula WHERE).
   * @param string $nombre        El nuevo nombre a asignar.
   * @param string $nuevoEmail    El nuevo correo electrónico a asignar.
   * @return bool Devuelve true si la actualización fue exitosa, o false en caso de excepción.
   */
  public function actualizar($emailOriginal, $nombre, $nuevoEmail)
  {
    try {
      $sql = "UPDATE usuarios2 SET nombre = :nombre, email = :nuevoEmail WHERE email = :emailOriginal";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([
        ':nombre'        => $nombre,
        ':nuevoEmail'    => $nuevoEmail,
        ':emailOriginal' => $emailOriginal
      ]);
    } catch (PDOException $e) {
      return false;
    }
  }
}
