<!-- Area_Circulo.php -->
<!DOCTYPE html>
<html>
<head>
    <title>Área del Círculo</title>
</head>
<body>
    <h1>Calcular Área del Círculo</h1>
    <form method="post">
        <label for="radio">Ingrese el radio:</label>
        <input type="number" step="any" name="radio" id="radio" required>
        <button type="submit">Calcular</button>
    </form>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $radio = $_POST["radio"];
        function CalcularArea($radio) : float {
            return pi() * pow($radio, 2);
            
        }
        echo "<h2>El área del círculo es: " . CalcularArea($radio) . "</h2>";
    }
    ?>
</body>
</html>
