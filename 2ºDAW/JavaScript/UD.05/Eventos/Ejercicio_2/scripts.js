document.addEventListener('DOMContentLoaded', () => {
  const myBox = document.getElementById('myBox');

  myBox.addEventListener('mouseenter', () => {
    const randomColor = '#' + Math.floor(Math.random() * 16777215).toString(16);
    myBox.style.backgroundColor = randomColor;
  });

  myBox.addEventListener('mouseleave', () => {
    myBox.style.backgroundColor = 'red'; // Vuelve al color inicial (rojo en este caso)
  });
});
