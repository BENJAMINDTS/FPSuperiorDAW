<form method="POST">
  Nombre a buscar: <input type="text" name="nombre">
  <input type="submit" value="Buscar">
</form>

<?php
if ($_POST) {
  $con = odbc_connect("mi_conexion", "root", "");
  $nombre = $_POST['nombre'];
  $res = odbc_exec($con, "SELECT * FROM usuarios4 WHERE nombre = '$nombre'");

  if ($fila = odbc_fetch_array($res)) {
    echo "Usuario encontrado: " . $fila['nombre'] . " (" . $fila['email'] . ")";
  } else {
    echo "No existe el usuario.";
  }
  odbc_close($con);
}
?>