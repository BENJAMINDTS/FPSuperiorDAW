<?php
include 'conexion_pdo.php';
// Nota: Usamos usuarios4 ya que es la tabla que tienes en tu imagen
$stmt = $pdo->query("SELECT id, nombre, email FROM usuarios4");

echo "<ul>";
while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
  echo "<li>" . $row['nombre'] . " - " . $row['email'] . "</li>";
}
echo "</ul>";
