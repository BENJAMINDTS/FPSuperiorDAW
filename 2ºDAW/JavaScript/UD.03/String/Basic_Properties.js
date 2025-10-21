//Basic Methods to work with strings in JavaScript
let frase = "JavaScript es genial"
//Length property to get the number of characters in the string
console.log(frase.length) //20

//toUpperCase() method to convert the string to uppercase
let mayusculas = frase.toUpperCase()
console.log(mayusculas) //JAVASCRIPT ES GENIAL

//toLowerCase() method to convert the string to lowercase
let minusculas = frase.toLowerCase()
console.log(minusculas) //javascript es genial

//Extracting a substring, genial, using slice()
let subcadena = frase.slice(14, 21)
console.log(subcadena) //genial