// RA02_E.js
//Declaramos la variable para el output y añadimos el encabezado
let outputE = "<div id='ficha-alumno'><h2>RA02_E</h2><ol>";
//Mostramos solo los datos de las posiciones pares del array fichAlumno junto con sus etiquetas
for (let i = 0; i < fichAlumno.length; i++) {
  if (i % 2 == 0)
    // Si el índice es par (0, 2, 4, 6...)
    outputE += `<li>${etiquetas[i]}${fichAlumno[i]}</li>`; // Mostramos la etiqueta y el dato correspondiente
}
outputE += "</ol></div>"; // Cerramos el div

//Insertamos el output en el body
document.body.insertAdjacentHTML("beforeend", outputE);
