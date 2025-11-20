<?php
$user = 'root';
$pass = '';
$host = 'localhost';
$dbname = 'daw';
try {
  $connection = new PDO("mysql:host=$host;dbname=$dbname", $user, $pass);
  $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
  echo 'Connection failed: ' . $e->getMessage();
}
