const cuadro = document.getElementById('cuadro');
const contenedor = document.getElementById('contenedor');

let posX = 0;
let posY = 0;
const step = 10; // Pixeles a mover por cada pulsación de tecla

document.addEventListener('keydown', (event) => {
  const contenedorWidth = contenedor.offsetWidth;
  const contenedorHeight = contenedor.offsetHeight;
  const cuadroWidth = cuadro.offsetWidth;
  const cuadroHeight = cuadro.offsetHeight;

  switch (event.key) {
    case 'ArrowUp':
      posY = Math.max(0, posY - step);
      break;
    case 'ArrowDown':
      posY = Math.min(contenedorHeight - cuadroHeight, posY + step);
      break;
    case 'ArrowLeft':
      posX = Math.max(0, posX - step);
      break;
    case 'ArrowRight':
      posX = Math.min(contenedorWidth - cuadroWidth, posX + step);
      break;
  }

  cuadro.style.top = posY + 'px';
  cuadro.style.left = posX + 'px';
});

document.getElementById('cuadro').addEventListener('click', () => {
  const colors = ['red', 'blue', 'green', 'yellow', 'purple', 'orange'];
  const randomColor = colors[Math.floor(Math.random() * colors.length)];
  cuadro.style.backgroundColor = randomColor;
});

document.addEventListener('mousemove', (event) => {
  event.preventDefault();
  const coordX = event.clientX;
  const coordY = event.clientY;
  document.getElementById('coordenadas').textContent = `X: ${coordX}, Y: ${coordY}`;
});

document.addEventListener('keydown', (event) => {
  const contenedor = document.getElementById('contenedor');
  const stepSize = 20; // Tamaño del incremento/decremento

  if (event.key === '+') {
    contenedor.style.width = (contenedor.offsetWidth + stepSize) + 'px';
    contenedor.style.height = (contenedor.offsetHeight + stepSize) + 'px';
    cuadro.style.top = Math.min(contenedor.offsetHeight - cuadro.offsetHeight, posY) + 'px';
    cuadro.style.left = Math.min(contenedor.offsetWidth - cuadro.offsetWidth, posX) + 'px';
  } else if (event.key === '-') {
    contenedor.style.width = Math.max(100, contenedor.offsetWidth - stepSize) + 'px';
    contenedor.style.height = Math.max(100, contenedor.offsetHeight - stepSize) + 'px';
    cuadro.style.top = Math.min(contenedor.offsetHeight - cuadro.offsetHeight, posY) + 'px';
    cuadro.style.left = Math.min(contenedor.offsetWidth - cuadro.offsetWidth, posX) + 'px';
  }
});

//Cuadrado sigue al ratón
document.addEventListener('mousemove', (event) => {
  const rect = contenedor.getBoundingClientRect();
  const mouseX = event.clientX - rect.left;
  const mouseY = event.clientY - rect.top;

  if (mouseX >= 0 && mouseX <= contenedor.offsetWidth - cuadro.offsetWidth &&
    mouseY >= 0 && mouseY <= contenedor.offsetHeight - cuadro.offsetHeight) {
    cuadro.style.left = mouseX + 'px';
    cuadro.style.top = mouseY + 'px';
    posX = mouseX;
    posY = mouseY;
  }
});
