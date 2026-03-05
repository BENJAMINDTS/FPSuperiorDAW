<?php
include '../includes/header.php';
include '../controllers/form_controller.php';

?>
<h1>Esto va a ser una pagina web dinamica</h1>
<h3>Ejercicio 1: Mostrar un mensaje dinamico</h3>
<p><b>Crea una página saludo.php que contenga código HTML con un título y un párrafo. <br>
		Dentro del párrafo, incrusta código PHP que muestre un saludo con tu nombre</b></p>
<?php
const NAME = "Benjamin";
?>
<p>Esto es un parrafo con php dentro y dice mi nombre: <?php echo NAME ?></p>
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<h3>Ejercicio 2: Mostrar la fecha actual</h3>
<p><b>Crea una página fecha.php con una estructura HTML. Dentro del contenido, muestra la fecha actual usando PHP, con el formato: <br>
		“Hoy es martes, 7 de octubre de 2025.”</b></p>
<p>Hoy es <?php echo date("l, j \d\e F \d\e Y") ?>.</p>
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<h3>.Ejercicio 3: Mostrar una lista de productos</h3>
<p><b>Crea una página productos.php que tenga una lista HTML.
		Dentro de la lista, utiliza un array PHP con varios productos 
		(por ejemplo, “Manzanas”, “Peras”,“Naranjas”) y genera los elementos de forma dinámica.</b></p>
<ul>
	<?php
	$products = ["Manzanas", "Peras", "Naranjas", "Platanos", "Fresas"];
	foreach ($products as $product) {
		echo "<li>$product</li>";
	}
	?>
</ul>
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<h3>Ejercicio 4: Formulario con mensaje personalizado</h3>
<p><b>Crea una página formulario.php con un formulario HTML que pida el nombre del usuario (método POST). <br>
		Cuando el usuario lo envíe, muestra un mensaje dentro del mismo HTML que diga: <br>
		“Hola, [nombre], gracias por visitar mi web.”</b></p>
<form method="POST" action="">
	<label for="name">Nombre:</label>
	<input type="text" id="name" name="name" required>
	<input type="submit" value="Enviar">
</form>

<?php
$my_name = isset($_SESSION['page_name']) ? $_SESSION['page_name'] : "";
?>

<p><?php if ($name) {
			echo "Hola, $name, gracias por visitar mi web.";
		} ?></p>
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<h3>Ejercicio 5: Calcular el total de una compra</h3>
<p><b>Crea una página compra.php con una tabla HTML que muestre el nombre del producto, cantidad, precio unitario y el total. <br>
		Declara los productos y precios en un array PHP, y muestra la tabla con un bucle. Al final, muestra el total general.</b></p>
<table border="1">
	<tr>
		<th>Producto</th>
		<th>Cantidad</th>
		<th>Precio Unitario</th>
		<th>Total</th>
	</tr>
	<?php
	$items = [
		["producto" => "Manzanas", "cantidad" => 2, "precio" => 1.5],
		["producto" => "Peras", "cantidad" => 3, "precio" => 2.0],
		["producto" => "Naranjas", "cantidad" => 1, "precio" => 1.0],
	];
	$total_general = 0;
	foreach ($items as $item) {
		$total = $item['cantidad'] * $item['precio'];
		$total_general += $total;
		echo "<tr></tr>
                <td>{$item['producto']}</td>
                <td>{$item['cantidad']}</td>
                <td>\${$item['precio']}</td>
                <td>\$$total</td>
              </tr>";
	}
	?>

	<?php
	include '../includes/footer.php';
	?>