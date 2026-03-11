/**
 * @fileoverview Colección de ejercicios de peticiones AJAX utilizando jQuery.
 * Incluye ejemplos de operaciones CRUD (GET, POST, PUT, DELETE), técnicas de 
 * optimización como debounce, paginación, scroll infinito y manejo de peticiones concurrentes.
 * @author BenjaminDTS
 */

// ==========================================
// 1. GET Básico
// ==========================================

/**
 * @description Realiza una petición GET a la API de JSONPlaceholder para obtener 
 * un post específico (ID: 1) y renderiza su título y contenido en el contenedor `#output`.
 * @function
 */
function ejercicio1() {
  $.ajax({
    url: 'https://jsonplaceholder.typicode.com/posts/1',
    method: 'GET',
    dataType: 'json',
    success: function (data) {
      $('#output').html(`<h3>${data.title}</h3><p>${data.body}</p>`);
    },
    error: function (xhr) {
      console.error('Error:', xhr);
    }
  });
}

// ==========================================
// 2. POST Básico
// ==========================================

/**
 * @description Recoge los valores de los inputs `#postTitle` y `#postBody` 
 * y realiza una petición POST para simular la creación de un nuevo recurso en la API.
 * Muestra una alerta con el ID del recurso recién creado.
 * @function
 */
function ejercicio2() {
  const nombre = $('#postTitle').val();
  const comentario = $('#postBody').val();

  $.ajax({
    url: 'https://jsonplaceholder.typicode.com/posts',
    method: 'POST',
    contentType: 'application/json; charset=UTF-8',
    data: JSON.stringify({
      title: nombre,
      body: comentario,
      userId: 1
    }),
    success: function (data) {
      alert(`Datos enviados. ID recibido: ${data.id}`);
    },
    error: function (xhr) {
      console.error('Error:', xhr);
    }
  });
}

// ==========================================
// 3. Lista Dinámica
// ==========================================

/**
 * @description Limpia la lista actual y realiza una petición GET para obtener un 
 * listado de usuarios. Posteriormente, itera sobre la respuesta para inyectar 
 * cada usuario como un elemento de lista (`<li>`) en el contenedor `#userList`.
 * @function
 */
function ejercicio3() {
  const $list = $('#userList');
  $list.empty(); // Limpiar lista

  $.ajax({
    url: 'https://jsonplaceholder.typicode.com/users',
    method: 'GET',
    success: function (users) {
      users.forEach(user => {
        $list.append(`<li>${user.name} - ${user.email}</li>`);
      });
    },
    error: function (xhr) {
      console.error('Error:', xhr);
    }
  });
}

// ==========================================
// 4. Búsqueda con Debounce
// ==========================================

/** @type {number|undefined} Identificador del temporizador para el debounce */
let debounceTimer;

/**
 * @description Retrasa la ejecución de la función de búsqueda (`ejercicio4`) 
 * hasta que el usuario haya dejado de escribir durante 500ms.
 * @function
 */
function debounceSearch() {
  clearTimeout(debounceTimer);
  debounceTimer = setTimeout(ejercicio4, 500);
}

/**
 * @description Captura el texto de búsqueda, realiza una petición GET de todos los posts
 * y filtra los resultados en el cliente basándose en el título. Muestra un máximo de 5 resultados.
 * @function
 */
function ejercicio4() {
  const query = $('#searchInput').val().toLowerCase();
  const $resultDiv = $('#searchResults');

  if (!query) {
    $resultDiv.empty();
    return;
  }

  // Nota: JSONPlaceholder no filtra bien todo, filtramos en cliente
  $.ajax({
    url: 'https://jsonplaceholder.typicode.com/posts',
    method: 'GET',
    success: function (posts) {
      const filtered = posts.filter(post => post.title.includes(query));

      // Creamos el HTML de los primeros 5 resultados
      const html = filtered.slice(0, 5).map(post =>
        `<div class="post-item">${post.title}</div>`
      ).join('');

      $resultDiv.html(html);
    }
  });
}

// ==========================================
// 5. Actualizar Datos (PUT)
// ==========================================

/**
 * @description Realiza una petición PUT para sobrescribir completamente un post existente 
 * (ID: 1) y muestra el resultado actualizado en el contenedor `#output`.
 * @function
 */
function ejercicio5() {
  $.ajax({
    url: 'https://jsonplaceholder.typicode.com/posts/1',
    method: 'PUT',
    contentType: 'application/json; charset=UTF-8',
    data: JSON.stringify({
      id: 1,
      title: 'Título Actualizado JQ',
      body: 'Contenido actualizado con jQuery',
      userId: 1,
    }),
    success: function (data) {
      $('#output').html(`<strong>Actualizado (PUT):</strong> ${data.title} - ${data.body}`);
    },
    error: function (xhr) {
      console.error('Error:', xhr);
    }
  });
}

// ==========================================
// 6. Manejo de Errores
// ==========================================

/**
 * @description Provoca un error intencionado al solicitar una URL inexistente para 
 * demostrar la captura y manejo de errores mediante la función `error` de `$.ajax`.
 * @function
 */
function ejercicio6() {
  $.ajax({
    url: 'https://jsonplaceholder.typicode.com/incorrecto', // URL mala a propósito
    method: 'GET',
    success: function () {
      // No debería entrar aquí
    },
    error: function (xhr, status, error) {
      $('#output').html(
        `<span style="color:var(--accent)">Error capturado: ${xhr.status} - ${error}</span>`
      );
    }
  });
}

// ==========================================
// 7. Paginación
// ==========================================

/** @type {number} Página actual de comentarios */
let currentPage = 1;
/** @type {number} Cantidad máxima de comentarios por página */
const limit = 10;

/**
 * @description Realiza una petición GET paginada a la API de comentarios utilizando 
 * los parámetros `_page` y `_limit`. Renderiza los resultados en el DOM.
 * @function
 */
function loadComments() {
  $.ajax({
    url: `https://jsonplaceholder.typicode.com/comments?_page=${currentPage}&_limit=${limit}`,
    method: 'GET',
    success: function (comments) {
      const html = comments.map(c =>
        `<div class="user-item">#${c.id} ${c.email}</div>`
      ).join('');

      $('#commentsList').html(html);
      $('#pageIndicator').text(`Página ${currentPage}`);
    }
  });
}

/**
 * @description Modifica la página actual de comentarios sumando o restando según 
 * la dirección indicada y vuelve a cargar los datos.
 * @param {number} direction - Valor a sumar a la página actual (ej. 1 para avanzar, -1 para retroceder).
 */
function changePage(direction) {
  if (currentPage + direction > 0) {
    currentPage += direction;
    loadComments();
  }
}

/**
 * @description Event Listener que se ejecuta cuando el DOM está listo. 
 * Inicializa la carga de comentarios y de fotos.
 */
$(document).ready(function () {
  loadComments();
  loadPhotos(); // Para el ejercicio 9
});

// ==========================================
// 8. Eliminar Datos (DELETE)
// ==========================================

/**
 * @description Obtiene los primeros 5 posts y los renderiza junto con un botón 
 * que permite simular la eliminación de cada registro específico.
 * @function
 */
function ejercicio8() {
  const $container = $('#deleteList');

  $.ajax({
    url: 'https://jsonplaceholder.typicode.com/posts?_limit=5',
    method: 'GET',
    success: function (posts) {
      $container.empty();
      posts.forEach(post => {
        // Usamos delegación de eventos en el onclick o jQuery events
        // Aquí insertamos HTML directo con onclick inline para mantener compatibilidad simple
        $container.append(`
                    <div class="post-item" id="post-${post.id}">
                        <span>${post.title.substring(0, 20)}...</span>
                        <button onclick="deletePost(${post.id})">Eliminar</button>
                    </div>
                `);
      });
    }
  });
}

/**
 * @description Solicita confirmación al usuario y, si acepta, realiza una petición DELETE 
 * al endpoint correspondiente para simular el borrado. Remueve el elemento del DOM tras el éxito.
 * @param {number} id - El identificador del post a eliminar.
 */
function deletePost(id) {
  if (!confirm('¿Eliminar post?')) return;

  $.ajax({
    url: `https://jsonplaceholder.typicode.com/posts/${id}`,
    method: 'DELETE',
    success: function () {
      $(`#post-${id}`).remove(); // Eliminar del DOM usando selector de ID
      alert(`Post ${id} eliminado`);
    },
    error: function () {
      alert('Error al eliminar');
    }
  });
}

// ==========================================
// 9. Scroll Infinito
// ==========================================

/** @type {number} Página actual de fotos cargadas */
let photoPage = 1;
/** @type {boolean} Indicador (flag) para evitar cargas simultáneas mientras se realiza una petición */
let loadingPhotos = false;

/**
 * @description Obtiene un bloque de 20 fotos simulando paginación e inyecta las imágenes 
 * en la galería. Bloquea peticiones adicionales hasta que la carga actual se complete.
 * @function
 */
function loadPhotos() {
  if (loadingPhotos) return;
  loadingPhotos = true;

  $.ajax({
    url: `https://jsonplaceholder.typicode.com/photos?_page=${photoPage}&_limit=20`,
    method: 'GET',
    success: function (photos) {
      const $gallery = $('#gallery');
      photos.forEach(photo => {
        $gallery.append(`<img src="${photo.thumbnailUrl}" alt="${photo.title}">`);
      });
      photoPage++;
    },
    complete: function () {
      loadingPhotos = false;
    }
  });
}

/**
 * @description Evento de scroll ligado a la ventana (window). Calcula si el usuario 
 * ha hecho scroll hasta casi el final de la página e invoca `loadPhotos()` para el scroll infinito.
 */
$(window).on('scroll', function () {
  // Si la posición del scroll + altura ventana >= altura documento - 100px
  if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
    loadPhotos();
  }
});

// ==========================================
// 10. Combinar Solicitudes ($.when)
// ==========================================

/**
 * @description Muestra cómo realizar múltiples peticiones AJAX de forma concurrente 
 * usando `$.when` (equivalente a Promise.all). Obtiene posts y usuarios, los cruza
 * para asignar el autor correspondiente a cada post y los renderiza.
 * @function
 */
function ejercicio10() {
  const $output = $('#output');
  $output.html('Cargando...');

  // $.when es el equivalente a Promise.all
  $.when(
    $.ajax({ url: 'https://jsonplaceholder.typicode.com/posts?_limit=10', method: 'GET' }),
    $.ajax({ url: 'https://jsonplaceholder.typicode.com/users', method: 'GET' })
  ).done(function (postsRes, usersRes) {
    // En $.when, cada respuesta es un array [data, status, xhr]
    const posts = postsRes[0];
    const users = usersRes[0];

    const combined = posts.map(post => {
      const author = users.find(u => u.id === post.userId);
      return `<div class="post-item" style="display:block">
                        <strong style="color:var(--primary)">${post.title}</strong><br>
                        <small style="color:var(--text-muted)">Autor: ${author ? author.name : 'Desconocido'}</small><br>
                        ${post.body}
                    </div>`;
    });

    $output.html(combined.join(''));
  }).fail(function () {
    $output.html('Error al combinar datos');
  });
}