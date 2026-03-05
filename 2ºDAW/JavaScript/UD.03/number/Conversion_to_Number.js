// Convert a string to a number
//Declare a variable with a string value
let number = "123.45"
//Output the original value and its type
console.log(number) //123.45
console.log(typeof number) //string


//Convert the string to a number using the Number() function
let convertedNumber = Number(number)

//Output the result
console.log(convertedNumber) //123.45
console.log(typeof convertedNumber) //number

//Convert the string using parseFloat() function
let parsedNumber = parseFloat(number)

//Output the result
console.log(parsedNumber) //123.45
console.log(typeof parsedNumber) //number