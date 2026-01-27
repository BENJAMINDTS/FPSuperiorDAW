/**
 * Función para obtener los datos de la foto
 */
function solicitarFoto() {
  const xhr = new XMLHttpRequest();
  // Usamos el ID 2 para variar un poco
  const urlAPI = "https://jsonplaceholder.typicode.com/photos/2";
  const contenedor = document.getElementById("contenedorImagen");

  xhr.open("GET", urlAPI, true);

  xhr.onload = function () {
    if (xhr.status === 200) {
      const datos = JSON.parse(xhr.responseText);

      // Usamos 'thumbnailUrl' que suele cargar más rápido y mejor que 'url'
      const urlImagen = datos.thumbnailUrl;
      const tituloImagen = datos.title;

      // Creamos el HTML e incluimos un mensaje de error si la imagen falla al cargar
      contenedor.innerHTML = `
                <img src="${urlImagen}" 
                     alt="${tituloImagen}" 
                     onerror="this.onerror=null; this.src='https://via.placeholder.com/150?text=Error+al+cargar+imagen';">
                <p><strong>Título:</strong> ${tituloImagen}</p>
            `;
    } else {
      contenedor.innerHTML = "<p>Error al conectar con la API.</p>";
    }
  };

  xhr.onerror = function () {
    contenedor.innerHTML = "<p>Error de red o CORS detectado.</p>";
  };

  xhr.send();
}

// Vinculamos el botón
document.addEventListener("DOMContentLoaded", () => {
  document.getElementById("btnCargar").onclick = solicitarFoto;
});