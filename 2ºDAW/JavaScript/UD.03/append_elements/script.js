// Ejercicio 1: Crear un párrafo
document.getElementById('btn-parrafo').addEventListener('click', function () {
	const contenedor = document.getElementById('contenedor-parrafo');
	if (!document.getElementById('parrafo-creado')) {
		const parrafo = document.createElement('p');
		parrafo.textContent = 'Este párrafo fue creado con JavaScript';
		parrafo.id = 'parrafo-creado';
		contenedor.appendChild(parrafo);
	}
});

document.getElementById('btn-eliminar-parrafo').addEventListener('click', function () {
	const parrafo = document.getElementById('parrafo-creado');
	if (parrafo) {
		parrafo.remove();
	}
});

// Ejercicio 2: Añadir un título dinámicamente
document.getElementById('btn-titulo').addEventListener('click', function () {
	const encabezado = document.getElementById('encabezado');
	if (!document.getElementById('titulo-creado')) {
		const titulo = document.createElement('h2');
		titulo.textContent = 'Bienvenido a mi página';
		titulo.id = 'titulo-creado';
		encabezado.appendChild(titulo);
	}
});

document.getElementById('btn-eliminar-titulo').addEventListener('click', function () {
	const titulo = document.getElementById('titulo-creado');
	if (titulo) {
		titulo.remove();
	}
});

// Ejercicio 3: Insertar un botón en una sección
document.getElementById('btn-boton').addEventListener('click', function () {
	const seccionBotones = document.getElementById('seccion-botones');
	if (!document.getElementById('boton-creado')) {
		const boton = document.createElement('button');
		boton.textContent = 'Haz clic aquí';
		boton.id = 'boton-creado';
		boton.className = 'success';
		seccionBotones.appendChild(boton);
	}
});

document.getElementById('btn-eliminar-boton').addEventListener('click', function () {
	const boton = document.getElementById('boton-creado');
	if (boton) {
		boton.remove();
	}
});

// Ejercicio 4: Agregar varios párrafos
document.getElementById('btn-parrafos').addEventListener('click', function () {
	const contenedor = document.getElementById('contenedor-parrafos');
	if (!document.getElementById('parrafos-creados')) {
		const divParrafos = document.createElement('div');
		divParrafos.id = 'parrafos-creados';

		for (let i = 1; i <= 3; i++) {
			const parrafo = document.createElement('p');
			parrafo.textContent = `Párrafo ${i}`;
			divParrafos.appendChild(parrafo);
		}

		contenedor.appendChild(divParrafos);
	}
});

document.getElementById('btn-eliminar-parrafos').addEventListener('click', function () {
	const parrafos = document.getElementById('parrafos-creados');
	if (parrafos) {
		parrafos.remove();
	}
});

// Ejercicio 5: Lista de tareas
document.getElementById('btn-lista-tareas').addEventListener('click', function () {
	const listaTareas = document.getElementById('lista-tareas');
	if (!document.getElementById('lista-tareas-creada')) {
		const lista = document.createElement('ul');
		lista.id = 'lista-tareas-creada';
		const tareas = ['Estudiar', 'Comer', 'Dormir'];

		tareas.forEach(tarea => {
			const elementoLista = document.createElement('li');
			elementoLista.textContent = tarea;
			lista.appendChild(elementoLista);
		});

		listaTareas.appendChild(lista);
	}
});

document.getElementById('btn-eliminar-lista-tareas').addEventListener('click', function () {
	const lista = document.getElementById('lista-tareas-creada');
	if (lista) {
		lista.remove();
	}
});

// Ejercicio 6: Generar elementos desde un array
document.getElementById('btn-lista-frutas').addEventListener('click', function () {
	const contenedor = document.getElementById('contenedor-lista-frutas');
	if (!document.getElementById('lista-frutas-creada')) {
		const frutas = ["Manzana", "Banana", "Naranja"];
		const listaFrutas = document.createElement('ul');
		listaFrutas.id = 'lista-frutas-creada';

		frutas.forEach(fruta => {
			const elementoLista = document.createElement('li');
			elementoLista.textContent = fruta;
			listaFrutas.appendChild(elementoLista);
		});

		contenedor.appendChild(listaFrutas);
	}
});

document.getElementById('btn-eliminar-lista-frutas').addEventListener('click', function () {
	const lista = document.getElementById('lista-frutas-creada');
	if (lista) {
		lista.remove();
	}
});

// Ejercicio 7: Insertar una imagen
document.getElementById('btn-imagen').addEventListener('click', function () {
	const contenedor = document.getElementById('contenedor-imagen');
	if (!document.getElementById('imagen-creada')) {
		const imagen = document.createElement('img');
		imagen.src = '';
		imagen.alt = 'Foto de ejemplo';
		imagen.id = 'imagen-creada';
		contenedor.appendChild(imagen);
	}
});

document.getElementById('btn-eliminar-imagen').addEventListener('click', function () {
	const imagen = document.getElementById('imagen-creada');
	if (imagen) {
		imagen.remove();
	}
});

// Ejercicio 8: Botón que crea un elemento nuevo
document.getElementById('btn-boton-parrafo').addEventListener('click', function () {
	const contenedor = document.getElementById('contenedor-boton-parrafo');
	if (!document.getElementById('boton-parrafo-creado')) {
		const botonParrafo = document.createElement('button');
		botonParrafo.textContent = 'Añadir párrafo';
		botonParrafo.id = 'boton-parrafo-creado';
		botonParrafo.className = 'success';

		const divParrafos = document.createElement('div');
		divParrafos.id = 'parrafos-dinamicos';

		botonParrafo.addEventListener('click', function () {
			const nuevoParrafo = document.createElement('p');
			nuevoParrafo.textContent = 'Nuevo párrafo creado';
			divParrafos.appendChild(nuevoParrafo);
		});

		contenedor.appendChild(botonParrafo);
		contenedor.appendChild(divParrafos);
	}
});

document.getElementById('btn-eliminar-boton-parrafo').addEventListener('click', function () {
	const boton = document.getElementById('boton-parrafo-creado');
	const parrafos = document.getElementById('parrafos-dinamicos');

	if (boton) boton.remove();
	if (parrafos) parrafos.remove();
});

// Ejercicio 9: Contador de clics
let contadorClics = 0;
document.getElementById('btn-contador').addEventListener('click', function () {
	const contenedor = document.getElementById('contenedor-contador');
	if (!document.getElementById('boton-contador-creado')) {
		const botonContador = document.createElement('button');
		botonContador.textContent = 'Haz clic';
		botonContador.id = 'boton-contador-creado';

		const divContador = document.createElement('div');
		divContador.id = 'resultados-contador';

		botonContador.addEventListener('click', function () {
			contadorClics++;
			const parrafoContador = document.createElement('p');
			parrafoContador.textContent = `Has hecho ${contadorClics} clics`;
			divContador.appendChild(parrafoContador);
		});

		contenedor.appendChild(botonContador);
		contenedor.appendChild(divContador);
	}
});

document.getElementById('btn-reset-contador').addEventListener('click', function () {
	const boton = document.getElementById('boton-contador-creado');
	const resultados = document.getElementById('resultados-contador');

	if (boton) boton.remove();
	if (resultados) resultados.remove();

	contadorClics = 0;
});

// Ejercicio 10: Crear una tarjeta con varios elementos
document.getElementById('btn-tarjeta').addEventListener('click', function () {
	const tarjetas = document.getElementById('tarjetas');
	if (!document.getElementById('tarjeta-creada')) {
		const tarjeta = document.createElement('div');
		tarjeta.className = 'tarjeta';
		tarjeta.id = 'tarjeta-creada';

		const tituloTarjeta = document.createElement('h3');
		tituloTarjeta.textContent = 'Tarjeta creada';

		const descripcion = document.createElement('p');
		descripcion.textContent = 'Esta es una tarjeta creada dinámicamente con JavaScript. Contiene un título, una descripción y una imagen.';

		const imagenTarjeta = document.createElement('img');
		imagenTarjeta.src = '';
		imagenTarjeta.alt = 'Imagen de ejemplo para la tarjeta';

		tarjeta.appendChild(tituloTarjeta);
		tarjeta.appendChild(descripcion);
		tarjeta.appendChild(imagenTarjeta);

		tarjetas.appendChild(tarjeta);
	}
});

document.getElementById('btn-eliminar-tarjeta').addEventListener('click', function () {
	const tarjeta = document.getElementById('tarjeta-creada');
	if (tarjeta) {
		tarjeta.remove();
	}
});