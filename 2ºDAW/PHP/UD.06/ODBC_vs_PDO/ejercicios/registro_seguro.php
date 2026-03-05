<form method="POST">
  Nombre: <input type="text" name="nom">
  Email: <input type="text" name="ema">
  <input type="submit" value="Registrar">
</form>

<?php
if ($_POST) {
  include 'conexion_pdo.php';
  $stmt = $pdo->prepare("INSERT INTO usuarios4 (nombre, email) VALUES (:n, :e)");
  $stmt->execute(['n' => $_POST['nom'], 'e' => $_POST['ema']]);
  echo "Registro seguro completado.";
}
?>