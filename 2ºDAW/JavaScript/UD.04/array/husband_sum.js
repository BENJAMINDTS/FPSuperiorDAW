// husband_sum.js
// Programa que crea una matriz a partir de la entrada del usuario
// y calcula la suma de los elementos vecinos para cada posición.

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

// Función para calcular la suma de los vecinos
function sumarVecinos(matriz) {
  // Crear una matriz para almacenar los resultados
  const resultado = [];

  // Recorrer cada elemento de la matriz original
  for (let i = 0; i < matriz.length; i++) {
    resultado[i] = [];
    for (let j = 0; j < matriz[i].length; j++) {
      let suma = 0;
      // Sumar los vecinos (arriba, abajo, izquierda, derecha)
      if (i > 0) suma += matriz[i - 1][j];
      if (i < matriz.length - 1) suma += matriz[i + 1][j];
      if (j > 0) suma += matriz[i][j - 1];
      if (j < matriz[i].length - 1) suma += matriz[i][j + 1];
      resultado[i][j] = suma;
    }
  }
  return resultado;
}

// Función para mostrar la matriz original y la de sumas
function mostrarResultados(matriz) {
  console.log("\nMatriz original:");
  matriz.forEach((fila) => console.log(fila.join("\t")));

  console.log("\nSuma de vecinos:");
  const vecinos = sumarVecinos(matriz);
  vecinos.forEach((fila) => console.log(fila.join("\t")));

  rl.close();
}

// Función para pedir números al usuario y llenar la matriz
function pedirNumero(contador, filas, columnas, matriz) {
  if (contador < filas * columnas) {
    // Calcular la posición actual en la matriz
    //Utilizamos división entera y módulo para obtener i y j
    // i: fila, j: columna
    const i = Math.floor(contador / columnas);
    const j = contador % columnas;

    rl.question(`[${i}][${j}]: `, (valor) => {
      if (!matriz[i]) matriz[i] = [];
      matriz[i][j] = int(valor);
      contador++;
      // Llamar recursivamente para el siguiente número
      pedirNumero(contador, filas, columnas, matriz);
    });
  } else {
    // Mostrar resultados
    mostrarResultados(matriz);
  }
}
// Función para pedir las dimensiones de la matriz
function pedirDimensiones() {
  let filas, columnas;
  let matriz = [];
  let contador = 0;
  rl.question("Número de filas: ", (filasInput) => {
    filas = int(filasInput);

    rl.question("Número de columnas: ", (columnasInput) => {
      columnas = int(columnasInput);
      console.log(`\nIngresa ${filas * columnas} números:`);
      // Iniciar la petición de números
      pedirNumero(contador, filas, columnas, matriz);
    });
  });
}

// Función principal
function main() {
  console.log("CREAR MATRIZ Y CALCULAR SUMA DE VECINOS\n");
  // Iniciar la petición de dimensiones
  pedirDimensiones();
}

main();
