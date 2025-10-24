<?php
$vista = $_GET['vista'] ?? 'default';

$vistas_permitidas = [
	'default',
	'month_form',
	'largest_number_form',
	'date',
	'can_vote_form',
	'calificator_form'
];

if (!in_array($vista, $vistas_permitidas)) {
	$vista = 'default';
}

?>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Sentencias y condicionales</title>
	<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<header>
		<nav>
			<a href="#" onclick="cargarVista('default')">Inicio</a>
			<a href="#" onclick="cargarVista('month_form')">Días del Mes</a>
			<a href="#" onclick="cargarVista('largest_number_form')">Número Mayor</a>
			<a href="#" onclick="cargarVista('date')">Fecha</a>
			<a href="#" onclick="cargarVista('can_vote_form')">Puede Votar</a>
			<a href="#" onclick="cargarVista('calificator_form')">Calificaciones</a>
		</nav>
	</header>

	<main>
		<div id="contenido-vista">
			<?php include "views/$vista.php"; ?>
		</div>
	</main>

	<footer>
		<p>2025- &copy; Benjamin.</p>
	</footer>

	<script src="js/script.js"></script>
</body>
</html>