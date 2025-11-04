<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Bienvenida - Tarea 3</title>
	<link rel="stylesheet" href="styles/main.css">
	<link rel="stylesheet" href="styles/forms.css">
</head>

<body>
	<div class="container">
		<div class="section">
			<h1>Página de Bienvenida</h1>

			<?php
			// Verificar si se ha proporcionado un nombre por GET
			if (isset($_GET["nombre"]) && !empty(trim($_GET["nombre"]))) {
				$nombre = htmlspecialchars(trim($_GET["nombre"]));

				echo "<div class='message success'>";
				echo "<h2>¡Bienvenido, $nombre!</h2>";
				echo "<p>Es un placer tenerte aquí en nuestra página.</p>";
				echo "</div>";
			} else {
				echo "<div class='message error'>";
				echo "<h3>Información incompleta</h3>";
				echo "<p>No se ha proporcionado un nombre válido en la URL.</p>";
				echo "<p>Ejemplo de uso: <code>bienvenida.php?nombre=Juan</code></p>";
				echo "</div>";
			}
			?>

			<div class="form-actions">
				<a href="index.php" class="btn">Volver a la página principal</a>
				<a href="index.php#RA03_f" class="btn-secondary">Probar con otro nombre</a>
			</div>
		</div>
	</div>
</body>

</html>