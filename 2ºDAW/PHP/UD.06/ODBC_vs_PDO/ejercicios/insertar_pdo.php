<form method="POST">
  Nombre: <input type="text" name="n"><br>
  Email: <input type="text" name="e"><br>
  <input type="submit" value="Añadir">
</form>

<?php
if ($_POST) {
  include 'conexion_pdo.php';
  $sql = "INSERT INTO usuarios4 (nombre, email) VALUES ('{$_POST['n']}', '{$_POST['e']}')";
  if ($pdo->exec($sql)) {
    echo "Insertado correctamente.";
  }
}
?>