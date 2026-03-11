<?php
require_once '../../config/database.php';

/**
 * Clase userModel
 *
 * Gestiona el acceso a los datos de la tabla de usuarios,
 * permitiendo la consulta de todos los registros o la búsqueda por identificador.
 *
 * @package Models
 * @author BenjaminDTS
 */
class userModel
{
    /**
     * @var PDO Instancia de la conexión a la base de datos.
     */
    private $connection;

    /**
     * Constructor de la clase userModel.
     *
     * @param PDO $connection Objeto PDO con la conexión activa a la base de datos.
     */
    public function __construct($connection)
    {
        $this->connection = $connection;
    }

    /**
     * Obtiene la lista de todos los usuarios registrados.
     *
     * @return array Un arreglo asociativo que contiene el 'nombre' y 'email' de todos los usuarios.
     */
    public function getAllUsers()
    {
        $stmt = $this->connection->prepare("SELECT nombre, email FROM users");
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    /**
     * Obtiene los datos de un usuario específico utilizando su ID.
     *
     * @param int $id El identificador único del usuario a buscar.
     * @return array|false Devuelve un arreglo asociativo con los datos del usuario si se encuentra, o false si no existe.
     */
    public function getUserById($id)
    {
        $stmt = $this->connection->prepare("SELECT nombre, email FROM users WHERE id = :id");
        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
        $stmt->execute();
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }
}
