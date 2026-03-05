<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>Dashboard de Usuario</title>
  <link rel="stylesheet" href="css/styles.css">
</head>

<body>
  <div class="container">
    <h2>Bienvenido, <?= htmlspecialchars($_SESSION['usuario']['nombre']) ?>!</h2>

    <p>Panel de control de tu información.</p>

    <h3>Tus Datos:</h3>
    <ul>
      <li><strong>ID:</strong> <?= htmlspecialchars($_SESSION['usuario']['id']) ?></li>
      <li><strong>Email:</strong> <?= htmlspecialchars($_SESSION['usuario']['email']) ?></li>
    </ul>

    <nav>
      <ul>
        <li><a href="index.php?accion=listar">Gestionar Usuarios</a></li>
        <li><a href="index.php?accion=subirArchivo">Subir Archivo</a></li>
        <li><a href="index.php?accion=verArchivos">Mis Archivos</a></li>
        <li><a href="index.php?accion=logout">Cerrar Sesión</a></li>
      </ul>
    </nav>
  </div>
</body>

</html>