let frase = "El clima es soleado"
//Replace "soleado" with "lluvioso" using indexOf() and replace()
let index = frase.indexOf("soleado")
if (index !== -1) {
  let nuevaFrase = frase.replace("soleado", "lluvioso")
  console.log(nuevaFrase) //El clima es lluvioso
} else {
  console.log("La palabra no se encontró en la frase.")
}