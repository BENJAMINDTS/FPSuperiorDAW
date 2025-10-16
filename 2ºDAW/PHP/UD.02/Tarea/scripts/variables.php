```php
<?php
$entero = 10;
$decimal = 8.22;
$booleano = true;
$cadena = "cadena";
define("MI_CONSTANTE", 3.14);

echo "VALORES DE LAS VARIABLES:\n";
echo "Variable entera: " . $entero . "\n";
echo "Variable decimal: " . $decimal . "\n";
echo "Variable booleana: " . ($booleano ? "verdadero" : "falso") . "\n";
echo "Variable cadena: " . $cadena . "\n";
echo "Constante: " . MI_CONSTANTE . "\n";

$suma = $entero + $decimal;
echo "\nRESULTADO DE LA SUMA:\n";
echo $entero . " + " . $decimal . " = " . $suma . "\n";
?>
```