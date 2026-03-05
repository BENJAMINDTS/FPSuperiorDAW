// Uso de readline para manejar la entrada y salida en la consola
const readline = require("readline");

// Configuración de la interfaz de readline
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

// Función para convertir entrada a entero
function int(value) {
  return parseInt(value, 10);
}

function pedirNumeros() {
  rl.question("Ingresa números separados por comas: ", (input) => {
    const elementos = input.split(",").map((num) => int(num.trim()));
    const arrayComprimido = comprimirArray(elementos);
    mostrarResultados(elementos, arrayComprimido);
    rl.close();
  });
}

function comprimirArray(array) {
  if (array.length === 0) return [];

  let comprimido = [];
  let contador = 1;

  for (let i = 1; i <= array.length; i++) {
    if (array[i] === array[i - 1]) {
      contador++;
    } else {
      comprimido.push({ valor: array[i - 1], cantidad: contador });
      contador = 1;
    }
  }

  return comprimido;
}

function mostrarResultados(original, comprimido) {
  console.log("\nArray original:");
  console.log(original.join("\t"));

  console.log("\nArray comprimido:");
  comprimido.forEach((item) => {
    console.log(`Valor: ${item.valor}, Cantidad: ${item.cantidad}`);
  });
}
function main() {
  pedirNumeros();
}

main();