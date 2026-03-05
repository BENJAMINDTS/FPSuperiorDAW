console.log("Vamos a multiplicar numeros hasta encontar un valor mayor que 10 ");
exterior: for (let i = 1; i <= 5; i++) {
    for (let j = 1; j <= 5; j++) {
        let valor = i * j;
        console.log(i + " * " + j + " = " + valor);
        if (valor > 10) {
            console.log("Valor encontrado: " + valor);
            break exterior;
        }
    }
}