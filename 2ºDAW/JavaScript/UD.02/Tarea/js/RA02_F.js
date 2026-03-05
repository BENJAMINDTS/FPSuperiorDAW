// RA02_F.js
//Declaramos la variable para el output y añadimos el encabezado
let outputF = "<div id='ficha-alumno'><h2>RA02_F</h2><h3>Bucle for</h3><ol>";
//Mostramos todos los datos del array fichAlumno junto con sus etiquetas usando un bucle for
for (let i = 0; i < fichAlumno.length; i++) {
  outputF += `<li>${etiquetas[i]}${fichAlumno[i]}</li>`; // Mostramos la etiqueta y el dato correspondiente
}
outputF += "</ol></div>"; // Cerramos el div

//Insertamos el output en el body

document.body.insertAdjacentHTML("beforeend", outputF);

//Usamos un bucle while para ajustar la longitud de los arrays si es necesario
// Si fichAlumno es más largo, eliminamos elementos hasta que tengan la misma longitud
while (fichAlumno.length > etiquetas.length) {
  fichAlumno.pop();
}
// Si etiquetas es más largo, eliminamos elementos hasta que tengan la misma longitud
while (etiquetas.length > fichAlumno.length) {
  etiquetas.pop();
}
// Mostramos los datos ajustados usando un bucle while
let outputF2 = "<div id='ficha-alumno-ajustada'><h3>Bucle While</h3><ol>";
let i = 0;
while (i < fichAlumno.length) {
  outputF2 += `<li>${etiquetas[i]}${fichAlumno[i]}</li>`; // Mostramos la etiqueta y el dato correspondiente
  i++;
}
outputF2 += "</ol></div>"; // Cerramos el div

document.body.insertAdjacentHTML("beforeend", outputF2); // Insertamos el output en el body

// Mostramos los datos usando un bucle forEach

let outputF3 = "<div id='ficha-alumno-foreach'><h3>Bucle For Each</h3><ol>";
fichAlumno.forEach((valor, indice) => {
  outputF3 += `<li>${etiquetas[indice]}${valor}</li>`; // Mostramos la etiqueta y el dato correspondiente
});
outputF3 += "</ol></div>"; // Cerramos el div

document.body.insertAdjacentHTML("beforeend", outputF3); // Insertamos el output en el body
