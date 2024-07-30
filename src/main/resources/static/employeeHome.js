let helloAccountDiv = document.createElement("div");
let apiHello = "http://localhost:8080/employee/user/";
let helloUserClass = document.getElementsByClassName("helloUser")[0];

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

function helloUser(){
    let userid = getCookie("id");
    let auth = getCookie("Authorization");
    let getUser = `${apiHello}${userid}`;
  
    fetch(getUser, {
      method: 'GET',
      headers: {
        id: userid,
        Authorization: auth
      }
    })
      .then((res) => res.json())
      .then((data) => {
        let fname = data.fname;
        helloAccountDiv.innerHTML += `<p style="font-size: 20px">Hello, ${fname}</p>`
        helloUserClass.append(helloAccountDiv);
  
      });
  }
  
  helloUser();