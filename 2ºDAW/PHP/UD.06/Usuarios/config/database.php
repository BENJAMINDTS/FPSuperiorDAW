<?php
$user = 'root';
$pass = '';
$host = 'localhost';
$dbname = 'daw';

try {
  // Creamos la instancia de PDO
  $connection = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8", $user, $pass);
  // Configuramos el modo de error para que lance Excepciones
  $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
  echo 'Connection failed: ' . $e->getMessage();
  die(); // Detenemos la ejecución si no hay base de datos
}
