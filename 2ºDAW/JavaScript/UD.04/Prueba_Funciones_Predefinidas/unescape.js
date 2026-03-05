//Utilizamos la funcion unescape para decodificar una URL
const codedUrl = "https%3A//www.fp-informatica.com";
const url = unescape (codedUrl);
console.log (url);