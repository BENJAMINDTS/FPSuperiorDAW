//We are gona show how to create a specific date in JavaScript
//We create a new Date object for December 25, 2023
let fechaEspecifica = new Date(2025, 9, 20) //Months are zero-indexed

//Now we are gonna extratct the week day
let diaSemana = fechaEspecifica.getDay() //0=Sunday, 1=Monday, ..., 6=Saturday

//We create an array to map the day number to the name
let dias = ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"]

//We output the specific date and the day of the week
console.log("Fecha específica:", fechaEspecifica.toDateString()) //Mon Dec 25 2023
console.log("Día de la semana:", dias[diaSemana]) //Lunes