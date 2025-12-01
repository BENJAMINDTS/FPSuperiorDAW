/**
 * @author Benjamin Santiago Gonzalez
 * @version 1.0.0
 * Módulo para gestión y ordenación de estudiantes
 * @module estudiantes
 */

/**
 * Clase que representa a un Estudiante
 * @class Estudiante
 */
class Estudiante {
    /**
     * Constructor de la clase Estudiante
     * @constructor
     * @param {string} nombre - Nombre del estudiante
     * @param {number} edad - Edad del estudiante
     * @param {number} promedio - Promedio del estudiante
     */
    constructor(nombre, edad, promedio) {
        this.nombre = nombre;
        this.edad = edad;
        this.promedio = promedio;
    }
}

/**
 * Ordena estudiantes por promedio ascendente y, en caso de empate, por edad ascendente
 * @function ordenarEstudiantes
 * @param {Estudiante[]} estudiantes - Array de objetos Estudiante
 * @returns {Estudiante[]} Array de estudiantes ordenados
 */
function ordenarEstudiantes(estudiantes) {
    // Crear copia para no modificar el array original
    const copiaEstudiantes = [...estudiantes];
    
    // Ordenar primero por promedio, luego por edad
    copiaEstudiantes.sort((a, b) => {
        // Comparar por promedio
        if (a.promedio < b.promedio) return -1;
        if (a.promedio > b.promedio) return 1;
        
        // En caso de empate en promedio, comparar por edad
        if (a.edad < b.edad) return -1;
        if (a.edad > b.edad) return 1;
        
        // Si hay empate en ambos, mantener orden original
        return 0;
    });
    
    return copiaEstudiantes;
}

// Ejemplo de uso
function main() {
    // Crear lista de estudiantes
    const estudiantes = [
        new Estudiante("Ana", 20, 8.5),
        new Estudiante("Carlos", 22, 7.8),
        new Estudiante("Beatriz", 21, 8.5),
        new Estudiante("David", 19, 9.2),
        new Estudiante("Elena", 23, 7.8)
    ];
    
    console.log("Estudiantes originales:");
    estudiantes.forEach(est => {
        console.log(`${est.nombre} - Edad: ${est.edad}, Promedio: ${est.promedio}`);
    });
    
    // Ordenar estudiantes
    const estudiantesOrdenados = ordenarEstudiantes(estudiantes);
    
    console.log("\nEstudiantes ordenados por promedio y edad:");
    estudiantesOrdenados.forEach(est => {
        console.log(`${est.nombre} - Edad: ${est.edad}, Promedio: ${est.promedio}`);
    });
}

// Ejecutar solo si es el archivo principal
if (require.main === module) {
    main();
}

// Exportar para uso en otros módulos
module.exports = {
    Estudiante,
    ordenarEstudiantes
};