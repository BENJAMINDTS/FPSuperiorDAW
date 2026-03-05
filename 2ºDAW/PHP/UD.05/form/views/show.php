<?php
require_once "../config/database.php";
require_once "../models/formModel.php";
require_once "../controllers/formController.php";

// Iniciar sesión para mensajes flash
session_start();

$formModel = new FormModel($connection);
$formController = new FormController($formModel);

// Manejar el envío del formulario (PRG Pattern)
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $formData = [
        'nombre' => $_POST['nombre'],
        'email' => $_POST['email'],
        'mensaje' => $_POST['mensaje']
    ];
    $success = $formController->submitForm($formData);
    
    // Guardar mensaje en sesión
    if ($success) {
        $_SESSION['form_message'] = 'success';
    } else {
        $_SESSION['form_message'] = 'error';
    }
    
    // Redirección para evitar reenvío
    header("Location: " . $_SERVER['PHP_SELF']);
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../css/style.css">
  <title>Form Details</title>
</head>

<body class="container">

  <div class="header">
    <h1>Form Details</h1>
    <p>Gestión completa de formularios del sistema</p>
  </div>
  
  <div class="table-container">
    <table class="form-table">
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Message</th>
      </tr>
      <?php
      $forms = $formController->listForms();
      if (empty($forms)) {
        echo '<tr><td colspan="3" class="no-forms">No hay formularios registrados</td></tr>';
      } else {
        foreach ($forms as $form) {
          echo "<tr>";
          echo "<td class='form-name'>" . htmlspecialchars($form['nombre']) . "</td>";
          echo "<td class='form-email'>" . htmlspecialchars($form['email']) . "</td>";
          echo "<td class='form-message'>" . htmlspecialchars($form['mensaje']) . "</td>";
          echo "</tr>";
        }
      }
      ?>
    </table>
  </div>

  <div class="search-section">
    <h2>Submit New Form</h2>
    <form method="POST" action="" class="submit-form">
      <div class="form-group">
        <label for="nombre">Name:</label>
        <input type="text" id="nombre" name="nombre" required>
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="mensaje">Message:</label>
        <textarea id="mensaje" name="mensaje" required></textarea>
      </div>
      <button type="submit" class="btn">Submit Form</button>
    </form>
    
    <?php
    // Mostrar mensaje de sesión si existe
    if (isset($_SESSION['form_message'])) {
        echo '<div class="submission-results">';
        if ($_SESSION['form_message'] === 'success') {
            echo "<div class='success-message'>Form submitted successfully!</div>";
        } else {
            echo "<div class='error-message'>Failed to submit form. Please try again.</div>";
        }
        echo '</div>';
        
        // Limpiar el mensaje después de mostrarlo
        unset($_SESSION['form_message']);
    }
    ?>
  </div>
</body>

</html>