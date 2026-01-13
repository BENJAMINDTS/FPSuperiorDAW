<form method="POST">
  Email: <input type="email" name="e"><br>
  Pass: <input type="password" name="p"><br>
  <input type="submit" value="Entrar">
</form>

<?php
if ($_POST) {
  include 'conexion_pdo.php';
  $stmt = $pdo->prepare("SELECT nombre FROM usuarios4 WHERE email = ? AND password = ?");
  $stmt->execute([$_POST['e'], $_POST['p']]);
  $user = $stmt->fetch();

  if ($user) {
    echo "Bienvenido, " . $user['nombre'];
  } else {
    echo "Acceso denegado.";
  }
}