<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cookie Borrada - Tarea 2</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>

<body>
  <header class="header">
    <div class="header-content">
      <h1>Tarea 2 - Cookie Borrada</h1>
      <nav>
        <ul class="nav-menu">
          <li><a href="../index.php">Inicio</a></li>
          <li><a href="actualizar_cookie.php">Actualizar Cookie</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <div class="main-content">
    <div class="container">
      <h1>Cookie Eliminada</h1>

      <div class="success">
        <h2>Operación Completada</h2>
        <p>La cookie "usuario" ha sido eliminada correctamente.</p>
        <p>La cookie se estableció con expiración en el pasado para forzar su eliminación.</p>
      </div>

      <div class="task-links">
        <a href="actualizar_cookie.php" class="btn">Establecer Nueva Cookie</a>
        <a href="../index.php" class="btn btn-secondary">Volver al Inicio</a>
      </div>
    </div>
  </div>

  <footer class="footer">
    <div class="footer-content">
      <p>Tarea 2 - Actualizar y Eliminar Cookies</p>
    </div>
  </footer>
</body>

</html>