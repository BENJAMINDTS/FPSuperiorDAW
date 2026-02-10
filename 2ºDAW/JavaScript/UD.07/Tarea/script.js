/**
 * @file Implementación de peticiones AJAX (XML, JSON y jQuery).
 * @author BenjaminDTS
 */

// Asignación de eventos tras la carga del DOM
document.addEventListener('DOMContentLoaded', () => {
  document.getElementById('btnXML').addEventListener('click', rellenarListaXML);
  document.getElementById('btnJSON').addEventListener('click', cargarAlbumesJSON);
  document.getElementById('btnJQuery').addEventListener('click', cargarXMLConJQuery);
});

/**
 * Realiza una petición asíncrona mediante XMLHttpRequest para procesar un XML local.
 * (RA7_c, RA7_e)
 * @return {void}
 */
function rellenarListaXML() {
  const xhr = new XMLHttpRequest();
  // Importante: El archivo debe estar en tu servidor (XAMPP/LAMPP)
  const url = "cd_catalog.xml";

  xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      const xmlDoc = this.responseXML;
      const artistas = xmlDoc.getElementsByTagName("ARTIST");
      const select = document.getElementById("listaArtistas");

      // Limpiamos el select (excepto el primer mensaje si se desea)
      select.options.length = 0;

      for (let i = 0; i < artistas.length; i++) {
        // Obtenemos el texto del nodo ARTIST
        const nombre = artistas[i].textContent || artistas[i].childNodes[0].nodeValue;

        // Creación dinámica de elementos
        const option = document.createElement("option");
        const textoNode = document.createTextNode(nombre);
        option.appendChild(textoNode);
        select.appendChild(option);
      }
      console.log("Evento XML completado. DOM actualizado sin recarga.");
    }
  };

  xhr.open("GET", url, true);
  xhr.send();
}

/**
 * Realiza una petición XMLHttpRequest para obtener datos en formato JSON.
 * (RA7_f)
 * @return {void}
 */
function cargarAlbumesJSON() {
  const xhr = new XMLHttpRequest();
  const url = "https://jsonplaceholder.typicode.com/albums";

  xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      const datos = JSON.parse(this.responseText);
      const listaUl = document.getElementById("listaAlbumes");

      // Limpiar lista mediante manipulación de texto para evitar innerHTML
      listaUl.textContent = '';

      // Solo mostramos los primeros 10 para no saturar el DOM en el informe
      datos.slice(0, 10).forEach(album => {
        const li = document.createElement("li");
        const textoAlbum = document.createTextNode(album.title);
        li.appendChild(textoAlbum);
        listaUl.appendChild(li);
      });
      console.log("Datos JSON recibidos e inyectados en el <ul>.");
    }
  };

  xhr.open("GET", url, true);
  xhr.send();
}

/**
 * Versión de carga de XML utilizando la librería externa jQuery.
 * (RA7_i)
 * @return {void}
 */
function cargarXMLConJQuery() {
  $.get("cd_catalog.xml", function (data) {
    const $select = $('#listaArtistas');
    $select.empty(); // Método de jQuery para limpiar el contenido

    $(data).find('ARTIST').each(function () {
      const nombre = $(this).text();
      const $opt = $('<option></option>').text(nombre);
      $select.append($opt);
    });
    console.log("Carga XML finalizada con éxito mediante jQuery.");
  }).fail(function () {
    console.error("Error en la petición jQuery. Revise CORS o ruta local.");
  });
}