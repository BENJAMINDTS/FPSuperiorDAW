const imagen = document.getElementById('imagen-interactiva');

imagen.addEventListener('click', () => {
  imagen.classList.toggle('agrandada');
});

imagen.addEventListener('mouseenter', () => {
  imagen.style.opacity = '1';
});

imagen.addEventListener('mouseleave', () => {
  imagen.style.opacity = '';
});