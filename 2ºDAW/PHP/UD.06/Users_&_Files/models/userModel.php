<?php

/**
 * Clase userModel
 *
 * Gestiona todas las operaciones de base de datos relacionadas con los usuarios,
 * incluyendo el CRUD completo, búsquedas, paginación y autenticación (login).
 *
 * @package Models
 * @author BenjaminDTS
 */
class userModel
{
  /**
   * @var PDO Instancia de la conexión a la base de datos.
   */
  private $pdo;

  /**
   * Constructor de la clase userModel.
   *
   * @param PDO $pdo Objeto PDO con la conexión a la base de datos activa.
   */
  public function __construct($pdo)
  {
    $this->pdo = $pdo;
  }

  /**
   * Obtiene una lista de usuarios, permitiendo filtrar por nombre y aplicar paginación.
   *
   * @param string|null $busqueda Término de búsqueda para filtrar por el nombre del usuario.
   * @param int|null    $limit    Cantidad máxima de registros a devolver (para paginación).
   * @param int|null    $offset   Número de registros a saltar (para paginación).
   * @return array Devuelve un arreglo asociativo con los usuarios encontrados, o un arreglo vacío en caso de error.
   */
  public function obtenerTodos($busqueda = null, $limit = null, $offset = null)
  {
    try {
      if ($busqueda) {
        $sql = "SELECT id, nombre, email FROM usuarios3 WHERE nombre LIKE :busqueda";
        // La paginación en búsquedas suele ser opcional, pero aquí la añadimos:
        if ($limit !== null && $offset !== null) {
          // Nota de seguridad: Asegúrate de que $limit y $offset se validen como enteros en el controlador.
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

  /**
   * Obtiene los datos de un único usuario utilizando su identificador.
   *
   * Ideal para rellenar formularios de edición o mostrar perfiles.
   *
   * @param int $id El identificador único del usuario.
   * @return array|false Devuelve un arreglo con los datos del usuario, o false si no existe.
   */
  public function obtenerPorId($id)
  {
    $sql = "SELECT id, nombre, email FROM usuarios3 WHERE id = :id LIMIT 1";
    $stmt = $this->pdo->prepare($sql);
    $stmt->execute([':id' => $id]);
    return $stmt->fetch(PDO::FETCH_ASSOC);
  }

  /**
   * Registra un nuevo usuario en la base de datos.
   *
   * La contraseña se encripta automáticamente utilizando password_hash antes de guardarse.
   *
   * @param string $nombre   El nombre del usuario.
   * @param string $email    El correo electrónico del usuario.
   * @param string $password La contraseña en texto plano.
   * @return bool Devuelve true si el registro fue exitoso, o false en caso de error.
   */
  public function registrar($nombre, $email, $password)
  {
    try {
      $passHash = password_hash($password, PASSWORD_DEFAULT);
      $sql = "INSERT INTO usuarios3 (nombre, email, password) VALUES (:nombre, :email, :password)";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([':nombre' => $nombre, ':email' => $email, ':password' => $passHash]);
    } catch (PDOException $e) {
      return false;
    }
  }

  /**
   * Elimina un usuario de la base de datos utilizando su identificador.
   *
   * @param int $id El identificador único del usuario a eliminar.
   * @return bool Devuelve true si la eliminación se realizó correctamente, o false en caso de error.
   */
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

  /**
   * Actualiza el nombre y el correo electrónico de un usuario existente.
   *
   * @param int    $id     El identificador del usuario a actualizar.
   * @param string $nombre El nuevo nombre del usuario.
   * @param string $email  El nuevo correo electrónico del usuario.
   * @return bool Devuelve true si la actualización fue exitosa, o false si ocurrió una excepción.
   */
  public function actualizar($id, $nombre, $email)
  {
    try {
      $sql = "UPDATE usuarios3 SET nombre = :nombre, email = :email WHERE id = :id";
      $stmt = $this->pdo->prepare($sql);
      return $stmt->execute([
        ':nombre' => $nombre,
        ':email'  => $email,
        ':id'     => $id
      ]);
    } catch (PDOException $e) {
      return false;
    }
  }

  /**
   * Verifica las credenciales de un usuario para el inicio de sesión.
   *
   * Comprueba que el correo exista y que la contraseña coincida con el hash almacenado.
   *
   * @param string $email    El correo electrónico ingresado por el usuario.
   * @param string $password La contraseña ingresada en texto plano.
   * @return array|null Devuelve un arreglo con los datos del usuario (sin contraseña) si el login es correcto, o null si falla.
   */
  public function iniciarSesion($email, $password)
  {
    try {
      $sql = "SELECT id, nombre, email, password FROM usuarios3 WHERE email = :email LIMIT 1";
      $stmt = $this->pdo->prepare($sql);
      $stmt->execute([':email' => $email]);
      $usuario = $stmt->fetch(PDO::FETCH_ASSOC);

      if ($usuario && password_verify($password, $usuario['password'])) {
        // Contraseña correcta: retornamos los datos seguros (sin el hash)
        return [
          'id'     => $usuario['id'],
          'nombre' => $usuario['nombre'],
          'email'  => $usuario['email']
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
