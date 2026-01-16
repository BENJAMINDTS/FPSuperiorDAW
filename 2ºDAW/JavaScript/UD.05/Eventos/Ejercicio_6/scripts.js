const container = document.getElementById('container');
const message = document.getElementById('message');

container.addEventListener('mousemove', (event) => {
  const rect = container.getBoundingClientRect();
  const x = event.clientX - rect.left;
  const y = event.clientY - rect.top;
  message.textContent = `Coordenadas: X=${x}, Y=${y}`;
});

container.addEventListener('mouseleave', () => {
  message.textContent = 'Mueve el ratón dentro de este contenedor.';
});