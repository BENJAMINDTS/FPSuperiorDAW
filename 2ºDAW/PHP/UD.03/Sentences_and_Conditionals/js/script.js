function cargarVista(vista) {
	fetch(`views/${vista}.php`)
		.then(response => response.text())
		.then(html => {
			document.getElementById('contenido-vista').innerHTML = html;
			// Vincular eventos después de cargar el contenido
			vincularEventosFormularios();
		})
		.catch(error => {
			console.error('Error:', error);
		});
}

function vincularEventosFormularios() {
	// Vincular todos los formularios
	const forms = document.querySelectorAll('form');
	forms.forEach(form => {
		// Remover event listeners anteriores para evitar duplicados
		form.removeEventListener('submit', manejarEnvioFormulario);
		// Agregar nuevo event listener
		form.addEventListener('submit', manejarEnvioFormulario);
	});
}

function manejarEnvioFormulario(e) {
	e.preventDefault();
	enviarFormulario(this);
}

function enviarFormulario(form) {
	const formData = new FormData(form);
	const action = obtenerControladorPorForm(form);
	
	fetch(action, {
		method: 'POST',
		body: formData
	})
	.then(response => response.text())
	.then(html => {
		// Buscar el contenedor de resultados
		const resultadoDiv = form.nextElementSibling;
		if (resultadoDiv && resultadoDiv.id.includes('resultado')) {
			resultadoDiv.innerHTML = html;
		} else {
			// Crear contenedor si no existe
			const newResultado = document.createElement('div');
			newResultado.className = 'resultado';
			newResultado.innerHTML = html;
			form.parentNode.insertBefore(newResultado, form.nextSibling);
		}
	})
	.catch(error => {
		console.error('Error:', error);
	});
}

function obtenerControladorPorForm(form) {
	const formId = form.parentNode.id;
	switch(formId) {
		case 'month_form': return 'controller/month_controller.php';
		case 'number_form': return 'controller/largest_number_controller.php';
		case 'vote_form': return 'controller/can_vote_controller.php';
		case 'calificator_form': return 'controller/calificator_controller.php';
		default: return 'controller/default.php';
	}
}

// Vincular eventos cuando se carga la página
document.addEventListener('DOMContentLoaded', function() {
	vincularEventosFormularios();
});