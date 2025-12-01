/**
 * @author Benjamin Santiago Gonzalez
 * @version 1.0.0
 */
const { Estudiante, ordenarEstudiantes } = require('./estudiantes');

// Prueba básica
const estudiantes = [
    new Estudiante("A", 20, 8.0),
    new Estudiante("B", 19, 7.5),
    new Estudiante("C", 21, 8.0)
];

const ordenados = ordenarEstudiantes(estudiantes);

console.log("Resultado ordenado:");
ordenados.forEach(est => {
    console.log(`Nombre: ${est.nombre}, Edad: ${est.edad}, Promedio: ${est.promedio}`);
});