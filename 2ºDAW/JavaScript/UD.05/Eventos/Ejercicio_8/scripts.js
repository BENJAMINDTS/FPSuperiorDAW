const nombreInput = document.getElementById('nombre');
const correoInput = document.getElementById('correo');
const errorNombre = document.getElementById('error-nombre');
const errorCorreo = document.getElementById('error-correo');

nombreInput.addEventListener('input', () => {
  if (nombreInput.value.trim() === "") {
    nombreInput.classList.add('error');
    nombreInput.classList.remove('success');
    errorNombre.classList.add('show-error');
  } else {
    nombreInput.classList.remove('error');
    nombreInput.classList.add('success');
    errorNombre.classList.remove('show-error');
  }
});

correoInput.addEventListener('blur', () => {
  const correo = correoInput.value.trim();
  const regexCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  if (!regexCorreo.test(correo)) {
    correoInput.classList.add('error');
    correoInput.classList.remove('success');
    errorCorreo.classList.add('show-error');
  } else {
    correoInput.classList.remove('error');
    correoInput.classList.add('success');
    errorCorreo.classList.remove('show-error');
  }
});