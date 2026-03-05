const mensajeDiv = document.getElementById('mensaje');

document.addEventListener('keydown', (event) => {
  if (event.key === 'Enter') {
    mensajeDiv.textContent = '¡Confirmado!';
    mensajeDiv.style.color = '#2ecc71';
  } else if (event.key === 'Escape') {
    mensajeDiv.textContent = '¡Cancelado!';
    mensajeDiv.style.color = '#e74c3c';
  } else {
    mensajeDiv.textContent = 'Presiona una tecla válida';
    mensajeDiv.style.color = '#333';
  }
});