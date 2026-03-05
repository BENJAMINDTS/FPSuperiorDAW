// Variables globales
let newWindow = null;

// Ejercicio 1: Manipulación del Objeto window
document.getElementById('openWindow').addEventListener('click', function () {
	newWindow = window.open('', 'nuevaVentana', 'width=600,height=400,top=100,left=100');
	if (newWindow) {
		newWindow.document.write(`
            <html>
                <head>
                    <title>Nueva Ventana</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            padding: 20px;
                            text-align: center;
                            background-color: #f0f8ff;
                        }
                        h1 { color: #3498db; }
                    </style>
                </head>
                <body>
                    <h1>¡Esta es una nueva ventana!</h1>
                    <p>Abierta mediante window.open()</p>
                    <p>Puedes cerrarla desde la ventana principal</p>
                </body>
            </html>
        `);
		document.getElementById('windowStatus').textContent = 'Estado: Ventana abierta';
	}
});

document.getElementById('closeWindow').addEventListener('click', function () {
	if (newWindow && !newWindow.closed) {
		const confirmClose = window.confirm('¿Estás seguro de que quieres cerrar la ventana?');
		if (confirmClose) {
			newWindow.close();
			document.getElementById('windowStatus').textContent = 'Estado: Ventana cerrada';
		}
	} else {
		alert('No hay ninguna ventana abierta para cerrar');
	}
});

// Ejercicio 2: Información del Navegador con navigator
document.getElementById('showBrowserInfo').addEventListener('click', function () {
	const browserInfo = document.getElementById('browserInfo');

	// Detectar si es Chrome
	let chromeMessage = '';
	if (navigator.userAgent.includes('Chrome') && !navigator.userAgent.includes('Edg')) {
		chromeMessage = '<div class="info-item" style="color: var(--success-color); font-weight: bold;">¡Estás usando Chrome!</div>';
	}

	browserInfo.innerHTML = `
        <div class="info-item"><span class="info-label">Nombre del navegador:</span> ${navigator.appName}</div>
        <div class="info-item"><span class="info-label">Versión:</span> ${navigator.appVersion}</div>
        <div class="info-item"><span class="info-label">Idioma:</span> ${navigator.language}</div>
        <div class="info-item"><span class="info-label">Cookies habilitadas:</span> ${navigator.cookieEnabled ? 'Sí' : 'No'}</div>
        <div class="info-item"><span class="info-label">User Agent:</span> ${navigator.userAgent}</div>
        <div class="info-item"><span class="info-label">Plataforma:</span> ${navigator.platform}</div>
        <div class="info-item"><span class="info-label">En línea:</span> ${navigator.onLine ? 'Sí' : 'No'}</div>
        ${chromeMessage}
    `;
});

// Ejercicio 3: Interactuar con el Objeto document
document.getElementById('addListItem').addEventListener('click', function () {
	const userInput = window.prompt('Ingresa el texto para el nuevo elemento de la lista:');

	if (userInput && userInput.trim() !== '') {
		const list = document.getElementById('itemList');
		const newListItem = document.createElement('li');
		newListItem.textContent = userInput;
		list.appendChild(newListItem);
	} else if (userInput !== null) {
		alert('Debes ingresar algún texto');
	}
});

// Ejercicio 4: Información sobre la URL con location
document.getElementById('showUrlInfo').addEventListener('click', function () {
	const urlInfo = document.getElementById('urlInfo');

	urlInfo.innerHTML = `
        <div class="info-item"><span class="info-label">URL completa:</span> ${location.href}</div>
        <div class="info-item"><span class="info-label">Protocolo:</span> ${location.protocol}</div>
        <div class="info-item"><span class="info-label">Hostname:</span> ${location.hostname}</div>
        <div class="info-item"><span class="info-label">Puerto:</span> ${location.port || '80 (por defecto)'}</div>
        <div class="info-item"><span class="info-label">Pathname:</span> ${location.pathname}</div>
        <div class="info-item"><span class="info-label">Parámetros de búsqueda:</span> ${location.search || 'Ninguno'}</div>
        <div class="info-item"><span class="info-label">Hash:</span> ${location.hash || 'Ninguno'}</div>
        <div class="info-item"><span class="info-label">Origen:</span> ${location.origin}</div>
    `;
});

document.getElementById('redirectPage').addEventListener('click', function () {
	const confirmRedirect = window.confirm('¿Estás seguro de que quieres redirigir a la página de ejemplo?');
	if (confirmRedirect) {
		// Redirigir a una página de ejemplo (puedes cambiar la URL)
		location.assign('https://www.example.com');
	}
});

// Ejercicio 5: Navegación en el Historial con history
document.getElementById('goBack').addEventListener('click', function () {
	if (history.length > 1) {
		history.back();
	} else {
		alert('No hay páginas anteriores en el historial');
	}
});

document.getElementById('goForward').addEventListener('click', function () {
	// Nota: forward() solo funciona si hay páginas adelante en el historial
	history.forward();
});

// Actualizar información del historial
document.getElementById('historyLength').textContent = history.length;

// Ejercicio 6: Información de la Pantalla con screen
document.getElementById('showScreenInfo').addEventListener('click', function () {
	const screenInfo = document.getElementById('screenInfo');

	let resolutionMessage = '';
	if (screen.width < 1280 || screen.height < 720) {
		resolutionMessage = `
            <div class="warning">
                <strong>Recomendación:</strong> Para una mejor experiencia, considera usar un dispositivo con mayor resolución (mínimo 1280x720).
            </div>
        `;
	}

	screenInfo.innerHTML = `
        <div class="info-item"><span class="info-label">Ancho de pantalla:</span> ${screen.width}px</div>
        <div class="info-item"><span class="info-label">Alto de pantalla:</span> ${screen.height}px</div>
        <div class="info-item"><span class="info-label">Ancho disponible:</span> ${screen.availWidth}px</div>
        <div class="info-item"><span class="info-label">Alto disponible:</span> ${screen.availHeight}px</div>
        <div class="info-item"><span class="info-label">Profundidad de color:</span> ${screen.colorDepth} bits</div>
        <div class="info-item"><span class="info-label">Resolución de color:</span> ${screen.pixelDepth} bits por píxel</div>
        ${resolutionMessage}
    `;
});

// Inicialización cuando se carga la página
document.addEventListener('DOMContentLoaded', function () {
	console.log('Página cargada correctamente');

	// Mostrar información básica al cargar
	document.getElementById('historyLength').textContent = history.length;
});