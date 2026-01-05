function handleLogin(event) {
    "use strict";
    event.preventDefault();

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (!username || !password) {
        alert("Username and Password are required!");
        return false;
    }

    // Example logic
    console.log("Username:", username);
    console.log("Password:", password);

    alert("Login button clicked!");

    // Later:
    // - Call servlet
    // - Call REST API
    // - Redirect page

    return true;
}
