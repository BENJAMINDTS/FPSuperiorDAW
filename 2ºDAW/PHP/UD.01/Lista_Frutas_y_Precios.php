<!-- fruta_List_and_precio.php -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de frutas</title>
</head>
<body>
    <h1>Lista de frutas</h1>

    <form method="post">
        <label for="fruta">Nombre de la fruta:</label>
        <input type="text" name="fruta" id="fruta" required>
        <label for="price">Introduce el precio:</label>
        <input type="number" step="any" name="price" id="price" required>
        <button type="submit" name="add">Añadir fruta</button>
    </form>

    <form method="post" style="margin-top: 10px;">
        <button type="submit" name="clear">Limpiar lista</button>
    </form>

    <?php
    session_start();

    if (!isset($_SESSION["frutas"])) {
        $_SESSION["frutas"] = [];
        $_SESSION["precio"] = [];
    }

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        if (isset($_POST["add"]) && isset($_POST["fruta"]) && isset($_POST["price"])) {
            $_SESSION["frutas"][] = $_POST["fruta"];
            $_SESSION["precio"][] = $_POST["price"];
        }

        if (isset($_POST["clear"])) {
            $_SESSION["frutas"] = [];
            $_SESSION["precio"] = [];
        }
    }

    function listfrutasAndprecio($frutas, $precios): string {
        $result = "<ul>";
        $count = min(count($frutas), count($precios));
        for ($i = 0; $i < $count; $i++) {
            $nombre = htmlspecialchars($frutas[$i]);
            $precioFormateado = number_format((float)$precios[$i], 2);
            $result .= "<li>$nombre: \$ $precioFormateado</li>";
        }
        $result .= "</ul>";
        return $result;
    }

    if (count($_SESSION["frutas"]) > 0) {
        echo "<h2>Lista de frutas y precios:</h2>";
        echo listfrutasAndprecio($_SESSION["frutas"], $_SESSION["precio"]);
    }
    ?>
</body>
</html>
