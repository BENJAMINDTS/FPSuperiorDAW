// Filtramos los elementos numéricos únicos de un array
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

// Array global para almacenar los números
let array = [];

function pedirArray() {
  rl.question("Ingresa un numero o 'fin' para terminar: ", (input) => {
    if (input.toLowerCase() === "fin") {
      // Cuando el usuario termina, procedemos con el filtrado
      let filteredArray = arrayFilter(array);
      mostrarArray(array, filteredArray);
      rl.close();
    } else {
      array.push(int(input));
      pedirArray(); // Llamada recursiva para pedir el siguiente número
    }
  });
}

function arrayFilter(array) {
  let filteredArray = [];
  
  for (let i = 0; i < array.length; i++) {
    let isUnique = true;
    
    // Verificamos si el elemento ya existe en el array filtrado
    for (let j = 0; j < filteredArray.length; j++) {
      if (array[i] === filteredArray[j]) {
        isUnique = false;
        break;
      }
    }
    
    // Si es único, lo agregamos al array filtrado
    if (isUnique) {
      filteredArray.push(array[i]);
    }
  }
  
  return filteredArray;
}

function mostrarArray(array, filteredArray) {
  console.log("\nArray original:");
  console.log(array.join("\t"));

  console.log("\nArray filtrado (elementos únicos):");
  console.log(filteredArray.join("\t"));
}

function main() {
  console.log("Programa para filtrar elementos únicos de un array");
  pedirArray();
}

// Ejecutar el programa
main();