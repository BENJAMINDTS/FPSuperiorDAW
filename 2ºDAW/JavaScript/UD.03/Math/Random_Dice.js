// Simulate rolling two six-sided dice and print the results and their sum
let dado1 = Math.floor(Math.random() * 6) + 1; // Roll first die (1-6)
let dado2 = Math.floor(Math.random() * 6) + 1; // Roll second die (1-6)

console.log(`Dado 1: ${dado1}`); // Print result of first die
console.log(`Dado 2: ${dado2}`); // Print result of second die
console.log(`Suma de los dados: ${dado1 + dado2}`); // Print sum of both dice