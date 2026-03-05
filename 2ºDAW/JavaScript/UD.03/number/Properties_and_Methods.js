//We define a number
let number = 45.6789

//We use toFixed() to format a number to a specified number of decimal places
let fixedNumber = number.toFixed(2)
console.log(fixedNumber) //45.68
console.log(typeof fixedNumber) //string

//We use toPrecision() to format a number to a specified length
let preciseNumber = number.toPrecision(3)
console.log(preciseNumber) //45.68
console.log(typeof preciseNumber) //string