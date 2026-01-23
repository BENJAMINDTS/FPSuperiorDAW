//Evento DOMContentLoaded
document.addEventListener('DOMContentLoaded', () => {
  console.log("El árbol DOM está completamente cargado y listo para manipularse.");
  console.log("Lista de nodos hijos del body:", document.body.childNodes);

  //Seleccionamos los elementos iniciales usando navegación del DOM
  const button = document.querySelector('button');
  const link = document.querySelector('a');

  // Evento click sobre el botón
  button.addEventListener('click', () => {
    //Crear dinámicamente los nodos encima del botón
    // Llamamos a la función para crear los nodos
    const { h1, hr, div } = creteNodes();// Desestructuramos el objeto retornado

    // Insertar los elementos ANTES del botón en el body usando una función
    insertNodes(h1, button, hr, div);

    //Modificar el enlace existente usando una función
    linkModify(link);
  });
});

function linkModify(link) {
  link.setAttribute('href', 'https://www.wikipedia.org');
  // Vaciamos el texto antiguo del enlace antes de añadir el nuevo
  link.textContent = "";
  const linkText = document.createTextNode('Ir a Wikipedia');
  link.appendChild(linkText);
}

function insertNodes(h1, button, hr, div) {
  document.body.insertBefore(h1, button);
  document.body.insertBefore(hr, button);
  document.body.insertBefore(div, button);
}
// Función para crear los nodos dinámicamente
function creteNodes() {
  //Crear el <h1>
  const h1 = document.createElement('h1');
  const h1Text = document.createTextNode('Encabezado dinámico');
  h1.appendChild(h1Text);

  //Crear el <hr>
  const hr = document.createElement('hr');

  //Crear el <div> con un <p> dentro
  const div = document.createElement('div');
  const p = document.createElement('p');
  const pText = document.createTextNode('Párrafo creado dinámicamente');
  p.appendChild(pText);
  div.appendChild(p); // Añadimos el párrafo al div
  return { h1, hr, div };
}
