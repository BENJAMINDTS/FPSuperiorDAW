<?php

$current_script = $_SERVER['SCRIPT_NAME'];
$current_page = basename($current_script, '.php');
?>

<nav>
    <a href="index.php" class="<?php echo ($current_page == 'index') ? 'activo' : ''; ?>">🏠 Inicio</a>
    <a href="servicios.php" class="<?php echo ($current_page == 'servicios') ? 'activo' : ''; ?>">⚙️ Servicios</a>
    <a href="contacto.php" class="<?php echo ($current_page == 'contacto') ? 'activo' : ''; ?>">📞 Contacto</a>
</nav>