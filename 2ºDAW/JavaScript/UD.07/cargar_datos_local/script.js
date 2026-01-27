  /**
   * Función para solicitar el archivo datos.txt vía AJAX
   */
  function cargarDatos() {
    // 1. Crear el objeto XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // 2. Configurar la función que procesará la respuesta
    xhr.onreadystatechange = function () {
      // Verificamos si la petición terminó (4) y si fue exitosa (200)
      if (xhr.readyState == 4 && xhr.status == 200) {
        // 3. Insertar el contenido del archivo en el <div>
        document.getElementById("contenedor").innerText = xhr.responseText;
      } else if (xhr.readyState == 4 && xhr.status != 200) {
        // Manejo de error si el archivo no existe
        alert("Error: No se pudo cargar el archivo.");
      }
    };

    // 4. Abrir la conexión y enviar la petición
    xhr.open("GET", "datos.txt", true);
    xhr.send();
  }