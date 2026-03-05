<?php
require_once '../config/database.php';
class FormModel
{
    private $connection;

    public function __construct($connection)
    {
        $this->connection = $connection;
    }

    public function getAllForms()
    {
        $stmt = $this->connection->prepare("SELECT * FROM form");
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function createForm($data)
    {
        $stmt = $this->connection->prepare("INSERT INTO form (nombre, email, mensaje) VALUES (:nombre, :email, :mensaje)");
        $stmt->bindParam(':nombre', $data['nombre'], PDO::PARAM_STR);
        $stmt->bindParam(':email', $data['email'], PDO::PARAM_STR);
        $stmt->bindParam(':mensaje', $data['mensaje'], PDO::PARAM_STR);
        return $stmt->execute();
    }
}
