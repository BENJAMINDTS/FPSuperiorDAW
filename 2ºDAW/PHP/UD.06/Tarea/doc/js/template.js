/**
 * @fileoverview Script para gestionar el comportamiento dinámico de elementos en la página,
 * como la tabla de contenidos (sticky) y la apertura interactiva de diagramas UML en formato SVG.
 * @author BenjaminDTS
 */

/**
 * Función anónima autoejecutable (IIFE) que inicializa un IntersectionObserver
 * cuando la página ha cargado completamente. Observa el contenedor principal
 * de la tabla de contenidos para añadir o quitar la clase CSS "-stuck"
 * dependiendo de si el elemento está fijo (sticky) en la pantalla.
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
 * Toma un elemento SVG del DOM, lo serializa a XML, crea un Blob con tipo MIME de imagen SVG,
 * y lo abre en una nueva pestaña o ventana del navegador utilizando una URL de objeto.
 * Una vez cargada la nueva ventana, la URL es revocada para liberar memoria.
 * * @param {SVGElement|Element} svg - El elemento del DOM correspondiente al gráfico SVG que se desea abrir.
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
 * @description Bloque de inicialización que busca todos los elementos SVG
 * dentro de contenedores de diagramas UML y les asigna un evento de clic.
 * Al hacer clic en un diagrama, este se abrirá en una nueva ventana utilizando la función `openSvg`.
 */
var svgs = document.querySelectorAll(".phpdocumentor-uml-diagram svg");
for (var i = 0, il = svgs.length; i < il; i++) {
    svgs[i].onclick = (evt) => openSvg(evt.target);
}