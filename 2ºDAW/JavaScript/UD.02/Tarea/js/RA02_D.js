// RA02_D.js
//Declaramos la variable para el output y añadimos el encabezado
let outputD = "<div id='data_type'><h2>RA02_D</h2>";
//Añadimos a la posición 7 del array etiquetas el resultado de la suma de un número y una cadena
etiquetas[7] = 1 + "2";
//Mostramos el tipo de dato y el valor de la posición 7 del array etiquetas
outputD += `<p>El tipo de dato de etiquetas[7] es: <strong>${typeof etiquetas[7]}</strong></p>`; // Usamos typeof para mostrar el tipo de dato
outputD += `<p>El valor de etiquetas[7] es: <strong>${etiquetas[7]}</strong></p>`; // Mostramos el valor
outputD += "</div>"; // Cerramos el div

document.body.insertAdjacentHTML("beforeend", outputD); // Insertamos el output en el body
