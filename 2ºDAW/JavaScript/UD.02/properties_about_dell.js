// listar-monitor.js

console.log("PROPIEDADES DEL MONITOR DELL 19\"");
console.log("=====================================\n");

const propiedadesMonitor = [
    "Marca: Dell",
    "Tamaño: 19 pulgadas",
    "Resolución: 1920 × 1080 píxeles (Full HD)",
];

let indice = 0;

console.log("Listando propiedades usando WHILE:\n");

while (indice < propiedadesMonitor.length) {
    console.log(propiedadesMonitor[indice]);
    indice++;
}

console.log("\n=====================================");
console.log("Total de propiedades listadas: " + propiedadesMonitor.length);