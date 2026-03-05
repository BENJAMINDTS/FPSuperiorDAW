<?php
$dsn = "mi_conexion";
$usuario = "root";
$password = "";

$conexion = odbc_connect($dsn, $usuario, $password);

if ($conexion) {
  echo "Conexión ODBC realizada correctamente";
  odbc_close($conexion);
} else {
  echo "Error: " . odbc_errormsg();
}
