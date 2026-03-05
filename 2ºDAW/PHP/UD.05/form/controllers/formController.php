<?php
  require_once '../config/database.php';
  require_once '../models/formModel.php';
  class FormController {
      private $model;

      public function __construct($model) {
          $this->model = $model;
      }

      public function listForms() {
          return $this->model->getAllForms();
      }

      public function submitForm($data) {
          return $this->model->createForm($data);
      }


  }
?>