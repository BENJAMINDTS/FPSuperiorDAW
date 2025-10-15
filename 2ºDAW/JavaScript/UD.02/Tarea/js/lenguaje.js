// Importa el módulo 'fs' de Node.js para trabajar con el sistema de archivos
const fs = require('fs');

// Muestra en consola el título principal sobre JavaScript
console.log('=== ¿QUÉ ES JAVASCRIPT Y POR QUÉ LO USAMOS? ===\n');

// Explica por qué JavaScript es importante en las páginas web
console.log('1. ¿Por qué es tan importante en las páginas web?');
console.log('Básicamente, JavaScript es el único lenguaje que entienden los navegadores para hacer cosas interactivas. Sin él, las webs serían solo texto e imágenes sin movimiento.\n');

// Enumera las ventajas de usar JavaScript
console.log('2. ¿Qué ventajas tiene JavaScript?');
console.log('- No necesitas instalar nada extra, funciona directamente en Chrome, Firefox, etc.');
console.log('- Hay miles de herramientas y librerías que te facilitan el trabajo');
console.log('- Si te atascas, hay millones de tutoriales y foros para ayudarte');
console.log('- Es bastante fácil de aprender comparado con otros lenguajes\n');

// Explica cómo JavaScript puede cambiar el contenido de una página web
console.log('3. ¿Cómo cambia las cosas en una página web?');
console.log('Con JavaScript puedes cambiar textos, colores, mostrar u ocultar elementos, responder a clicks... todo sin recargar la página.\n');

// Define el contenido que se va a guardar en un archivo de texto
const contenido = `RESUMEN: Para qué sirve JavaScript en las páginas web

1. ¿POR QUÉ ES TAN IMPORTANTE?
JavaScript es el lenguaje que hace que las páginas web sean interactivas. Es el único que los navegadores (Chrome, Firefox, Safari...) entienden de forma directa. Gracias a él, puedes hacer que un botón haga algo cuando lo pulsas, validar formularios antes de enviarlos, crear animaciones, mostrar u ocultar contenido... En resumen, sin JavaScript las páginas web serían como documentos de Word: solo texto e imágenes estáticas.

2. VENTAJAS DE USAR JAVASCRIPT
- Funciona en cualquier navegador: No tienes que instalar nada especial, simplemente funciona.
- Montones de recursos: Hay un montón de librerías como React o Vue que te ahorran mucho trabajo. Es como tener atajos para hacer cosas complicadas.
- Comunidad gigante: Si tienes un problema, seguro que alguien ya lo tuvo antes y hay soluciones en internet.
- Relativamente fácil: Comparado con otros lenguajes de programación, JavaScript es bastante accesible para empezar.
- Puedes usarlo para todo: No solo para páginas web, también para servidores (con Node.js) o aplicaciones móviles.

3. ¿CÓMO FUNCIONA CON EL DOM?
El DOM (Document Object Model) es básicamente la estructura de tu página HTML vista como un árbol. JavaScript puede "hablar" con ese árbol para cambiar cosas:

- Buscar elementos: Puedes encontrar cualquier parte de tu página (un botón, un párrafo, una imagen...) usando comandos como document.getElementById() o querySelector().

- Cambiar contenido: Puedes modificar el texto que aparece, cambiar atributos, añadir o quitar clases CSS...

- Crear y eliminar cosas: Puedes crear nuevos elementos y añadirlos a la página, o eliminar los que ya existen.

- Reaccionar a eventos: Puedes hacer que pasen cosas cuando el usuario hace click, escribe en un campo, mueve el ratón, etc.

Por ejemplo, si quieres que al pulsar un botón aparezca un mensaje, JavaScript es el que lo hace posible. O si quieres que un formulario te avise si dejas un campo vacío antes de enviarlo. Todo eso es JavaScript manipulando el DOM.`;

// Escribe el contenido anterior en un archivo llamado 'resumen.txt' en el directorio padre
// El método writeFile recibe la ruta del archivo, el contenido y una función callback
fs.writeFile('../generated/resumen.txt', contenido, (err) => {
  // Si ocurre un error al escribir el archivo, lanza una excepción
  if (err) throw err;
  // Si todo sale bien, muestra un mensaje de éxito en la consola
  console.log('Listo! El archivo resumen.txt se ha creado correctamente en el directorio anterior');
});