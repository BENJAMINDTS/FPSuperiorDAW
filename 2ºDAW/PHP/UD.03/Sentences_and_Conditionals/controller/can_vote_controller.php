<?php
if ($_POST) {
    $edad = $_POST['age'];
    if ($edad >= 18) {
        echo "<h3>Usted puede votar.</h3>";
    } else {
        echo "<h3>Usted no puede votar.</h3>";
    }
} else {
    echo "<h3>No se han recibido datos del formulario.</h3>";
}
?>