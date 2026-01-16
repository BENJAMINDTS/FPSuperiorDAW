document.addEventListener('keydown', (event) => {
  const keyDisplay = document.getElementById('keyDisplay');
  keyDisplay.textContent = event.key;
});