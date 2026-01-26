<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Cliente API Biblioteca DWES</title>
	<link rel="stylesheet" href="styles.css">
</head>

<body>

	<div class="container">
		<header>
			<h1>Biblioteca DWES - Cliente</h1>
			<nav>
				<a href="?action=get_listado_autores">Listado de Autores</a>
				<a href="?action=get_listado_libros">Listado de Libros</a>
			</nav>
		</header>

		<?php

		// Configuración de la URL base de la API

		define('API_BASE_URL', 'http://localhost/Tarea/api.php');


		$action = isset($_GET["action"]) ? $_GET["action"] : "get_listado_autores";
		// VISTA 1: DATOS DE UN AUTOR ESPECÍFICO

		if ($action == "get_datos_autor" && isset($_GET["id"])) {
			$json = file_get_contents(API_BASE_URL . '?action=get_datos_autor&id=' . $_GET["id"]);
			$app_info = json_decode($json);

			if (isset($app_info->error)) {
				echo "<h2>Error</h2><p>" . $app_info->error . "</p>";
			} else {
				echo "<h2>Ficha del Autor</h2>";
				echo "<div class='detalle'>";
				echo "<p><strong>Nombre:</strong> " . $app_info->datos->nombre . "</p>";
				echo "<p><strong>Apellidos:</strong> " . $app_info->datos->apellidos . "</p>";
				echo "<p><strong>Nacionalidad:</strong> " . $app_info->datos->nacionalidad . "</p>";
				echo "</div>";

				echo "<h3>Libros publicados por este autor:</h3>";
				if (count($app_info->libros) > 0) {
					echo "<ul>";
					foreach ($app_info->libros as $libro) {
						echo "<li><a href='?action=get_datos_libro&id=" . $libro->id . "'>" . $libro->titulo . "</a></li>";
					}
					echo "</ul>";
				} else {
					echo "<p>No hay libros registrados para este autor.</p>";
				}
			}
		}


		// VISTA 2: DATOS DE UN LIBRO ESPECÍFICO

		elseif ($action == "get_datos_libro" && isset($_GET["id"])) {
			$json = file_get_contents(API_BASE_URL . '?action=get_datos_libro&id=' . $_GET["id"]);
			$libro_info = json_decode($json);

			if (!$libro_info) {
				echo "<h2>Error</h2><p>Libro no encontrado.</p>";
			} else {
				echo "<h2>Ficha del Libro</h2>";
				echo "<div class='detalle'>";
				echo "<p><strong>Título:</strong> " . $libro_info->titulo . "</p>";
				echo "<p><strong>Fecha de Publicación:</strong> " . $libro_info->f_publicacion . "</p>";
				echo "<p><strong>Autor:</strong> <a href='?action=get_datos_autor&id=" . $libro_info->id_autor . "'>" . $libro_info->nombre . " " . $libro_info->apellidos . "</a></p>";
				echo "</div>";
			}
		}


		// VISTA 3: LISTADO GENERAL DE LIBROS

		elseif ($action == "get_listado_libros") {
			$json = file_get_contents(API_BASE_URL . '?action=get_listado_libros');
			$lista_libros = json_decode($json);

			echo "<h2>Todos los Libros</h2>";
			echo "<ul>";
			foreach ($lista_libros as $libro) {
				echo "<li><a href='?action=get_datos_libro&id=" . $libro->id . "'>" . $libro->titulo . "</a></li>";
			}
			echo "</ul>";
		}


		// VISTA 4: LISTADO GENERAL DE AUTORES

		else {
			$json = file_get_contents(API_BASE_URL . '?action=get_listado_autores');
			$lista_autores = json_decode($json);

			echo "<h2>Todos los Autores</h2>";
			echo "<ul>";
			foreach ($lista_autores as $autor) {
				echo "<li><a href='?action=get_datos_autor&id=" . $autor->id . "'>" . $autor->nombre . " " . $autor->apellidos . "</a></li>";
			}
			echo "</ul>";
		}
		?>

	</div>
</body>

</html>