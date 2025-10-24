<?php
if ($_POST) {
    $numero1 = $_POST['number1'];
    $numero2 = $_POST['number2'];
    $numero3 = $_POST['number3'];
    
    $mayor = max($numero1, $numero2, $numero3);
    
    echo "<p>El número mayor es: <strong>$mayor</strong></p>";
}
?>