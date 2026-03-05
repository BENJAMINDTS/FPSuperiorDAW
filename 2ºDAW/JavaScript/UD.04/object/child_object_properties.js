profesor = new Object();
profesor.nombre = "Ana";
profesor.apellido = "García";
profesor.edad = 35;

profesor.mostrarInfo = function() {
    return this.nombre + " " + this.apellido + ", Edad: " + this.edad;
};

console.log(profesor.mostrarInfo());

curso = new Object();
curso.titulo = "Programación en JavaScript";
curso.duracion = "3 meses";
curso.instructor = profesor;

console.log("Curso: " + curso.titulo + ", Duración: " + curso.duracion);
console.log("Instructor: " + curso.instructor.mostrarInfo());