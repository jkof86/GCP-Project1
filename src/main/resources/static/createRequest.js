const apiCreateRequest = 'http://localhost:8080/employee/request';
let createRequestButton = document.getElementById("requestForm");


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


createRequestButton.addEventListener('submit', function(event) {

    let amount = document.getElementById("amount").value;
    let description = document.getElementById("description").value;
    let typeId = document.getElementById("type").value;
    event.preventDefault();

    let author = getCookie("id");
    let statusId = 1;
    let auth = getCookie("Authorization");

    let request = {
        amount, description, author, statusId, typeId,
    }

    fetch(apiCreateRequest, {
        method: 'POST',
        body: JSON.stringify(request),
        headers: {
            id: author,
            Authorization: auth
          }
    })
    .then(res => res.text())
    .then(data => {
        console.log(amount);
        console.log(description);
        console.log(typeId);
        console.log('Success', data);
        document.location.href = "employeeHome.html";
    })
    .catch((error) => {
        console.error('Error:', error);
    });
});