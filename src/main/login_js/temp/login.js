// get user login text
var username;
var password;


// when user entered username
const usernameUA = document.querySelector('#usernameUA');
usernameUA.addEventListener('input', getUsername)

// when user entered password
const passwordUA = document.querySelector('#passwordUA');
passwordUA.addEventListener('input', getPassword)

const loginButton = document.querySelector('#loginButton');


// get user input username func
function getUsername(event) {
    username = event.target.value; // Capture the input value each time
    console.log("Username:", username); // Log or process the username as needed
}

// get user input password func
function getPassword(event) {
    password = event.target.value;
    console.log("Password:", password); // Log or process the username as needed
}

