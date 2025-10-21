// This program compares two numbers input by the user and indicates their relationship
//Declare readline module to read user input
const readline = require('readline');

//Create interface for reading user input
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

//
rl.question("Ingresa el primer número: ", (input1) => {
  const num1 = parseFloat(input1);
  //Second question inside the first to ensure sequential input
  rl.question("Ingresa el segundo número: ", (input2) => {
    const num2 = parseFloat(input2);

    //Compare the two numbers and display the result
    if (num1 === num2) {
      console.log("Los números son iguales.");
    } else if (num1 > num2) {
      console.log("El primer número es mayor que el segundo.");
    } else {
      console.log("El segundo número es mayor que el primero.");
    }

    //Close the readline interface
    rl.close();
  });
});