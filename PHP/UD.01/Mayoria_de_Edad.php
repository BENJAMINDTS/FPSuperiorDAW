<!-- Area_Circulo.php -->
<!DOCTYPE html>
<html>
<head>
    <title>¿Eres mayor de edad?</title>
</head>
<body>
    <h1>¿Eres mayor de edad?</h1>
    <form method="post">
        <label for="edad">Ingrese su edad</label>
        <input type="number" step="any" name="edad" id="edad" required>
        <button type="submit">Calcular</button>
    </form>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $edad = $_POST["edad"];
        function CalcularEdad($edad) : string {
            if ($edad >= 18) {
                return "Eres mayor de edad.";
            } else {
                return "Eres menor de edad.";
            }
            
        }
        echo "<h2>" . CalcularEdad($edad) . "</h2>";
    }
    ?>
</body>
</html>
