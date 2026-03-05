const boton = document.getElementById('miBoton');
const display = document.getElementById('contador-display');

// 2. Variables de estado
let contador = 0;
let intervalo = null;

boton.addEventListener('dblclick', () => {
  if (intervalo !== null) return;

  console.log("Temporizador iniciado");
  contador++;
  display.textContent = contador;

  intervalo = setInterval(() => {
    contador++;
    display.textContent = contador;
  }, 1000);
});

boton.addEventListener('contextmenu', (e) => {
  e.preventDefault();

  if (intervalo) {
    clearInterval(intervalo);
    intervalo = null;
    console.log("Temporizador detenido");
  }
});