/**
 * @fileoverview Script para gestionar elementos dinámicos en la interfaz, como
 * el comportamiento fijo (sticky) de la tabla de contenidos y la apertura de 
 * diagramas UML en formato SVG en nuevas ventanas.
 * @author BenjaminDTS
 */

/**
 * Función anónima autoejecutable (IIFE) que configura un IntersectionObserver
 * tras la carga completa de la página. Evalúa si el contenedor de la tabla
 * de contenidos está completamente visible y alterna la clase CSS "-stuck".
 * @function
 */
(function () {
    window.addEventListener('load', () => {
        const el = document.querySelector('.phpdocumentor-on-this-page__content')
        if (!el) {
            return;
        }

        const observer = new IntersectionObserver(
            ([e]) => {
                e.target.classList.toggle("-stuck", e.intersectionRatio < 1);
            },
            { threshold: [1] }
        );

        observer.observe(el);
    })
})();

/**
 * Convierte un elemento SVG en un archivo Blob y lo abre en una nueva ventana 
 * del navegador. Revoca la URL del objeto una vez que la ventana ha cargado 
 * para permitir la recolección de basura y liberar memoria.
 * @param {SVGElement} svg - El elemento DOM del gráfico SVG a abrir.
 */
function openSvg(svg) {
    // convert to a valid XML source
    const as_text = new XMLSerializer().serializeToString(svg);
    // store in a Blob
    const blob = new Blob([as_text], { type: "image/svg+xml" });
    // create an URI pointing to that blob
    const url = URL.createObjectURL(blob);
    const win = open(url);
    // so the Garbage Collector can collect the blob
    win.onload = (evt) => URL.revokeObjectURL(url);
};

/**
 * @description Selecciona todos los diagramas UML SVG en el documento
 * y les añade un evento 'onclick' para abrirlos en una nueva ventana usando `openSvg`.
 */
var svgs = document.querySelectorAll(".phpdocumentor-uml-diagram svg");
for (var i = 0, il = svgs.length; i < il; i++) {
    svgs[i].onclick = (evt) => openSvg(evt.target);
}