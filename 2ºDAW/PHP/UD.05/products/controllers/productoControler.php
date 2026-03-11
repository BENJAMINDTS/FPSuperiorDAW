<?php
require_once '../../config/database.php';
require_once '../../models/productoModel.php';

/**
 * Clase ProductoController
 *
 * Controlador encargado de gestionar la lógica de presentación y operaciones 
 * de los productos, utilizando el modelo ProductoModel.
 *
 * @package Controllers
 * @author BenjaminDTS
 */
class ProductoController
{
  /**
   * @var ProductoModel Instancia del modelo encargado de los datos de productos.
   */
  private $model;

  /**
   * Constructor de la clase ProductoController.
   *
   * @param ProductoModel $model Instancia del modelo de productos.
   */
  public function __construct($model)
  {
    $this->model = $model;
  }

  /**
   * Obtiene todos los productos desde el modelo y los imprime directamente en pantalla.
   *
   * @return void Este método no retorna un valor, sino que imprime el HTML directamente.
   */
  public function listProducts()
  {
    $products = $this->model->getAllProducts();

    // Iteramos sobre cada producto para imprimir su nombre y precio
    foreach ($products as $product) {
      echo "Nombre: " . $product['nombre'] . " - Precio: " . $product['precio'] . "<br>";
    }
  }
}
