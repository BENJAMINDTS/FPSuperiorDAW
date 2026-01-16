const selector = document.getElementById('selectorColor');

selector.addEventListener('change', (e) => {
  document.body.style.backgroundColor = e.target.value;
});