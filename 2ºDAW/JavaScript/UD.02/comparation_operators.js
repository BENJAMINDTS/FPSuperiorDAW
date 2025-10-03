const prompt = require('prompt-sync')();

var valor1 = prompt("Ingrese un valor: ");
var valor2 = prompt("Ingrese otro valor: ");

if (valor1 == valor2) {
    console.log("Los valores son iguales");
  } else if (valor1 > valor2) {
    console.log(valor1 + " mayor que " + valor2);
  } else {
    console.log(valor1 + " menor que " + valor2);
}