<?php
// Establecer cookie antes de cualquier salida
$cookie_name = "usuario";
$cookie_value = "alumno123";
$expiry = time() + (7 * 24 * 60 * 60); // 7 días
$path = "/";

setcookie($cookie_name, $cookie_value, $expiry, $path);

// Redirigir para evitar problemas con headers ya enviados
header("Location: leer_cookie.php");
exit();
?>