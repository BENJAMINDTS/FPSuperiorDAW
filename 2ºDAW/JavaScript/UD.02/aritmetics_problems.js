console.log("1. PRIMERA OPERACIÓN:");
console.log("----------------------");
var operandol = "10";  // string
var operando2 = 2;     // number
console.log("operandol = '10' (string)");
console.log("operando2 = 2 (number)");
console.log("operandol + operando2 = " + (operandol + operando2));
console.log("💡 EXPLICACIÓN: El operador '+' con un string concatena en lugar de sumar");
console.log("💡 '10' + 2 = '102' (concatenación)\n");

console.log("2. SEGUNDA OPERACIÓN:");
console.log("----------------------");
operandol = 10;        // number
operando2 = 3;         // number
console.log("operandol = 10 (number)");
console.log("operando2 = 3 (number)");
console.log("operandol / operando2 = " + (operandol / operando2));
console.log("💡 EXPLICACIÓN: División normal entre números");
console.log("💡 10 / 3 = 3.3333333333333335\n");

console.log("3. TERCERA OPERACIÓN:");
console.log("----------------------");
operandol = 5;         // number
operando2 = 0;         // number
console.log("operandol = 5 (number)");
console.log("operando2 = 0 (number)");
console.log("operandol / operando2 = " + (operandol / operando2));
console.log("💡 EXPLICACIÓN: División por cero en JavaScript resulta en Infinity");
console.log("💡 5 / 0 = Infinity\n");

console.log("4. CUARTA OPERACIÓN:");
console.log("----------------------");
operandol = 30;        // number
operando2 = 10;        // number
console.log("operandol = 30 (number)");
console.log("operando2 = 10 (number)");
console.log("operandol / operando2 = " + (operandol / operando2));
console.log("💡 EXPLICACIÓN: División normal entre números");
console.log("💡 30 / 10 = 3\n");

console.log("5. QUINTA OPERACIÓN:");
console.log("----------------------");
operandol = 30;        // number
operando2 = "a";       // string no numérico
console.log("operandol = 30 (number)");
console.log("operando2 = 'a' (string no numérico)");
console.log("operandol / operando2 = " + (operandol / operando2));
console.log("💡 EXPLICACIÓN: No se puede convertir 'a' a número, resultado es NaN");
console.log("💡 30 / 'a' = NaN (Not a Number)\n");