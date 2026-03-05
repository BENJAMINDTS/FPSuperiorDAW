const slider = document.getElementById('volumeSlider');
const display = document.getElementById('volumeValue');

slider.addEventListener('input', (e) => {
  display.textContent = e.target.value;
});