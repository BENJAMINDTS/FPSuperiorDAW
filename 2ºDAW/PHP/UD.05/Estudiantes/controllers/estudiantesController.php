<?php
require_once '../../config/database.php';
require_once '../../models/estudiantes.php';

/**
 * Clase EstudiantesController
 *
 * Controlador encargado de gestionar la lógica de negocio y la comunicación 
 * entre la vista y el modelo de Estudiantes.
 *
 * @author BenjaminDTS
 */
class EstudiantesController
{
  /**
   * @var Estudiantes Instancia del modelo Estudiantes.
   */
  private $estudiantesModel;

  /**
   * Constructor de la clase EstudiantesController.
   *
   * Inicializa el modelo de estudiantes utilizando la conexión proporcionada.
   *
   * @param PDO $connection Objeto PDO con la conexión a la base de datos activa.
   */
  public function __construct($connection)
  {
    $this->estudiantesModel = new Estudiantes($connection);
  }

  /**
   * Obtiene la lista completa de estudiantes.
   *
   * @return array Un arreglo asociativo con los datos de todos los estudiantes.
   */
  public function listEstudiantes()
  {
    $estudiantes = $this->estudiantesModel->getAllEstudiantes();
    return $estudiantes;
  }

  /**
   * Añade un nuevo estudiante a través del modelo.
   *
   * @param string $dni    El Documento Nacional de Identidad del estudiante.
   * @param string $nombre El nombre completo del estudiante.
   * @param string $email  La dirección de correo electrónico del estudiante.
   * @return bool Devuelve true si el registro fue exitoso, o false en caso de error.
   */
  public function addEstudiante($dni, $nombre, $email)
  {
    return $this->estudiantesModel->addEstudiantes($dni, $nombre, $email);
  }

  /**
   * Elimina un estudiante existente a través del modelo utilizando su DNI.
   *
   * @param string $dni El Documento Nacional de Identidad del estudiante a eliminar.
   * @return bool Devuelve true si la eliminación fue exitosa, o false en caso contrario.
   */
  public function deleteEstudiante($dni)
  {
    return $this->estudiantesModel->deleteEstudiantes($dni);
  }
}
