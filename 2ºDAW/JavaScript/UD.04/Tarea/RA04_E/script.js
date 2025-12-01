/**
 * Módulo principal para procesamiento de arrays numéricos
 * @author Benjamin Santiago Gonzalez
 * @version 1.0
 * @module arraysScript
 * @description Aplicación web que permite al usuario ingresar números,
 *              separarlos en pares e impares, ordenarlos y mostrar los resultados.
 */

// Datos del alumno - REEMPLAZAR CON INFORMACIÓN REAL
/**
 * Objeto que contiene los datos personales del alumno
 * @type {Object}
 * @property {string} nombre - Nombre del alumno
 * @property {string} apellidos - Apellidos del alumno
 * @property {string} dni - Documento Nacional de Identidad
 */
const DATOS_ALUMNO = {
    nombre: "Benjamin",
    apellidos: "Santiago Gonzalez",
    dni: "20889654L"
};

// Variables globales para referencias a elementos DOM
/** @type {HTMLInputElement} Campo de entrada para números */
let numerosInput;
/** @type {HTMLButtonElement} Botón para procesar el array */
let procesarBtn;
/** @type {HTMLButtonElement} Botón para limpiar los datos */
let limpiarBtn;
/** @type {HTMLParagraphElement} Elemento para mostrar el array original */
let arrayOriginalElem;
/** @type {HTMLParagraphElement} Elemento para mostrar números pares */
let paresElem;
/** @type {HTMLParagraphElement} Elemento para mostrar números impares */
let imparesElem;
/** @type {HTMLParagraphElement} Elemento para información adicional de pares */
let infoParesElem;
/** @type {HTMLParagraphElement} Elemento para información adicional de impares */
let infoImparesElem;
/** @type {HTMLParagraphElement} Elemento para mostrar datos del alumno */
let datosAlumnoElem;

/**
 * Inicializa todas las referencias a elementos DOM y configura los event listeners
 * @function inicializarDOM
 * @description Esta función se ejecuta cuando la página se carga completamente.
 *              Obtiene referencias a todos los elementos HTML necesarios y
 *              configura los manejadores de eventos para los botones y el campo de entrada.
 * @returns {void}
 * @example
 * // Se ejecuta automáticamente cuando el DOM está listo
 * inicializarDOM();
 */
function inicializarDOM() {
    // Obtener referencias a elementos del DOM
    numerosInput = document.getElementById('numerosInput');
    procesarBtn = document.getElementById('procesarBtn');
    limpiarBtn = document.getElementById('limpiarBtn');
    
    arrayOriginalElem = document.getElementById('arrayOriginal');
    paresElem = document.getElementById('pares');
    imparesElem = document.getElementById('impares');
    infoParesElem = document.getElementById('infoPares');
    infoImparesElem = document.getElementById('infoImpares');
    datosAlumnoElem = document.getElementById('datosAlumno');
    
    // Configurar evento para el botón procesar
    procesarBtn.addEventListener('click', procesarArray);
    
    // Configurar evento para el botón limpiar
    limpiarBtn.addEventListener('click', limpiarDatos);
    
    // Permitir procesar con la tecla Enter en el campo de entrada
    numerosInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            procesarArray();
        }
    });
    
    // Mostrar datos del alumno al inicializar
    mostrarDatosAlumno();
}

/**
 * Muestra los datos del alumno en el elemento correspondiente del DOM
 * @function mostrarDatosAlumno
 * @description Toma los datos del objeto DATOS_ALUMNO y los formatea
 *              para mostrarlos en el elemento HTML designado.
 * @returns {void}
 * @example
 * // Muestra: "Nombre: Juan | Apellidos: Pérez García | DNI: 12345678A"
 * mostrarDatosAlumno();
 */
function mostrarDatosAlumno() {
    if (datosAlumnoElem) {
        datosAlumnoElem.textContent = 
            `Nombre: ${DATOS_ALUMNO.nombre} | Apellidos: ${DATOS_ALUMNO.apellidos} | DNI: ${DATOS_ALUMNO.dni}`;
    }
}

/**
 * Función principal que procesa la entrada del usuario
 * @function procesarArray
 * @description Toma la entrada del usuario, la convierte en un array de números,
 *              separa los números pares e impares, los ordena y muestra los resultados.
 *              También realiza validaciones de entrada.
 * @returns {void}
 * @throws {Error} Si la entrada no contiene números válidos
 * @example
 * // Cuando el usuario ingresa "1,2,3,4,5" y hace clic en procesar
 * procesarArray();
 */
function procesarArray() {
    const entrada = numerosInput.value.trim();
    
    // Validar que la entrada no esté vacía
    if (!entrada) {
        mostrarError('Por favor, ingresa algunos números separados por comas.');
        return;
    }
    
    try {
        // Convertir entrada en array de números
        const numeros = convertirEntradaEnArray(entrada);
        
        // Validar que se encontraron números
        if (numeros.length === 0) {
            mostrarError('No se encontraron números válidos en la entrada.');
            return;
        }
        
        // Mostrar array original
        mostrarArrayOriginal(numeros);
        
        // Obtener y mostrar números pares
        const pares = obtenerParesOrdenados(numeros);
        mostrarPares(pares);
        
        // Obtener y mostrar números impares
        const impares = obtenerImparesOrdenados(numeros);
        mostrarImpares(impares);
        
    } catch (error) {
        mostrarError(`Error al procesar: ${error.message}`);
    }
}

/**
 * Convierte una cadena de texto con números separados por comas en un array de números
 * @function convertirEntradaEnArray
 * @param {string} entrada - Cadena de texto con números separados por comas
 * @returns {number[]} Array de números extraídos de la cadena
 * @description Divide la cadena por comas, convierte cada elemento a número
 *              y filtra los valores que no sean números válidos.
 * @example
 * // Retorna [1, 2, 3, 4, 5]
 * convertirEntradaEnArray("1,2,3,4,5");
 * // Retorna [10, 20, 30]
 * convertirEntradaEnArray("10, 20, 30, abc, 40");
 */
function convertirEntradaEnArray(entrada) {
    return entrada
        .split(',')
        .map(item => parseFloat(item.trim()))
        .filter(num => !isNaN(num));
}

/**
 * Filtra y ordena los números pares de un array
 * @function obtenerParesOrdenados
 * @param {number[]} array - Array de números a procesar
 * @returns {number[]} Array de números pares ordenados ascendentemente
 * @description Utiliza el método filter() para obtener solo los números pares
 *              y sort() para ordenarlos de menor a mayor.
 * @example
 * // Retorna [2, 4, 6]
 * obtenerParesOrdenados([3, 1, 4, 2, 6, 5]);
 * // Retorna [10, 20, 30]
 * obtenerParesOrdenados([15, 10, 30, 20, 25]);
 */
function obtenerParesOrdenados(array) {
    // Filtrar números pares (resto de división entre 2 es 0)
    const pares = array.filter(numero => numero % 2 === 0);
    
    // Ordenar ascendentemente
    pares.sort((a, b) => a - b);
    
    return pares;
}

/**
 * Filtra y ordena los números impares de un array
 * @function obtenerImparesOrdenados
 * @param {number[]} array - Array de números a procesar
 * @returns {number[]} Array de números impares ordenados ascendentemente
 * @description Utiliza el método filter() para obtener solo los números impares
 *              y sort() para ordenarlos de menor a mayor.
 * @example
 * // Retorna [1, 3, 5]
 * obtenerImparesOrdenados([3, 1, 4, 2, 6, 5]);
 * // Retorna [15, 25]
 * obtenerImparesOrdenados([15, 10, 30, 20, 25]);
 */
function obtenerImparesOrdenados(array) {
    // Filtrar números impares (resto de división entre 2 no es 0)
    const impares = array.filter(numero => numero % 2 !== 0);
    
    // Ordenar ascendentemente
    impares.sort((a, b) => a - b);
    
    return impares;
}

/**
 * Muestra el array original en el elemento correspondiente del DOM
 * @function mostrarArrayOriginal
 * @param {number[]} array - Array de números a mostrar
 * @returns {void}
 * @description Formatea el array como cadena y lo muestra en el elemento HTML
 *              junto con la cantidad de números.
 * @example
 * // Muestra: "[1, 2, 3, 4, 5] (5 números)"
 * mostrarArrayOriginal([1, 2, 3, 4, 5]);
 */
function mostrarArrayOriginal(array) {
    if (arrayOriginalElem) {
        arrayOriginalElem.textContent = `[${array.join(', ')}] (${array.length} números)`;
        arrayOriginalElem.style.color = '#4a5568';
    }
}

/**
 * Muestra los números pares en el elemento correspondiente del DOM
 * @function mostrarPares
 * @param {number[]} pares - Array de números pares a mostrar
 * @returns {void}
 * @description Si hay números pares, los muestra formateados y agrega información
 *              adicional (cantidad, suma, mínimo y máximo). Si no hay pares,
 *              muestra un mensaje indicándolo.
 * @example
 * // Con entrada [2, 4, 6, 8]
 * // Muestra: "[2, 4, 6, 8]" y "Cantidad: 4 | Suma: 20 | Mín: 2 | Máx: 8"
 * mostrarPares([2, 4, 6, 8]);
 */
function mostrarPares(pares) {
    if (paresElem && infoParesElem) {
        if (pares.length > 0) {
            // Mostrar array de pares
            paresElem.textContent = `[${pares.join(', ')}]`;
            
            // Calcular y mostrar estadísticas
            const suma = pares.reduce((acumulador, numero) => acumulador + numero, 0);
            const minimo = Math.min(...pares);
            const maximo = Math.max(...pares);
            
            infoParesElem.textContent = 
                `Cantidad: ${pares.length} | Suma: ${suma} | Mín: ${minimo} | Máx: ${maximo}`;
        } else {
            // Caso cuando no hay números pares
            paresElem.textContent = 'No hay números pares';
            infoParesElem.textContent = '';
            paresElem.style.color = '#718096';
        }
    }
}

/**
 * Muestra los números impares en el elemento correspondiente del DOM
 * @function mostrarImpares
 * @param {number[]} impares - Array de números impares a mostrar
 * @returns {void}
 * @description Si hay números impares, los muestra formateados y agrega información
 *              adicional (cantidad, suma, mínimo y máximo). Si no hay impares,
 *              muestra un mensaje indicándolo.
 * @example
 * // Con entrada [1, 3, 5, 7]
 * // Muestra: "[1, 3, 5, 7]" y "Cantidad: 4 | Suma: 16 | Mín: 1 | Máx: 7"
 * mostrarImpares([1, 3, 5, 7]);
 */
function mostrarImpares(impares) {
    if (imparesElem && infoImparesElem) {
        if (impares.length > 0) {
            // Mostrar array de impares
            imparesElem.textContent = `[${impares.join(', ')}]`;
            
            // Calcular y mostrar estadísticas
            const suma = impares.reduce((acumulador, numero) => acumulador + numero, 0);
            const minimo = Math.min(...impares);
            const maximo = Math.max(...impares);
            
            infoImparesElem.textContent = 
                `Cantidad: ${impares.length} | Suma: ${suma} | Mín: ${minimo} | Máx: ${maximo}`;
        } else {
            // Caso cuando no hay números impares
            imparesElem.textContent = 'No hay números impares';
            infoImparesElem.textContent = '';
            imparesElem.style.color = '#718096';
        }
    }
}

/**
 * Muestra un mensaje de error al usuario
 * @function mostrarError
 * @param {string} mensaje - Mensaje de error a mostrar
 * @returns {void}
 * @description Muestra una alerta con el mensaje de error y pone el foco
 *              en el campo de entrada para facilitar la corrección.
 * @example
 * // Muestra alerta con el mensaje
 * mostrarError('Por favor, ingresa números válidos');
 */
function mostrarError(mensaje) {
    alert(mensaje);
    numerosInput.focus();
}

/**
 * Limpia todos los datos y restaura el estado inicial
 * @function limpiarDatos
 * @description Vacía el campo de entrada, limpia todos los elementos de resultados
 *              y restaura los mensajes y estilos por defecto.
 * @returns {void}
 * @example
 * // Limpia todos los datos y deja la aplicación lista para nueva entrada
 * limpiarDatos();
 */
function limpiarDatos() {
    // Limpiar campo de entrada
    numerosInput.value = '';
    
    // Restaurar elementos de resultados a estado inicial
    arrayOriginalElem.textContent = 'Esperando datos...';
    paresElem.textContent = '';
    imparesElem.textContent = '';
    infoParesElem.textContent = '';
    infoImparesElem.textContent = '';
    
    // Restaurar colores por defecto
    arrayOriginalElem.style.color = '';
    paresElem.style.color = '';
    imparesElem.style.color = '';
    
    // Poner foco en el campo de entrada
    numerosInput.focus();
}

/**
 * Evento que se ejecuta cuando el DOM está completamente cargado
 * @event DOMContentLoaded
 * @description Inicializa la aplicación cuando la página está lista.
 *              Configura el DOM, muestra los datos del alumno y prepara
 *              la aplicación para recibir entrada del usuario.
 */
document.addEventListener('DOMContentLoaded', () => {
    // Inicializar referencias DOM y event listeners
    inicializarDOM();
});