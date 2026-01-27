// Esperar a que el DOM esté cargado
document.addEventListener("DOMContentLoaded", function () {

  const boton = document.getElementById("btnVerificar");
  const visor = document.getElementById("statusDisplay");

  boton.addEventListener("click", function () {
    // 1. Crear la instancia de XMLHttpRequest
    const xhr = new XMLHttpRequest();
    const url = "https://jsonplaceholder.typicode.com/posts/1";

    // Actualizar visor mientras carga
    visor.innerText = "Comprobando...";
    visor.className = "status-box";

    // 2. Configurar la petición
    xhr.open("GET", url, true);

    // 3. Definir qué hacer cuando la petición finalice
    xhr.onload = function () {
      if (xhr.status === 200) {
        // Éxito: Servidor en línea
        visor.innerText = "¡Conexión Exitosa! El servidor está en línea.";
        visor.classList.add("online");
      } else {
        // Error de respuesta del servidor (ej. 404 o 500)
        visor.innerText = "Error: El servidor respondió con estado " + xhr.status;
        visor.classList.add("offline");
      }
    };

    // 4. Manejar errores de red (ej. sin internet o DNS fallido)
    xhr.onerror = function () {
      visor.innerText = "Servidor fuera de servicio o error de red.";
      visor.classList.add("offline");
    };

    // 5. Enviar la solicitud
    xhr.send();
  });
});