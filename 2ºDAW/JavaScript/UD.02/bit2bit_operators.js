const prompt = require('prompt-sync')();

let a = parseInt(prompt("Primer número: "));
let b = parseInt(prompt("Segundo número: "));


console.log(`AND  (${a} & ${b})  = ${a & b}` + " esta es la operacion and que hace una comparacion bit a bit para comprobar si ambos bits son 1");
console.log(`OR   (${a} | ${b})  = ${a | b}` + " esta es la operacion or que hace una comparacion bit a bit para comprobar si al menos uno de los bits es 1");
console.log(`XOR  (${a} ^ ${b})  = ${a ^ b}` + " esta es la operacion xor que hace una comparacion bit a bit para comprobar si los bits son diferentes");
console.log(`LEFT (${a} << ${b}) = ${a << b}` + " esta es la operacion left shift que desplaza los bits de a hacia la izquierda b veces");
console.log(`NOT  (~${a})       = ${~a}` +  " esta es la operacion not que invierte los bits de a");
console.log(`NOT  (~${b})       = ${~b}` + " esta es la operacion not que invierte los bits de b");