// ==========================================
// 1. GET Básico
// ==========================================
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
let debounceTimer;

function debounceSearch() {
  clearTimeout(debounceTimer);
  debounceTimer = setTimeout(ejercicio4, 500);
}

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
let currentPage = 1;
const limit = 10;

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

function changePage(direction) {
  if (currentPage + direction > 0) {
    currentPage += direction;
    loadComments();
  }
}

// Iniciar carga
$(document).ready(function () {
  loadComments();
  loadPhotos(); // Para el ejercicio 9
});

// ==========================================
// 8. Eliminar Datos (DELETE)
// ==========================================
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
let photoPage = 1;
let loadingPhotos = false;

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

// Evento scroll con jQuery
$(window).on('scroll', function () {
  // Si la posición del scroll + altura ventana >= altura documento - 100px
  if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
    loadPhotos();
  }
});

// ==========================================
// 10. Combinar Solicitudes ($.when)
// ==========================================
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