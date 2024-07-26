document.getElementById("logoutButton").addEventListener("click", function () {
    fetch("http://localhost:8080/logout", {
        method: "POST",
        credentials: "include"
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "http://127.0.0.1:5500/inmemory/login.html?logout=true";
        } else {
            console.error("Logout failed.");
        }
    })
    .catch(error => console.error("Error:", error));
});