//We build a new Date object to get the current date and time
let fechaActual = new Date()

//We extract the day, month, and year from the Date object
let dia = fechaActual.getDate()
let mes = fechaActual.getMonth() + 1 //Months are zero-indexed
let anio = fechaActual.getFullYear()

//We format the date as DD/MM/YYYY
let fechaFormateada = `${dia}/${mes}/${anio}`

//We output the current date
console.log("Fecha actual:", fechaFormateada)

//We also output the full date and time
console.log("Fecha y hora actual:", fechaActual.toString())


