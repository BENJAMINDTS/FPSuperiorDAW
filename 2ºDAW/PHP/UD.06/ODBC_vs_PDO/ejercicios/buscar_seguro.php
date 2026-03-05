<form method="POST">
  Buscar: <input type="text" name="b">
  <input type="submit" value="Filtrar">
</form>

<?php
if ($_POST) {
  include 'conexion_pdo.php';
  $stmt = $pdo->prepare("SELECT * FROM usuarios4 WHERE nombre LIKE ?");
  $stmt->execute(["%" . $_POST['b'] . "%"]);

  foreach ($stmt as $row) {
    echo $row['nombre'] . "<br>";
  }
}
?>