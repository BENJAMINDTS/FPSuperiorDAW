function construirCoche(marca, modelo, año) {
  return {
    marca: marca,
    modelo: modelo,
    año: año,
    mostrarInfo: function() {
      return this.marca + " " + this.modelo + " (" + this.año + ")";
    }
  };
}

const miCoche = construirCoche("Honda", "Civic", 2022);
console.log(miCoche.mostrarInfo());