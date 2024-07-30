let pendingRequestsDiv = document.createElement("div");
let apiPendingRequests = "http://localhost:8080/manager/requests/pending";
let pendingRequestsButton = document.getElementById("all-pending-requests");

let pendingRequestsClass = document.getElementsByClassName("allPendingRequests")[0];


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

pendingRequestsButton.addEventListener('click', function (event) {

    let userid = getCookie("id");
    let auth = getCookie("Authorization");
    let getPendingRequests = `${apiPendingRequests}`;

    //Display the url in the console.
    console.log(getPendingRequests);
    
    //Fetch the url request.
    fetch(getPendingRequests, {
      method: 'GET',
      headers: {
        id: userid,
        Authorization: auth
      }
    })
        .then((res) => res.json())
        .then((data) => {
            console.log(data)
            console.log(data)
            pendingRequestsDiv.innerHTML += `<p style="font-size: 30px">All Pending Requests</p>`
            pendingRequestsClass.append(pendingRequestsDiv);
            for(let i = 0; i < data.length; i++){
              let dateSubmitted = new Date(data[i].submitted);
              //console.log(date.toDateString());
              //not the best way to fix this but...
              let type = data[i].typeId;
              if(type == 1){
                type = "LODGING";
              } else if(type == 2) {
                type = "TRAVEL";
              } else if(type == 3) {
                type = "FOOD";
              } else {
                type = "OTHER";
              }
            pendingRequestsDiv.innerHTML += `<style>
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
              text-align: center;
            }
            </style>
            <table>
            <tr>
              <th>ID #</th>
              <th>Amount $</th>
              <th>Submitted</th>
              <th>Description</th>
              <th>Author ID</th>
              <th>Type</th>
            </tr>
            <tr>
              <td>${data[i].id}</td>
              <td>$${data[i].amount}</td>
              <td>${dateSubmitted.toDateString()}</td>
              <td>${data[i].description}</td>
              <td>${data[i].author}</td>
              <td>${type}</td>
            </tr>
            <br>
          </table>`
            }
            pendingRequestsClass.append(pendingRequestsDiv);
            console.log(pendingRequestsClass);
        });
})