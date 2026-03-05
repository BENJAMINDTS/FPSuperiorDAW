const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function classifyAge(age) {
    if (age >= 0 && age <= 30) {
        return "Joven";
    } else if (age >= 30 && age <= 59) {
        return "Mediana edad";
    } else if (age >= 59) {
        return "Viejo";
    } else {
        return "Edad no válida";
    }
}

rl.question('Por favor, introduce tu edad: ', (input) => {
    const age = parseInt(input);
    const classification = classifyAge(age);
    console.log(`Clasificación: ${classification}`);
    rl.close();
});