document.addEventListener('DOMContentLoaded', () => {
  const clickButton = document.getElementById('clickButton');
  const clickCountSpan = document.getElementById('clickCount');
  let clickCount = 0;

  clickButton.addEventListener('click', () => {
    clickCount++;
    clickCountSpan.textContent = clickCount;
  });
});