// get user login text
var userId;
var password;


// when user entered username
const userIdUA = document.querySelector('#userIdUA');
userIdUA.addEventListener('input', getUsername);

// when user entered password
const passwordUA = document.querySelector('#passwordUA');
passwordUA.addEventListener('input', getPassword);

const loginButton = document.querySelector('#loginButton');
loginButton.addEventListener('click', () => execute(userId, password));

// get user input username func
function getUsername(event) {
    userId = event.target.value; // Capture the input value each time
    console.log("UserId:", userId); // Log or process the username as needed
}

// get user input password func
function getPassword(event) {
    password = event.target.value;
    console.log("Password:", password); // Log or process the username as needed
}

// pass to backend
function execute(userId, password) {
    // convert to json
    const loginInputData = {
        "identification": userId, 
        "password": password
    };

    const loginButtonJson = JSON.stringify(loginInputData);

    fetch('http://localhost:8080/login', 
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: loginButtonJson
        }
    )

    .then(response => {
        if (!response.ok) {
            throw new Error("Network reponse was not ok");
        }
        return response.text();
    })
    .then(data => {
        console.log('Success:', data);

        // Handle successful login, e.g., redirect to homepage
            // window.location.href = '/homepage.html';
        alert("Login succes!");
    })
    .catch(error => {
        console.error('Error:', error);
        // Display error message to the user
        alert(error.message);
    });
}

