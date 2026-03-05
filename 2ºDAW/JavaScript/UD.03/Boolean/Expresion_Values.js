//We will see different boolean expression values in JavaScript
//Boolean as a number 0 is false and 1 is true
let boolean = (0)
console.log(boolean)
boolean =  Boolean(boolean)
console.log(boolean) //false

console.log("-------------------")

//Bolean as a empty string is false
bolean = ("")
console.log(boolean)
boolean =  Boolean(boolean)
console.log(boolean) //false

console.log("-------------------")

//Boolean as null is false
boolean= (null)
console.log(boolean) //false
boolean =  Boolean(boolean)
console.log(boolean) //false

console.log("-------------------")

//Boolean as a not empty string is true
boolean = ("JavaScript")
console.log(boolean) //true
boolean =  Boolean(boolean)
console.log(boolean) //true

