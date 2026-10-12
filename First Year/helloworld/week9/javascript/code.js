console.log('Hello World From an exernal JS file');

//Variables & Constants
const MAX_SCORE = 42;
let age = 50;
let username = "Ryan";
console.log("Hello " + username + "! You are " + age);


//IF Statements
if (age > MAX_SCORE) {
    console.log("Too old. Prepare to die");
} else {
    console.log("We appreciate you");
}


// Arrays & FOR Loop
let fruits = ["apple", "pear", "cherry"];
console.log(fruits);
console.log(fruits.length);
for (let index = 0; index < fruits.length; index++) {
    console.log(fruits[index]);
}

// String Functions
let text = "JavaScript Basics"

text.includes("Script");
text.indexOf("Script");
text.replace("Basics", "101");

let values = text.split(" ")

// Numeric Functions
let a = "10"
let b =5;
console.log(Number(a) + b);

//To nearest integer
Math.round(b/2);
// To set d.p.
(b/2).toFixed(2)