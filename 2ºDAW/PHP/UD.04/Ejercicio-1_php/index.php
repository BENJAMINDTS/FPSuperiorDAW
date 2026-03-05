<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Proyecto Cookies - Menú Principal</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>
  <header class="header">
    <div class="header-content">
      <h1>Proyecto de Cookies PHP</h1>
      <nav>
        <ul class="nav-menu">
          <li><a href="#tareas">Tareas</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <div class="main-content">
    <div class="container">
      <h1>Menú de Navegación</h1>

      <div class="date-container">
        <div class="date">
          Fecha actual: <?php echo date('d/m/Y H:i:s'); ?>
        </div>
      </div>

      <div class="tasks-grid" id="tareas">
        <div class="task-card">
          <h2>Tarea 1 - Cookie Basica</h2>
          <p>Establecer y leer una cookie basica con caducidad de 7 dias</p>
          <div class="task-links">
            <a href="tarea-1-cookie-basica/set_cookie.php" class="btn">Establecer Cookie</a>
            <a href="tarea-1-cookie-basica/leer_cookie.php" class="btn btn-secondary">Leer Cookie</a>
          </div>
        </div>

        <div class="task-card">
          <h2>Tarea 2 - Actualizar y Eliminar</h2>
          <p>Gestion avanzada de cookies: actualizar valores y eliminar</p>
          <div class="task-links">
            <a href="tarea-2-actualizar-borrar/actualizar_cookie.php" class="btn">Actualizar Cookie</a>
            <a href="tarea-2-actualizar-borrar/borrar_cookie.php" class="btn btn-secondary">Borrar Cookie</a>
          </div>
        </div>

        <div class="task-card">
          <h2>Tarea 3 - Preferencia de Idioma</h2>
          <p>Sistema de seleccion de idioma con cookie persistente</p>
          <div class="task-links">
            <a href="tarea-3-preferencia-idioma/index.php" class="btn">Seleccionar Idioma</a>
          </div>
        </div>

        <div class="task-card">
          <h2>Tarea 4 - Carrito Persistente</h2>
          <p>Carrito de compras con almacenamiento en cookies</p>
          <div class="task-links">
            <a href="tarea-4-carrito-persistente/catalog.php" class="btn">Ver Catalogo</a>
            <a href="tarea-4-carrito-persistente/cart.php" class="btn btn-secondary">Ver Carrito</a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <footer class="footer">
    <div class="footer-content">
      <p>&copy; Benjamin Santiago González</p>
    </div>
  </footer>
</body>

</html>