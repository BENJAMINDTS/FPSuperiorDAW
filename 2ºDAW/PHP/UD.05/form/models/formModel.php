<?php
require_once '../config/database.php';

/**
 * Clase FormModel
 *
 * Gestiona las operaciones de acceso a datos para la tabla de formularios,
 * permitiendo la consulta y creación de nuevos registros.
 *
 * @package Models
 * @author BenjaminDTS
 */
class FormModel
{
    /**
     * @var PDO Instancia de la conexión a la base de datos.
     */
    private $connection;

    /**
     * Constructor de la clase FormModel.
     *
     * @param PDO $connection Objeto PDO con la conexión activa a la base de datos.
     */
    public function __construct($connection)
    {
        $this->connection = $connection;
    }

    /**
     * Obtiene todos los registros almacenados en la tabla de formularios.
     *
     * @return array Un arreglo asociativo que contiene todos los formularios recuperados.
     */
    public function getAllForms()
    {
        $stmt = $this->connection->prepare("SELECT * FROM form");
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    /**
     * Inserta un nuevo registro de formulario en la base de datos.
     *
     * @param array $data Arreglo asociativo con la información del formulario.
     * Se espera que contenga las claves: 'nombre', 'email' y 'mensaje'.
     * @return bool Devuelve true si la inserción fue exitosa, o false en caso de error.
     */
    public function createForm($data)
    {
        $stmt = $this->connection->prepare("INSERT INTO form (nombre, email, mensaje) VALUES (:nombre, :email, :mensaje)");

        // Se utiliza PDO::PARAM_STR para asegurar que los datos se traten como cadenas de texto
        $stmt->bindParam(':nombre', $data['nombre'], PDO::PARAM_STR);
        $stmt->bindParam(':email', $data['email'], PDO::PARAM_STR);
        $stmt->bindParam(':mensaje', $data['mensaje'], PDO::PARAM_STR);

        return $stmt->execute();
    }
}
