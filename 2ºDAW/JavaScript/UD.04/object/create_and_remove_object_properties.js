coche = new Object();
coche.marca = "Toyota";
coche.modelo = "Corolla";
coche.año = 2020;

coche.mostrarInfo = function() {
    return this.marca + " " + this.modelo + " (" + this.año + ")";
};

console.log(coche.mostrarInfo());

delete coche.año;

console.log(coche.mostrarInfo());

// Intentar acceder a la propiedad eliminada
console.log("Año después de eliminar:", coche.año);

//Agregar una nueva propiedad
coche.color = "Rojo";
console.log("Color del coche:", coche.color);

// Modificar una propiedad existente
coche.modelo = "Camry";
console.log("Modelo modificado del coche:", coche.modelo);

console.log(coche.mostrarInfo());