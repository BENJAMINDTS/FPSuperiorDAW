// ámbito.js - Demostración del ámbito de variables en JavaScript

// Variable global
let globalVar = "Soy global";

// Función con variable local
function miFuncion() {
  let localVar = "Soy local";
  console.log("Dentro de la función:");
  console.log("localVar:", localVar);
  console.log("globalVar:", globalVar); // Se puede acceder a la global
}

// Ejecutar la función
miFuncion();

// Intentar acceder a la variable local fuera de la función
try {
  console.log("Fuera de la función - localVar:", localVar);
} catch (error) {
  console.log(
    "Error al acceder a localVar fuera de la función:",
    error.message
  );
}

// Función con variable de bloque
function miBloque() {
  if (true) {
    let bloqueVar = "Soy del bloque";
    console.log("Dentro del bloque:");
    console.log("bloqueVar:", bloqueVar);
  }

  // Intentar acceder a la variable de bloque fuera del bloque
  try {
    console.log("Fuera del bloque - bloqueVar:", bloqueVar);
  } catch (error) {
    console.log(
      "Error al acceder a bloqueVar fuera del bloque:",
      error.message
    );
  }
}

// Ejecutar la función de bloque
miBloque();
