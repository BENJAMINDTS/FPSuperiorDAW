const readline = require('readline');

// Crear interfaz para leer entrada del usuario
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

// Solicitar el primer número
rl.question("Ingrese el primer número: ", (num1) => {
  // Solicitar el segundo número
  rl.question("Ingrese el segundo número: ", (num2) => {
    // Convertir las cadenas a números
    const number1 = Number(num1);
    const number2 = Number(num2);

    // Calcular suma, resta y multiplicación
    const suma = number1 + number2;
    const resta = number1 - number2;
    const multiplicacion = number1 * number2;

    // Mostrar los resultados
    console.log(`La suma es: ${suma}`);
    console.log(`La resta es: ${resta}`);
    console.log(`La multiplicación es: ${multiplicacion}`);

    // Cerrar la interfaz
    rl.close();
  });
});