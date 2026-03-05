<?php

/**
 * Archivo de configuración de conexión a base de datos MySQL usando PDO
 * Este archivo establece una conexión a la base de datos 'daw' usando PDO.
 * Implementa manejo de excepciones para capturar errores de conexión.
 * @package Database
 * @category Configuration
 * @author Benjamín Santiago González
 * @version 1.0.0
 * @since 2025
 * @license MIT
 * @copyright 2025 Benjamín Santiago González
 */

/**
 * Nombre de usuario para la conexión a MySQL
 * @var string USER Usuario de la base de datos
 */
$user = 'root';

/**
 * Contraseña para la conexión a MySQL
 * @var string PASS Contraseña del usuario
 */
$pass = '';

/**
 * Dirección del servidor de base de datos
 * @var string HOST Servidor MySQL
 */
$host = 'localhost';

/**
 * Nombre de la base de datos a utilizar
 * @var string DBNAME Nombre de la base de datos
 */
$dbname = 'daw';

/**
 * Intenta establecer una conexión PDO a la base de datos MySQL
 * @global PDO $connection Variable global que almacena la conexión PDO
 * @throws PDOException Si la conexión a la base de datos falla
 */
try {
  /**
   * Crea una nueva instancia de PDO para conectarse a MySQL
   * @param string $dsn Data Source Name: "mysql:host=localhost;dbname=daw"
   * @param string $username Nombre de usuario
   * @param string $password Contraseña
   * @return PDO Objeto de conexión PDO
   */
  $connection = new PDO("mysql:host=$host;dbname=$dbname", $user, $pass);

  /**
   * Configura el atributo de manejo de errores de PDO
   * PDO::ATTR_ERRMODE: Define cómo PDO reporta los errores
   * PDO::ERRMODE_EXCEPTION: Lanza excepciones cuando ocurren errores
   * @param int $attribute Atributo a configurar (PDO::ATTR_ERRMODE)
   * @param mixed $value Valor del atributo (PDO::ERRMODE_EXCEPTION)
   * @return bool True si se configuró correctamente
   */
  $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
  /**
   * Maneja y muestra el error cuando falla la conexión a la base de datos
   * @param PDOException $e Excepción capturada con información del error
   * @return void
   */
  echo 'Connection failed: ' . $e->getMessage();
}
