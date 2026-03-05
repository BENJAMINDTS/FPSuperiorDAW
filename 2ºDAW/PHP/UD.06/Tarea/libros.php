<?php

/**
 * Clase Libros
 * * Gestiona la conexión a la base de datos y las operaciones CRUD
 * relacionadas con autores y libros.
 */
class Libros
{

  /**
   * Establece la conexión con la base de datos.
   *
   * @param string $servidor La dirección del servidor (ej. "localhost").
   * @param string $base_datos El nombre de la base de datos.
   * @param string $usuario El nombre de usuario de la base de datos.
   * @param string $contraseña La contraseña del usuario.
   * @return PDO|null Retorna el objeto de conexión PDO o null si hay error.
   */
  public function conexion($servidor, $base_datos, $usuario, $contraseña)
  {
    try {
      $dsn = "mysql:host=$servidor;dbname=$base_datos;charset=utf8mb4";
      $opciones = [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_OBJ
      ];
      $conexion = new PDO($dsn, $usuario, $contraseña, $opciones);
      return $conexion;
    } catch (PDOException $e) {
      // En un entorno real, registrar el error en un log en lugar de mostrarlo
      return null;
    }
  }

  /**
   * Consulta los autores disponibles.
   *
   * @param PDO $conexion Objeto de conexión a la base de datos.
   * @param int|null $id_autor (Opcional) ID del autor a buscar.
   * @return array|object|null Retorna un objeto autor, un array de autores, o null si falla.
   */
  public function consultarAutores($conexion, $id_autor = null)
  {
    if (!$conexion) return null;

    try {
      if ($id_autor !== null) {
        $sql = "SELECT * FROM Autor WHERE id = :id";
        $stmt = $conexion->prepare($sql);
        $stmt->execute([':id' => $id_autor]);
        return $stmt->fetch(); // Retorna un solo objeto
      } else {
        $sql = "SELECT * FROM Autor";
        $stmt = $conexion->query($sql);
        return $stmt->fetchAll(); // Retorna array de objetos
      }
    } catch (PDOException $e) {
      return null;
    }
  }

  /**
   * Consulta los libros disponibles.
   *
   * @param PDO $conexion Objeto de conexión a la base de datos.
   * @param int|null $id_autor (Opcional) ID del autor para filtrar libros.
   * @return array|null Retorna un array de libros o null si falla.
   */
  public function consultarLibros($conexion, $id_autor = null)
  {
    if (!$conexion) return null;

    try {
      if ($id_autor !== null) {
        $sql = "SELECT * FROM Libro WHERE id_autor = :id";
        $stmt = $conexion->prepare($sql);
        $stmt->execute([':id' => $id_autor]);
      } else {
        $sql = "SELECT * FROM Libro";
        $stmt = $conexion->query($sql);
      }
      return $stmt->fetchAll();
    } catch (PDOException $e) {
      return null;
    }
  }

  /**
   * Consulta los datos de un libro específico.
   *
   * @param PDO $conexion Objeto de conexión a la base de datos.
   * @param int $id_libro ID del libro a consultar.
   * @return object|null Retorna el objeto libro o null si no existe/falla.
   */
  public function consultarDatosLibro($conexion, $id_libro)
  {
    if (!$conexion) return null;

    try {
      $sql = "SELECT * FROM Libro WHERE id = :id";
      $stmt = $conexion->prepare($sql);
      $stmt->execute([':id' => $id_libro]);
      return $stmt->fetch();
    } catch (PDOException $e) {
      return null;
    }
  }

  /**
   * Elimina un autor por su ID.
   *
   * @param PDO $conexion Objeto de conexión a la base de datos.
   * @param int $id_autor ID del autor a eliminar.
   * @return bool Retorna verdadero si tuvo éxito, falso si hubo error.
   */
  public function borrarAutor($conexion, $id_autor)
  {
    if (!$conexion) return false;

    try {
      $sql = "DELETE FROM Autor WHERE id = :id";
      $stmt = $conexion->prepare($sql);
      $stmt->execute([':id' => $id_autor]);
      // Verificar si se afectó alguna fila
      return $stmt->rowCount() > 0;
    } catch (PDOException $e) {
      return false;
    }
  }

  /**
   * Elimina un libro por su ID.
   *
   * @param PDO $conexion Objeto de conexión a la base de datos.
   * @param int $id_libro ID del libro a eliminar.
   * @return bool Retorna verdadero si tuvo éxito, falso si hubo error.
   */
  public function borrarLibro($conexion, $id_libro)
  {
    if (!$conexion) return false;

    try {
      $sql = "DELETE FROM Libro WHERE id = :id";
      $stmt = $conexion->prepare($sql);
      $stmt->execute([':id' => $id_libro]);
      return $stmt->rowCount() > 0;
    } catch (PDOException $e) {
      return false;
    }
  }
}
