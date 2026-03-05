<?php
//Functions Tarea 3

//RA03_a) Verifica si una persona es mayor de edad

function verificarMayoriaEdad($edad)
{
	// Verificar si la edad es mayor o igual a 18
	if ($edad >= 18) {
		return "Eres mayor de edad";
	} else {
		return "Eres menor de edad";
	}
}

//RA03_c) Genera un array decreciente de tres en tres hasta cero

function generarArray($valor)
{
	$numeros = array(); // Crear array vacío

	// Generar valores decrecientes de tres en tres hasta cero
	for ($i = $valor; $i >= 0; $i -= 3) {
		$numeros[] = $i; // Agregar valor al array
	}

	return $numeros; // Retornar el array generado
}

//RA03_b) Crea una tabla HTML a partir de un array

function tabla($valores)
{
	// Iniciar la tabla HTML
	$tabla = "<table class='data-table'>";
	$tabla .= "<thead><tr><th>Índice</th><th>Valor</th></tr></thead>";
	$tabla .= "<tbody>";

	// Recorrer el array con un bucle foreach
	foreach ($valores as $indice => $valor) {
		$tabla .= "<tr>";
		$tabla .= "<td>$indice</td>";
		$tabla .= "<td>$valor</td>";
		$tabla .= "</tr>";
	}

	$tabla .= "</tbody></table>"; // Cerrar la tabla
	return $tabla; // Retornar el código HTML de la tabla
}
