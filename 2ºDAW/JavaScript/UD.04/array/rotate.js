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

// Rotar la matriz 90 grados en sentido horario
function rotarMatriz(matriz) {
  const filas = matriz.length;
  const columnas = matriz[0].length;
  let matrizRotada = [];

  for (let j = 0; j < columnas; j++) {
    matrizRotada[j] = [];
    for (let i = filas - 1; i >= 0; i--) {
      matrizRotada[j][filas - 1 - i] = matriz[i][j];
    }
  }

  return matrizRotada;
}

// Función para mostrar la matriz original y la rotada
function mostrarResultados(matriz) {
  console.log("\nMatriz original:");
  matriz.forEach((fila) => console.log(fila.join("\t")));

  console.log("\nMatriz rotada 90 grados en sentido horario:");
  const matrizRotada = rotarMatriz(matriz);
  matrizRotada.forEach((fila) => console.log(fila.join("\t")));

  rl.close();
}

function main() {
  console.log("Programa para rotar una matriz 90 grados en sentido horario");
  pedirDimensiones();
}

main();
  