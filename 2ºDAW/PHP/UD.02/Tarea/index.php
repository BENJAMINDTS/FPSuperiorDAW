<?php
include 'contents/header.php';
?>
<body>
    <div class="main-content">
        <div class="container">
            <h1>Saludo Personalizado</h1>
            
            <div class="date-container">
                <p class="date">
                    <?php
                    echo "Fecha actual: " . date("Y-m-d");
                    ?>
                </p>
            </div>
            
            <div class="form-container">
                <form method="POST" action="">
                    <div class="form-group">
                        <label for="nombre">Ingresa tu nombre:</label>
                        <input type="text" id="nombre" name="nombre" placeholder="Escribe tu nombre aquí" required>
                    </div>
                    <button type="submit" class="btn">Saludar</button>
                </form>
            </div>
            
            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['nombre'])) {
                $nombre = htmlspecialchars(trim($_POST['nombre']));
                if (!empty($nombre)) {
                    echo '<div class="greeting">';
                    echo '<h2>¡Saludo Personalizado!</h2>';
                    echo '<p>Hola, <strong>' . $nombre . '</strong>. ¡Es un placer saludarte!</p>';
                    echo '</div>';
                }
            }
            ?>
            
            <div class="instructions">
                <h3>Instrucciones:</h3>
                <ul>
                    <li>Ingresa tu nombre en el campo de texto</li>
                    <li>Haz clic en el botón "Saludar"</li>
                    <li>Recibirás un saludo personalizado con tu nombre</li>
                    <li>La fecha se actualiza automáticamente cada día</li>
                </ul>
            </div>
        </div>
    </div>
<?php
include 'contents/footer.php';
?>