const prompt = require('prompt-sync')();

let edad = parseInt(prompt("Ingrese su edad: "));
let contraseña = prompt("Ingrese su contraseña: ");


if (edad >= 18 && edad <= 65 && contraseña.length >= 8) {
    console.log("¡Usuario válido! Ambas condiciones son verdaderas.");
} else {
    console.log("Usuario no válido. Al menos una condición es falsa.");
}