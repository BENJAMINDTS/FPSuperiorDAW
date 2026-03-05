<?php

/**
 * Vista: Layout Header
 * Cabecera común para todas las páginas con enlaces a CSS separados
 * @package Views
 * @category Layouts
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
  die("Acceso directo no permitido. Debe acceder a través del controlador.");
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><?php echo $titulo ?? 'Sistema DAW'; ?></title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

  <!-- CSS Personalizados -->
  <link rel="stylesheet" href="/daw/public/css/style.css">

  <!-- CSS específico de la página -->
  <?php if (isset($css_extra)): ?>
    <link rel="stylesheet" href="/daw/public/css/<?php echo $css_extra; ?>.css">
  <?php endif; ?>
</head>

<body>
  <!-- Barra de navegación -->
  <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
    <div class="container">
      <a class="navbar-brand" href="/daw">
        <i class="fas fa-store me-2"></i>Sistema DAW
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <a class="nav-link" href="/daw/sugerencia/index">
              <i class="fas fa-comment-dots me-1"></i> Sugerencias
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/daw/usuario/index">
              <i class="fas fa-users me-1"></i> Usuarios
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/daw/producto/index">
              <i class="fas fa-box me-1"></i> Productos
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Contenedor principal -->
  <div class="container mt-4">
    <div class="row">
      <div class="col-12">
        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb" class="mb-4">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/daw">Inicio</a></li>
            <li class="breadcrumb-item active"><?php echo $titulo ?? 'Sistema'; ?></li>
          </ol>
        </nav>

        <!-- Título de página -->
        <div class="d-flex justify-content-between align-items-center mb-4">
          <h1 class="h3">
            <i class="fas fa-<?php echo $icono ?? 'home'; ?> me-2"></i>
            <?php echo $titulo ?? 'Sistema DAW'; ?>
          </h1>
          <?php if (isset($accion)): ?>
            <a href="<?php echo $accion['url']; ?>" class="btn btn-primary">
              <i class="fas fa-<?php echo $accion['icono'] ?? 'plus'; ?> me-1"></i>
              <?php echo $accion['texto']; ?>
            </a>
          <?php endif; ?>
        </div>