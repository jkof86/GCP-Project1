
let viewAccountDiv = document.createElement("div");
let apiViewAccount = "http://localhost:8080/employee/user/";
let viewAccountButton = document.getElementById("view-account");

let viewUserClass = document.getElementsByClassName("userInfo")[0];
let viewUser = document.getElementById("currUser");



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

viewAccountButton.addEventListener('click', function (event) {

    let userid = getCookie("id");
    let auth = getCookie("Authorization");
    let getUser = `${apiViewAccount}${userid}`;

    //Display the url in the console.
    console.log(getUser);
    
    //Fetch the url request.
    fetch(getUser, {
        headers: {
          id: userid,
          Authorization: auth
        }
    })
        .then((res) => res.json())
        .then((data) => {
            viewAccountDiv.innerHTML += `<h1>Account Information</h1>
            <p>First Name: ${data.fname}</p>
            <p>Last Name: ${data.lname}</p>
            <p>E-mail: ${data.email}</p>
            <p>Username: ${data.username}</p>
            <p>Password: ${data.password}</p>`
            viewUserClass.append(viewAccountDiv);
            console.log(viewUserClass);
        });
})