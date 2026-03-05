//We can see how far is a date from another date in JavaScript
//We create the actual date
let fechaActual = new Date()

//We create a specific date, for example new Year's Day 2026
let fechaFutura = new Date(2026, 0, 1) //Months are zero-indexed

//We calculate the difference in milliseconds
let diferenciaMs = fechaFutura - fechaActual

//We convert milliseconds to days
let diferenciaDias = Math.ceil(diferenciaMs / (1000 * 60 * 60 * 24))

//We output the difference in days
console.log(`Faltan ${diferenciaDias} días para el Año Nuevo 2026.`)