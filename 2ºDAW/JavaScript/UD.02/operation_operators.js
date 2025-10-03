console.log("Vamos a introducir dos numeros para realizar todas las operaciones pertinentes")
const prompt = require('prompt-sync')();

var valor1 = parseFloat(prompt("Ingrese un valor: "));
var valor2 = parseFloat(prompt("Ingrese otro valor: "));

console.log("Suma: " + (valor1 + valor2));
console.log("Resta: " + (valor1 - valor2));
console.log("Multiplicacion: " + (valor1 * valor2));
console.log("Division: " + (valor1 / valor2));
console.log("Modulo: " + (valor1 % valor2));
console.log("Exponente: " + (valor1 ** valor2));
