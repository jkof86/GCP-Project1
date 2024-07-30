
const apiLogin = 'http://localhost:8080/login';
let loginButton = document.getElementById("loginForm");

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }


loginButton.addEventListener('submit', function(event) {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    event.preventDefault();


    let login = {
        username, password
    }

    fetch(apiLogin, {
        method: 'POST',
        body: JSON.stringify(login),
    })
    .then(res => {
        document.cookie = `id=${res.headers.get("id")}`;
        document.cookie = `Authorization=${res.headers.get("Authorization")}`;
        return res.text();
    })
    .then(data => {
        console.log('Success', data);
        if(getCookie("Authorization") == "EMPLOYEE-TOKEN"){
            document.location.href = "employeeHome.html";
        } else if(getCookie("Authorization") == "MANAGER-TOKEN") {
            document.location.href = "managerHome.html";
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });

});





