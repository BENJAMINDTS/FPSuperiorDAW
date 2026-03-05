/**
 * @author Benjamin Santiago Gonzalez
 * @version 1.0
 * Clase que representa un Libro con sus propiedades básicas
 * @class
 * @description Modela un libro con título, autor y número de páginas
 */
class Libro {
    /**
     * Constructor de la clase Libro
     * @constructor
     * @param {string} titulo - Título del libro
     * @param {string} autor - Autor del libro
     * @param {number} numPaginas - Número de páginas del libro
     */
    constructor(titulo, autor, numPaginas) {
        /** @property {string} titulo - Título del libro */
        this.titulo = titulo;
        
        /** @property {string} autor - Autor del libro */
        this.autor = autor;
        
        /** @property {number} numPaginas - Número de páginas del libro */
        this.numPaginas = numPaginas;
    }
    
    /**
     * Devuelve la información del libro formateada
     * @method
     * @returns {string} Información del libro
     */
    mostrarInfo() {
        return `"${this.titulo}" - ${this.autor} (${this.numPaginas} páginas)`;
    }
}

/**
 * Función que filtra libros con más de 300 páginas
 * @function
 * @param {Array<Libro>} libros - Array de objetos Libro
 * @returns {Array<string>} Array con los títulos de libros con más de 300 páginas
 * @description Recorre la colección de libros y devuelve los títulos
 *              de aquellos que tienen más de 300 páginas
 */
function filtrarLibrosLargos(libros) {
    // Filtrar libros con más de 300 páginas y mapear a sus títulos
    return libros
        .filter(libro => libro.numPaginas > 300)
        .map(libro => libro.titulo);
}

/**
 * Función que filtra libros con más de 300 páginas (versión con for tradicional)
 * @function
 * @param {Array<Libro>} libros - Array de objetos Libro
 * @returns {Array<string>} Array con los títulos de libros con más de 300 páginas
 */
function filtrarLibrosLargosFor(libros) {
    const titulos = [];
    
    // Recorrer el array con un bucle for tradicional
    for (let i = 0; i < libros.length; i++) {
        if (libros[i].numPaginas > 300) {
            titulos.push(libros[i].titulo);
        }
    }
    
    return titulos;
}

/**
 * Función principal que ejecuta el programa
 * @function
 * @description Crea una colección de libros, la muestra y filtra los libros largos
 */
function main() {
    console.log("======================================================");
    console.log("GESTIÓN DE COLECCIÓN DE LIBROS");
    console.log("======================================================");
    
    // 1. Crear un array (matriz) de objetos Libro
    const biblioteca = [
        new Libro("Cien años de soledad", "Gabriel García Márquez", 417),
        new Libro("El principito", "Antoine de Saint-Exupéry", 96),
        new Libro("1984", "George Orwell", 328),
        new Libro("Crimen y castigo", "Fiódor Dostoyevski", 551),
        new Libro("El viejo y el mar", "Ernest Hemingway", 127),
        new Libro("Los pilares de la Tierra", "Ken Follett", 1104),
        new Libro("El hobbit", "J.R.R. Tolkien", 310),
        new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 309),
        new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 863),
        new Libro("Orgullo y prejuicio", "Jane Austen", 279)
    ];
    
    console.log("\nCOLECCIÓN COMPLETA DE LIBROS:");
    console.log("----------------------------------");
    
    // 2. Mostrar todos los libros
    biblioteca.forEach((libro, index) => {
        console.log(`${index + 1}. ${libro.mostrarInfo()}`);
    });
    
    console.log("\n======================================================");
    console.log("LIBROS CON MÁS DE 300 PÁGINAS:");
    console.log("======================================================");
    
    // 3. Filtrar libros con más de 300 páginas
    const librosLargos = filtrarLibrosLargos(biblioteca);
    
    if (librosLargos.length > 0) {
        console.log("\nSe encontraron " + librosLargos.length + " libros con más de 300 páginas:\n");
        
        librosLargos.forEach((titulo, index) => {
            // Encontrar el libro completo para mostrar más información
            const libro = biblioteca.find(lib => lib.titulo === titulo);
            console.log(`${index + 1}. ${libro.mostrarInfo()}`);
        });
    } else {
        console.log("\nNo se encontraron libros con más de 300 páginas.");
    }
    
    console.log("\n======================================================");
    console.log("ESTADÍSTICAS DE LA BIBLIOTECA:");
    console.log("======================================================");
    
    // 4. Estadísticas adicionales
    const totalLibros = biblioteca.length;
    const totalPaginas = biblioteca.reduce((suma, libro) => suma + libro.numPaginas, 0);
    const promedioPaginas = totalPaginas / totalLibros;
    
    // Contar libros por rangos de páginas
    const rangos = {
        cortos: biblioteca.filter(libro => libro.numPaginas <= 200).length,
        medianos: biblioteca.filter(libro => libro.numPaginas > 200 && libro.numPaginas <= 400).length,
        largos: biblioteca.filter(libro => libro.numPaginas > 400).length
    };
    
    console.log(`\n• Total de libros: ${totalLibros}`);
    console.log(`• Total de páginas: ${totalPaginas}`);
    console.log(`• Promedio de páginas por libro: ${promedioPaginas.toFixed(1)}`);
    console.log(`\n• Libros cortos (≤200 págs): ${rangos.cortos}`);
    console.log(`• Libros medianos (201-400 págs): ${rangos.medianos}`);
    console.log(`• Libros largos (>400 págs): ${rangos.largos}`);
    
    console.log("\n======================================================");
    console.log("BÚSQUEDA POR AUTOR:");
    console.log("======================================================");
    
    // 5. Ejemplo adicional: buscar libros por autor
    const autoresUnicos = [...new Set(biblioteca.map(libro => libro.autor))];
    
    console.log("\nAutores en la colección:");
    autoresUnicos.forEach((autor, index) => {
        const librosAutor = biblioteca.filter(libro => libro.autor === autor);
        console.log(`${index + 1}. ${autor}: ${librosAutor.length} libro(s)`);
    });
}

// Ejecutar el programa
main();