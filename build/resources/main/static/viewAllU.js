
let newDiv = document.createElement("div");
let apiView = "http://localhost:8080/manager/users";
let viewButton = document.getElementById("view-employees");

let newUserClass = document.getElementsByClassName("userInfo")[0];
let newUser = document.getElementById("currUser");



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

viewButton.addEventListener('click', function (event) {

    let userid = getCookie("id");
    let auth = getCookie("Authorization");

    //Fetch the url request.
    fetch(apiView, {
      method: 'GET',
      headers: {
        id: userid,
        Authorization: auth
      }
    })
        .then((res) => res.json())
        .then((data) => {
            console.log(data),
            console.log(data)
            newDiv.innerHTML += `<p style="font-size: 30px">All Employees</p>`
            newUserClass.append(newDiv);
            for(let i = 0; i < data.length; i++){
            newDiv.innerHTML += `<style>
            table, td, th {
              border: 1px solid white;
            }
            
            table {
              border-collapse: collapse;
              width: 100%;
              table-layout: fixed;
            }
            
            td {
              height: 30px;
            }
            </style>
            <table>
            <tr>
              <th>ID #</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>E-Mail</th>
              <th>Username</th>
            </tr>
            <tr>
              <td>${data[i].id}</td>
              <td>${data[i].fname}</td>
              <td>${data[i].lname}</td>
              <td>${data[i].email}</td>
              <td>${data[i].username}</td>
            </tr>
            <br>
          </table>`
          }
            newUserClass.append(newDiv);
            console.log(newUser);
        });
})