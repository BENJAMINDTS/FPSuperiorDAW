<?php
echo "<h3>Resultados con ODBC</h3>";
$c1 = odbc_connect("mi_conexion", "root", "");
$r1 = odbc_exec($c1, "SELECT * FROM usuarios4");
while ($f = odbc_fetch_array($r1)) echo $f['nombre'] . "<br>";

echo "<h3>Resultados con PDO</h3>";
$c2 = new PDO("mysql:host=localhost;dbname=daw", "root", "");
foreach ($c2->query("SELECT * FROM usuarios4") as $f) echo $f['nombre'] . "<br>";
?>

<hr>
<p><b>¿Cuál es más flexible?</b> PDO, ya que permite cambiar de base de datos sin cambiar el código.</p>
<p><b>¿Cuál es más seguro?</b> PDO, por su gestión nativa de consultas preparadas.</p>
<p><b>¿Cuál usarías?</b> PDO, es el estándar actual y más eficiente.</p>