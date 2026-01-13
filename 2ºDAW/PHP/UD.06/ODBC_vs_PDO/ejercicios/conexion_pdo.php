<?php
$dsn = "mysql:host=localhost;dbname=daw;charset=utf8mb4";
try {
  $pdo = new PDO($dsn, "root", "");
  $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  echo "Conexión PDO realizada con éxito";
} catch (PDOException $e) {
  echo "Error: " . $e->getMessage();
}
