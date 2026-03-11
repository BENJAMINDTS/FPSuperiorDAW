<?php
require_once '../../config/database.php';

/**
 * Clase ProductoModel
 *
 * Se encarga de gestionar el acceso a los datos de la tabla de productos,
 * realizando las consultas necesarias a la base de datos.
 *
 * @package Models
 * @author BenjaminDTS
 */
class ProductoModel
{
    /**
     * @var PDO Instancia de la conexión a la base de datos.
     */
    private $connection;

    /**
     * Constructor de la clase ProductoModel.
     *
     * @param PDO $connection Objeto PDO que contiene la conexión activa a la base de datos.
     */
    public function __construct($connection)
    {
        $this->connection = $connection;
    }

    /**
     * Recupera el nombre y el precio de todos los productos disponibles.
     *
     * @return array Un arreglo asociativo que contiene el 'nombre' y 'precio' de cada producto.
     */
    public function getAllProducts()
    {
        $stmt = $this->connection->prepare("SELECT nombre, precio FROM productos");
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}
