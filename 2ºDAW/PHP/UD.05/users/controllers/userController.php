<?php
require_once '../../config/database.php';
require_once '../../models/userModel.php';

class UserController
{
    private $model;

    public function __construct($model)
    {
        $this->model = $model;
    }

    public function listUsers()
    {
        return $this->model->getAllUsers();
    }

    public function getUser($id)
    {
        return $this->model->getUserById($id);
    }
}
