<?php
$mensaje = "Bienvenido al sitio";

function mostrar_mensaje() {
    global $mensaje;
    $mensaje_local = " - Mensaje adicional desde la función";
    echo $mensaje . $mensaje_local . "\n";
}

mostrar_mensaje();
?>