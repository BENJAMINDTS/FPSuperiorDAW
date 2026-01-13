<?php
$con = odbc_connect("mi_conexion", "root", "");
$res = odbc_exec($con, "SELECT id, nombre, email FROM usuarios4");

echo "<table border='1'>
        <tr><th>ID</th><th>Nombre</th><th>Email</th></tr>";

while ($fila = odbc_fetch_array($res)) {
  echo "<tr>
            <td>{$fila['id']}</td>
            <td>{$fila['nombre']}</td>
            <td>{$fila['email']}</td>
          </tr>";
}
echo "</table>";
odbc_close($con);
