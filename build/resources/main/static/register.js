const apiRegister = 'http://localhost:8080/user';
let registerButton = document.getElementById("registerForm");

registerButton.addEventListener('submit', function(event) {

    let fname = document.getElementById("first").value;
    let lname = document.getElementById("last").value;
    let email = document.getElementById("email").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let roleId = document.getElementById("roles").value;

    event.preventDefault();

    let register = {
        fname, lname, email, username, password, roleId}


    fetch(apiRegister, {
        method: 'POST',
        body: JSON.stringify(register),
    })
    .then(res => res.text())
    .then(data => {
        console.log(fname);
        console.log(lname);
        console.log(email);
        console.log(username);
        console.log(password);
        console.log('Success', data);
        document.location.href = "login.html";
    })
    .catch((error) => {
        console.error('Error:', error);
    });
});