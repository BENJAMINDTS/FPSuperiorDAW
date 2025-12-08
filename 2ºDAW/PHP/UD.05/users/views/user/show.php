<?php
require_once '../../config/database.php';
require_once '../../models/userModel.php';
require_once '../../controllers/userController.php';
$userModel = new ProductoModel($connection);
$userController = new UserController($userModel);
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../css/style.css">
  <title>User Details</title>
</head>

<body>
  <div class="container">
    <div class="header">
      <h1>User Details</h1>
      <p>Gestión completa de usuarios del sistema</p>
    </div>

    <div class="table-container">
      <table class="user-table">
        <tr>
          <th>Name</th>
          <th>Email</th>
        </tr>
        <?php
        $users = $userController->listUsers();
        if (empty($users)) {
          echo '<tr><td colspan="2" class="no-users">No hay usuarios registrados</td></tr>';
        } else {
          foreach ($users as $user) {
            echo "<tr>";
            echo "<td class='user-name'>" . htmlspecialchars($user['nombre']) . "</td>";
            echo "<td class='user-email'>" . htmlspecialchars($user['email']) . "</td>";
            echo "</tr>";
          }
        }
        ?>
      </table>
    </div>

    <div class="search-section">
      <h2>Find by ID</h2>
      <form method="GET" action="" class="search-form">
        <div class="form-group">
          <label for="user_id">User ID:</label>
          <input type="number" id="user_id" name="user_id" required min="1">
        </div>
        <button type="submit" class="btn">Get User</button>
      </form>

      <?php
      if (isset($_GET['user_id'])) {
        $userId = intval($_GET['user_id']);
        $user = $userController->getUser($userId);
        echo '<div class="search-results">';
        if ($user) {
          echo "<h3>User Information:</h3>";
          echo '<div class="user-info">';
          echo "<p><strong>Name:</strong> " . htmlspecialchars($user['nombre']) . "</p>";
          echo "<p><strong>Email:</strong> " . htmlspecialchars($user['email']) . "</p>";
          echo '</div>';
        } else {
          echo "<div class='no-results'>No user found with ID " . htmlspecialchars($userId) . "</div>";
        }
        echo '</div>';
      }
      ?>
    </div>
  </div>
</body>

</html>