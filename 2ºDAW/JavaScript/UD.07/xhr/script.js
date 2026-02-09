/**
 * Lógica para los 7 ejercicios de peticiones API utilizando XMLHttpRequest.
 *
 * @author BenjaminDTS
 */

// --- Ejercicio 1: Petición GET Sencilla ---
document.getElementById('btnEj1').addEventListener('click', function () {
  /**
   * Realiza una petición GET al post con ID 1.
   */
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "https://jsonplaceholder.typicode.com/posts/1", true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var respuesta = JSON.parse(xhr.responseText);
      console.info("--- Ejercicio 1 ---");
      console.log("Título del post:", respuesta.title);
    }
  };

  xhr.send();
});

// --- Ejercicio 2: Manejo de Errores ---
document.getElementById('btnEj2').addEventListener('click', function () {
  /**
   * Intenta realizar una petición a un recurso inexistente y verifica el status.
   */
  var xhr = new XMLHttpRequest();
  // URL incorrecta a propósito
  xhr.open("GET", "https://jsonplaceholder.typicode.com/posts/99999999", true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      console.info("--- Ejercicio 2 ---");
      if (xhr.status === 200) {
        console.log("Éxito:", JSON.parse(xhr.responseText));
      } else {
        console.error("Ocurrió un error en la petición. Código de estado:", xhr.status);
      }
    }
  };

  xhr.send();
});

// --- Ejercicio 3: Mostrar datos en Web ---
document.getElementById('btnEj3').addEventListener('click', function () {
  var contenedor = document.getElementById('resultadoUsuario');

  /**
   * Obtiene los datos del usuario 1 y actualiza el DOM.
   */
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "https://jsonplaceholder.typicode.com/users/1", true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var usuario = JSON.parse(xhr.responseText);
      contenedor.innerHTML = `
                <p><strong>Nombre:</strong> ${usuario.name}</p>
                <p><strong>Email:</strong> ${usuario.email}</p>
            `;
    }
  };

  xhr.send();
});

// --- Ejercicio 4: Enviar datos POST ---
document.getElementById('btnEj4').addEventListener('click', function () {
  var nuevoPost = {
    title: 'Post creado con XHR',
    body: 'Contenido enviado mediante XMLHttpRequest.',
    userId: 1
  };

  /**
   * Envía un objeto nuevo a la API usando POST y headers JSON.
   */
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "https://jsonplaceholder.typicode.com/posts", true);
  xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status === 201)) {
      var respuesta = JSON.parse(xhr.responseText);
      console.info("--- Ejercicio 4 ---");
      console.log("Respuesta del servidor (Post creado):", respuesta);
    }
  };

  xhr.send(JSON.stringify(nuevoPost));
});

// --- Ejercicio 5: Cargar lista dinámica ---
document.getElementById('btnEj5').addEventListener('click', function () {
  var lista = document.getElementById('listaUsuarios');

  /**
   * Obtiene la lista de usuarios y manipula el DOM con un bucle.
   */
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "https://jsonplaceholder.typicode.com/users", true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var usuarios = JSON.parse(xhr.responseText);
      lista.innerHTML = ''; // Limpiar lista

      usuarios.forEach(function (usuario) {
        var item = document.createElement('li');
        item.textContent = "👤 " + usuario.name;
        lista.appendChild(item);
      });
    }
  };

  xhr.send();
});

// --- Ejercicio 6: Actualizar datos PUT ---
document.getElementById('btnEj6').addEventListener('click', function () {
  var updateData = {
    id: 1,
    title: 'Título actualizado con XHR',
    body: 'Cuerpo actualizado.',
    userId: 1
  };

  /**
   * Actualiza el post 1 usando el método PUT.
   */
  var xhr = new XMLHttpRequest();
  xhr.open("PUT", "https://jsonplaceholder.typicode.com/posts/1", true);
  xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var respuesta = JSON.parse(xhr.responseText);
      console.info("--- Ejercicio 6 ---");
      console.log("Post actualizado:", respuesta);
    }
  };

  xhr.send(JSON.stringify(updateData));
});

// --- Ejercicio 7: Eliminar recurso DELETE ---
document.getElementById('btnEj7').addEventListener('click', function () {
  /**
   * Elimina el post 1 usando el método DELETE.
   */
  var xhr = new XMLHttpRequest();
  xhr.open("DELETE", "https://jsonplaceholder.typicode.com/posts/1", true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      console.info("--- Ejercicio 7 ---");
      if (xhr.status === 200 || xhr.status === 204) {
        console.log("El post se eliminó correctamente.");
      } else {
        console.error("Error al eliminar el post.");
      }
    }
  };

  xhr.send();
});