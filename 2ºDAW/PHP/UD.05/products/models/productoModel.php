<?php
require_once '../../config/database.php';
class ProductoModel
{
    private $connection;

    public function __construct($connection)
    {
        $this->connection = $connection;
    }

    public function getAllProducts()
    {
        $stmt = $this->connection->prepare("SELECT nombre, precio FROM productos");
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}
