const inputTarea = document.getElementById('inputTarea');
const btnAgregar = document.getElementById('btnAgregar');
const listaTareas = document.getElementById('listaTareas');

function agregarTarea() {
  const texto = inputTarea.value.trim();

  if (texto !== "") {
    const li = document.createElement('li');
    li.textContent = texto;
    listaTareas.appendChild(li);

    inputTarea.value = "";
    inputTarea.focus();
  }
}

btnAgregar.addEventListener('click', agregarTarea);

inputTarea.addEventListener('keypress', (e) => {
  if (e.key === 'Enter') agregarTarea();
});

listaTareas.addEventListener('click', (e) => {
  if (e.target.tagName === 'LI') {
    e.target.classList.toggle('completada');
  }
});