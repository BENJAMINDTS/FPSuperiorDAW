<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Tarea 3 - Programación básica</title>
	<link rel="stylesheet" href="styles/main.css">
	<link rel="stylesheet" href="styles/forms.css">
	<link rel="stylesheet" href="styles/tables.css">
</head>

<body>
	<div class="container">
		<header>
			<h1>Tarea 3 - Programación básica</h1>
			<p>Ejercicios completos de PHP con HTML, CSS y JavaScript</p>
		</header>

		<!-- Incluir funciones -->
		<?php include 'functions.php'; ?>

		<!-- RA03_a) Formulario para verificar mayoría de edad -->
		<section class="section" id="RA03_a">
			<h2>RA03_a) Verificación de mayoría de edad</h2>

			<?php
			// Procesar formulario de mayoría de edad si se ha enviado
			if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['edad_verificar'])) {
				$edad_ingresada = intval($_POST['edad_verificar']);

				if ($edad_ingresada < 0) {
					echo "<div class='message error'>La edad no puede ser negativa</div>";
				} elseif ($edad_ingresada > 120) {
					echo "<div class='message error'>Por favor, ingresa una edad válida</div>";
				} else {
					$resultado = verificarMayoriaEdad($edad_ingresada);
					$clase_mensaje = ($edad_ingresada >= 18) ? 'success' : 'warning';
					echo "<div class='message $clase_mensaje'>";
					echo "<h3>Resultado:</h3>";
					echo "<p><strong>Edad ingresada:</strong> $edad_ingresada años</p>";
					echo "<p><strong>Estado:</strong> $resultado</p>";
					echo "</div>";
				}
			}
			?>

			<div class="form-container">
				<form method="POST" action="<?php echo htmlentities($_SERVER['PHP_SELF']); ?>#RA03_a">
					<div class="form-group">
						<label for="edad_verificar">Ingresa tu edad:</label>
						<input type="number" id="edad_verificar" name="edad_verificar"
							min="0" max="120" required
							placeholder="Ej: 18, 25, 16...">
						<small class="form-help">Ingresa tu edad para verificar si eres mayor de edad</small>
					</div>
					<div class="form-actions">
						<button type="submit">Verificar Edad</button>
						<button type="reset" class="btn-secondary">Limpiar</button>
					</div>
				</form>
			</div>
		</section>

		<!-- RA03_c) Formulario para generar array decreciente -->
		<section class="section" id="RA03_c">
			<h2>RA03_c) Generar array decreciente de tres en tres</h2>

			<?php
			// Procesar formulario de generación de array
			if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['valor_array'])) {
				$valor_array = trim($_POST['valor_array']);

				if (empty($valor_array)) {
					echo "<div class='message error'>Introduzca un valor para generar el array</div>";
				} elseif (!is_numeric($valor_array)) {
					echo "<div class='message error'>Introduzca un valor numérico válido</div>";
				} elseif ($valor_array < 0) {
					echo "<div class='message error'>El valor debe ser positivo o cero</div>";
				} else {
					$valor_array = intval($valor_array);
					$arrayGenerado = generarArray($valor_array);

					echo "<div class='message success'>";
					echo "<h3>Array generado exitosamente:</h3>";
					echo "<p><strong>Valor inicial:</strong> $valor_array</p>";
					echo "<p><strong>Array resultante:</strong> [" . implode(", ", $arrayGenerado) . "]</p>";
					echo "<p><strong>Número de elementos:</strong> " . count($arrayGenerado) . "</p>";
					echo "</div>";

					// Mostrar la tabla con el array generado
					echo "<h3>Visualización en tabla:</h3>";
					echo tabla($arrayGenerado);
				}
			}
			?>

			<div class="form-container">
				<form method="POST" action="<?php echo htmlentities($_SERVER['PHP_SELF']); ?>#RA03_c">
					<div class="form-group">
						<label for="valor_array">Ingresa un número para generar el array:</label>
						<input type="number" id="valor_array" name="valor_array"
							min="0" max="100" required
							placeholder="Ej: 15, 20, 8...">
						<small class="form-help">Se generará un array decreciente de 3 en 3 hasta 0</small>
					</div>
					<div class="form-actions">
						<button type="submit">Generar Array</button>
						<button type="reset" class="btn-secondary">Limpiar</button>
					</div>
				</form>
			</div>
		</section>

		<!-- RA03_b) Bucles para crear tabla HTML -->
		<section class="section">
			<h2>RA03_b) Crear tabla HTML con bucles</h2>
			<?php
			// Ejemplo de uso de la función tabla
			$arrayEjemplo = generarArray(15);
			echo "<p>Ejemplo de tabla generada con array [15, 12, 9, 6, 3, 0]:</p>";
			echo tabla($arrayEjemplo);
			?>
		</section>

		<!-- RA03_d) Formulario con método POST -->
		<section class="section" id="RA03_d">
			<h2>RA03_d) Formulario con método POST</h2>
			<div class="form-container">
				<form method="POST" action="procesar.php">
					<div class="form-group">
						<label for="nombre">Nombre:</label>
						<input type="text" id="nombre" name="nombre" required placeholder="Ingresa tu nombre completo">
					</div>
					<div class="form-group">
						<label for="edad">Edad:</label>
						<input type="number" id="edad" name="edad" min="0" max="120" required placeholder="Ingresa tu edad">
					</div>
					<div class="form-actions">
						<button type="submit">Enviar Datos</button>
						<button type="reset" class="btn-secondary">Limpiar</button>
					</div>
				</form>
			</div>
		</section>

		<!-- RA03_e) Formulario con procesamiento en la misma página -->
		<section class="section" id="RA03_e">
			<h2>RA03_e) Formulario con procesamiento en la misma página</h2>

			<?php
			// Procesar el formulario si se ha enviado
			if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["valor"])) {
				$valor = trim($_POST["valor"]);

				// Validar el valor proporcionado
				if (empty($valor)) {
					echo "<div class='message error'>Introduzca un valor</div>";
				} elseif (!is_numeric($valor)) {
					echo "<div class='message error'>Introduzca un valor numérico</div>";
				} elseif ($valor < 0) {
					echo "<div class='message error'>Introduzca un valor positivo</div>";
				} elseif ($valor > 10) {
					echo "<div class='message error'>Número demasiado grande</div>";
				} elseif ($valor >= 0 && $valor <= 10) {
					echo "<div class='message success'>Array generado para valor <strong>$valor</strong>:</div>";
					$arrayGenerado = generarArray($valor);
					echo tabla($arrayGenerado);
				} else {
					echo "<div class='message error'>Valor desconocido</div>";
				}
			} else {
				echo "<div class='message info'>No se ha introducido ningún valor</div>";
			}
			?>

			<div class="form-container">
				<form method="POST" action="<?php echo htmlentities($_SERVER['PHP_SELF']); ?>#RA03_e">
					<div class="form-group">
						<label for="valor">Valor (0-10):</label>
						<input type="number" id="valor" name="valor" min="0" max="10"
							placeholder="Ingresa un valor entre 0 y 10">
						<small class="form-help">Se generará un array decreciente de 3 en 3</small>
					</div>
					<div class="form-actions">
						<button type="submit">Generar Array</button>
						<button type="reset" class="btn-secondary">Limpiar</button>
					</div>
				</form>
			</div>
		</section>

		<!-- RA03_f) Enlace con método GET -->
		<section class="section" id="RA03_f">
			<h2>RA03_f) Enlace con método GET</h2>

			<div class="form-container">
				<form method="GET" action="bienvenida.php">
					<div class="form-group">
						<label for="nombre">Ingresa tu nombre:</label>
						<input type="text" id="nombre" name="nombre"
							required placeholder="Ingresa tu nombre">
						<small class="form-help">Se redirigirá a la página de bienvenida usando método GET</small>
					</div>
					<div class="form-actions">
						<button type="submit">Ir a Bienvenida</button>
						<button type="reset" class="btn-secondary">Limpiar</button>
					</div>
				</form>
			</div>
		</section>

		<!-- RA03_g) Bucle for con números pares e impares -->
		<section class="section">
			<h2>RA03_g) Bucle for con números pares e impares</h2>
			<div class="table-responsive">
				<table class="data-table">
					<thead>
						<tr>
							<th>Número</th>
							<th>Tipo</th>
						</tr>
					</thead>
					<tbody>
						<?php
						// Bucle for para imprimir números del 1 al 10 y determinar si son pares o impares
						for ($i = 1; $i <= 10; $i++) {
							// Determinar si el número es par o impar
							$tipo = ($i % 2 == 0) ? "Par" : "Impar";
							$color = ($i % 2 == 0) ? "table-success" : "table-info";

							echo "<tr class='$color'>";
							echo "<td><strong>$i</strong></td>";
							echo "<td>$tipo</td>";
							echo "</tr>";
						}
						?>
					</tbody>
				</table>
			</div>
		</section>
	</div>
</body>

</html>