
function logout(){
    document.cookie = 'id=;';
    document.cookie = 'Authorization=;';
    console.log("check");
}


let logoutBtn = document.getElementById('logout').addEventListener('click', logout);