const box = document.getElementById('box');
const container = document.getElementById('container');

let posX = 0;
let posY = 0;
const step = 10; // Pixeles a mover por cada pulsación de tecla

document.addEventListener('keydown', (event) => {
  const containerWidth = container.offsetWidth;
  const containerHeight = container.offsetHeight;
  const boxWidth = box.offsetWidth;
  const boxHeight = box.offsetHeight;

  switch (event.key) {
    case 'ArrowUp':
      posY = Math.max(0, posY - step);
      break;
    case 'ArrowDown':
      posY = Math.min(containerHeight - boxHeight, posY + step);
      break;
    case 'ArrowLeft':
      posX = Math.max(0, posX - step);
      break;
    case 'ArrowRight':
      posX = Math.min(containerWidth - boxWidth, posX + step);
      break;
  }

  box.style.top = posY + 'px';
  box.style.left = posX + 'px';
});