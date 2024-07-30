let apiUpdateAccount = "http://localhost:8080/employee/user/";
let updateAccountButton = document.getElementById("update");
let fname = "";
let lname = "";
let email = "";
let username = "";
let password = "";


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

function getInfo(){
  let userid = getCookie("id");
  let auth = getCookie("Authorization");
  let getUser = `${apiUpdateAccount}${userid}`;

  fetch(getUser, {
    method: 'GET',
    headers: {
      id: userid,
      Authorization: auth
    }
  })
    .then((res) => res.json())
    .then((data) => {
      fname = data.fname;
      document.getElementById('first').setAttribute('value', fname);
      lname = data.lname;
      document.getElementById('last').setAttribute('value', lname);
      email = data.email;
      document.getElementById('email').setAttribute('value', email);
      username = data.username;
      document.getElementById('username').setAttribute('value', username);
      //maybe have them fillin the whole password again?
      //password = data.password;
      //document.getElementById('password').setAttribute('value', password);

    });
}

getInfo();



updateAccountButton.addEventListener('click', function (event) {

    let userid = getCookie("id");
    let auth = getCookie("Authorization");
    let getUser2 = `${apiUpdateAccount}${userid}`;
    fname = document.getElementById("first").value;
    lname = document.getElementById("last").value;
    email = document.getElementById("email").value;
    username = document.getElementById("username").value;
    password = document.getElementById("password").value;
    event.preventDefault();

    let update = {
        fname, lname, email, username, password
      }

    
    //Fetch the url request.
    fetch(getUser2, {
        method: 'PUT',
        headers: {
          id: userid,
          Authorization: auth
        },
        body: JSON.stringify(update)
    })
        .then((res) => res.text())
        .then((data) => {
            console.log(data),
            console.log("Updated.")
            document.location.href = "employeeHome.html";
        });
})