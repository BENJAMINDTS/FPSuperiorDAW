Coche = new Object();
Coche.marca = "Toyota";
Coche.modelo = "Corolla";
Coche.año = 2020;

Coche.mostrarInfo = function() {
    return this.marca + " " + this.modelo + " (" + this.año + ")";
};
console.log(Coche.mostrarInfo());