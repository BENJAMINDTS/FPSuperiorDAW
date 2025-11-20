<?php
require_once '../../config/database.php';
require_once '../../models/estudiantes.php';

class EstudiantesController
{
  private $estudiantesModel;

  public function __construct($connection)
  {
    $this->estudiantesModel = new Estudiantes($connection);
  }

  public function listEstudiantes()
  {
    $estudiantes = $this->estudiantesModel->getAllEstudiantes();
    return $estudiantes;
  }

  public function addEstudiante($dni, $nombre, $email)
  {
    return $this->estudiantesModel->addEstudiantes($dni, $nombre, $email);
    
  }

  public function deleteEstudiante($dni)
  {
    return $this->estudiantesModel->deleteEstudiantes($dni);
  }
}