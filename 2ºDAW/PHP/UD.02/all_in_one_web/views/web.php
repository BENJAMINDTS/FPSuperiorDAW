<?php

$seccion = isset($_GET['seccion']) ? $_GET['seccion'] : 'inicio';
$secciones_validas = ['inicio', 'servicios', 'contacto', 'acerca', 'blog'];
if (!in_array($seccion, $secciones_validas)) {
	$seccion = 'inicio';
}
$pagina_activa = $seccion;
$titulos = [
	'inicio' => 'Inicio',
	'servicios' => 'Nuestros Servicios',
	'contacto' => 'Contacto',
	'acerca' => 'Acerca de Nosotros',
	'blog' => 'Blog'
];
$titulo_pagina = isset($titulos[$seccion]) ? $titulos[$seccion] : 'Inicio';

include '../includes/head.php';
include '../includes/menu.php';
?>




	<div class="contenido">
		<?php
		$archivo_contenido = "../contents/{$seccion}.php";
		if (file_exists($archivo_contenido)) {
			include $archivo_contenido;
		} else {
			include '../contents/index.php';
		}
		?>

		<div class="panel-url">
			<h3>🔧 Sistema de Navegación Dinámica</h3>
			<p>Esta página usa el parámetro GET <code>seccion</code> para cargar contenido dinámicamente.</p>

			<div class="parametros">
				<h4>Parámetros URL disponibles:</h4>
				<ul>
					<li><code>?seccion=inicio</code> - Página de inicio</li>
					<li><code>?seccion=servicios</code> - Nuestros servicios</li>
					<li><code>?seccion=contacto</code> - Información de contacto</li>
					<li><code>?seccion=acerca</code> - Acerca de nosotros</li>
					<li><code>?seccion=blog</code> - Artículos del blog</li>
				</ul>
			</div>

			<div class="info-tecnica">
				<h4>📊 Información Técnica:</h4>
				<p><strong>Sección actual:</strong> <code><?php echo $seccion; ?></code></p>
				<p><strong>URL completa:</strong> <code><?php echo $_SERVER['REQUEST_URI']; ?></code></p>
				<p><strong>Parámetros GET:</strong> <?php echo !empty($_GET) ? json_encode($_GET) : 'Ninguno'; ?></p>
				<p><strong>Directorio actual:</strong> <code><?php echo __DIR__; ?></code></p>
			</div>
		</div>
	</div>

<?php include '../includes/footer.php'; ?>