const apiResolveRequest = 'http://localhost:8080/manager/request/';
let resolveRequestButton = document.getElementById("resolve-requests");


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


resolveRequestButton.addEventListener('click', function(event) {

    let resolver = getCookie("id");
    let requestId = document.getElementById("request-id").value;
    let statusId = document.getElementById("status").value;
    let auth = getCookie("Authorization");
    let getUser = `${apiResolveRequest}${requestId}`;
    event.preventDefault();

    console.log(resolver);
    console.log(requestId);
    console.log(statusId);

    let request = {
        resolver, statusId,
    }

    fetch(getUser, {
        method: 'PUT',
        body: JSON.stringify(request),
        headers: {
            id: resolver,
            Authorization: auth
          }
    })
    .then(res => res.text())
    .then(data => {
      console.log(data)
      console.log("Updated.")
      document.location.href = "managerHome.html";
    })
    .catch((error) => {
        console.error('Error:', error);
    });
});