<?php
require_once '../config/database.php';
require_once '../models/formModel.php';

/**
 * Clase FormController
 *
 * Controlador encargado de gestionar las operaciones relacionadas con los formularios,
 * actuando como intermediario entre la vista y el modelo de datos.
 *
 * @package Controllers
 * @author BenjaminDTS
 */
class FormController
{
    /**
     * @var FormModel Instancia del modelo encargado de las operaciones de base de datos para formularios.
     */
    private $model;

    /**
     * Constructor de la clase FormController.
     *
     * @param FormModel $model Instancia del modelo de formularios.
     */
    public function __construct($model)
    {
        $this->model = $model;
    }

    /**
     * Obtiene y devuelve el listado de todos los formularios.
     *
     * @return array Un arreglo con todos los registros de formularios obtenidos desde el modelo.
     */
    public function listForms()
    {
        return $this->model->getAllForms();
    }

    /**
     * Procesa los datos recibidos y los envía al modelo para crear un nuevo registro.
     *
     * @param array $data Los datos del formulario enviados por el usuario (generalmente un arreglo asociativo como $_POST).
     * @return bool Devuelve true si la creación del formulario fue exitosa, o false en caso contrario.
     */
    public function submitForm($data)
    {
        return $this->model->createForm($data);
    }
}
