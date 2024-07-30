let viewAllRequestsDiv = document.createElement("div");
let apiViewAllRequests = "http://localhost:8080/manager/requests";
let viewAllRequestsButton = document.getElementById("all-requests");

let viewAllRequestsClass = document.getElementsByClassName("userInfo")[0];



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

viewAllRequestsButton.addEventListener('click', function (event) {

    let userid = getCookie("id");
    let auth = getCookie("Authorization");
    

    //Fetch the url request.
    fetch(apiViewAllRequests, {
      method: 'GET',
      headers: {
        id: userid,
        Authorization: auth
      }
    })
        .then((res) => res.text())
        .then((data) => {
            console.log(data),
            console.log(data)
            viewAllRequestsDiv.innerHTML += `<p>${data}</p>`
            viewAllRequestsClass.append(viewAllRequestsDiv);
          });
})