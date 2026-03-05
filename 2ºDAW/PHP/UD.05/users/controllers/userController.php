<?php
require_once '../../config/database.php';
require_once '../../models/userModel.php';

/**
 * Clase UserController
 *
 * Controlador encargado de gestionar las operaciones y la lógica de negocio
 * relacionada con los usuarios del sistema, conectando la vista con el modelo.
 *
 * @package Controllers
 * @author BenjaminDTS
 */
class UserController
{
    /**
     * @var UserModel Instancia del modelo encargado de los datos de usuario.
     */
    private $model;

    /**
     * Constructor de la clase UserController.
     *
     * @param UserModel $model Instancia del modelo de usuarios.
     */
    public function __construct($model)
    {
        $this->model = $model;
    }

    /**
     * Obtiene la lista completa de usuarios registrados en el sistema.
     *
     * @return array Un arreglo asociativo con los datos de todos los usuarios.
     */
    public function listUsers()
    {
        return $this->model->getAllUsers();
    }

    /**
     * Obtiene los datos detallados de un usuario específico mediante su identificador.
     *
     * @param int|string $id El identificador único del usuario a consultar.
     * @return array|false Devuelve un arreglo con los datos del usuario, o false si no existe.
     */
    public function getUser($id)
    {
        return $this->model->getUserById($id);
    }
}
