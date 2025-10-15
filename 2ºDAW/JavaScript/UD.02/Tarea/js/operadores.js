// Declaración de variables
let numero1 = 5;
const numero2 = 10;
var resultado;

// Operaciones aritméticas
console.log('=== OPERACIONES ARITMÉTICAS ===');

// Suma
resultado = numero1 + numero2;
console.log('Suma:', numero1 + ' + ' + numero2 + ' = ' + resultado);

// Resta
resultado = numero1 - numero2;
console.log('Resta:', numero1 + ' - ' + numero2 + ' = ' + resultado);

// Multiplicación
resultado = numero1 * numero2;
console.log('Multiplicación:', numero1 + ' * ' + numero2 + ' = ' + resultado);

// División
resultado = numero1 / numero2;
console.log('División:', numero1 + ' / ' + numero2 + ' = ' + resultado);

// Módulo
resultado = numero2 % numero1;
console.log('Módulo:', numero2 + ' % ' + numero1 + ' = ' + resultado);

// Operaciones relacionales
console.log('\n=== OPERACIONES RELACIONALES ===');
console.log('¿Es número1 mayor que número2?', numero1 > numero2);
console.log('¿Es número1 menor que número2?', numero1 < numero2);
console.log('¿Es número1 igual a número2?', numero1 == numero2);
console.log('¿Es número1 diferente de número2?', numero1 != numero2);
console.log('¿Es número1 mayor o igual que número2?', numero1 >= numero2);
console.log('¿Es número1 menor o igual que número2?', numero1 <= numero2);

// Operaciones lógicas
console.log('\n=== OPERACIONES LÓGICAS ===');
console.log('¿Es número1 mayor que 3 y número2 menor que 15?', numero1 > 3 && numero2 < 15);
console.log('¿Es número1 mayor que 3 o número2 menor que 5?', numero1 > 3 || numero2 < 5);
console.log('¿NO es número1 mayor que 3?', !(numero1 > 3));
console.log('¿Es número1 mayor que 3 y número2 menor que 15 y número1 diferente de número2?',
            numero1 > 3 && numero2 < 15 && numero1 != numero2);
