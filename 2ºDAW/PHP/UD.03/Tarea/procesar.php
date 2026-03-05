<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Procesar Formulario - Tarea 3</title>
	<link rel="stylesheet" href="styles/main.css">
	<link rel="stylesheet" href="styles/forms.css">
</head>

<body>
	<div class="container">
		<div class="section">
			<h1>Resultado del Formulario</h1>

			<?php
			// Incluir funciones
			include 'functions.php';

			// Verificar si se han enviado los datos por POST
			if ($_SERVER["REQUEST_METHOD"] == "POST") {
				// Obtener y sanitizar los datos del formulario
				$nombre = htmlspecialchars(trim($_POST["nombre"]));
				$edad = intval($_POST["edad"]);

				// Mostrar el mensaje personalizado
				echo "<div class='message success'>";
				echo "<h3>¡Datos recibidos correctamente!</h3>";
				echo "Hola <strong>$nombre</strong>, tienes <strong>$edad años</strong>";
				echo "</div>";

				// Verificar si es mayor de edad
				echo "<div class='message " . ($edad >= 18 ? "success" : "warning") . "'>";
				echo "<strong>" . verificarMayoriaEdad($edad) . "</strong>";
				echo "</div>";
			} else {
				echo "<div class='message error'>";
				echo "<h3>Error</h3>";
				echo "<p>No se han recibido datos del formulario.</p>";
				echo "</div>";
			}
			?>

			<div class="form-actions">
				<a href="index.php" class="btn">Volver al formulario principal</a>
				<a href="index.php#RA03_d" class="btn-secondary">Volver a llenar el formulario</a>
			</div>
		</div>
	</div>
</body>

</html>