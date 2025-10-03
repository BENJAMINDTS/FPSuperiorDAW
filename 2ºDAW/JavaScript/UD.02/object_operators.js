let persona = {
    nombre: "Ana",
    edad: 25,
    ocupación: "Programadora"
};

console.log("ANTES de delete:");
console.log(persona);

delete persona.ocupación;

console.log("\nDESPUÉS de delete:");
console.log(persona);