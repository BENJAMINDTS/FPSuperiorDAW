/**
 * @fileoverview Módulo de búsqueda para phpDocumentor.
 * Este módulo es un envoltorio (wrapper) alrededor de fuse.js que utiliza un índice dado
 * y se vincula a un formulario de búsqueda y a un panel de resultados identificados por
 * atributos de datos (`data-search-form` y `data-search-results`).
 * @author BenjaminDTS
 */

var Search = (function () {
    /** @type {Object} Instancia del motor de búsqueda Fuse.js */
    var fuse;

    /** @type {Array<Object>} Arreglo que almacena el índice de búsqueda */
    var index = [];

    /** * @type {Object} 
     * @description Opciones de configuración predeterminadas para Fuse.js
     */
    var options = {
        shouldSort: true,
        threshold: 0.6,
        location: 0,
        distance: 100,
        maxPatternLength: 32,
        minMatchCharLength: 1,
        keys: [
            "fqsen",
            "name",
            "summary",
            "url"
        ]
    };

    /**
     * Limita la frecuencia con la que se puede ejecutar una función.
     * La función proporcionada solo se ejecutará después de que transcurran `wait` 
     * milisegundos sin que haya sido invocada de nuevo.
     * * @param {Function} func - La función que se desea limitar.
     * @param {number} wait - Tiempo de espera en milisegundos.
     * @param {boolean} [immediate] - Si es `true`, ejecuta la función al inicio en lugar de al final del tiempo de espera.
     * @returns {Function} Una versión "debounced" de la función proporcionada.
     */
    function debounce(func, wait, immediate) {
        var timeout;

        return function executedFunction() {
            var context = this;
            var args = arguments;

            var later = function () {
                timeout = null;
                if (!immediate) func.apply(context, args);
            };

            var callNow = immediate && !timeout;
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
            if (callNow) func.apply(context, args);
        };
    }

    /**
     * Cierra el panel de resultados de la búsqueda, restaura el comportamiento
     * de desplazamiento (scroll) original de la página y quita el foco del campo de texto.
     * @function
     */
    function close() {
        // Prevención de scroll: https://css-tricks.com/prevent-page-scrolling-when-a-modal-is-open/
        const scrollY = document.body.style.top;
        document.body.style.position = '';
        document.body.style.top = '';
        window.scrollTo(0, parseInt(scrollY || '0') * -1);

        var form = document.querySelector('[data-search-form]');
        var searchResults = document.querySelector('[data-search-results]');

        form.classList.toggle('phpdocumentor-search--has-results', false);
        searchResults.classList.add('phpdocumentor-search-results--hidden');
        var searchField = document.querySelector('[data-search-form] input[type="search"]');
        searchField.blur();
    }

    /**
     * Maneja el evento de entrada en el campo de búsqueda, bloquea el scroll de la página,
     * ejecuta la consulta utilizando Fuse.js y construye el DOM con los resultados.
     * * @param {Event} event - El evento disparado por el campo de entrada.
     */
    function search(event) {
        // Prevención de scroll
        document.body.style.position = 'fixed';
        document.body.style.top = `-${window.scrollY}px`;

        // Previene que presionar 'Enter' envíe el formulario automáticamente
        event.stopPropagation();

        var form = document.querySelector('[data-search-form]');
        var searchResults = document.querySelector('[data-search-results]');
        var searchResultEntries = document.querySelector('[data-search-results] .phpdocumentor-search-results__entries');

        searchResultEntries.innerHTML = '';

        if (!event.target.value) {
            close();
            return;
        }

        form.classList.toggle('phpdocumentor-search--has-results', true);
        searchResults.classList.remove('phpdocumentor-search-results--hidden');
        var results = fuse.search(event.target.value, { limit: 25 });

        results.forEach(function (result) {
            var entry = document.createElement("li");
            entry.classList.add("phpdocumentor-search-results__entry");
            entry.innerHTML += '<h3><a href="' + document.baseURI + result.url + '">' + result.name + "</a></h3>\n";
            entry.innerHTML += '<small>' + result.fqsen + "</small>\n";
            entry.innerHTML += '<div class="phpdocumentor-summary">' + result.summary + '</div>';
            searchResultEntries.appendChild(entry)
        });
    }

    /**
     * Concatena nuevos datos al índice de búsqueda existente.
     * Si Fuse.js ya estaba inicializado, se reinicia con el índice actualizado.
     * * @param {Array<Object>} added - Arreglo de objetos a incorporar al índice de búsqueda.
     */
    function appendIndex(added) {
        index = index.concat(added);

        if (typeof fuse !== 'undefined') {
            fuse = new Fuse(index, options);
        }
    }

    /**
     * Inicializa la instancia de Fuse.js, configura los 'event listeners' para los
     * clics y atajos de teclado, y habilita el campo de entrada de búsqueda.
     * @function
     */
    function init() {
        fuse = new Fuse(index, options);

        var form = document.querySelector('[data-search-form]');
        var searchField = document.querySelector('[data-search-form] input[type="search"]');

        var closeButton = document.querySelector('.phpdocumentor-search-results__close');
        closeButton.addEventListener('click', function () { close() }.bind(this));

        var searchResults = document.querySelector('[data-search-results]');
        searchResults.addEventListener('click', function () { close() }.bind(this));

        form.classList.add('phpdocumentor-search--active');

        searchField.setAttribute('placeholder', 'Search (Press "/" to focus)');
        searchField.removeAttribute('disabled');
        searchField.addEventListener('keyup', debounce(search, 300));

        window.addEventListener('keyup', function (event) {
            if (event.key === '/') {
                searchField.focus();
            }
            if (event.code === 'Escape') {
                close();
            }
        }.bind(this));
    }

    return {
        appendIndex,
        init
    }
})();

/**
 * Escucha el evento `DOMContentLoaded`.
 * Agrega una clase para mostrar el cuadro de búsqueda una vez que se confirme que JS es compatible.
 */
window.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('[data-search-form]');
    form.classList.add('phpdocumentor-search--enabled');
});

/**
 * Escucha el evento `load`.
 * Ejecuta la inicialización de la lógica de búsqueda tras cargar todos los recursos de la página.
 */
window.addEventListener('load', function () {
    Search.init();
});