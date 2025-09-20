<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lector de Archivos por Línea</title>
</head>
<body>
  <h1>Vamos a leer nuestro archivo datos.txt linea por linea</h1>
  <?php
    $archivo = fopen("datos.txt", "r") or die("No se pudo abrir el archivo.");
    while(!feof($archivo)) {
        $linea = fgets($archivo);
        echo nl2br(htmlspecialchars($linea));
    }
    fclose($archivo);
  ?>
</body>
</html>