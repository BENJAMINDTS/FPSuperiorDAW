<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>Lista de Autores y Libros</title>
  <link rel="stylesheet" href="estilos.css">
</head>

<body>

  <h1>Catálogo de Biblioteca</h1>

  <?php
  require_once 'Libros.php';

  // Configuración de conexión (ajusta 'root' y '' según tu XAMPP)
  $servidor = "localhost";
  $db = "daw";
  $usuario = "root";
  $pass = "";

  $gestorLibros = new Libros();
  $conexion = $gestorLibros->conexion($servidor, $db, $usuario, $pass);

  if ($conexion) {
    // 1. Obtener todos los autores
    $autores = $gestorLibros->consultarAutores($conexion);

    if ($autores) {
      foreach ($autores as $autor) {
        echo "<div class='autor-card'>";
        // Mostrar datos del Autor
        echo "<div class='autor-info'>";
        echo "Autor: " . htmlspecialchars($autor->nombre) . " " . htmlspecialchars($autor->apellidos) .
          " <small>(" . htmlspecialchars($autor->nacionalidad) . ")</small>";
        echo "</div>";

        // 2. Obtener libros de este autor
        $libros = $gestorLibros->consultarLibros($conexion, $autor->id);

        if ($libros) {
          echo "<table>";
          echo "<thead><tr><th>ID</th><th>Título</th><th>Fecha Publicación</th></tr></thead>";
          echo "<tbody>";
          foreach ($libros as $libro) {
            // Formatear fecha para mostrarla bonita
            $fecha = date("d/m/Y", strtotime($libro->f_publicacion));
            echo "<tr>";
            echo "<td>" . $libro->id . "</td>";
            echo "<td>" . htmlspecialchars($libro->titulo) . "</td>";
            echo "<td>" . $fecha . "</td>";
            echo "</tr>";
          }
          echo "</tbody></table>";
        } else {
          echo "<p><em>No hay libros registrados para este autor.</em></p>";
        }
        echo "</div>"; // Fin autor-card
      }
    } else {
      echo "<p>No se encontraron autores.</p>";
    }
  } else {
    echo "<p style='color:red'>Error: No se pudo conectar a la base de datos.</p>";
  }
  ?>

</body>

</html>