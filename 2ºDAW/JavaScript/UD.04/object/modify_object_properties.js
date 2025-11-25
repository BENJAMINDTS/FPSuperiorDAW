cuentaBancaria = new Object();
cuentaBancaria.titular = "Sebastian";
cuentaBancaria.saldo = 1500.75;
cuentaBancaria.tipoCuenta = "Ahorros";

cuentaBancaria.mostrarInfo = function() {
    return "Titular: " + this.titular + ", Saldo: $" + this.saldo.toFixed(2) + ", Tipo de Cuenta: " + this.tipoCuenta;
}

console.log(cuentaBancaria.mostrarInfo());

function depositar(cantidad) {
    if (cantidad > 0) {
        this.saldo += cantidad;
        console.log("Depósito exitoso de $" + cantidad.toFixed(2));
    } else {
        console.log("El cantidad a depositar debe ser positivo.");
    }
}

function retirar(cantidad) {
    if (cantidad > 0 && cantidad <= this.saldo) {
        this.saldo -= cantidad;
        console.log("Retiro exitoso de $" + cantidad.toFixed(2));
    } else if (cantidad > this.saldo) {
        console.log("Fondos insuficientes para el retiro de $" + cantidad.toFixed(2));
    } else {
        console.log("El cantidad a retirar debe ser positivo.");
    }
}

cuentaBancaria.depositar = depositar;
cuentaBancaria.retirar = retirar;

cuentaBancaria.depositar(500);
console.log(cuentaBancaria.mostrarInfo());

cuentaBancaria.retirar(300);
console.log(cuentaBancaria.mostrarInfo());

cuentaBancaria.retirar(2000); // Intento de retiro con fondos insuficientes