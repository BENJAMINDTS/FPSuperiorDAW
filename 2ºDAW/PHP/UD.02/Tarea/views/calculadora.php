<?php
include '../contents/header.php';
?>
<body>
  <div class="main-content">
    <div class="container">
      <h1>Calculadora Simple</h1>
      <div class="calculator">
        <form method="POST" action="">
          <div class="form-group">
            <label for="numero1">Número 1:</label>
            <input type="number" id="numero1" name="numero1" required>
          </div>
          <div class="form-group">
            <label for="numero2">Número 2:</label>
            <input type="number" id="numero2" name="numero2" required>
          </div>
          <div class="form-group">
            <label for="operacion">Operación:</label>
            <select id="operacion" name="operacion" required>
              <option value="sumar">Sumar</option>
              <option value="restar">Restar</option>
              <option value="multiplicar">Multiplicar</option>
              <option value="dividir">Dividir</option>
            </select>
          </div>
          <button type="submit" class="btn">Calcular</button>
        </form>
      </div>
      <?php
      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $numero1 = $_POST['numero1'];
        $numero2 = $_POST['numero2'];
        $operacion = $_POST['operacion'];
        $resultado = null;

        if (!is_numeric($numero1) || !is_numeric($numero2)) {
          echo '<p class="error">Error: Ambos valores deben ser numéricos.</p>';
        } else {
          $numero1 = floatval($numero1);
          $numero2 = floatval($numero2);
          switch ($operacion) {
            case 'sumar':
              $resultado = $numero1 + $numero2;
              break;
            case 'restar':
              $resultado = $numero1 - $numero2;
              break;
            case 'multiplicar':
              $resultado = $numero1 * $numero2;
              break;
            case 'dividir':
              if ($numero2 != 0) {
                $resultado = $numero1 / $numero2;
              } else {
                echo '<p class="error">Error: División por cero no permitida.</p>';
              }
              break;
            default:
              echo '<p class="error">Operación no válida.</p>';
          }
          if ($resultado !== null) {
            echo '<div class="result">';
            echo '<h2>Resultado:</h2>';
            echo "<p>El resultado de la operación es: <strong>{$resultado}</strong></p>";
            echo '</div>';
          }
        }
      }
      ?>
    </div>
  </div>
</body>
<?php
include '../contents/footer.php';
?>