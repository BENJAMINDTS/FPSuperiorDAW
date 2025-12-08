<?php
require_once '../../config/database.php';
require_once '../../models/productoModel.php';
class ProductoController
{
  private $model;
  public function __construct($model)
  {
    $this->model = $model;
  }
  public function listProducts()
  {
    $products = $this->model->getAllProducts(); {
      foreach ($products as $product) {
        echo "Nombre: " . $product['nombre'] . " - Precio: " . $product['precio'] . "<br>";
      }
    }
  }
}
