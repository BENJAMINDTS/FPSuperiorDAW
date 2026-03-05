/**
 * RA5_f, RA5_g, RA5_d, RA5_h
 * Script de validación de formulario usando Eventos y Expresiones Regulares.
 */

document.addEventListener("DOMContentLoaded", () => {

  // 1. Capturamos los elementos del DOM
  const formulario = document.getElementById("miFormulario");
  const inputUsuario = document.getElementById("usuario");
  const inputPassword = document.getElementById("password");

  const errorUsuario = document.getElementById("errorUsuario");
  const errorPassword = document.getElementById("errorPassword");
  const mensajeExito = document.getElementById("mensajeExito");

  // 2. Definición de Expresiones Regulares (Regex) según requisitos

  /* Regex Usuario:
     ^      -> Inicio de la cadena
     [a-z]  -> Solo letras minúsculas
     {3,12} -> Longitud entre 3 y 12 caracteres
     $      -> Fin de la cadena
  */
  const regexUsuario = /^[a-z]{3,12}$/;

  /* Regex Contraseña:
     ^          -> Inicio
     [A-Z]      -> Exactamente una letra mayúscula al principio
     [.,-]      -> Seguido de un punto, una coma o un guion medio
     [a-z0-9]{6}-> Seguido de exactamente 6 caracteres (letras minúsculas o números)
     $          -> Fin
     
     Ejemplos válidos: A.carbot, F,as14dg, H-951357
  */
  const regexPassword = /^[A-Z][.,-][a-z0-9]{6}$/;

  // 3. Función manejadora del evento (RA5_d)
  // Esta función se ejecuta cuando el usuario intenta enviar el formulario
  const validarFormulario = (evento) => {
    // Prevenimos que el formulario se envíe al servidor automáticamente
    evento.preventDefault();

    // Reiniciamos estados visuales (borrar errores previos)
    limpiarErrores();

    let esUsuarioValido = false;
    let esPasswordValido = false;

    // --- VALIDACIÓN CAMPO USUARIO ---
    const valorUsuario = inputUsuario.value.trim(); // Eliminamos espacios extra

    if (valorUsuario === "") {
      mostrarError(inputUsuario, errorUsuario, "Error: El usuario no puede estar vacío.");
    } else if (!regexUsuario.test(valorUsuario)) {
      mostrarError(inputUsuario, errorUsuario, "Error: Formato inválido. Debe tener 3-12 minúsculas (ej: juan).");
    } else {
      marcarValido(inputUsuario);
      esUsuarioValido = true;
    }

    // --- VALIDACIÓN CAMPO CONTRASEÑA ---
    const valorPassword = inputPassword.value.trim();

    if (valorPassword === "") {
      mostrarError(inputPassword, errorPassword, "Error: La contraseña no puede estar vacía.");
    } else if (!regexPassword.test(valorPassword)) {
      mostrarError(inputPassword, errorPassword, "Error: Formato inválido. Ej: A.carbot (Mayús + signo + 6 minúsculas/números).");
    } else {
      marcarValido(inputPassword);
      esPasswordValido = true;
    }

    // --- RESULTADO FINAL ---
    if (esUsuarioValido && esPasswordValido) {
      mensajeExito.textContent = "¡Formulario validado correctamente!";
      // Aquí se podría hacer formulario.submit() si fuera real
    }
  };

  // Funciones auxiliares para alterar el DOM y mostrar feedback visual

  function mostrarError(input, span, mensaje) {
    input.classList.add("invalid"); // Añade borde rojo (CSS)
    span.textContent = mensaje;     // Inserta texto de error
  }

  function marcarValido(input) {
    input.classList.add("valid");   // Añade borde verde (CSS)
  }

  function limpiarErrores() {
    // Quitamos clases y textos de error
    inputUsuario.classList.remove("invalid", "valid");
    inputPassword.classList.remove("invalid", "valid");
    errorUsuario.textContent = "";
    errorPassword.textContent = "";
    mensajeExito.textContent = "";
  }

  // 4. Asignación del Evento (RA5_f, RA5_d)
  // Escuchamos el evento 'submit' en el formulario
  formulario.addEventListener("submit", validarFormulario);

});