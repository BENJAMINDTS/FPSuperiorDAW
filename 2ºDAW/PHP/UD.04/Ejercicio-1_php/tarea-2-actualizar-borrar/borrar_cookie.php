<?php
// Borrar la cookie estableciendo expiración en el pasado
$past = time() - 3600; // Una hora en el pasado
setcookie('usuario', '', $past, "/");

// Redirigir para evitar problemas con headers
header("Location: cookie_borrada.php");
exit();
?>